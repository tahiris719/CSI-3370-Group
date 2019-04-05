package com.mycompany.myapp.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.mycompany.myapp.domain.enumeration.CharacterClassEnum;

import com.mycompany.myapp.domain.enumeration.BackgroundEnum;

import com.mycompany.myapp.domain.enumeration.RaceEnum;

import com.mycompany.myapp.domain.enumeration.AlignmentEnum;

import com.mycompany.myapp.domain.enumeration.DiceEnum;

import com.mycompany.myapp.domain.enumeration.DeathSaveSuccessEnum;

import com.mycompany.myapp.domain.enumeration.DeathSaveFailEnum;

/**
 * A CharacterSheet.
 */
@Entity
@Table(name = "character_sheet")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CharacterSheet implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "character_name")
    private String characterName;

    @Enumerated(EnumType.STRING)
    @Column(name = "charachter_class")
    private CharacterClassEnum charachterClass;

    @Enumerated(EnumType.STRING)
    @Column(name = "background")
    private BackgroundEnum background;

    @Column(name = "player_name")
    private String playerName;

    @Enumerated(EnumType.STRING)
    @Column(name = "race")
    private RaceEnum race;

    @Enumerated(EnumType.STRING)
    @Column(name = "alignment")
    private AlignmentEnum alignment;

    @Column(name = "experience_points")
    private Integer experiencePoints;

    @Column(name = "strength")
    private Integer strength;

    @Column(name = "dexterity")
    private Integer dexterity;

    @Column(name = "constitution")
    private Integer constitution;

    @Column(name = "intelligence")
    private Integer intelligence;

    @Column(name = "wisdom")
    private Integer wisdom;

    @Column(name = "charisma")
    private Integer charisma;

    @Column(name = "inspiration")
    private Integer inspiration;

    @Column(name = "proficiency_bounus")
    private Integer proficiencyBounus;

    @Column(name = "strength_saving_throw")
    private Integer strengthSavingThrow;

    @Column(name = "dexterity_saving_throw")
    private Integer dexteritySavingThrow;

    @Column(name = "constitution_saving_throw")
    private Integer constitutionSavingThrow;

    @Column(name = "intelligence_saving_throw")
    private Integer intelligenceSavingThrow;

    @Column(name = "wisdom_saving_throw")
    private Integer wisdomSavingThrow;

    @Column(name = "charisma_saving_throw")
    private Integer charismaSavingThrow;

    @Column(name = "acrobatics")
    private Integer acrobatics;

    @Column(name = "animal_handeling")
    private Integer animalHandeling;

    @Column(name = "arcana")
    private Integer arcana;

    @Column(name = "athletics")
    private Integer athletics;

    @Column(name = "deception")
    private Integer deception;

    @Column(name = "history")
    private Integer history;

    @Column(name = "insight")
    private Integer insight;

    @Column(name = "intimidation")
    private Integer intimidation;

    @Column(name = "investigation")
    private Integer investigation;

    @Column(name = "medicine")
    private Integer medicine;

    @Column(name = "nature")
    private Integer nature;

    @Column(name = "perception")
    private Integer perception;

    @Column(name = "performance")
    private Integer performance;

    @Column(name = "persuasion")
    private Integer persuasion;

    @Column(name = "religion")
    private Integer religion;

    @Column(name = "sleight_of_hand")
    private Integer sleightOfHand;

    @Column(name = "stealth")
    private Integer stealth;

    @Column(name = "survival")
    private Integer survival;

    @Column(name = "passive_wisdom_perception")
    private Integer passiveWisdomPerception;

    @Column(name = "armor_class")
    private Integer armorClass;

    @Column(name = "initiative")
    private Integer initiative;

    @Column(name = "speed")
    private Integer speed;

    @Column(name = "hit_point_maximum")
    private Integer hitPointMaximum;

    @Column(name = "current_hit_points")
    private Integer currentHitPoints;

    @Column(name = "temporary_hit_points")
    private Integer temporaryHitPoints;

    @Enumerated(EnumType.STRING)
    @Column(name = "hit_dice")
    private DiceEnum hitDice;

    @Enumerated(EnumType.STRING)
    @Column(name = "death_save_success")
    private DeathSaveSuccessEnum deathSaveSuccess;

    @Enumerated(EnumType.STRING)
    @Column(name = "death_save_fail")
    private DeathSaveFailEnum deathSaveFail;

    @Column(name = "other_proficiencies_and_languages")
    private String otherProficienciesAndLanguages;

    @Column(name = "copper_pieces")
    private Integer copperPieces;

    @Column(name = "silver_pieces")
    private Integer silverPieces;

    @Column(name = "electrum_pieces")
    private Integer electrumPieces;

    @Column(name = "gold_pieces")
    private Integer goldPieces;

    @Column(name = "platinum_pieces")
    private Integer platinumPieces;

    @Column(name = "equipment")
    private String equipment;

    @Column(name = "features_and_traits")
    private String featuresAndTraits;

    @Column(name = "attacks_and_spell_casting")
    private String attacksAndSpellCasting;

    @Column(name = "allies_and_organizations")
    private String alliesAndOrganizations;

    @Column(name = "additional_features_and_traits")
    private String additionalFeaturesAndTraits;

    @Column(name = "treasure")
    private String treasure;

    @Column(name = "character_backstory")
    private String characterBackstory;

    @Column(name = "age")
    private Integer age;

    @Column(name = "height")
    private String height;

    @Column(name = "weight")
    private String weight;

    @Column(name = "eyes")
    private String eyes;

    @Column(name = "skin")
    private String skin;

    @Column(name = "hair")
    private String hair;

    @Column(name = "spell_casting_class")
    private String spellCastingClass;

    @Column(name = "spell_casting_ability")
    private Integer spellCastingAbility;

    @Column(name = "spell_save_dc")
    private Integer spellSaveDC;

    @Column(name = "spell_attack_bounus")
    private Integer spellAttackBounus;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public CharacterSheet characterName(String characterName) {
        this.characterName = characterName;
        return this;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public CharacterClassEnum getCharachterClass() {
        return charachterClass;
    }

    public CharacterSheet charachterClass(CharacterClassEnum charachterClass) {
        this.charachterClass = charachterClass;
        return this;
    }

    public void setCharachterClass(CharacterClassEnum charachterClass) {
        this.charachterClass = charachterClass;
    }

    public BackgroundEnum getBackground() {
        return background;
    }

    public CharacterSheet background(BackgroundEnum background) {
        this.background = background;
        return this;
    }

    public void setBackground(BackgroundEnum background) {
        this.background = background;
    }

    public String getPlayerName() {
        return playerName;
    }

    public CharacterSheet playerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public RaceEnum getRace() {
        return race;
    }

    public CharacterSheet race(RaceEnum race) {
        this.race = race;
        return this;
    }

    public void setRace(RaceEnum race) {
        this.race = race;
    }

    public AlignmentEnum getAlignment() {
        return alignment;
    }

    public CharacterSheet alignment(AlignmentEnum alignment) {
        this.alignment = alignment;
        return this;
    }

    public void setAlignment(AlignmentEnum alignment) {
        this.alignment = alignment;
    }

    public Integer getExperiencePoints() {
        return experiencePoints;
    }

    public CharacterSheet experiencePoints(Integer experiencePoints) {
        this.experiencePoints = experiencePoints;
        return this;
    }

    public void setExperiencePoints(Integer experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public Integer getStrength() {
        return strength;
    }

    public CharacterSheet strength(Integer strength) {
        this.strength = strength;
        return this;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public CharacterSheet dexterity(Integer dexterity) {
        this.dexterity = dexterity;
        return this;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getConstitution() {
        return constitution;
    }

    public CharacterSheet constitution(Integer constitution) {
        this.constitution = constitution;
        return this;
    }

    public void setConstitution(Integer constitution) {
        this.constitution = constitution;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public CharacterSheet intelligence(Integer intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getWisdom() {
        return wisdom;
    }

    public CharacterSheet wisdom(Integer wisdom) {
        this.wisdom = wisdom;
        return this;
    }

    public void setWisdom(Integer wisdom) {
        this.wisdom = wisdom;
    }

    public Integer getCharisma() {
        return charisma;
    }

    public CharacterSheet charisma(Integer charisma) {
        this.charisma = charisma;
        return this;
    }

    public void setCharisma(Integer charisma) {
        this.charisma = charisma;
    }

    public Integer getInspiration() {
        return inspiration;
    }

    public CharacterSheet inspiration(Integer inspiration) {
        this.inspiration = inspiration;
        return this;
    }

    public void setInspiration(Integer inspiration) {
        this.inspiration = inspiration;
    }

    public Integer getProficiencyBounus() {
        return proficiencyBounus;
    }

    public CharacterSheet proficiencyBounus(Integer proficiencyBounus) {
        this.proficiencyBounus = proficiencyBounus;
        return this;
    }

    public void setProficiencyBounus(Integer proficiencyBounus) {
        this.proficiencyBounus = proficiencyBounus;
    }

    public Integer getStrengthSavingThrow() {
        return strengthSavingThrow;
    }

    public CharacterSheet strengthSavingThrow(Integer strengthSavingThrow) {
        this.strengthSavingThrow = strengthSavingThrow;
        return this;
    }

    public void setStrengthSavingThrow(Integer strengthSavingThrow) {
        this.strengthSavingThrow = strengthSavingThrow;
    }

    public Integer getDexteritySavingThrow() {
        return dexteritySavingThrow;
    }

    public CharacterSheet dexteritySavingThrow(Integer dexteritySavingThrow) {
        this.dexteritySavingThrow = dexteritySavingThrow;
        return this;
    }

    public void setDexteritySavingThrow(Integer dexteritySavingThrow) {
        this.dexteritySavingThrow = dexteritySavingThrow;
    }

    public Integer getConstitutionSavingThrow() {
        return constitutionSavingThrow;
    }

    public CharacterSheet constitutionSavingThrow(Integer constitutionSavingThrow) {
        this.constitutionSavingThrow = constitutionSavingThrow;
        return this;
    }

    public void setConstitutionSavingThrow(Integer constitutionSavingThrow) {
        this.constitutionSavingThrow = constitutionSavingThrow;
    }

    public Integer getIntelligenceSavingThrow() {
        return intelligenceSavingThrow;
    }

    public CharacterSheet intelligenceSavingThrow(Integer intelligenceSavingThrow) {
        this.intelligenceSavingThrow = intelligenceSavingThrow;
        return this;
    }

    public void setIntelligenceSavingThrow(Integer intelligenceSavingThrow) {
        this.intelligenceSavingThrow = intelligenceSavingThrow;
    }

    public Integer getWisdomSavingThrow() {
        return wisdomSavingThrow;
    }

    public CharacterSheet wisdomSavingThrow(Integer wisdomSavingThrow) {
        this.wisdomSavingThrow = wisdomSavingThrow;
        return this;
    }

    public void setWisdomSavingThrow(Integer wisdomSavingThrow) {
        this.wisdomSavingThrow = wisdomSavingThrow;
    }

    public Integer getCharismaSavingThrow() {
        return charismaSavingThrow;
    }

    public CharacterSheet charismaSavingThrow(Integer charismaSavingThrow) {
        this.charismaSavingThrow = charismaSavingThrow;
        return this;
    }

    public void setCharismaSavingThrow(Integer charismaSavingThrow) {
        this.charismaSavingThrow = charismaSavingThrow;
    }

    public Integer getAcrobatics() {
        return acrobatics;
    }

    public CharacterSheet acrobatics(Integer acrobatics) {
        this.acrobatics = acrobatics;
        return this;
    }

    public void setAcrobatics(Integer acrobatics) {
        this.acrobatics = acrobatics;
    }

    public Integer getAnimalHandeling() {
        return animalHandeling;
    }

    public CharacterSheet animalHandeling(Integer animalHandeling) {
        this.animalHandeling = animalHandeling;
        return this;
    }

    public void setAnimalHandeling(Integer animalHandeling) {
        this.animalHandeling = animalHandeling;
    }

    public Integer getArcana() {
        return arcana;
    }

    public CharacterSheet arcana(Integer arcana) {
        this.arcana = arcana;
        return this;
    }

    public void setArcana(Integer arcana) {
        this.arcana = arcana;
    }

    public Integer getAthletics() {
        return athletics;
    }

    public CharacterSheet athletics(Integer athletics) {
        this.athletics = athletics;
        return this;
    }

    public void setAthletics(Integer athletics) {
        this.athletics = athletics;
    }

    public Integer getDeception() {
        return deception;
    }

    public CharacterSheet deception(Integer deception) {
        this.deception = deception;
        return this;
    }

    public void setDeception(Integer deception) {
        this.deception = deception;
    }

    public Integer getHistory() {
        return history;
    }

    public CharacterSheet history(Integer history) {
        this.history = history;
        return this;
    }

    public void setHistory(Integer history) {
        this.history = history;
    }

    public Integer getInsight() {
        return insight;
    }

    public CharacterSheet insight(Integer insight) {
        this.insight = insight;
        return this;
    }

    public void setInsight(Integer insight) {
        this.insight = insight;
    }

    public Integer getIntimidation() {
        return intimidation;
    }

    public CharacterSheet intimidation(Integer intimidation) {
        this.intimidation = intimidation;
        return this;
    }

    public void setIntimidation(Integer intimidation) {
        this.intimidation = intimidation;
    }

    public Integer getInvestigation() {
        return investigation;
    }

    public CharacterSheet investigation(Integer investigation) {
        this.investigation = investigation;
        return this;
    }

    public void setInvestigation(Integer investigation) {
        this.investigation = investigation;
    }

    public Integer getMedicine() {
        return medicine;
    }

    public CharacterSheet medicine(Integer medicine) {
        this.medicine = medicine;
        return this;
    }

    public void setMedicine(Integer medicine) {
        this.medicine = medicine;
    }

    public Integer getNature() {
        return nature;
    }

    public CharacterSheet nature(Integer nature) {
        this.nature = nature;
        return this;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public Integer getPerception() {
        return perception;
    }

    public CharacterSheet perception(Integer perception) {
        this.perception = perception;
        return this;
    }

    public void setPerception(Integer perception) {
        this.perception = perception;
    }

    public Integer getPerformance() {
        return performance;
    }

    public CharacterSheet performance(Integer performance) {
        this.performance = performance;
        return this;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public Integer getPersuasion() {
        return persuasion;
    }

    public CharacterSheet persuasion(Integer persuasion) {
        this.persuasion = persuasion;
        return this;
    }

    public void setPersuasion(Integer persuasion) {
        this.persuasion = persuasion;
    }

    public Integer getReligion() {
        return religion;
    }

    public CharacterSheet religion(Integer religion) {
        this.religion = religion;
        return this;
    }

    public void setReligion(Integer religion) {
        this.religion = religion;
    }

    public Integer getSleightOfHand() {
        return sleightOfHand;
    }

    public CharacterSheet sleightOfHand(Integer sleightOfHand) {
        this.sleightOfHand = sleightOfHand;
        return this;
    }

    public void setSleightOfHand(Integer sleightOfHand) {
        this.sleightOfHand = sleightOfHand;
    }

    public Integer getStealth() {
        return stealth;
    }

    public CharacterSheet stealth(Integer stealth) {
        this.stealth = stealth;
        return this;
    }

    public void setStealth(Integer stealth) {
        this.stealth = stealth;
    }

    public Integer getSurvival() {
        return survival;
    }

    public CharacterSheet survival(Integer survival) {
        this.survival = survival;
        return this;
    }

    public void setSurvival(Integer survival) {
        this.survival = survival;
    }

    public Integer getPassiveWisdomPerception() {
        return passiveWisdomPerception;
    }

    public CharacterSheet passiveWisdomPerception(Integer passiveWisdomPerception) {
        this.passiveWisdomPerception = passiveWisdomPerception;
        return this;
    }

    public void setPassiveWisdomPerception(Integer passiveWisdomPerception) {
        this.passiveWisdomPerception = passiveWisdomPerception;
    }

    public Integer getArmorClass() {
        return armorClass;
    }

    public CharacterSheet armorClass(Integer armorClass) {
        this.armorClass = armorClass;
        return this;
    }

    public void setArmorClass(Integer armorClass) {
        this.armorClass = armorClass;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public CharacterSheet initiative(Integer initiative) {
        this.initiative = initiative;
        return this;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    public Integer getSpeed() {
        return speed;
    }

    public CharacterSheet speed(Integer speed) {
        this.speed = speed;
        return this;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getHitPointMaximum() {
        return hitPointMaximum;
    }

    public CharacterSheet hitPointMaximum(Integer hitPointMaximum) {
        this.hitPointMaximum = hitPointMaximum;
        return this;
    }

    public void setHitPointMaximum(Integer hitPointMaximum) {
        this.hitPointMaximum = hitPointMaximum;
    }

    public Integer getCurrentHitPoints() {
        return currentHitPoints;
    }

    public CharacterSheet currentHitPoints(Integer currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
        return this;
    }

    public void setCurrentHitPoints(Integer currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
    }

    public Integer getTemporaryHitPoints() {
        return temporaryHitPoints;
    }

    public CharacterSheet temporaryHitPoints(Integer temporaryHitPoints) {
        this.temporaryHitPoints = temporaryHitPoints;
        return this;
    }

    public void setTemporaryHitPoints(Integer temporaryHitPoints) {
        this.temporaryHitPoints = temporaryHitPoints;
    }

    public DiceEnum getHitDice() {
        return hitDice;
    }

    public CharacterSheet hitDice(DiceEnum hitDice) {
        this.hitDice = hitDice;
        return this;
    }

    public void setHitDice(DiceEnum hitDice) {
        this.hitDice = hitDice;
    }

    public DeathSaveSuccessEnum getDeathSaveSuccess() {
        return deathSaveSuccess;
    }

    public CharacterSheet deathSaveSuccess(DeathSaveSuccessEnum deathSaveSuccess) {
        this.deathSaveSuccess = deathSaveSuccess;
        return this;
    }

    public void setDeathSaveSuccess(DeathSaveSuccessEnum deathSaveSuccess) {
        this.deathSaveSuccess = deathSaveSuccess;
    }

    public DeathSaveFailEnum getDeathSaveFail() {
        return deathSaveFail;
    }

    public CharacterSheet deathSaveFail(DeathSaveFailEnum deathSaveFail) {
        this.deathSaveFail = deathSaveFail;
        return this;
    }

    public void setDeathSaveFail(DeathSaveFailEnum deathSaveFail) {
        this.deathSaveFail = deathSaveFail;
    }

    public String getOtherProficienciesAndLanguages() {
        return otherProficienciesAndLanguages;
    }

    public CharacterSheet otherProficienciesAndLanguages(String otherProficienciesAndLanguages) {
        this.otherProficienciesAndLanguages = otherProficienciesAndLanguages;
        return this;
    }

    public void setOtherProficienciesAndLanguages(String otherProficienciesAndLanguages) {
        this.otherProficienciesAndLanguages = otherProficienciesAndLanguages;
    }

    public Integer getCopperPieces() {
        return copperPieces;
    }

    public CharacterSheet copperPieces(Integer copperPieces) {
        this.copperPieces = copperPieces;
        return this;
    }

    public void setCopperPieces(Integer copperPieces) {
        this.copperPieces = copperPieces;
    }

    public Integer getSilverPieces() {
        return silverPieces;
    }

    public CharacterSheet silverPieces(Integer silverPieces) {
        this.silverPieces = silverPieces;
        return this;
    }

    public void setSilverPieces(Integer silverPieces) {
        this.silverPieces = silverPieces;
    }

    public Integer getElectrumPieces() {
        return electrumPieces;
    }

    public CharacterSheet electrumPieces(Integer electrumPieces) {
        this.electrumPieces = electrumPieces;
        return this;
    }

    public void setElectrumPieces(Integer electrumPieces) {
        this.electrumPieces = electrumPieces;
    }

    public Integer getGoldPieces() {
        return goldPieces;
    }

    public CharacterSheet goldPieces(Integer goldPieces) {
        this.goldPieces = goldPieces;
        return this;
    }

    public void setGoldPieces(Integer goldPieces) {
        this.goldPieces = goldPieces;
    }

    public Integer getPlatinumPieces() {
        return platinumPieces;
    }

    public CharacterSheet platinumPieces(Integer platinumPieces) {
        this.platinumPieces = platinumPieces;
        return this;
    }

    public void setPlatinumPieces(Integer platinumPieces) {
        this.platinumPieces = platinumPieces;
    }

    public String getEquipment() {
        return equipment;
    }

    public CharacterSheet equipment(String equipment) {
        this.equipment = equipment;
        return this;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getFeaturesAndTraits() {
        return featuresAndTraits;
    }

    public CharacterSheet featuresAndTraits(String featuresAndTraits) {
        this.featuresAndTraits = featuresAndTraits;
        return this;
    }

    public void setFeaturesAndTraits(String featuresAndTraits) {
        this.featuresAndTraits = featuresAndTraits;
    }

    public String getAttacksAndSpellCasting() {
        return attacksAndSpellCasting;
    }

    public CharacterSheet attacksAndSpellCasting(String attacksAndSpellCasting) {
        this.attacksAndSpellCasting = attacksAndSpellCasting;
        return this;
    }

    public void setAttacksAndSpellCasting(String attacksAndSpellCasting) {
        this.attacksAndSpellCasting = attacksAndSpellCasting;
    }

    public String getAlliesAndOrganizations() {
        return alliesAndOrganizations;
    }

    public CharacterSheet alliesAndOrganizations(String alliesAndOrganizations) {
        this.alliesAndOrganizations = alliesAndOrganizations;
        return this;
    }

    public void setAlliesAndOrganizations(String alliesAndOrganizations) {
        this.alliesAndOrganizations = alliesAndOrganizations;
    }

    public String getAdditionalFeaturesAndTraits() {
        return additionalFeaturesAndTraits;
    }

    public CharacterSheet additionalFeaturesAndTraits(String additionalFeaturesAndTraits) {
        this.additionalFeaturesAndTraits = additionalFeaturesAndTraits;
        return this;
    }

    public void setAdditionalFeaturesAndTraits(String additionalFeaturesAndTraits) {
        this.additionalFeaturesAndTraits = additionalFeaturesAndTraits;
    }

    public String getTreasure() {
        return treasure;
    }

    public CharacterSheet treasure(String treasure) {
        this.treasure = treasure;
        return this;
    }

    public void setTreasure(String treasure) {
        this.treasure = treasure;
    }

    public String getCharacterBackstory() {
        return characterBackstory;
    }

    public CharacterSheet characterBackstory(String characterBackstory) {
        this.characterBackstory = characterBackstory;
        return this;
    }

    public void setCharacterBackstory(String characterBackstory) {
        this.characterBackstory = characterBackstory;
    }

    public Integer getAge() {
        return age;
    }

    public CharacterSheet age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public CharacterSheet height(String height) {
        this.height = height;
        return this;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public CharacterSheet weight(String weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getEyes() {
        return eyes;
    }

    public CharacterSheet eyes(String eyes) {
        this.eyes = eyes;
        return this;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public String getSkin() {
        return skin;
    }

    public CharacterSheet skin(String skin) {
        this.skin = skin;
        return this;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getHair() {
        return hair;
    }

    public CharacterSheet hair(String hair) {
        this.hair = hair;
        return this;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getSpellCastingClass() {
        return spellCastingClass;
    }

    public CharacterSheet spellCastingClass(String spellCastingClass) {
        this.spellCastingClass = spellCastingClass;
        return this;
    }

    public void setSpellCastingClass(String spellCastingClass) {
        this.spellCastingClass = spellCastingClass;
    }

    public Integer getSpellCastingAbility() {
        return spellCastingAbility;
    }

    public CharacterSheet spellCastingAbility(Integer spellCastingAbility) {
        this.spellCastingAbility = spellCastingAbility;
        return this;
    }

    public void setSpellCastingAbility(Integer spellCastingAbility) {
        this.spellCastingAbility = spellCastingAbility;
    }

    public Integer getSpellSaveDC() {
        return spellSaveDC;
    }

    public CharacterSheet spellSaveDC(Integer spellSaveDC) {
        this.spellSaveDC = spellSaveDC;
        return this;
    }

    public void setSpellSaveDC(Integer spellSaveDC) {
        this.spellSaveDC = spellSaveDC;
    }

    public Integer getSpellAttackBounus() {
        return spellAttackBounus;
    }

    public CharacterSheet spellAttackBounus(Integer spellAttackBounus) {
        this.spellAttackBounus = spellAttackBounus;
        return this;
    }

    public void setSpellAttackBounus(Integer spellAttackBounus) {
        this.spellAttackBounus = spellAttackBounus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CharacterSheet characterSheet = (CharacterSheet) o;
        if (characterSheet.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), characterSheet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CharacterSheet{" +
            "id=" + getId() +
            ", characterName='" + getCharacterName() + "'" +
            ", charachterClass='" + getCharachterClass() + "'" +
            ", background='" + getBackground() + "'" +
            ", playerName='" + getPlayerName() + "'" +
            ", race='" + getRace() + "'" +
            ", alignment='" + getAlignment() + "'" +
            ", experiencePoints=" + getExperiencePoints() +
            ", strength=" + getStrength() +
            ", dexterity=" + getDexterity() +
            ", constitution=" + getConstitution() +
            ", intelligence=" + getIntelligence() +
            ", wisdom=" + getWisdom() +
            ", charisma=" + getCharisma() +
            ", inspiration=" + getInspiration() +
            ", proficiencyBounus=" + getProficiencyBounus() +
            ", strengthSavingThrow=" + getStrengthSavingThrow() +
            ", dexteritySavingThrow=" + getDexteritySavingThrow() +
            ", constitutionSavingThrow=" + getConstitutionSavingThrow() +
            ", intelligenceSavingThrow=" + getIntelligenceSavingThrow() +
            ", wisdomSavingThrow=" + getWisdomSavingThrow() +
            ", charismaSavingThrow=" + getCharismaSavingThrow() +
            ", acrobatics=" + getAcrobatics() +
            ", animalHandeling=" + getAnimalHandeling() +
            ", arcana=" + getArcana() +
            ", athletics=" + getAthletics() +
            ", deception=" + getDeception() +
            ", history=" + getHistory() +
            ", insight=" + getInsight() +
            ", intimidation=" + getIntimidation() +
            ", investigation=" + getInvestigation() +
            ", medicine=" + getMedicine() +
            ", nature=" + getNature() +
            ", perception=" + getPerception() +
            ", performance=" + getPerformance() +
            ", persuasion=" + getPersuasion() +
            ", religion=" + getReligion() +
            ", sleightOfHand=" + getSleightOfHand() +
            ", stealth=" + getStealth() +
            ", survival=" + getSurvival() +
            ", passiveWisdomPerception=" + getPassiveWisdomPerception() +
            ", armorClass=" + getArmorClass() +
            ", initiative=" + getInitiative() +
            ", speed=" + getSpeed() +
            ", hitPointMaximum=" + getHitPointMaximum() +
            ", currentHitPoints=" + getCurrentHitPoints() +
            ", temporaryHitPoints=" + getTemporaryHitPoints() +
            ", hitDice='" + getHitDice() + "'" +
            ", deathSaveSuccess='" + getDeathSaveSuccess() + "'" +
            ", deathSaveFail='" + getDeathSaveFail() + "'" +
            ", otherProficienciesAndLanguages='" + getOtherProficienciesAndLanguages() + "'" +
            ", copperPieces=" + getCopperPieces() +
            ", silverPieces=" + getSilverPieces() +
            ", electrumPieces=" + getElectrumPieces() +
            ", goldPieces=" + getGoldPieces() +
            ", platinumPieces=" + getPlatinumPieces() +
            ", equipment='" + getEquipment() + "'" +
            ", featuresAndTraits='" + getFeaturesAndTraits() + "'" +
            ", attacksAndSpellCasting='" + getAttacksAndSpellCasting() + "'" +
            ", alliesAndOrganizations='" + getAlliesAndOrganizations() + "'" +
            ", additionalFeaturesAndTraits='" + getAdditionalFeaturesAndTraits() + "'" +
            ", treasure='" + getTreasure() + "'" +
            ", characterBackstory='" + getCharacterBackstory() + "'" +
            ", age=" + getAge() +
            ", height='" + getHeight() + "'" +
            ", weight='" + getWeight() + "'" +
            ", eyes='" + getEyes() + "'" +
            ", skin='" + getSkin() + "'" +
            ", hair='" + getHair() + "'" +
            ", spellCastingClass='" + getSpellCastingClass() + "'" +
            ", spellCastingAbility=" + getSpellCastingAbility() +
            ", spellSaveDC=" + getSpellSaveDC() +
            ", spellAttackBounus=" + getSpellAttackBounus() +
            "}";
    }
}
