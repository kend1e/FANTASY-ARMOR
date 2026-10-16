package fantasy.definitions

import fantasy.build.translations

val languages = translations {
    language("en_us") {
        armorNames(
            helmet = "{name} Helmet",
            chestplate = "{name} Chestplate",
            leggings = "{name} Leggings",
            boots = "{name} Boots",
        )
        entry("itemGroup.fantasy_armor.fa_tab", "Fantasy Armor")
        entry("item.fantasy_armor.moon_crystal", "Moon Crystal")

        armor("eclipse_soldier", name = "Eclipse Soldier") {
            description = "§7The armor of an infantryman who fought for the triumph of the eclipse over the sun long ago. \nIt is said that beneath the boots of the legions thousands of unfortunates have found death."
        }

        armor("golden_horns", name = "Golden Horns") {
            description = "§7A set that shines so brightly it appears to be carved from the light itself. \nHowever, its gold is not a blessing but a curse. \nThis gold has witnessed death and destruction, and its light feeds greed and bloodlust. \nThis armor serves its owner only as long as he is willing to sacrifice his enemies."
        }

        armor("dragonslayer", name = "Dragonslayer") {
            description = "§7This set is forged from the scales of defeated dragons, each one a symbol of ancient strength and fury.\nThe plates and spikes grant you the power of victory over them, but do not forget that every step taken in it bears the weight of many deaths."
        }

        armor("hero", name = "Hero") {
            description = "§7This set is the legacy of ancient warriors, each of whom fought to their last breath.\nIt holds the memory of countless battles and hardships endured in the search for justice.\nBut glory is a heavy burden.\nNot every story will endure through the ages."
        }

        armor("thief", name = "Thief") {
            description = "§7This set is the perfect companion for those who live in shadows and breathe darkness.\nThe wearer becomes one with the night.\nThe black mask conceals your face, leaving your enemies guessing."
        }

        armor("wandering_wizard", name = "Wandering Wizard") {
            description = "§7This set is infused with ancient magic.\nThe garments whisper forgotten spells and open gates to other worlds.\nEach thread of this attire binds its wearer to unknown forces, granting him the power to perform magic, but at the same time imposing the curse of eternal wandering without the right to rest."
        }

        armor("sunset_wings", name = "Sunset Wings") {
            description = "§7Sunset is both the end and the beginning at the same time.\nThe wings guide their owner through the darkness.\nWhen you don them, you become the somber messenger of eternal night."
        }

        armor("chess_board_knight", name = "Chess Board Knight") {
            description = "§7Every move is a battle, every step is a strategy.\nThis armor embodies the cold logic and ruthless power of warfare.\nWear it with the pride of a strategist, prepared to sacrifice everything for victory, even your soul."
        }

        armor("dark_lord", name = "Dark Lord") {
            description = "§7Created in the depths of another world, this set is the embodiment of the power of terror.\nIts spikes and black armor resemble broken fangs.\nWear it if you dare, but know that it demands eternal devotion to the darkness and devours those who attempt to resist it."
        }

        armor("fog_guard", name = "Fog Guard") {
            description = "§7Shrouded in ancient fog, this armor offers unmatched protection, but weakens its wearer with visions of lost realms."
        }

        armor("dark_cover", name = "Dark Cover") {
            description = "§7Enveloping its wearer in shadows, this armor grants protection from light and silence, but whispers forgotten secrets of dark forces.\nWear it, but heed the shadows' warnings."
        }

        armor("spark_of_dawn", name = "Spark of Dawn") {
            description = "§7Forged in the first light, this armor carries warmth and brightness, yet burns those not pure of heart.\nOnly the righteous may unlock its true power."
        }

        armor("golden_execution", name = "Golden Execution") {
            description = "§7Bearing the weight of the empire's wrath, this armor shines golden but is soaked in the blood of the fallen.\nWear it with pride, but never forget its cost."
        }

        armor("forgotten_trace", name = "Forgotten Trace") {
            description = "§7The memory of ancient wars lingers in this armor, haunting the wearer with echoes of battles long lost.\nOnce donned, it marks the wearer as a soul from another age."
        }

        armor("redeemer", name = "Redeemer") {
            description = "§7An armor set created by the old order. \nYour god was slain long ago, but your faith has endured. \nKnowing very well you will likely not return from your quest, you step forward bravely into the unknown."
        }

        armor("twinned", name = "Twinned") {
            description = "§7Bound to another in spirit, this armor shares both pain and triumph with its twin.\nA shared destiny awaits those who wear the full set."
        }

        armor("gilded_hunt", name = "Gilded Hunt") {
            description = "§7Worn by those who chase honor and glory, this armor reflects the wild ambition of its bearer.\nHunt with it, but beware - glory often has a steep price."
        }

        armor("lady_maria", name = "Lady Maria's") {
            description = "§7A corpse... should be left well alone. \nOh, I know very well. How the secrets beckon so sweetly. \nOnly an honest death will cure you now. \nLiberate you, from your wild curiosity."
        }

        armor("crucible_knight", name = "Crucible Knight") {
            description = "§7Hold the power of the crucible of life. \nIn time, the strength shown by these knights, and even their appearance, was seen as chaotic and deserving of scorn."
        }

        armor("evening_ghost", name = "Evening Ghost") {
            description = "§7Hidden in the night, the shadow knows its place. \nIts steps are silent, but the chill of its presence is felt on the skin."
        }

        armor("ronin", name = "Ronin") {
            description = "§7Steel bears the scars of the past, its patterns soaked with long-cooled blood."
        }

        armor("malenia", name = "Malenia") {
            description = "§7...Heed my words. I am Malenia. Blade of Miquella. And I have never known defeat."
        }

        armor("old_knight", name = "Old Knight") {
            description = "§7A set of armor once worn by a knight who survived countless battles. \nIts weight is unbearable for the weak, but those who endure shall never fall."
        }

        armor("silver_knight", name = "Silver Knight") {
            description = "§7The armor of an elite warrior who never faltered in battle. \nIts silver gleam reflects the moonlight, bringing swift death to those who dare oppose its master."
        }

        armor("dead_gladiator", name = "Dead Gladiator") {
            description = "§7Armor of a slave fallen in the arena. \nHis name is forgotten, but fury still burns beneath the steel."
        }

        armor("flesh_of_the_feaster", name = "Flesh of the Feaster") {
            description = "§7This armor reeks of blood and greed. \nIt once belonged to one who fed without end — and became what he consumed."
        }

        armor("wind_worshipper", name = "Wind Worshipper") {
            description = "§7The fabric is light as breath. \nWorn by those who know no rest, drifting wherever the wind may carry them."
        }

        armor("grave_sentinel", name = "Grave Sentinel") {
            description = "§7The armor that guards the sleep of the dead. \nOnce sworn to noble oaths, over time their duty twisted into a silent vigil among dust and silence."
        }

        armor("ornstein", name = "Ornstein") {
            description = "§7Golden plates shaped in the image of a lion.\nForged for the First Lord’s fiercest dragonslayer, the armor still hums with remnant lightning.\nThose who don the full set feel the echo of a spear strike, capable of piercing ancient stone scales."
        }
    }

    language("ja_jp") {
        armorNames(
            helmet = "{name}のヘルメット",
            chestplate = "{name}のチェストプレート",
            leggings = "{name}のレギンス",
            boots = "{name}のブーツ",
        )
        entry("itemGroup.fantasy_armor.fa_tab", "Fantasy Armor")
        entry("item.fantasy_armor.moon_crystal", "月の結晶")

        armor("eclipse_soldier", name = "覆面兵士") {
            description = "§7太陽の勝利を掲げて戦った、かつての歩兵の鎧\n数千の不運な者たちが、軍団の足元で死を迎えたと言われている"
        }

        armor("golden_horns", name = "黄金角") {
            description = "§7まるで光そのものから削り出されたかのように、まばゆく輝く鎧\nしかしその黄金は祝福ではなく呪いである\nこの黄金は死と破壊の立ち会い人であり、その光は欲望と殺意を育む\nこの鎧が持ち主に仕えるのは、敵を生贄に捧げる意志を持つ間だけだ"
        }

        armor("dragonslayer", name = "竜殺し") {
            description = "§7打ち倒された竜の鱗から鍛え上げられたこの鎧は、その鱗の一枚一枚が古の力と猛りの象徴である\n重厚な板金と棘は竜を屠った力を授けるが、忘れるなかれ。その一歩ずつに、幾多の死が刻まれていることを"
        }

        armor("hero", name = "英雄") {
            description = "§7歴戦の勇士たちの遺産であり、その一人一人が息絶えるまで闘い抜いた証\n数え切れぬ戦いや、正義を求めて耐え抜いた苦難の記憶を宿している\nしかし、栄光とは重き荷である\nすべての物語が、時を越えて語り継がれるわけではないのだから"
        }

        armor("thief", name = "盗賊") {
            description = "§7闇に生き、影を友とする者に最適な装衣\n身につければ夜そのものと一体化する\n黒い仮面が顔を隠し、敵に正体を悟らせない"
        }

        armor("wandering_wizard", name = "放浪魔術師") {
            description = "§7この装備には古代の魔法が宿っている\nその衣は忘れられた呪文をささやき、異世界への扉を開く\nその衣の一本一本の糸が、着用者を未知の力で縛り付け、魔法を操る力を授ける。しかし同時に安らぎを奪い、永遠の放浪を強いる呪いが宿る"
        }

        armor("sunset_wings", name = "逢魔が翼") {
            description = "§7黄昏とは終わりであり、同時に始まりでもある\nその翼は、身につける者を暗闇へと導く\nこれを身につける時、あなたは常夜の薄暗き眷属となる"
        }

        armor("chess_board_knight", name = "チェス盤の騎士") {
            description = "§7すべての一手が戦いであり、すべての歩みが戦略である\nこの鎧は戦争の冷徹な論理と容赦なき力を体現する\n戦略家の誇りを胸に宿し、勝利のためには魂すら捧げる覚悟で挑むのだ"
        }

        armor("dark_lord", name = "暗黒卿") {
            description = "§7恐怖の力の具現であるこの装備は、異世界の深淵で創られた\n棘とその黒き鎧は、折れた牙を思わせる\n身に着ける者には、闇への永遠の献身が求められ、抗う者はその力に喰い尽くされる"
        }

        armor("fog_guard", name = "霧の守り人") {
            description = "§7古の霧に包まれた防具。一切に勝る防御を誇るが、失われた領域の幻影が身に着ける者の力を蝕む"
        }

        armor("dark_cover", name = "影纏い") {
            description = "§7影で身を包み、光と静寂からの保護を授けるが、暗黒の力の忘れ去られた秘密を囁き続ける\nこれを身につけるならば、影からの警告に耳を傾けよ"
        }

        armor("spark_of_dawn", name = "暁の閃光") {
            description = "§7始めの光の中で鍛えられたこの鎧は、温もりと輝きを宿し、不浄なる者を焼き払う\n正しき者のみが、その真の力を解き放つことができる"
        }

        armor("golden_execution", name = "黄金の断罪") {
            description = "§7黄金に輝きながら倒れた者の血に染まる、帝国の怒りの重みを背負う防具\n誇りを胸に身につけよ、その代償を忘れぬように"
        }

        armor("forgotten_trace", name = "忘却の痕跡") {
            description = "§7古の戦の記憶がこの防具に残り、長く失われたその残響が身に着ける者を苛む\n装備した者は、かつての時代の魂の片鱗を帯びる"
        }

        armor("redeemer", name = "救い手") {
            description = "§7古き秩序によって作られた鎧\n神は遠い昔に倒されたが、信仰はなお残る\n帰らぬことを承知で、未知へと勇敢なる一歩を踏み出す"
        }

        armor("twinned") {
            helmet = "双児の兜"
            chestplate = "双児の鎧"
            leggings = "双児の脚当て"
            boots = "双児の足甲"
            description = "§7精神で結ばれたもう一人の者と痛みも勝利も共有する鎧\n一式を身に着ける者には、共通の運命が待つ"
        }

        armor("gilded_hunt", name = "金色狩り") {
            description = "§7名誉と栄光を追い求める者の野心を映す鏡のような鎧\n狩りに臨め、だが覚悟せよ—— 栄光には高い代償が伴うだろう"
        }

        armor("lady_maria") {
            helmet = "レディ・マリアの狩帽子"
            chestplate = "レディ・マリアの狩装束"
            leggings = "レディ・マリアの狩ズボン"
            boots = "レディ・マリアの狩ブーツ"
            description = "§7死体は…そっとしておくべきもの\nああ、よく分かっているよ。秘密は甘いものだ\n今や救いは、誠実な死のみ\n好奇心の奔流から解き放たれるのだ"
        }

        armor("crucible_knight") {
            helmet = "坩堝の騎士の斧兜"
            chestplate = "坩堝の騎士の斧鎧"
            leggings = "坩堝の騎士の脚当て"
            boots = "坩堝の騎士の足甲"
            description = "§7生命の坩堝の力を宿す鎧\nその姿、そして力は、後に秩序無きものとして蔑まれた"
        }

        armor("evening_ghost") {
            helmet = "宵闇の冥人の編笠"
            chestplate = "宵闇の冥人の装束"
            leggings = "宵闇の冥人の脚当て"
            boots = "宵闇の冥人の足袋"
            description = "§7夜に潜み、影はその居場所を知る\n足取りは気取られずとも、その気配の冷たさは肌に感じられる"
        }

        armor("ronin") {
            helmet = "浪人の兜"
            chestplate = "浪人の胴当て"
            leggings = "浪人の脛当て"
            boots = "浪人の足袋"
            description = "§7鋼に刻まれた意匠ともとれるような過去の傷跡には、冷めた血が染み込んでいる"
        }

        armor("malenia") {
            helmet = "マレニアの翼兜"
            chestplate = "マレニアの鎧"
            leggings = "マレニアの脚当て"
            boots = "マレニアの足甲"
            description = "§7…貴公も、知るがよい。ミケラの刃、マレニアを。敗れを知らぬ戦いを"
        }

        armor("old_knight", name = "老騎士") {
            description = "§7数え切れぬ戦を生き抜いた騎士がかつて身に纏った鎧\n弱者には耐えられない重さだが、扱える者は決して倒れないだろう"
        }

        armor("silver_knight", name = "銀騎士") {
            description = "§7決してひるむことなく戦った精鋭たちの鎧\nその白銀たる輝きは月光を反射し、対峙する者に容赦なき死をもたらす"
        }

        armor("dead_gladiator", name = "死せる剣闘士") {
            description = "§7闘技場で倒れた奴隷の鎧\n名も忘れられたかつての主が身につけたその鋼の下には、激しい怒りが今もなお燃え続けている"
        }

        armor("flesh_of_the_feaster", name = "肉の饗宴者") {
            description = "§7この鎧は血と欲望の匂いを漂わせる\nそれはかつて、果てなき食欲に身を委ねた者のもの――そして、彼は自らが貪ったものと化した"
        }

        armor("wind_worshipper", name = "風の崇拝者") {
            description = "§7布地は息のように軽い\n休みを知らぬ者が身にまとい、風の赴くまま漂う"
        }

        armor("grave_sentinel", name = "墓所の番人") {
            description = "§7死者の眠りを守る鎧\nかつては高貴な誓いを立てたが、時とともにその務めは、塵と静寂の中で沈黙の番人へと変わった"
        }
    }

    language("ru_ru") {
        armorNames(
            helmet = "Шлем {name}",
            chestplate = "Нагрудник {name}",
            leggings = "Поножи {name}",
            boots = "Ботинки {name}",
        )
        entry("itemGroup.fantasy_armor.fa_tab", "Fantasy Armor")
        entry("item.fantasy_armor.moon_crystal", "Лунный кристалл")

        armor("eclipse_soldier", name = "Солдата Затмения") {
            description = "§7Доспехи пехотинца, когда-то давно сражавшегося за торжество затмения над солнцем. \nКто-то говорит, что под сапогами легионов нашли смерть тысячи несчастных."
        }

        armor("golden_horns", name = "Золотого Рога") {
            description = "§7Комплект, сияющий так ярко, что кажется, будто он вырезан из самого света. \nНо золото его — не благословение, а проклятие. \nЭто золото увидело смерть и разрушение, и его свет питает алчность и жажду крови. \nЭтот доспех служит своему хозяину до тех пор, пока тот готов приносить в жертву его врагов."
        }

        armor("dragonslayer", name = "Драконоборца") {
            description = "§7Этот комплект выкован из чешуи поверженных драконов, каждый из которых был символом древней силы и ярости. \nПластины и шипы дают мощь победы над ними, но не забудь, что каждый шаг в нем несет отпечаток многих смертей."
        }

        armor("hero", name = "Героя") {
            description = "§7Этот комплект — наследие древних воинов, каждый из которых сражался до последнего вздоха. \nОн хранит в себе память о бесчисленных битвах и тяготах, перенесенных в поисках справедливости. \nНо слава — это тяжкое бремя. \nНе каждая история сохранится в веках."
        }

        armor("thief", name = "Вора") {
            description = "§7Этот комплект — идеальный спутник для тех, кто живет в тенях и дышит темнотой. \nОблаченный в него становится частью ночи. \nЧёрная маска скрывает лицо, оставляя врагам лишь догадки."
        }

        armor("wandering_wizard", name = "Странствующего Чародея") {
            description = "§7Этот комплект пронизан древней магией. \nОдеяния шепчут забытые заклинания и открывают врата в иные миры. \nКаждая нить этой одежды связывает своего носителя с неведомыми силами, давая ему власть творить волшбу, но в то же время накладывая проклятие вечного странствия без права на покой."
        }

        armor("sunset_wings", name = "Крыльев Заката") {
            description = "§7Закат — это конец и начало одновременно. \nКрылья ведут своего хозяина сквозь мглу. \nОблачившись в него, вы становитесь мрачным вестником вечной ночи."
        }

        armor("chess_board_knight", name = "Рыцаря Шахматной Доски") {
            description = "§7Каждый ход — битва, каждый шаг — стратегия. \nЭта броня несет в себе холодную логику и безжалостную силу войн. \nНосите его с гордостью стратега, готового пожертвовать всем ради победы, даже своей душой."
        }

        armor("dark_lord", name = "Тёмного Лорда") {
            description = "§7Созданный в глубинах иного мира, этот комплект является воплощением власти ужаса. \nЕго шипы и черные доспехи подобны сломанным клыкам. \nНосите его, если осмелитесь, но знайте — он требует вечной преданности мраку и пожирает тех, кто пытается ему противостоять."
        }

        armor("fog_guard", name = "Стража Тумана") {
            description = "§7Окутанный древним туманом, этот доспех обеспечивает непревзойденную защиту, но ослабляет своего носителя видениями забытых миров."
        }

        armor("dark_cover", name = "Тёмного Покрова") {
            description = "§7Окутывая носителя в тени, этот доспех дарует защиту от света и тишину, но нашёптывает забытые секреты тёмных сил.\nНоси его, но остерегайся предупреждений теней."
        }

        armor("spark_of_dawn", name = "Искры Рассвета") {
            description = "§7Созданный на заре, этот доспех несёт тепло и свет, но обжигает тех, кто не чист сердцем.\nТолько праведные смогут раскрыть его истинную силу."
        }

        armor("golden_execution", name = "Золотой Казни") {
            description = "§7Несущий тяжесть имперского гнева, этот доспех сияет золотом, но пропитан кровью павших.\nНосите его с гордостью, но никогда не забывайте его цену."
        }

        armor("forgotten_trace", name = "Забытых Следов") {
            description = "§7Память о древних войнах остаётся в этом доспехе, преследуя носителя эхом давно проигранных битв.\nОднажды надевший его отмечен душами из другой эпохи."
        }

        armor("redeemer", name = "Искупителя") {
            description = "§7Доспех, созданный при древнем порядке.\nВаш бог был убит давно, но вера осталась.\nХоть и зная, что, шансы вернуться невелики, вы смело ступаете в неизвестность."
        }

        armor("twinned", name = "Близнецов") {
            description = "§7Связанная с другой в духе, это броня делит и боль, и триумф со своим близнецом.\nОбщая судьба ожидает тех, кто носит полный комплект."
        }

        armor("gilded_hunt", name = "Золотой Охоты") {
            description = "§7Надетая теми, кто жаждет чести и славы, это броня отражает дикое стремление своего владельца.\nОхотьтесь с ней, но будьте осторожны - слава часто имеет высокую цену."
        }

        armor("lady_maria", name = "Леди Марии") {
            description = "§7Не стоит прикасаться к телам. \nО, я-то знаю, как сладостно манят тайны. \nТеперь только честная смерть принесет тебе исцеление. \nОсвободит тебя от твоего неимоверного любопытства."
        }

        armor("crucible_knight", name = "Рыцаря Горнила") {
            description = "§7Овладейте силой горнила жизни. \nСо временем сила, демонстрируемая этими рыцарями, и даже их внешний вид стали восприниматься как хаотичные и заслуживающие презрения."
        }

        armor("evening_ghost", name = "Вечернего Призрака") {
            description = "§7Скрытая в ночи, тень знает, где её место. \nЕё шаги неслышны, но холод присутствия ощущается кожей."
        }

        armor("ronin", name = "Ронина") {
            description = "§7Сталь носит шрамы прошлого, кровь давно остывшая впиталась в её узоры."
        }

        armor("malenia", name = "Малении") {
            description = "§7...Внемлите моим словам. Я — Маления, клинок Микеллы. И я никогда не знала поражения."
        }

        armor("old_knight", name = "Старого Рыцаря") {
            description = "§7Когда-то этот доспех носил рыцарь, прошедший сотни битв. \nЕго вес невыносим для слабых, но те, кто выдержат — никогда не падут."
        }

        armor("silver_knight", name = "Серебряного Рыцаря") {
            description = "§7Доспех элитного воина, не знавшего поражений. \nЕго серебряный блеск отражает лунный свет и несёт быструю смерть врагам."
        }

        armor("dead_gladiator", name = "Мёртвого гладиатора") {
            description = "§7Броня раба, павшего на арене. \nИмя забыто, но ярость по-прежнему пылает под этой сталью."
        }

        armor("flesh_of_the_feaster", name = "Плоти Пожирателя") {
            description = "§7Эта броня источает запах крови и жадности. \nКогда-то она принадлежала тому, кто ел без остановки — и стал тем, чем он питался."
        }

        armor("wind_worshipper", name = "Поклонника ветра") {
            description = "§7Ткань легка, как дыхание. \nЕё носят те, кто не знает покоя и следует за ветром, куда бы он ни унес."
        }

        armor("grave_sentinel", name = "Стража Кладбища") {
            description = "§7Облачитесь в броню, что сторожит покой мёртвых. \nКогда-то их клятвы были благородны, но с течением веков их долг исказился в бессловесную стражу среди праха и тишины."
        }

        armor("ornstein", name = "Орнштейна") {
            description = "§7Золотые пластины, созданные в образе льва.\nКованные для яростнейшего драконоборца Первого Владыки, доспехи всё ещё гудят остаточной молнией.\nТот, кто облачится в полный комплект, ощущает отзвук копья, способного пронзить древнюю каменную чешую."
        }
    }

    language("uk_ua") {
        armorNames(
            helmet = "Шолом {name}",
            chestplate = "Нагрудник {name}",
            leggings = "Наголінники {name}",
            boots = "Чоботи {name}",
        )
        entry("itemGroup.fantasy_armor.fa_tab", "Fantasy Armor")
        entry("item.fantasy_armor.moon_crystal", "Місячний кристал")

        armor("eclipse_soldier", name = "Солдата Затемнення") {
            description = "§7Броня піхотинця, який колись боровся за тріумф затемнення над сонцем. \nКажуть, що під черевиками легіонів знайшли смерть тисячі нещасних."
        }

        armor("golden_horns", name = "Золотого Рогу") {
            description = "§7Комплект, що сяє так яскраво, що здається, ніби вирізаний із самого світла. \nАле золото його — не благословення, а прокляття. \nЦе золото бачило смерть і руйнування, і його світло живить жадобу та жагу крові. \nЦей доспіх служить своєму власнику до тих пір, поки той готовий приносити в жертву своїх ворогів."
        }

        armor("dragonslayer", name = "Драконоборця") {
            description = "§7Цей комплект кований з луски переможених драконів, кожен з яких був символом древньої сили та люті. \nПластини і шипи надають могутність перемоги над ними, але не забувайте, що кожен крок в ньому несе відбиток багатьох смертей."
        }

        armor("hero", name = "Героя") {
            description = "§7Цей комплект — спадщина древніх воїнів, кожен з яких боровся до останнього подиху. \nВін зберігає в собі пам’ять про безліч битв і тягот, перенесених у пошуках справедливості. \nАле слава — це важке тягар. \nНе кожна історія залишиться в віках."
        }

        armor("thief", name = "Крадія") {
            description = "§7Цей комплект — ідеальний супутник для тих, хто живе в тінях і дихає темрявою. \nОдягнувшись у нього, стаєш частиною ночі. \nЧорна маска приховує обличчя, залишаючи ворогам лише здогадки."
        }

        armor("wandering_wizard", name = "Мандрівного Чаклуна") {
            description = "§7Цей комплект пронизаний стародавньою магією. \nОдягання шепоче забуті закляття і відкриває ворота в інші світи. \nКожна нитка цього одягу пов'язує свого носія з невідомими силами, надаючи йому владу творити чари, але в той же час накладаючи прокляття вічного мандрування без права на спокій."
        }

        armor("sunset_wings", name = "Крил Сутінків") {
            description = "§7Захід сонця — це кінець і початок одночасно. \nКрила ведуть свого господаря крізь мглу. \nОдягнувшись у нього, ви стаєте похмурим вісником вічної ночі."
        }

        armor("chess_board_knight", name = "Шахової Дошки") {
            description = "§7Кожен хід — це битва, кожен крок — стратегія. \nЦя броня несе в собі холодну логіку та безжальну силу війн. \nНосіть його з гордістю стратега, готового пожертвувати всім заради перемоги, навіть своєю душею."
        }

        armor("dark_lord", name = "Темного Лорда") {
            description = "§7Створений в глибинах іншого світу, цей комплект є втіленням влади жаху. \nЙого шипи і чорні лати подібні до поламаних іклів. \nНосіть його, якщо наважитеся, але знайте — він вимагає вічної відданості темряві та поглинає тих, хто намагається йому протистояти."
        }

        armor("fog_guard", name = "Старжника Туману") {
            description = "§7Охоплений давнім туманом, цей обладунок забезпечує неперевершений захист, але робить свого носія вразливи через видіння забутих світів."
        }

        armor("dark_cover", name = "Темного Покрову") {
            description = "§7Загортаючи носія у тіні, цей обладунок надає захист від світла й тишу, але нашіптує забуті секрети темних сил.\nНоси його, але стережися попереджень тіней."
        }

        armor("spark_of_dawn", name = "Іскри Світанку") {
            description = "§7Створений на зорі, цей обладунок несе тепло та світло, але обпалює тих, хто не чистий серцем.\nЛише праведні зможуть розкрити його справжню силу."
        }

        armor("golden_execution", name = "Золотої Страти") {
            description = "§7Несе вагу імперського гніву, цей обладунок сяє золотом, але просякнутий кров'ю полеглих.\nНосіть його з гордістю, але ніколи не забувайте його ціну."
        }

        armor("forgotten_trace", name = "Забутих Слідів") {
            description = "§7Пам'ять про давні війни залишається в цьому обладунку, переслідуючи носія ехом давно програних битв.\nТой, хто носив його, позначений душами з іншої епохи."
        }

        armor("redeemer", name = "Викупителя") {
            description = "§7Обладунок, створений давнім орденом.\nВаш бог був убитий давно, але віра залишилася.\nНавіть знаючи, що загине, той, хто носить цей обладунок, знайде себе."
        }

        armor("twinned", name = "Близнюків") {
            description = "§7Пов'язана з іншою духом, ця броня розділяє і біль, і тріумф зі своїм близнюком.\nСпільна доля чекає тих, хто носить повний комплект."
        }

        armor("gilded_hunt", name = "Золотого Полювання") {
            description = "§7Одягнена тими, хто прагне честі та слави, ця броня відображає дике прагнення свого власника.\nПолюйте з нею, але будьте обережні - слава часто має високу ціну."
        }

        armor("lady_maria", name = "Леді Марії") {
            description = "§7Не варто торкатися тіл. \nО, я-то знаю, як солодко ваблять таємниці. \nТепер лише чесна смерть принесе тобі зцілення. \nЗвільнить тебе від твоєї неймовірної допитливості."
        }

        armor("crucible_knight", name = "Лицаря Горнила") {
            description = "§7Опануйте силу горнила життя. \nЗ часом сила, яку демонстрували ці лицарі, та навіть їхній вигляд почав вважатися хаотичними та негідними."
        }

        armor("evening_ghost", name = "Примари Вечора") {
            description = "§7Схована в ночі, тінь знає своє місце. \nЇї кроки нечутні, але холод її присутності відчувається на шкірі."
        }

        armor("ronin", name = "Роніна") {
            description = "§7Сталь несе шрами минулого, її візерунки просякнуті давно охололою кров’ю."
        }

        armor("malenia", name = "Маленії") {
            description = "§7...Прислухайтесь до моїх слів. Я — Маленія, клинок Мікелли. І я ніколи не знала поразки."
        }

        armor("old_knight", name = "Старого Лицаря") {
            description = "§7Колись цей обладунок належав лицарю, що пережив сотні битв. \nЙого вага нестерпна для слабких, але ті, хто витримає — ніколи не впадуть."
        }

        armor("silver_knight", name = "Срібного Лицаря") {
            description = "§7Обладунок елітного воїна, що не знав поразок. \nЙого срібний блиск відображає місячне сяйво, приносячи швидку смерть ворогам."
        }

        armor("dead_gladiator", name = "Мертвого гладіатора") {
            description = "§7Броня воїна, що помер на арені. \nІм’я зникло, та лють усе ще палає під цією сталлю."
        }

        armor("flesh_of_the_feaster", name = "Пожирача") {
            description = "§7Ця броня просякнута запахом крові та жадоби. \nКолись її носив той, хто жер без зупинки, й сам став тим, що їв."
        }

        armor("wind_worshipper", name = "Шанувальника вітру") {
            description = "§7Тканина легка, мов подих. \nЇї носять ті, хто не знає спокою й мандрує за вітром, куди б він не ніс."
        }

        armor("grave_sentinel", name = "Могильного Вартового") {
            description = "§7Вдягніть броню, що стереже спокій мертвих. \nКолись їхні обітниці були шляхетними, та з роками їхній обов’язок спотворився у мовчазну варту серед попелу й тиші."
        }

        armor("ornstein", name = "Орнштейну") {
            description = "§7Золоті пластини, створені в образі лева.\nКовані для найлютішого драконоборця Першого Владики, обладунки все ще гудуть залишковою блискавкою.\nТой, хто одягнеться в повний комплект, відчуває відгук списа, здатного пронизати стародавню кам'яну луску."
        }
    }

    language("zh_cn") {
        armorNames(
            helmet = "{name}头盔",
            chestplate = "{name}胸甲",
            leggings = "{name}护腿",
            boots = "{name}靴子",
        )
        entry("itemGroup.fantasy_armor.fa_tab", "奇幻铠甲")
        entry("item.fantasy_armor.moon_crystal", "皎月水晶")

        armor("eclipse_soldier", name = "日蚀战士") {
            description = "§7很久以前是为日蚀战胜太阳的步兵的铠甲。\n据说，在军团的铁蹄下，成千上万的不幸者败亡。"
        }

        armor("golden_horns", name = "金制角") {
            description = "§7这套装备闪烁着如此耀眼的光芒，仿佛是由光本身雕刻而成的。\n然而，上面的黄金不是祝福，而是诅咒。 \n这些黄金见证了死亡和毁灭，它的光芒助长了贪婪和嗜血。\n唯有在主人愿意献祭敌人的情况下，这件铠甲才会为其服务。"
        }

        armor("dragonslayer", name = "屠龙") {
            description = "§7用败亡之龙的鳞片所锻造而成的一套铠甲，每条巨龙都象征着远古的力量和愤怒。\n这些铁板与尖刺赋予你战胜它们的力量，但不要忘记，每一步都承载着许多死亡的重量。"
        }

        armor("hero", name = "英雄") {
            description = "§7这套装备是古代战士的遗产，他们每个人都浴血奋战至最后一刻。\n它保存着为寻求正义而经历的无数战斗与艰辛的记忆。\n但荣耀是一个沉重的负担。\n也并非每个故事，都会经久不衰。"
        }

        armor("thief", name = "盗贼") {
            helmet = "盗贼兜帽"
            description = "§7这套装备是那些生于阴影并在黑暗中呼吸之人的完美伴侣。\n穿戴者会与黑夜融为一体。\n黑色面具掩面，使敌人心怀揣测。"
        }

        armor("wandering_wizard", name = "流浪巫师") {
            helmet = "流浪巫师大帽"
            description = "§7这套装备注入了古老魔法。\n使得这些衣物低语着被遗忘的咒语，打开了通往其他世界的大门。\n这件服装的千丝万缕都将穿戴者与未知之力捆绑在一起，赋予其施放魔法的能力，但也同时降下永无止息地流浪的诅咒。"
        }

        armor("sunset_wings", name = "薄暮双翼") {
            description = "§7薄暮既是终焉，亦是开端。\n双翼引导它们的主人穿过黑暗。\n当你穿戴上它们时，就会化身永恒之夜的忧郁使者。"
        }

        armor("chess_board_knight", name = "棋盘骑士") {
            helmet = "棋盘骑士铠甲"
            description = "§7一举一动都是一场搏杀，一静一动都是一次战略。\n这件铠甲显现出战争的冷酷逻辑和无情力量。\n带着战略家的自豪穿戴它，准备好为胜利赴汤蹈火，甚至献上你的灵魂。"
        }

        armor("dark_lord", name = "黑暗之主") {
            helmet = "黑暗之主铠甲"
            description = "§7这套铠甲创造于其他世界的深渊，是可怖力量的化身。\n其上的尖刺和黑色甲胄犹如折断的獠牙。\n若有勇力，就穿上它，但记住，它需要你永世臣服于黑暗，并吞噬那些妄图抵抗它的人。"
            bootsDescription = "§7这套铠甲创造于其他世界深渊的，是可怖力量的化身。\n其上的尖刺和黑色甲胄犹如折断的獠牙。\n若有勇力，就穿上它，但记住，它需要你永世臣服于黑暗，并吞噬那些妄图抵抗它的人。"
        }

        armor("fog_guard", name = "雾卫") {
            description = "§7这件铠甲笼罩在古老的迷雾中，因而拥有无与伦比的保护性，但穿戴者会因恍惚于破败国度的幻象而遭到削弱"
        }

        armor("dark_cover", name = "暗覆") {
            description = "§7这件铠甲将佩戴者笼罩于阴影之中，使得穿戴者具有对光明和寂静的抗性，但其低语着已被遗忘的黑暗力量的秘密。\n穿上它，但要对阴影们有戒备之心。"
        }

        armor("spark_of_dawn", name = "明焰") {
            description = "§7这件铠甲于第一道光中锻造而成，承载着温暖和光明，但也会燃烧那些不纯之人。\n唯有义人才能使其显现出真正的力量"
        }

        armor("golden_execution", name = "黄金处刑") {
            description = "§7这件铠甲承受着帝国的愤怒，浸透了罪人鲜血的铠甲缝隙中，闪耀着金色的光芒\n自豪地穿戴吧，但永远不要忘记它由什么铸成。"
        }

        armor("forgotten_trace", name = "遗迹") {
            description = "§7古代战争上的记忆萦绕于这件铠甲，使得穿戴者身旁回荡着久违鏖战的回声。\n一旦穿戴，即意味着你，是来自于另一个时代的灵魂。"
            leggingsDescription = "§7古代战争上的记忆萦绕于这件铠甲，使得穿戴者身旁回荡着久违鏖战的回声。\n一旦穿戴即，意味着你，是来自于另一个时代的灵魂。"
        }

        armor("redeemer", name = "救世主") {
            description = "§7一套由古旧秩序所创造的铠甲。 \n汝神虽逝，信念长存。\n纵有身死，亦勇往之。"
        }

        armor("twinned", name = "孪生") {
            description = "§7这件铠甲在精神领域上，与另一件铠甲相连，并与它的孪生兄弟共承甘苦。\n穿着全套铠甲的人将面临共同的命运。"
        }

        armor("gilded_hunt", name = "镀金狩猎") {
            description = "§7追逐荣誉荣耀之人所穿戴的铠甲，反映了其持有者的不羁野心。\n用它去狩猎吧，但要小心——荣耀之重有千斤。"
        }

        armor("lady_maria", name = "玛丽亚女士的") {
            helmet = "玛丽亚女士的帽子"
            description = "§7应该让尸体好好安息才对。\n噢，我清楚得很，那些秘密确实令人难以抗拒。 \n看来只有让你安息，才得以治愈你了。\n使你从那失控的好奇心中解脱。"
        }

        armor("crucible_knight", name = "熔炉骑士") {
            description = "§7拥有生命熔炉的力量。 \n随着岁月流逝，这些骑士所显露的力量乃至其形象，皆被视为混沌，理应受到轻蔑。"
        }

        armor("evening_ghost", name = "夜鬼") {
            description = "§7藏匿于夜幕之中，暗影知晓其归宿。\n它的步伐悄无声息，可皮肤上确切的能感受到上面阵阵寒意的存在。"
        }

        armor("ronin", name = "浪人") {
            description = "§7钢铁承载着过往的伤痕，其上的纹理浸透了早已冷却的血迹。"
        }

        armor("malenia", name = "玛莲妮亚的") {
            description = "§7...好好感受吧。我玛莲妮亚，身为米凯拉的锋刃———战无不胜的理由。"
        }

        armor("old_knight", name = "古老骑士") {
            description = "§7这套铠甲曾经被一位在无数战斗中幸存下来的骑士所穿戴。\n它的重量，弱者难以承受，但对于那些能够承受住它的人来说，他们将屹立不倒。"
        }

        armor("silver_knight", name = "银骑士") {
            description = "§7这是一套曾历经无数战斗洗礼的精锐战士所穿戴的铠甲。 \n其银色的光泽在月光下闪烁，仿佛是夜幕中的一道寒芒，对敢于反抗其主人的敌人而言，它即是迅速降下死亡的宣告。"
        }

        armor("dead_gladiator", name = "死亡斗士") {
            description = "§7这是一位在角斗场中战败奴隶的战甲。\n他的姓名早已被遗忘，可那怒火却依旧在寒铁之下熊熊燃烧。"
        }

        armor("flesh_of_the_feaster", name = "奢欲之躯") {
            helmet = "奢宴之躯头盔"
            description = "§7这副战甲浸透着鲜血与贪婪。\n它曾为一位永不餍足的吞噬者所有——而他最终化为了他所吞食之物的化身。"
        }

        armor("wind_worshipper", name = "风崇者") {
            description = "§7风崇者之衣的布料轻若呼吸。\n它属于那些永无停歇的流浪者,他们随风漂泊，任凭风将他们带到任何地方。"
        }

        armor("grave_sentinel", name = "墓穴哨兵") {
            description = "§7守护亡者长眠的铠甲。\n它们曾立下崇高誓言，却在时光中渐次扭曲，化作尘寂之中的缄默守望。"
            bootsDescription = "§7守护亡者长眠的铠甲。 \n它们曾立下崇高誓言，却在时光中渐次扭曲，化作尘寂之中的缄默守望。"
        }
    }

}
