package net.kenddie.fantasyarmor.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "fantasyarmor")
public class FAConfig implements ConfigData {

    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.Tooltip
    public boolean applyArmorEffects = true;

    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.Tooltip
    public boolean applyModifiers = true;

    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.Tooltip
    public boolean showDescriptions = true;

    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.Tooltip
    public boolean showCapes = true;

    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.Tooltip
    public boolean showParticles = false;

    @ConfigEntry.Category("general")
    @ConfigEntry.BoundedDiscrete(min = 1, max = Integer.MAX_VALUE)
    @ConfigEntry.Gui.Tooltip
    public int effectsInterval = 241;

    @ConfigEntry.Category("general")
    @ConfigEntry.BoundedDiscrete(min = 20, max = 1000)
    @ConfigEntry.Gui.Tooltip
    public int descriptionsLength = 250;

    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.Tooltip
    public boolean showEffectIcon = true;
}