export const enum CharacterClassEnum {
    BARBARIAN = 'BARBARIAN',
    BARD = 'BARD',
    CLERIC = 'CLERIC',
    DRUID = 'DRUID',
    FIGHTER = 'FIGHTER',
    MONK = 'MONK',
    PALADIN = 'PALADIN',
    RANGER = 'RANGER',
    ROGUE = 'ROGUE',
    SORCERER = 'SORCERER',
    WARLOCK = 'WARLOCK',
    WIZARD = 'WIZARD'
}

export const enum BackgroundEnum {
    ACOLYTE = 'ACOLYTE',
    CHARLATAN = 'CHARLATAN',
    CRIMINAL = 'CRIMINAL',
    ENTERTAINER = 'ENTERTAINER',
    FOLKHERO = 'FOLKHERO',
    GUILDARTISAN = 'GUILDARTISAN',
    HERMIT = 'HERMIT',
    NOBLE = 'NOBLE',
    OUTLANDER = 'OUTLANDER',
    SAGE = 'SAGE',
    SAILOR = 'SAILOR',
    SOLDIER = 'SOLDIER',
    URCHIN = 'URCHIN'
}

export const enum RaceEnum {
    DWARF = 'DWARF',
    ELF = 'ELF',
    HALFLING = 'HALFLING',
    HUMAN = 'HUMAN',
    DRAGONBORN = 'DRAGONBORN',
    GNOME = 'GNOME',
    HALF_ELF = 'HALF_ELF',
    HALF_ORC = 'HALF_ORC',
    TIEFLING = 'TIEFLING'
}

export const enum AlignmentEnum {
    LAWFUL_GOOD = 'LAWFUL_GOOD',
    NUETRAL_GOOD = 'NUETRAL_GOOD',
    CHAOTIC_GOOD = 'CHAOTIC_GOOD',
    LAWFUL_NUETRAL = 'LAWFUL_NUETRAL',
    TRUE_NUETRAL = 'TRUE_NUETRAL',
    CHAOTIC_NUETRAL = 'CHAOTIC_NUETRAL',
    LAWFUL_EVIL = 'LAWFUL_EVIL',
    NEUTRAL_EVIL = 'NEUTRAL_EVIL'
}

export const enum DiceEnum {
    D4 = 'D4',
    D6 = 'D6',
    D8 = 'D8',
    D12 = 'D12',
    D10 = 'D10',
    D20 = 'D20'
}

export const enum DeathSaveSuccessEnum {
    ZERO = 'ZERO',
    ONE = 'ONE',
    TWO = 'TWO',
    THREE = 'THREE'
}

export const enum DeathSaveFailEnum {
    ZERO = 'ZERO',
    ONE = 'ONE',
    TWO = 'TWO',
    THREE = 'THREE'
}

export interface ICharacterSheet {
    id?: number;
    createdDate?: string;
    createdBy?: string;
    searchTags?: string;
    characterName?: string;
    characterClass?: CharacterClassEnum;
    background?: BackgroundEnum;
    playerName?: string;
    race?: RaceEnum;
    alignment?: AlignmentEnum;
    expPoints?: number;
    strength?: number;
    dexterity?: number;
    constitution?: number;
    intelligence?: number;
    wisdom?: number;
    charisma?: number;
    inspiration?: number;
    proficiencyBonus?: number;
    strSavingThrow?: number;
    dexSavingThrow?: number;
    constSavingThrow?: number;
    intSavingThrow?: number;
    wisSavingThrow?: number;
    charSavingThrow?: number;
    acrobatics?: number;
    animalHandling?: number;
    arcana?: number;
    athletics?: number;
    deception?: number;
    history?: number;
    insight?: number;
    intimidation?: number;
    investigation?: number;
    medicine?: number;
    nature?: number;
    perception?: number;
    performance?: number;
    persuasion?: number;
    religion?: number;
    sleightOfHand?: number;
    stealth?: number;
    survival?: number;
    passiveWisdomPerception?: number;
    armorClass?: number;
    initiative?: number;
    speed?: number;
    hpMaximum?: number;
    currentHP?: number;
    temporaryHP?: number;
    hitDice?: DiceEnum;
    deathSaveSuccess?: DeathSaveSuccessEnum;
    deathSaveFail?: DeathSaveFailEnum;
    otherProficienciesAndLanguages?: string;
    copperPieces?: number;
    silverPieces?: number;
    electrumPieces?: number;
    goldPieces?: number;
    platinumPieces?: number;
    equipment?: string;
    featuresAndTraits?: string;
    attacksAndSpellCasting?: string;
    alliesAndOrganizations?: string;
    additionalFeaturesAndTraits?: string;
    treasure?: string;
    characterBackstory?: string;
    age?: number;
    height?: string;
    weight?: string;
    eyes?: string;
    skin?: string;
    hair?: string;
    spellCastingClass?: string;
    spellCastingAbility?: number;
    spellSaveDC?: number;
    spellAttackBonus?: number;
}

export class CharacterSheet implements ICharacterSheet {
    constructor(
        public id?: number,
        public createdDate?: string,
        public createdBy?: string,
        public searchTags?: string,
        public characterName?: string,
        public characterClass?: CharacterClassEnum,
        public background?: BackgroundEnum,
        public playerName?: string,
        public race?: RaceEnum,
        public alignment?: AlignmentEnum,
        public expPoints?: number,
        public strength?: number,
        public dexterity?: number,
        public constitution?: number,
        public intelligence?: number,
        public wisdom?: number,
        public charisma?: number,
        public inspiration?: number,
        public proficiencyBonus?: number,
        public strSavingThrow?: number,
        public dexSavingThrow?: number,
        public constSavingThrow?: number,
        public intSavingThrow?: number,
        public wisSavingThrow?: number,
        public charSavingThrow?: number,
        public acrobatics?: number,
        public animalHandling?: number,
        public arcana?: number,
        public athletics?: number,
        public deception?: number,
        public history?: number,
        public insight?: number,
        public intimidation?: number,
        public investigation?: number,
        public medicine?: number,
        public nature?: number,
        public perception?: number,
        public performance?: number,
        public persuasion?: number,
        public religion?: number,
        public sleightOfHand?: number,
        public stealth?: number,
        public survival?: number,
        public passiveWisdomPerception?: number,
        public armorClass?: number,
        public initiative?: number,
        public speed?: number,
        public hpMaximum?: number,
        public currentHP?: number,
        public temporaryHP?: number,
        public hitDice?: DiceEnum,
        public deathSaveSuccess?: DeathSaveSuccessEnum,
        public deathSaveFail?: DeathSaveFailEnum,
        public otherProficienciesAndLanguages?: string,
        public copperPieces?: number,
        public silverPieces?: number,
        public electrumPieces?: number,
        public goldPieces?: number,
        public platinumPieces?: number,
        public equipment?: string,
        public featuresAndTraits?: string,
        public attacksAndSpellCasting?: string,
        public alliesAndOrganizations?: string,
        public additionalFeaturesAndTraits?: string,
        public treasure?: string,
        public characterBackstory?: string,
        public age?: number,
        public height?: string,
        public weight?: string,
        public eyes?: string,
        public skin?: string,
        public hair?: string,
        public spellCastingClass?: string,
        public spellCastingAbility?: number,
        public spellSaveDC?: number,
        public spellAttackBonus?: number
    ) {}
}
