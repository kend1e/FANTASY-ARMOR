#!/usr/bin/env bash
set -uo pipefail

usage() {
    cat <<'USAGE'
Usage: install-dev.sh [--dry-run] [--dist DIR] [--minecraft DIR] [--help]

Install Fantasy Armor builds into existing *-DevTest profiles.
Require GeckoLib for all loaders and Fabric API and Cloth Config for Fabric.
Replace only fantasy-armor-*.jar files.

Environment:
  MINECRAFT_DIR  Minecraft directory (default: ~/.minecraft)
USAGE
}

script_dir="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)" || exit 1
project_dir="$(cd -- "$script_dir/.." && pwd)" || exit 1
dist_dir="$project_dir/dist"
minecraft_dir="${MINECRAFT_DIR:-$HOME/.minecraft}"
dry_run=false

while (($#)); do
    case "$1" in
        --dry-run)
            dry_run=true
            shift
            ;;
        --dist|--minecraft)
            if (($# < 2)) || [[ -z "$2" ]]; then
                printf 'ERROR: %s requires a directory\n' "$1" >&2
                exit 2
            fi
            if [[ "$1" == --dist ]]; then
                dist_dir="$2"
            else
                minecraft_dir="$2"
            fi
            shift 2
            ;;
        -h|--help)
            usage
            exit 0
            ;;
        *)
            printf 'ERROR: unknown argument: %s\n' "$1" >&2
            usage >&2
            exit 2
            ;;
    esac
done

versions_dir="$minecraft_dir/versions"

if [[ ! -d "$dist_dir" || ! -d "$versions_dir" ]]; then
    printf 'ERROR: missing dist or versions directory:\n  dist: %s\n  versions: %s\n' \
        "$dist_dir" "$versions_dir" >&2
    exit 1
fi

shopt -s nullglob

installed=0
skipped=0
failed=0
found=0

has_mod() {
    local pattern="$1" jar

    for jar in "$mods_dir"/$pattern; do
        [[ -f "$jar" && ! -L "$jar" ]] && return 0
    done

    return 1
}

for profile in "$versions_dir"/*-DevTest; do
    [[ -d "$profile" && ! -L "$profile" ]] || continue

    profile_name="${profile##*/}"

    if [[ ! "$profile_name" =~ ^([0-9]+\.[0-9]+(\.[0-9]+)?)-(Forge|NeoForge|Fabric)-DevTest$ ]]; then
        continue
    fi

    ((found+=1))

    mc_version="${BASH_REMATCH[1]}"
    loader="${BASH_REMATCH[3],,}"
    mods_dir="$profile/mods"

    if [[ ! -d "$mods_dir" || -L "$mods_dir" ]]; then
        printf 'ERROR %s: mods/ is missing or is a symlink\n' "$profile_name" >&2
        ((failed+=1))
        continue
    fi

    builds=("$dist_dir"/fantasy-armor-*-"$mc_version"-"$loader".jar)

    if ((${#builds[@]} == 0)); then
        printf 'SKIP  %s: no matching build\n' "$profile_name"
        ((skipped+=1))
        continue
    fi

    if ((${#builds[@]} != 1)) || [[ ! -f "${builds[0]}" || -L "${builds[0]}" ]]; then
        printf 'ERROR %s: expected exactly one regular build (found %d)\n' \
            "$profile_name" "${#builds[@]}" >&2
        ((failed+=1))
        continue
    fi

    missing=()

    has_mod "geckolib-${loader}-*.jar" ||
        missing+=("GeckoLib ($loader)")

    if [[ "$loader" == fabric ]]; then
        has_mod "fabric-api-*${mc_version}.jar" ||
            missing+=("Fabric API ($mc_version)")

        has_mod "cloth-config-*-fabric.jar" ||
            missing+=("Cloth Config (fabric)")
    fi

    if ((${#missing[@]})); then
        printf 'ERROR %s: missing dependencies: ' "$profile_name" >&2
        printf '%s' "${missing[0]}" >&2

        for dependency in "${missing[@]:1}"; do
            printf ', %s' "$dependency" >&2
        done

        printf '\n' >&2
        ((failed+=1))
        continue
    fi

    source_jar="${builds[0]}"
    target="$mods_dir/${source_jar##*/}"
    old_jars=("$mods_dir"/fantasy-armor-*.jar)

    unsafe=false

    for old in "${old_jars[@]}"; do
        if [[ -L "$old" || ! -f "$old" ]]; then
            printf 'ERROR %s: unsafe destination: %s\n' "$profile_name" "$old" >&2
            unsafe=true
        fi
    done

    if [[ -L "$target" || ( -e "$target" && ! -f "$target" ) ]]; then
        printf 'ERROR %s: unsafe target: %s\n' "$profile_name" "$target" >&2
        unsafe=true
    fi

    if [[ "$unsafe" == true ]]; then
        ((failed+=1))
        continue
    fi

    stale_count=0

    for old in "${old_jars[@]}"; do
        [[ "$old" == "$target" ]] || ((stale_count+=1))
    done

    if [[ "$dry_run" == true ]]; then
        printf 'PLAN  %s: install %s; remove %d old JAR(s)\n' \
            "$profile_name" "${source_jar##*/}" "$stale_count"
        ((installed+=1))
        continue
    fi

    # Install the new build before removing older ones.
    if ! tmp="$(mktemp "$mods_dir/.fantasy-armor.XXXXXXXX")"; then
        printf 'ERROR %s: cannot create temporary file\n' "$profile_name" >&2
        ((failed+=1))
        continue
    fi

    if ! cp -- "$source_jar" "$tmp" || ! mv -f -- "$tmp" "$target"; then
        rm -f -- "$tmp"
        printf 'ERROR %s: installation failed\n' "$profile_name" >&2
        ((failed+=1))
        continue
    fi

    removal_failed=false

    for old in "${old_jars[@]}"; do
        [[ "$old" == "$target" ]] && continue

        if ! rm -- "$old"; then
            printf 'ERROR %s: cannot remove %s\n' "$profile_name" "$old" >&2
            removal_failed=true
        fi
    done

    if [[ "$removal_failed" == true ]]; then
        ((failed+=1))
    else
        printf 'OK    %s: %s\n' "$profile_name" "${source_jar##*/}"
        ((installed+=1))
    fi
done

printf '\n%s: %d processed, %d skipped, %d failed (profiles found: %d)\n' \
    "$([[ "$dry_run" == true ]] && printf 'Dry run' || printf 'Done')" \
    "$installed" "$skipped" "$failed" "$found"

((failed == 0))
