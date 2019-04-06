package com.csi3370.dnd.web.rest;

import com.csi3370.dnd.DungeonsAndDatabasesApp;

import com.csi3370.dnd.domain.CharacterSheet;
import com.csi3370.dnd.repository.CharacterSheetRepository;
import com.csi3370.dnd.repository.search.CharacterSheetSearchRepository;
import com.csi3370.dnd.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;


import static com.csi3370.dnd.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.csi3370.dnd.domain.enumeration.CharacterClassEnum;
import com.csi3370.dnd.domain.enumeration.BackgroundEnum;
import com.csi3370.dnd.domain.enumeration.RaceEnum;
import com.csi3370.dnd.domain.enumeration.AlignmentEnum;
import com.csi3370.dnd.domain.enumeration.DiceEnum;
import com.csi3370.dnd.domain.enumeration.DeathSaveSuccessEnum;
import com.csi3370.dnd.domain.enumeration.DeathSaveFailEnum;
/**
 * Test class for the CharacterSheetResource REST controller.
 *
 * @see CharacterSheetResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DungeonsAndDatabasesApp.class)
public class CharacterSheetResourceIntTest {

    private static final String DEFAULT_CREATED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_SEARCH_KEYWORDS = "AAAAAAAAAA";
    private static final String UPDATED_SEARCH_KEYWORDS = "BBBBBBBBBB";

    private static final String DEFAULT_CHARACTER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHARACTER_NAME = "BBBBBBBBBB";

    private static final CharacterClassEnum DEFAULT_CHARACTER_CLASS = CharacterClassEnum.BARBARIAN;
    private static final CharacterClassEnum UPDATED_CHARACTER_CLASS = CharacterClassEnum.BARD;

    private static final BackgroundEnum DEFAULT_BACKGROUND = BackgroundEnum.ACOLYTE;
    private static final BackgroundEnum UPDATED_BACKGROUND = BackgroundEnum.CHARLATAN;

    private static final String DEFAULT_PLAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PLAYER_NAME = "BBBBBBBBBB";

    private static final RaceEnum DEFAULT_RACE = RaceEnum.DWARF;
    private static final RaceEnum UPDATED_RACE = RaceEnum.ELF;

    private static final AlignmentEnum DEFAULT_ALIGNMENT = AlignmentEnum.LAWFUL_GOOD;
    private static final AlignmentEnum UPDATED_ALIGNMENT = AlignmentEnum.NUETRAL_GOOD;

    private static final Integer DEFAULT_EXP_POINTS = 1;
    private static final Integer UPDATED_EXP_POINTS = 2;

    private static final Integer DEFAULT_STRENGTH = 1;
    private static final Integer UPDATED_STRENGTH = 2;

    private static final Integer DEFAULT_DEXTERITY = 1;
    private static final Integer UPDATED_DEXTERITY = 2;

    private static final Integer DEFAULT_CONSTITUTION = 1;
    private static final Integer UPDATED_CONSTITUTION = 2;

    private static final Integer DEFAULT_INTELLIGENCE = 1;
    private static final Integer UPDATED_INTELLIGENCE = 2;

    private static final Integer DEFAULT_WISDOM = 1;
    private static final Integer UPDATED_WISDOM = 2;

    private static final Integer DEFAULT_CHARISMA = 1;
    private static final Integer UPDATED_CHARISMA = 2;

    private static final Integer DEFAULT_INSPIRATION = 1;
    private static final Integer UPDATED_INSPIRATION = 2;

    private static final Integer DEFAULT_PROFICIENCY_BONUS = 1;
    private static final Integer UPDATED_PROFICIENCY_BONUS = 2;

    private static final Integer DEFAULT_STR_SAVING_THROW = 1;
    private static final Integer UPDATED_STR_SAVING_THROW = 2;

    private static final Integer DEFAULT_DEX_SAVING_THROW = 1;
    private static final Integer UPDATED_DEX_SAVING_THROW = 2;

    private static final Integer DEFAULT_CONST_SAVING_THROW = 1;
    private static final Integer UPDATED_CONST_SAVING_THROW = 2;

    private static final Integer DEFAULT_INT_SAVING_THROW = 1;
    private static final Integer UPDATED_INT_SAVING_THROW = 2;

    private static final Integer DEFAULT_WIS_SAVING_THROW = 1;
    private static final Integer UPDATED_WIS_SAVING_THROW = 2;

    private static final Integer DEFAULT_CHAR_SAVING_THROW = 1;
    private static final Integer UPDATED_CHAR_SAVING_THROW = 2;

    private static final Integer DEFAULT_ACROBATICS = 1;
    private static final Integer UPDATED_ACROBATICS = 2;

    private static final Integer DEFAULT_ANIMAL_HANDLING = 1;
    private static final Integer UPDATED_ANIMAL_HANDLING = 2;

    private static final Integer DEFAULT_ARCANA = 1;
    private static final Integer UPDATED_ARCANA = 2;

    private static final Integer DEFAULT_ATHLETICS = 1;
    private static final Integer UPDATED_ATHLETICS = 2;

    private static final Integer DEFAULT_DECEPTION = 1;
    private static final Integer UPDATED_DECEPTION = 2;

    private static final Integer DEFAULT_HISTORY = 1;
    private static final Integer UPDATED_HISTORY = 2;

    private static final Integer DEFAULT_INSIGHT = 1;
    private static final Integer UPDATED_INSIGHT = 2;

    private static final Integer DEFAULT_INTIMIDATION = 1;
    private static final Integer UPDATED_INTIMIDATION = 2;

    private static final Integer DEFAULT_INVESTIGATION = 1;
    private static final Integer UPDATED_INVESTIGATION = 2;

    private static final Integer DEFAULT_MEDICINE = 1;
    private static final Integer UPDATED_MEDICINE = 2;

    private static final Integer DEFAULT_NATURE = 1;
    private static final Integer UPDATED_NATURE = 2;

    private static final Integer DEFAULT_PERCEPTION = 1;
    private static final Integer UPDATED_PERCEPTION = 2;

    private static final Integer DEFAULT_PERFORMANCE = 1;
    private static final Integer UPDATED_PERFORMANCE = 2;

    private static final Integer DEFAULT_PERSUASION = 1;
    private static final Integer UPDATED_PERSUASION = 2;

    private static final Integer DEFAULT_RELIGION = 1;
    private static final Integer UPDATED_RELIGION = 2;

    private static final Integer DEFAULT_SLEIGHT_OF_HAND = 1;
    private static final Integer UPDATED_SLEIGHT_OF_HAND = 2;

    private static final Integer DEFAULT_STEALTH = 1;
    private static final Integer UPDATED_STEALTH = 2;

    private static final Integer DEFAULT_SURVIVAL = 1;
    private static final Integer UPDATED_SURVIVAL = 2;

    private static final Integer DEFAULT_PASSIVE_WISDOM_PERCEPTION = 1;
    private static final Integer UPDATED_PASSIVE_WISDOM_PERCEPTION = 2;

    private static final Integer DEFAULT_ARMOR_CLASS = 1;
    private static final Integer UPDATED_ARMOR_CLASS = 2;

    private static final Integer DEFAULT_INITIATIVE = 1;
    private static final Integer UPDATED_INITIATIVE = 2;

    private static final Integer DEFAULT_SPEED = 1;
    private static final Integer UPDATED_SPEED = 2;

    private static final Integer DEFAULT_HP_MAXIMUM = 1;
    private static final Integer UPDATED_HP_MAXIMUM = 2;

    private static final Integer DEFAULT_CURRENT_HP = 1;
    private static final Integer UPDATED_CURRENT_HP = 2;

    private static final Integer DEFAULT_TEMPORARY_HP = 1;
    private static final Integer UPDATED_TEMPORARY_HP = 2;

    private static final DiceEnum DEFAULT_HIT_DICE = DiceEnum.D4;
    private static final DiceEnum UPDATED_HIT_DICE = DiceEnum.D6;

    private static final DeathSaveSuccessEnum DEFAULT_DEATH_SAVE_SUCCESS = DeathSaveSuccessEnum.ZERO;
    private static final DeathSaveSuccessEnum UPDATED_DEATH_SAVE_SUCCESS = DeathSaveSuccessEnum.ONE;

    private static final DeathSaveFailEnum DEFAULT_DEATH_SAVE_FAIL = DeathSaveFailEnum.ZERO;
    private static final DeathSaveFailEnum UPDATED_DEATH_SAVE_FAIL = DeathSaveFailEnum.ONE;

    private static final String DEFAULT_OTHER_PROFICIENCIES_AND_LANGUAGES = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_PROFICIENCIES_AND_LANGUAGES = "BBBBBBBBBB";

    private static final Integer DEFAULT_COPPER_PIECES = 1;
    private static final Integer UPDATED_COPPER_PIECES = 2;

    private static final Integer DEFAULT_SILVER_PIECES = 1;
    private static final Integer UPDATED_SILVER_PIECES = 2;

    private static final Integer DEFAULT_ELECTRUM_PIECES = 1;
    private static final Integer UPDATED_ELECTRUM_PIECES = 2;

    private static final Integer DEFAULT_GOLD_PIECES = 1;
    private static final Integer UPDATED_GOLD_PIECES = 2;

    private static final Integer DEFAULT_PLATINUM_PIECES = 1;
    private static final Integer UPDATED_PLATINUM_PIECES = 2;

    private static final String DEFAULT_EQUIPMENT = "AAAAAAAAAA";
    private static final String UPDATED_EQUIPMENT = "BBBBBBBBBB";

    private static final String DEFAULT_FEATURES_AND_TRAITS = "AAAAAAAAAA";
    private static final String UPDATED_FEATURES_AND_TRAITS = "BBBBBBBBBB";

    private static final String DEFAULT_ATTACKS_AND_SPELL_CASTING = "AAAAAAAAAA";
    private static final String UPDATED_ATTACKS_AND_SPELL_CASTING = "BBBBBBBBBB";

    private static final String DEFAULT_ALLIES_AND_ORGANIZATIONS = "AAAAAAAAAA";
    private static final String UPDATED_ALLIES_AND_ORGANIZATIONS = "BBBBBBBBBB";

    private static final String DEFAULT_ADDITIONAL_FEATURES_AND_TRAITS = "AAAAAAAAAA";
    private static final String UPDATED_ADDITIONAL_FEATURES_AND_TRAITS = "BBBBBBBBBB";

    private static final String DEFAULT_TREASURE = "AAAAAAAAAA";
    private static final String UPDATED_TREASURE = "BBBBBBBBBB";

    private static final String DEFAULT_CHARACTER_BACKSTORY = "AAAAAAAAAA";
    private static final String UPDATED_CHARACTER_BACKSTORY = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final String DEFAULT_HEIGHT = "AAAAAAAAAA";
    private static final String UPDATED_HEIGHT = "BBBBBBBBBB";

    private static final String DEFAULT_WEIGHT = "AAAAAAAAAA";
    private static final String UPDATED_WEIGHT = "BBBBBBBBBB";

    private static final String DEFAULT_EYES = "AAAAAAAAAA";
    private static final String UPDATED_EYES = "BBBBBBBBBB";

    private static final String DEFAULT_SKIN = "AAAAAAAAAA";
    private static final String UPDATED_SKIN = "BBBBBBBBBB";

    private static final String DEFAULT_HAIR = "AAAAAAAAAA";
    private static final String UPDATED_HAIR = "BBBBBBBBBB";

    private static final String DEFAULT_SPELL_CASTING_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_SPELL_CASTING_CLASS = "BBBBBBBBBB";

    private static final Integer DEFAULT_SPELL_CASTING_ABILITY = 1;
    private static final Integer UPDATED_SPELL_CASTING_ABILITY = 2;

    private static final Integer DEFAULT_SPELL_SAVE_DC = 1;
    private static final Integer UPDATED_SPELL_SAVE_DC = 2;

    private static final Integer DEFAULT_SPELL_ATTACK_BONUS = 1;
    private static final Integer UPDATED_SPELL_ATTACK_BONUS = 2;

    @Autowired
    private CharacterSheetRepository characterSheetRepository;

    /**
     * This repository is mocked in the com.csi3370.dnd.repository.search test package.
     *
     * @see com.csi3370.dnd.repository.search.CharacterSheetSearchRepositoryMockConfiguration
     */
    @Autowired
    private CharacterSheetSearchRepository mockCharacterSheetSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCharacterSheetMockMvc;

    private CharacterSheet characterSheet;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CharacterSheetResource characterSheetResource = new CharacterSheetResource(characterSheetRepository, mockCharacterSheetSearchRepository);
        this.restCharacterSheetMockMvc = MockMvcBuilders.standaloneSetup(characterSheetResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CharacterSheet createEntity(EntityManager em) {
        CharacterSheet characterSheet = new CharacterSheet()
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .searchKeywords(DEFAULT_SEARCH_KEYWORDS)
            .characterName(DEFAULT_CHARACTER_NAME)
            .characterClass(DEFAULT_CHARACTER_CLASS)
            .background(DEFAULT_BACKGROUND)
            .playerName(DEFAULT_PLAYER_NAME)
            .race(DEFAULT_RACE)
            .alignment(DEFAULT_ALIGNMENT)
            .expPoints(DEFAULT_EXP_POINTS)
            .strength(DEFAULT_STRENGTH)
            .dexterity(DEFAULT_DEXTERITY)
            .constitution(DEFAULT_CONSTITUTION)
            .intelligence(DEFAULT_INTELLIGENCE)
            .wisdom(DEFAULT_WISDOM)
            .charisma(DEFAULT_CHARISMA)
            .inspiration(DEFAULT_INSPIRATION)
            .proficiencyBonus(DEFAULT_PROFICIENCY_BONUS)
            .strSavingThrow(DEFAULT_STR_SAVING_THROW)
            .dexSavingThrow(DEFAULT_DEX_SAVING_THROW)
            .constSavingThrow(DEFAULT_CONST_SAVING_THROW)
            .intSavingThrow(DEFAULT_INT_SAVING_THROW)
            .wisSavingThrow(DEFAULT_WIS_SAVING_THROW)
            .charSavingThrow(DEFAULT_CHAR_SAVING_THROW)
            .acrobatics(DEFAULT_ACROBATICS)
            .animalHandling(DEFAULT_ANIMAL_HANDLING)
            .arcana(DEFAULT_ARCANA)
            .athletics(DEFAULT_ATHLETICS)
            .deception(DEFAULT_DECEPTION)
            .history(DEFAULT_HISTORY)
            .insight(DEFAULT_INSIGHT)
            .intimidation(DEFAULT_INTIMIDATION)
            .investigation(DEFAULT_INVESTIGATION)
            .medicine(DEFAULT_MEDICINE)
            .nature(DEFAULT_NATURE)
            .perception(DEFAULT_PERCEPTION)
            .performance(DEFAULT_PERFORMANCE)
            .persuasion(DEFAULT_PERSUASION)
            .religion(DEFAULT_RELIGION)
            .sleightOfHand(DEFAULT_SLEIGHT_OF_HAND)
            .stealth(DEFAULT_STEALTH)
            .survival(DEFAULT_SURVIVAL)
            .passiveWisdomPerception(DEFAULT_PASSIVE_WISDOM_PERCEPTION)
            .armorClass(DEFAULT_ARMOR_CLASS)
            .initiative(DEFAULT_INITIATIVE)
            .speed(DEFAULT_SPEED)
            .hpMaximum(DEFAULT_HP_MAXIMUM)
            .currentHP(DEFAULT_CURRENT_HP)
            .temporaryHP(DEFAULT_TEMPORARY_HP)
            .hitDice(DEFAULT_HIT_DICE)
            .deathSaveSuccess(DEFAULT_DEATH_SAVE_SUCCESS)
            .deathSaveFail(DEFAULT_DEATH_SAVE_FAIL)
            .otherProficienciesAndLanguages(DEFAULT_OTHER_PROFICIENCIES_AND_LANGUAGES)
            .copperPieces(DEFAULT_COPPER_PIECES)
            .silverPieces(DEFAULT_SILVER_PIECES)
            .electrumPieces(DEFAULT_ELECTRUM_PIECES)
            .goldPieces(DEFAULT_GOLD_PIECES)
            .platinumPieces(DEFAULT_PLATINUM_PIECES)
            .equipment(DEFAULT_EQUIPMENT)
            .featuresAndTraits(DEFAULT_FEATURES_AND_TRAITS)
            .attacksAndSpellCasting(DEFAULT_ATTACKS_AND_SPELL_CASTING)
            .alliesAndOrganizations(DEFAULT_ALLIES_AND_ORGANIZATIONS)
            .additionalFeaturesAndTraits(DEFAULT_ADDITIONAL_FEATURES_AND_TRAITS)
            .treasure(DEFAULT_TREASURE)
            .characterBackstory(DEFAULT_CHARACTER_BACKSTORY)
            .age(DEFAULT_AGE)
            .height(DEFAULT_HEIGHT)
            .weight(DEFAULT_WEIGHT)
            .eyes(DEFAULT_EYES)
            .skin(DEFAULT_SKIN)
            .hair(DEFAULT_HAIR)
            .spellCastingClass(DEFAULT_SPELL_CASTING_CLASS)
            .spellCastingAbility(DEFAULT_SPELL_CASTING_ABILITY)
            .spellSaveDC(DEFAULT_SPELL_SAVE_DC)
            .spellAttackBonus(DEFAULT_SPELL_ATTACK_BONUS);
        return characterSheet;
    }

    @Before
    public void initTest() {
        characterSheet = createEntity(em);
    }

    @Test
    @Transactional
    public void createCharacterSheet() throws Exception {
        int databaseSizeBeforeCreate = characterSheetRepository.findAll().size();

        // Create the CharacterSheet
        restCharacterSheetMockMvc.perform(post("/api/character-sheets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(characterSheet)))
            .andExpect(status().isCreated());

        // Validate the CharacterSheet in the database
        List<CharacterSheet> characterSheetList = characterSheetRepository.findAll();
        assertThat(characterSheetList).hasSize(databaseSizeBeforeCreate + 1);
        CharacterSheet testCharacterSheet = characterSheetList.get(characterSheetList.size() - 1);
        assertThat(testCharacterSheet.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCharacterSheet.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCharacterSheet.getSearchKeywords()).isEqualTo(DEFAULT_SEARCH_KEYWORDS);
        assertThat(testCharacterSheet.getCharacterName()).isEqualTo(DEFAULT_CHARACTER_NAME);
        assertThat(testCharacterSheet.getCharacterClass()).isEqualTo(DEFAULT_CHARACTER_CLASS);
        assertThat(testCharacterSheet.getBackground()).isEqualTo(DEFAULT_BACKGROUND);
        assertThat(testCharacterSheet.getPlayerName()).isEqualTo(DEFAULT_PLAYER_NAME);
        assertThat(testCharacterSheet.getRace()).isEqualTo(DEFAULT_RACE);
        assertThat(testCharacterSheet.getAlignment()).isEqualTo(DEFAULT_ALIGNMENT);
        assertThat(testCharacterSheet.getExpPoints()).isEqualTo(DEFAULT_EXP_POINTS);
        assertThat(testCharacterSheet.getStrength()).isEqualTo(DEFAULT_STRENGTH);
        assertThat(testCharacterSheet.getDexterity()).isEqualTo(DEFAULT_DEXTERITY);
        assertThat(testCharacterSheet.getConstitution()).isEqualTo(DEFAULT_CONSTITUTION);
        assertThat(testCharacterSheet.getIntelligence()).isEqualTo(DEFAULT_INTELLIGENCE);
        assertThat(testCharacterSheet.getWisdom()).isEqualTo(DEFAULT_WISDOM);
        assertThat(testCharacterSheet.getCharisma()).isEqualTo(DEFAULT_CHARISMA);
        assertThat(testCharacterSheet.getInspiration()).isEqualTo(DEFAULT_INSPIRATION);
        assertThat(testCharacterSheet.getProficiencyBonus()).isEqualTo(DEFAULT_PROFICIENCY_BONUS);
        assertThat(testCharacterSheet.getStrSavingThrow()).isEqualTo(DEFAULT_STR_SAVING_THROW);
        assertThat(testCharacterSheet.getDexSavingThrow()).isEqualTo(DEFAULT_DEX_SAVING_THROW);
        assertThat(testCharacterSheet.getConstSavingThrow()).isEqualTo(DEFAULT_CONST_SAVING_THROW);
        assertThat(testCharacterSheet.getIntSavingThrow()).isEqualTo(DEFAULT_INT_SAVING_THROW);
        assertThat(testCharacterSheet.getWisSavingThrow()).isEqualTo(DEFAULT_WIS_SAVING_THROW);
        assertThat(testCharacterSheet.getCharSavingThrow()).isEqualTo(DEFAULT_CHAR_SAVING_THROW);
        assertThat(testCharacterSheet.getAcrobatics()).isEqualTo(DEFAULT_ACROBATICS);
        assertThat(testCharacterSheet.getAnimalHandling()).isEqualTo(DEFAULT_ANIMAL_HANDLING);
        assertThat(testCharacterSheet.getArcana()).isEqualTo(DEFAULT_ARCANA);
        assertThat(testCharacterSheet.getAthletics()).isEqualTo(DEFAULT_ATHLETICS);
        assertThat(testCharacterSheet.getDeception()).isEqualTo(DEFAULT_DECEPTION);
        assertThat(testCharacterSheet.getHistory()).isEqualTo(DEFAULT_HISTORY);
        assertThat(testCharacterSheet.getInsight()).isEqualTo(DEFAULT_INSIGHT);
        assertThat(testCharacterSheet.getIntimidation()).isEqualTo(DEFAULT_INTIMIDATION);
        assertThat(testCharacterSheet.getInvestigation()).isEqualTo(DEFAULT_INVESTIGATION);
        assertThat(testCharacterSheet.getMedicine()).isEqualTo(DEFAULT_MEDICINE);
        assertThat(testCharacterSheet.getNature()).isEqualTo(DEFAULT_NATURE);
        assertThat(testCharacterSheet.getPerception()).isEqualTo(DEFAULT_PERCEPTION);
        assertThat(testCharacterSheet.getPerformance()).isEqualTo(DEFAULT_PERFORMANCE);
        assertThat(testCharacterSheet.getPersuasion()).isEqualTo(DEFAULT_PERSUASION);
        assertThat(testCharacterSheet.getReligion()).isEqualTo(DEFAULT_RELIGION);
        assertThat(testCharacterSheet.getSleightOfHand()).isEqualTo(DEFAULT_SLEIGHT_OF_HAND);
        assertThat(testCharacterSheet.getStealth()).isEqualTo(DEFAULT_STEALTH);
        assertThat(testCharacterSheet.getSurvival()).isEqualTo(DEFAULT_SURVIVAL);
        assertThat(testCharacterSheet.getPassiveWisdomPerception()).isEqualTo(DEFAULT_PASSIVE_WISDOM_PERCEPTION);
        assertThat(testCharacterSheet.getArmorClass()).isEqualTo(DEFAULT_ARMOR_CLASS);
        assertThat(testCharacterSheet.getInitiative()).isEqualTo(DEFAULT_INITIATIVE);
        assertThat(testCharacterSheet.getSpeed()).isEqualTo(DEFAULT_SPEED);
        assertThat(testCharacterSheet.getHpMaximum()).isEqualTo(DEFAULT_HP_MAXIMUM);
        assertThat(testCharacterSheet.getCurrentHP()).isEqualTo(DEFAULT_CURRENT_HP);
        assertThat(testCharacterSheet.getTemporaryHP()).isEqualTo(DEFAULT_TEMPORARY_HP);
        assertThat(testCharacterSheet.getHitDice()).isEqualTo(DEFAULT_HIT_DICE);
        assertThat(testCharacterSheet.getDeathSaveSuccess()).isEqualTo(DEFAULT_DEATH_SAVE_SUCCESS);
        assertThat(testCharacterSheet.getDeathSaveFail()).isEqualTo(DEFAULT_DEATH_SAVE_FAIL);
        assertThat(testCharacterSheet.getOtherProficienciesAndLanguages()).isEqualTo(DEFAULT_OTHER_PROFICIENCIES_AND_LANGUAGES);
        assertThat(testCharacterSheet.getCopperPieces()).isEqualTo(DEFAULT_COPPER_PIECES);
        assertThat(testCharacterSheet.getSilverPieces()).isEqualTo(DEFAULT_SILVER_PIECES);
        assertThat(testCharacterSheet.getElectrumPieces()).isEqualTo(DEFAULT_ELECTRUM_PIECES);
        assertThat(testCharacterSheet.getGoldPieces()).isEqualTo(DEFAULT_GOLD_PIECES);
        assertThat(testCharacterSheet.getPlatinumPieces()).isEqualTo(DEFAULT_PLATINUM_PIECES);
        assertThat(testCharacterSheet.getEquipment()).isEqualTo(DEFAULT_EQUIPMENT);
        assertThat(testCharacterSheet.getFeaturesAndTraits()).isEqualTo(DEFAULT_FEATURES_AND_TRAITS);
        assertThat(testCharacterSheet.getAttacksAndSpellCasting()).isEqualTo(DEFAULT_ATTACKS_AND_SPELL_CASTING);
        assertThat(testCharacterSheet.getAlliesAndOrganizations()).isEqualTo(DEFAULT_ALLIES_AND_ORGANIZATIONS);
        assertThat(testCharacterSheet.getAdditionalFeaturesAndTraits()).isEqualTo(DEFAULT_ADDITIONAL_FEATURES_AND_TRAITS);
        assertThat(testCharacterSheet.getTreasure()).isEqualTo(DEFAULT_TREASURE);
        assertThat(testCharacterSheet.getCharacterBackstory()).isEqualTo(DEFAULT_CHARACTER_BACKSTORY);
        assertThat(testCharacterSheet.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testCharacterSheet.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testCharacterSheet.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testCharacterSheet.getEyes()).isEqualTo(DEFAULT_EYES);
        assertThat(testCharacterSheet.getSkin()).isEqualTo(DEFAULT_SKIN);
        assertThat(testCharacterSheet.getHair()).isEqualTo(DEFAULT_HAIR);
        assertThat(testCharacterSheet.getSpellCastingClass()).isEqualTo(DEFAULT_SPELL_CASTING_CLASS);
        assertThat(testCharacterSheet.getSpellCastingAbility()).isEqualTo(DEFAULT_SPELL_CASTING_ABILITY);
        assertThat(testCharacterSheet.getSpellSaveDC()).isEqualTo(DEFAULT_SPELL_SAVE_DC);
        assertThat(testCharacterSheet.getSpellAttackBonus()).isEqualTo(DEFAULT_SPELL_ATTACK_BONUS);

        // Validate the CharacterSheet in Elasticsearch
        verify(mockCharacterSheetSearchRepository, times(1)).save(testCharacterSheet);
    }

    @Test
    @Transactional
    public void createCharacterSheetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = characterSheetRepository.findAll().size();

        // Create the CharacterSheet with an existing ID
        characterSheet.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCharacterSheetMockMvc.perform(post("/api/character-sheets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(characterSheet)))
            .andExpect(status().isBadRequest());

        // Validate the CharacterSheet in the database
        List<CharacterSheet> characterSheetList = characterSheetRepository.findAll();
        assertThat(characterSheetList).hasSize(databaseSizeBeforeCreate);

        // Validate the CharacterSheet in Elasticsearch
        verify(mockCharacterSheetSearchRepository, times(0)).save(characterSheet);
    }

    @Test
    @Transactional
    public void getAllCharacterSheets() throws Exception {
        // Initialize the database
        characterSheetRepository.saveAndFlush(characterSheet);

        // Get all the characterSheetList
        restCharacterSheetMockMvc.perform(get("/api/character-sheets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(characterSheet.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].searchKeywords").value(hasItem(DEFAULT_SEARCH_KEYWORDS.toString())))
            .andExpect(jsonPath("$.[*].characterName").value(hasItem(DEFAULT_CHARACTER_NAME.toString())))
            .andExpect(jsonPath("$.[*].characterClass").value(hasItem(DEFAULT_CHARACTER_CLASS.toString())))
            .andExpect(jsonPath("$.[*].background").value(hasItem(DEFAULT_BACKGROUND.toString())))
            .andExpect(jsonPath("$.[*].playerName").value(hasItem(DEFAULT_PLAYER_NAME.toString())))
            .andExpect(jsonPath("$.[*].race").value(hasItem(DEFAULT_RACE.toString())))
            .andExpect(jsonPath("$.[*].alignment").value(hasItem(DEFAULT_ALIGNMENT.toString())))
            .andExpect(jsonPath("$.[*].expPoints").value(hasItem(DEFAULT_EXP_POINTS)))
            .andExpect(jsonPath("$.[*].strength").value(hasItem(DEFAULT_STRENGTH)))
            .andExpect(jsonPath("$.[*].dexterity").value(hasItem(DEFAULT_DEXTERITY)))
            .andExpect(jsonPath("$.[*].constitution").value(hasItem(DEFAULT_CONSTITUTION)))
            .andExpect(jsonPath("$.[*].intelligence").value(hasItem(DEFAULT_INTELLIGENCE)))
            .andExpect(jsonPath("$.[*].wisdom").value(hasItem(DEFAULT_WISDOM)))
            .andExpect(jsonPath("$.[*].charisma").value(hasItem(DEFAULT_CHARISMA)))
            .andExpect(jsonPath("$.[*].inspiration").value(hasItem(DEFAULT_INSPIRATION)))
            .andExpect(jsonPath("$.[*].proficiencyBonus").value(hasItem(DEFAULT_PROFICIENCY_BONUS)))
            .andExpect(jsonPath("$.[*].strSavingThrow").value(hasItem(DEFAULT_STR_SAVING_THROW)))
            .andExpect(jsonPath("$.[*].dexSavingThrow").value(hasItem(DEFAULT_DEX_SAVING_THROW)))
            .andExpect(jsonPath("$.[*].constSavingThrow").value(hasItem(DEFAULT_CONST_SAVING_THROW)))
            .andExpect(jsonPath("$.[*].intSavingThrow").value(hasItem(DEFAULT_INT_SAVING_THROW)))
            .andExpect(jsonPath("$.[*].wisSavingThrow").value(hasItem(DEFAULT_WIS_SAVING_THROW)))
            .andExpect(jsonPath("$.[*].charSavingThrow").value(hasItem(DEFAULT_CHAR_SAVING_THROW)))
            .andExpect(jsonPath("$.[*].acrobatics").value(hasItem(DEFAULT_ACROBATICS)))
            .andExpect(jsonPath("$.[*].animalHandling").value(hasItem(DEFAULT_ANIMAL_HANDLING)))
            .andExpect(jsonPath("$.[*].arcana").value(hasItem(DEFAULT_ARCANA)))
            .andExpect(jsonPath("$.[*].athletics").value(hasItem(DEFAULT_ATHLETICS)))
            .andExpect(jsonPath("$.[*].deception").value(hasItem(DEFAULT_DECEPTION)))
            .andExpect(jsonPath("$.[*].history").value(hasItem(DEFAULT_HISTORY)))
            .andExpect(jsonPath("$.[*].insight").value(hasItem(DEFAULT_INSIGHT)))
            .andExpect(jsonPath("$.[*].intimidation").value(hasItem(DEFAULT_INTIMIDATION)))
            .andExpect(jsonPath("$.[*].investigation").value(hasItem(DEFAULT_INVESTIGATION)))
            .andExpect(jsonPath("$.[*].medicine").value(hasItem(DEFAULT_MEDICINE)))
            .andExpect(jsonPath("$.[*].nature").value(hasItem(DEFAULT_NATURE)))
            .andExpect(jsonPath("$.[*].perception").value(hasItem(DEFAULT_PERCEPTION)))
            .andExpect(jsonPath("$.[*].performance").value(hasItem(DEFAULT_PERFORMANCE)))
            .andExpect(jsonPath("$.[*].persuasion").value(hasItem(DEFAULT_PERSUASION)))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION)))
            .andExpect(jsonPath("$.[*].sleightOfHand").value(hasItem(DEFAULT_SLEIGHT_OF_HAND)))
            .andExpect(jsonPath("$.[*].stealth").value(hasItem(DEFAULT_STEALTH)))
            .andExpect(jsonPath("$.[*].survival").value(hasItem(DEFAULT_SURVIVAL)))
            .andExpect(jsonPath("$.[*].passiveWisdomPerception").value(hasItem(DEFAULT_PASSIVE_WISDOM_PERCEPTION)))
            .andExpect(jsonPath("$.[*].armorClass").value(hasItem(DEFAULT_ARMOR_CLASS)))
            .andExpect(jsonPath("$.[*].initiative").value(hasItem(DEFAULT_INITIATIVE)))
            .andExpect(jsonPath("$.[*].speed").value(hasItem(DEFAULT_SPEED)))
            .andExpect(jsonPath("$.[*].hpMaximum").value(hasItem(DEFAULT_HP_MAXIMUM)))
            .andExpect(jsonPath("$.[*].currentHP").value(hasItem(DEFAULT_CURRENT_HP)))
            .andExpect(jsonPath("$.[*].temporaryHP").value(hasItem(DEFAULT_TEMPORARY_HP)))
            .andExpect(jsonPath("$.[*].hitDice").value(hasItem(DEFAULT_HIT_DICE.toString())))
            .andExpect(jsonPath("$.[*].deathSaveSuccess").value(hasItem(DEFAULT_DEATH_SAVE_SUCCESS.toString())))
            .andExpect(jsonPath("$.[*].deathSaveFail").value(hasItem(DEFAULT_DEATH_SAVE_FAIL.toString())))
            .andExpect(jsonPath("$.[*].otherProficienciesAndLanguages").value(hasItem(DEFAULT_OTHER_PROFICIENCIES_AND_LANGUAGES.toString())))
            .andExpect(jsonPath("$.[*].copperPieces").value(hasItem(DEFAULT_COPPER_PIECES)))
            .andExpect(jsonPath("$.[*].silverPieces").value(hasItem(DEFAULT_SILVER_PIECES)))
            .andExpect(jsonPath("$.[*].electrumPieces").value(hasItem(DEFAULT_ELECTRUM_PIECES)))
            .andExpect(jsonPath("$.[*].goldPieces").value(hasItem(DEFAULT_GOLD_PIECES)))
            .andExpect(jsonPath("$.[*].platinumPieces").value(hasItem(DEFAULT_PLATINUM_PIECES)))
            .andExpect(jsonPath("$.[*].equipment").value(hasItem(DEFAULT_EQUIPMENT.toString())))
            .andExpect(jsonPath("$.[*].featuresAndTraits").value(hasItem(DEFAULT_FEATURES_AND_TRAITS.toString())))
            .andExpect(jsonPath("$.[*].attacksAndSpellCasting").value(hasItem(DEFAULT_ATTACKS_AND_SPELL_CASTING.toString())))
            .andExpect(jsonPath("$.[*].alliesAndOrganizations").value(hasItem(DEFAULT_ALLIES_AND_ORGANIZATIONS.toString())))
            .andExpect(jsonPath("$.[*].additionalFeaturesAndTraits").value(hasItem(DEFAULT_ADDITIONAL_FEATURES_AND_TRAITS.toString())))
            .andExpect(jsonPath("$.[*].treasure").value(hasItem(DEFAULT_TREASURE.toString())))
            .andExpect(jsonPath("$.[*].characterBackstory").value(hasItem(DEFAULT_CHARACTER_BACKSTORY.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.toString())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.toString())))
            .andExpect(jsonPath("$.[*].eyes").value(hasItem(DEFAULT_EYES.toString())))
            .andExpect(jsonPath("$.[*].skin").value(hasItem(DEFAULT_SKIN.toString())))
            .andExpect(jsonPath("$.[*].hair").value(hasItem(DEFAULT_HAIR.toString())))
            .andExpect(jsonPath("$.[*].spellCastingClass").value(hasItem(DEFAULT_SPELL_CASTING_CLASS.toString())))
            .andExpect(jsonPath("$.[*].spellCastingAbility").value(hasItem(DEFAULT_SPELL_CASTING_ABILITY)))
            .andExpect(jsonPath("$.[*].spellSaveDC").value(hasItem(DEFAULT_SPELL_SAVE_DC)))
            .andExpect(jsonPath("$.[*].spellAttackBonus").value(hasItem(DEFAULT_SPELL_ATTACK_BONUS)));
    }
    
    @Test
    @Transactional
    public void getCharacterSheet() throws Exception {
        // Initialize the database
        characterSheetRepository.saveAndFlush(characterSheet);

        // Get the characterSheet
        restCharacterSheetMockMvc.perform(get("/api/character-sheets/{id}", characterSheet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(characterSheet.getId().intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.searchKeywords").value(DEFAULT_SEARCH_KEYWORDS.toString()))
            .andExpect(jsonPath("$.characterName").value(DEFAULT_CHARACTER_NAME.toString()))
            .andExpect(jsonPath("$.characterClass").value(DEFAULT_CHARACTER_CLASS.toString()))
            .andExpect(jsonPath("$.background").value(DEFAULT_BACKGROUND.toString()))
            .andExpect(jsonPath("$.playerName").value(DEFAULT_PLAYER_NAME.toString()))
            .andExpect(jsonPath("$.race").value(DEFAULT_RACE.toString()))
            .andExpect(jsonPath("$.alignment").value(DEFAULT_ALIGNMENT.toString()))
            .andExpect(jsonPath("$.expPoints").value(DEFAULT_EXP_POINTS))
            .andExpect(jsonPath("$.strength").value(DEFAULT_STRENGTH))
            .andExpect(jsonPath("$.dexterity").value(DEFAULT_DEXTERITY))
            .andExpect(jsonPath("$.constitution").value(DEFAULT_CONSTITUTION))
            .andExpect(jsonPath("$.intelligence").value(DEFAULT_INTELLIGENCE))
            .andExpect(jsonPath("$.wisdom").value(DEFAULT_WISDOM))
            .andExpect(jsonPath("$.charisma").value(DEFAULT_CHARISMA))
            .andExpect(jsonPath("$.inspiration").value(DEFAULT_INSPIRATION))
            .andExpect(jsonPath("$.proficiencyBonus").value(DEFAULT_PROFICIENCY_BONUS))
            .andExpect(jsonPath("$.strSavingThrow").value(DEFAULT_STR_SAVING_THROW))
            .andExpect(jsonPath("$.dexSavingThrow").value(DEFAULT_DEX_SAVING_THROW))
            .andExpect(jsonPath("$.constSavingThrow").value(DEFAULT_CONST_SAVING_THROW))
            .andExpect(jsonPath("$.intSavingThrow").value(DEFAULT_INT_SAVING_THROW))
            .andExpect(jsonPath("$.wisSavingThrow").value(DEFAULT_WIS_SAVING_THROW))
            .andExpect(jsonPath("$.charSavingThrow").value(DEFAULT_CHAR_SAVING_THROW))
            .andExpect(jsonPath("$.acrobatics").value(DEFAULT_ACROBATICS))
            .andExpect(jsonPath("$.animalHandling").value(DEFAULT_ANIMAL_HANDLING))
            .andExpect(jsonPath("$.arcana").value(DEFAULT_ARCANA))
            .andExpect(jsonPath("$.athletics").value(DEFAULT_ATHLETICS))
            .andExpect(jsonPath("$.deception").value(DEFAULT_DECEPTION))
            .andExpect(jsonPath("$.history").value(DEFAULT_HISTORY))
            .andExpect(jsonPath("$.insight").value(DEFAULT_INSIGHT))
            .andExpect(jsonPath("$.intimidation").value(DEFAULT_INTIMIDATION))
            .andExpect(jsonPath("$.investigation").value(DEFAULT_INVESTIGATION))
            .andExpect(jsonPath("$.medicine").value(DEFAULT_MEDICINE))
            .andExpect(jsonPath("$.nature").value(DEFAULT_NATURE))
            .andExpect(jsonPath("$.perception").value(DEFAULT_PERCEPTION))
            .andExpect(jsonPath("$.performance").value(DEFAULT_PERFORMANCE))
            .andExpect(jsonPath("$.persuasion").value(DEFAULT_PERSUASION))
            .andExpect(jsonPath("$.religion").value(DEFAULT_RELIGION))
            .andExpect(jsonPath("$.sleightOfHand").value(DEFAULT_SLEIGHT_OF_HAND))
            .andExpect(jsonPath("$.stealth").value(DEFAULT_STEALTH))
            .andExpect(jsonPath("$.survival").value(DEFAULT_SURVIVAL))
            .andExpect(jsonPath("$.passiveWisdomPerception").value(DEFAULT_PASSIVE_WISDOM_PERCEPTION))
            .andExpect(jsonPath("$.armorClass").value(DEFAULT_ARMOR_CLASS))
            .andExpect(jsonPath("$.initiative").value(DEFAULT_INITIATIVE))
            .andExpect(jsonPath("$.speed").value(DEFAULT_SPEED))
            .andExpect(jsonPath("$.hpMaximum").value(DEFAULT_HP_MAXIMUM))
            .andExpect(jsonPath("$.currentHP").value(DEFAULT_CURRENT_HP))
            .andExpect(jsonPath("$.temporaryHP").value(DEFAULT_TEMPORARY_HP))
            .andExpect(jsonPath("$.hitDice").value(DEFAULT_HIT_DICE.toString()))
            .andExpect(jsonPath("$.deathSaveSuccess").value(DEFAULT_DEATH_SAVE_SUCCESS.toString()))
            .andExpect(jsonPath("$.deathSaveFail").value(DEFAULT_DEATH_SAVE_FAIL.toString()))
            .andExpect(jsonPath("$.otherProficienciesAndLanguages").value(DEFAULT_OTHER_PROFICIENCIES_AND_LANGUAGES.toString()))
            .andExpect(jsonPath("$.copperPieces").value(DEFAULT_COPPER_PIECES))
            .andExpect(jsonPath("$.silverPieces").value(DEFAULT_SILVER_PIECES))
            .andExpect(jsonPath("$.electrumPieces").value(DEFAULT_ELECTRUM_PIECES))
            .andExpect(jsonPath("$.goldPieces").value(DEFAULT_GOLD_PIECES))
            .andExpect(jsonPath("$.platinumPieces").value(DEFAULT_PLATINUM_PIECES))
            .andExpect(jsonPath("$.equipment").value(DEFAULT_EQUIPMENT.toString()))
            .andExpect(jsonPath("$.featuresAndTraits").value(DEFAULT_FEATURES_AND_TRAITS.toString()))
            .andExpect(jsonPath("$.attacksAndSpellCasting").value(DEFAULT_ATTACKS_AND_SPELL_CASTING.toString()))
            .andExpect(jsonPath("$.alliesAndOrganizations").value(DEFAULT_ALLIES_AND_ORGANIZATIONS.toString()))
            .andExpect(jsonPath("$.additionalFeaturesAndTraits").value(DEFAULT_ADDITIONAL_FEATURES_AND_TRAITS.toString()))
            .andExpect(jsonPath("$.treasure").value(DEFAULT_TREASURE.toString()))
            .andExpect(jsonPath("$.characterBackstory").value(DEFAULT_CHARACTER_BACKSTORY.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.toString()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.toString()))
            .andExpect(jsonPath("$.eyes").value(DEFAULT_EYES.toString()))
            .andExpect(jsonPath("$.skin").value(DEFAULT_SKIN.toString()))
            .andExpect(jsonPath("$.hair").value(DEFAULT_HAIR.toString()))
            .andExpect(jsonPath("$.spellCastingClass").value(DEFAULT_SPELL_CASTING_CLASS.toString()))
            .andExpect(jsonPath("$.spellCastingAbility").value(DEFAULT_SPELL_CASTING_ABILITY))
            .andExpect(jsonPath("$.spellSaveDC").value(DEFAULT_SPELL_SAVE_DC))
            .andExpect(jsonPath("$.spellAttackBonus").value(DEFAULT_SPELL_ATTACK_BONUS));
    }

    @Test
    @Transactional
    public void getNonExistingCharacterSheet() throws Exception {
        // Get the characterSheet
        restCharacterSheetMockMvc.perform(get("/api/character-sheets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCharacterSheet() throws Exception {
        // Initialize the database
        characterSheetRepository.saveAndFlush(characterSheet);

        int databaseSizeBeforeUpdate = characterSheetRepository.findAll().size();

        // Update the characterSheet
        CharacterSheet updatedCharacterSheet = characterSheetRepository.findById(characterSheet.getId()).get();
        // Disconnect from session so that the updates on updatedCharacterSheet are not directly saved in db
        em.detach(updatedCharacterSheet);
        updatedCharacterSheet
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .searchKeywords(UPDATED_SEARCH_KEYWORDS)
            .characterName(UPDATED_CHARACTER_NAME)
            .characterClass(UPDATED_CHARACTER_CLASS)
            .background(UPDATED_BACKGROUND)
            .playerName(UPDATED_PLAYER_NAME)
            .race(UPDATED_RACE)
            .alignment(UPDATED_ALIGNMENT)
            .expPoints(UPDATED_EXP_POINTS)
            .strength(UPDATED_STRENGTH)
            .dexterity(UPDATED_DEXTERITY)
            .constitution(UPDATED_CONSTITUTION)
            .intelligence(UPDATED_INTELLIGENCE)
            .wisdom(UPDATED_WISDOM)
            .charisma(UPDATED_CHARISMA)
            .inspiration(UPDATED_INSPIRATION)
            .proficiencyBonus(UPDATED_PROFICIENCY_BONUS)
            .strSavingThrow(UPDATED_STR_SAVING_THROW)
            .dexSavingThrow(UPDATED_DEX_SAVING_THROW)
            .constSavingThrow(UPDATED_CONST_SAVING_THROW)
            .intSavingThrow(UPDATED_INT_SAVING_THROW)
            .wisSavingThrow(UPDATED_WIS_SAVING_THROW)
            .charSavingThrow(UPDATED_CHAR_SAVING_THROW)
            .acrobatics(UPDATED_ACROBATICS)
            .animalHandling(UPDATED_ANIMAL_HANDLING)
            .arcana(UPDATED_ARCANA)
            .athletics(UPDATED_ATHLETICS)
            .deception(UPDATED_DECEPTION)
            .history(UPDATED_HISTORY)
            .insight(UPDATED_INSIGHT)
            .intimidation(UPDATED_INTIMIDATION)
            .investigation(UPDATED_INVESTIGATION)
            .medicine(UPDATED_MEDICINE)
            .nature(UPDATED_NATURE)
            .perception(UPDATED_PERCEPTION)
            .performance(UPDATED_PERFORMANCE)
            .persuasion(UPDATED_PERSUASION)
            .religion(UPDATED_RELIGION)
            .sleightOfHand(UPDATED_SLEIGHT_OF_HAND)
            .stealth(UPDATED_STEALTH)
            .survival(UPDATED_SURVIVAL)
            .passiveWisdomPerception(UPDATED_PASSIVE_WISDOM_PERCEPTION)
            .armorClass(UPDATED_ARMOR_CLASS)
            .initiative(UPDATED_INITIATIVE)
            .speed(UPDATED_SPEED)
            .hpMaximum(UPDATED_HP_MAXIMUM)
            .currentHP(UPDATED_CURRENT_HP)
            .temporaryHP(UPDATED_TEMPORARY_HP)
            .hitDice(UPDATED_HIT_DICE)
            .deathSaveSuccess(UPDATED_DEATH_SAVE_SUCCESS)
            .deathSaveFail(UPDATED_DEATH_SAVE_FAIL)
            .otherProficienciesAndLanguages(UPDATED_OTHER_PROFICIENCIES_AND_LANGUAGES)
            .copperPieces(UPDATED_COPPER_PIECES)
            .silverPieces(UPDATED_SILVER_PIECES)
            .electrumPieces(UPDATED_ELECTRUM_PIECES)
            .goldPieces(UPDATED_GOLD_PIECES)
            .platinumPieces(UPDATED_PLATINUM_PIECES)
            .equipment(UPDATED_EQUIPMENT)
            .featuresAndTraits(UPDATED_FEATURES_AND_TRAITS)
            .attacksAndSpellCasting(UPDATED_ATTACKS_AND_SPELL_CASTING)
            .alliesAndOrganizations(UPDATED_ALLIES_AND_ORGANIZATIONS)
            .additionalFeaturesAndTraits(UPDATED_ADDITIONAL_FEATURES_AND_TRAITS)
            .treasure(UPDATED_TREASURE)
            .characterBackstory(UPDATED_CHARACTER_BACKSTORY)
            .age(UPDATED_AGE)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .eyes(UPDATED_EYES)
            .skin(UPDATED_SKIN)
            .hair(UPDATED_HAIR)
            .spellCastingClass(UPDATED_SPELL_CASTING_CLASS)
            .spellCastingAbility(UPDATED_SPELL_CASTING_ABILITY)
            .spellSaveDC(UPDATED_SPELL_SAVE_DC)
            .spellAttackBonus(UPDATED_SPELL_ATTACK_BONUS);

        restCharacterSheetMockMvc.perform(put("/api/character-sheets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCharacterSheet)))
            .andExpect(status().isOk());

        // Validate the CharacterSheet in the database
        List<CharacterSheet> characterSheetList = characterSheetRepository.findAll();
        assertThat(characterSheetList).hasSize(databaseSizeBeforeUpdate);
        CharacterSheet testCharacterSheet = characterSheetList.get(characterSheetList.size() - 1);
        assertThat(testCharacterSheet.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCharacterSheet.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCharacterSheet.getSearchKeywords()).isEqualTo(UPDATED_SEARCH_KEYWORDS);
        assertThat(testCharacterSheet.getCharacterName()).isEqualTo(UPDATED_CHARACTER_NAME);
        assertThat(testCharacterSheet.getCharacterClass()).isEqualTo(UPDATED_CHARACTER_CLASS);
        assertThat(testCharacterSheet.getBackground()).isEqualTo(UPDATED_BACKGROUND);
        assertThat(testCharacterSheet.getPlayerName()).isEqualTo(UPDATED_PLAYER_NAME);
        assertThat(testCharacterSheet.getRace()).isEqualTo(UPDATED_RACE);
        assertThat(testCharacterSheet.getAlignment()).isEqualTo(UPDATED_ALIGNMENT);
        assertThat(testCharacterSheet.getExpPoints()).isEqualTo(UPDATED_EXP_POINTS);
        assertThat(testCharacterSheet.getStrength()).isEqualTo(UPDATED_STRENGTH);
        assertThat(testCharacterSheet.getDexterity()).isEqualTo(UPDATED_DEXTERITY);
        assertThat(testCharacterSheet.getConstitution()).isEqualTo(UPDATED_CONSTITUTION);
        assertThat(testCharacterSheet.getIntelligence()).isEqualTo(UPDATED_INTELLIGENCE);
        assertThat(testCharacterSheet.getWisdom()).isEqualTo(UPDATED_WISDOM);
        assertThat(testCharacterSheet.getCharisma()).isEqualTo(UPDATED_CHARISMA);
        assertThat(testCharacterSheet.getInspiration()).isEqualTo(UPDATED_INSPIRATION);
        assertThat(testCharacterSheet.getProficiencyBonus()).isEqualTo(UPDATED_PROFICIENCY_BONUS);
        assertThat(testCharacterSheet.getStrSavingThrow()).isEqualTo(UPDATED_STR_SAVING_THROW);
        assertThat(testCharacterSheet.getDexSavingThrow()).isEqualTo(UPDATED_DEX_SAVING_THROW);
        assertThat(testCharacterSheet.getConstSavingThrow()).isEqualTo(UPDATED_CONST_SAVING_THROW);
        assertThat(testCharacterSheet.getIntSavingThrow()).isEqualTo(UPDATED_INT_SAVING_THROW);
        assertThat(testCharacterSheet.getWisSavingThrow()).isEqualTo(UPDATED_WIS_SAVING_THROW);
        assertThat(testCharacterSheet.getCharSavingThrow()).isEqualTo(UPDATED_CHAR_SAVING_THROW);
        assertThat(testCharacterSheet.getAcrobatics()).isEqualTo(UPDATED_ACROBATICS);
        assertThat(testCharacterSheet.getAnimalHandling()).isEqualTo(UPDATED_ANIMAL_HANDLING);
        assertThat(testCharacterSheet.getArcana()).isEqualTo(UPDATED_ARCANA);
        assertThat(testCharacterSheet.getAthletics()).isEqualTo(UPDATED_ATHLETICS);
        assertThat(testCharacterSheet.getDeception()).isEqualTo(UPDATED_DECEPTION);
        assertThat(testCharacterSheet.getHistory()).isEqualTo(UPDATED_HISTORY);
        assertThat(testCharacterSheet.getInsight()).isEqualTo(UPDATED_INSIGHT);
        assertThat(testCharacterSheet.getIntimidation()).isEqualTo(UPDATED_INTIMIDATION);
        assertThat(testCharacterSheet.getInvestigation()).isEqualTo(UPDATED_INVESTIGATION);
        assertThat(testCharacterSheet.getMedicine()).isEqualTo(UPDATED_MEDICINE);
        assertThat(testCharacterSheet.getNature()).isEqualTo(UPDATED_NATURE);
        assertThat(testCharacterSheet.getPerception()).isEqualTo(UPDATED_PERCEPTION);
        assertThat(testCharacterSheet.getPerformance()).isEqualTo(UPDATED_PERFORMANCE);
        assertThat(testCharacterSheet.getPersuasion()).isEqualTo(UPDATED_PERSUASION);
        assertThat(testCharacterSheet.getReligion()).isEqualTo(UPDATED_RELIGION);
        assertThat(testCharacterSheet.getSleightOfHand()).isEqualTo(UPDATED_SLEIGHT_OF_HAND);
        assertThat(testCharacterSheet.getStealth()).isEqualTo(UPDATED_STEALTH);
        assertThat(testCharacterSheet.getSurvival()).isEqualTo(UPDATED_SURVIVAL);
        assertThat(testCharacterSheet.getPassiveWisdomPerception()).isEqualTo(UPDATED_PASSIVE_WISDOM_PERCEPTION);
        assertThat(testCharacterSheet.getArmorClass()).isEqualTo(UPDATED_ARMOR_CLASS);
        assertThat(testCharacterSheet.getInitiative()).isEqualTo(UPDATED_INITIATIVE);
        assertThat(testCharacterSheet.getSpeed()).isEqualTo(UPDATED_SPEED);
        assertThat(testCharacterSheet.getHpMaximum()).isEqualTo(UPDATED_HP_MAXIMUM);
        assertThat(testCharacterSheet.getCurrentHP()).isEqualTo(UPDATED_CURRENT_HP);
        assertThat(testCharacterSheet.getTemporaryHP()).isEqualTo(UPDATED_TEMPORARY_HP);
        assertThat(testCharacterSheet.getHitDice()).isEqualTo(UPDATED_HIT_DICE);
        assertThat(testCharacterSheet.getDeathSaveSuccess()).isEqualTo(UPDATED_DEATH_SAVE_SUCCESS);
        assertThat(testCharacterSheet.getDeathSaveFail()).isEqualTo(UPDATED_DEATH_SAVE_FAIL);
        assertThat(testCharacterSheet.getOtherProficienciesAndLanguages()).isEqualTo(UPDATED_OTHER_PROFICIENCIES_AND_LANGUAGES);
        assertThat(testCharacterSheet.getCopperPieces()).isEqualTo(UPDATED_COPPER_PIECES);
        assertThat(testCharacterSheet.getSilverPieces()).isEqualTo(UPDATED_SILVER_PIECES);
        assertThat(testCharacterSheet.getElectrumPieces()).isEqualTo(UPDATED_ELECTRUM_PIECES);
        assertThat(testCharacterSheet.getGoldPieces()).isEqualTo(UPDATED_GOLD_PIECES);
        assertThat(testCharacterSheet.getPlatinumPieces()).isEqualTo(UPDATED_PLATINUM_PIECES);
        assertThat(testCharacterSheet.getEquipment()).isEqualTo(UPDATED_EQUIPMENT);
        assertThat(testCharacterSheet.getFeaturesAndTraits()).isEqualTo(UPDATED_FEATURES_AND_TRAITS);
        assertThat(testCharacterSheet.getAttacksAndSpellCasting()).isEqualTo(UPDATED_ATTACKS_AND_SPELL_CASTING);
        assertThat(testCharacterSheet.getAlliesAndOrganizations()).isEqualTo(UPDATED_ALLIES_AND_ORGANIZATIONS);
        assertThat(testCharacterSheet.getAdditionalFeaturesAndTraits()).isEqualTo(UPDATED_ADDITIONAL_FEATURES_AND_TRAITS);
        assertThat(testCharacterSheet.getTreasure()).isEqualTo(UPDATED_TREASURE);
        assertThat(testCharacterSheet.getCharacterBackstory()).isEqualTo(UPDATED_CHARACTER_BACKSTORY);
        assertThat(testCharacterSheet.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testCharacterSheet.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testCharacterSheet.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testCharacterSheet.getEyes()).isEqualTo(UPDATED_EYES);
        assertThat(testCharacterSheet.getSkin()).isEqualTo(UPDATED_SKIN);
        assertThat(testCharacterSheet.getHair()).isEqualTo(UPDATED_HAIR);
        assertThat(testCharacterSheet.getSpellCastingClass()).isEqualTo(UPDATED_SPELL_CASTING_CLASS);
        assertThat(testCharacterSheet.getSpellCastingAbility()).isEqualTo(UPDATED_SPELL_CASTING_ABILITY);
        assertThat(testCharacterSheet.getSpellSaveDC()).isEqualTo(UPDATED_SPELL_SAVE_DC);
        assertThat(testCharacterSheet.getSpellAttackBonus()).isEqualTo(UPDATED_SPELL_ATTACK_BONUS);

        // Validate the CharacterSheet in Elasticsearch
        verify(mockCharacterSheetSearchRepository, times(1)).save(testCharacterSheet);
    }

    @Test
    @Transactional
    public void updateNonExistingCharacterSheet() throws Exception {
        int databaseSizeBeforeUpdate = characterSheetRepository.findAll().size();

        // Create the CharacterSheet

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCharacterSheetMockMvc.perform(put("/api/character-sheets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(characterSheet)))
            .andExpect(status().isBadRequest());

        // Validate the CharacterSheet in the database
        List<CharacterSheet> characterSheetList = characterSheetRepository.findAll();
        assertThat(characterSheetList).hasSize(databaseSizeBeforeUpdate);

        // Validate the CharacterSheet in Elasticsearch
        verify(mockCharacterSheetSearchRepository, times(0)).save(characterSheet);
    }

    @Test
    @Transactional
    public void deleteCharacterSheet() throws Exception {
        // Initialize the database
        characterSheetRepository.saveAndFlush(characterSheet);

        int databaseSizeBeforeDelete = characterSheetRepository.findAll().size();

        // Delete the characterSheet
        restCharacterSheetMockMvc.perform(delete("/api/character-sheets/{id}", characterSheet.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CharacterSheet> characterSheetList = characterSheetRepository.findAll();
        assertThat(characterSheetList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the CharacterSheet in Elasticsearch
        verify(mockCharacterSheetSearchRepository, times(1)).deleteById(characterSheet.getId());
    }

    @Test
    @Transactional
    public void searchCharacterSheet() throws Exception {
        // Initialize the database
        characterSheetRepository.saveAndFlush(characterSheet);
        when(mockCharacterSheetSearchRepository.search(queryStringQuery("id:" + characterSheet.getId())))
            .thenReturn(Collections.singletonList(characterSheet));
        // Search the characterSheet
        restCharacterSheetMockMvc.perform(get("/api/_search/character-sheets?query=id:" + characterSheet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(characterSheet.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].searchKeywords").value(hasItem(DEFAULT_SEARCH_KEYWORDS)))
            .andExpect(jsonPath("$.[*].characterName").value(hasItem(DEFAULT_CHARACTER_NAME)))
            .andExpect(jsonPath("$.[*].characterClass").value(hasItem(DEFAULT_CHARACTER_CLASS.toString())))
            .andExpect(jsonPath("$.[*].background").value(hasItem(DEFAULT_BACKGROUND.toString())))
            .andExpect(jsonPath("$.[*].playerName").value(hasItem(DEFAULT_PLAYER_NAME)))
            .andExpect(jsonPath("$.[*].race").value(hasItem(DEFAULT_RACE.toString())))
            .andExpect(jsonPath("$.[*].alignment").value(hasItem(DEFAULT_ALIGNMENT.toString())))
            .andExpect(jsonPath("$.[*].expPoints").value(hasItem(DEFAULT_EXP_POINTS)))
            .andExpect(jsonPath("$.[*].strength").value(hasItem(DEFAULT_STRENGTH)))
            .andExpect(jsonPath("$.[*].dexterity").value(hasItem(DEFAULT_DEXTERITY)))
            .andExpect(jsonPath("$.[*].constitution").value(hasItem(DEFAULT_CONSTITUTION)))
            .andExpect(jsonPath("$.[*].intelligence").value(hasItem(DEFAULT_INTELLIGENCE)))
            .andExpect(jsonPath("$.[*].wisdom").value(hasItem(DEFAULT_WISDOM)))
            .andExpect(jsonPath("$.[*].charisma").value(hasItem(DEFAULT_CHARISMA)))
            .andExpect(jsonPath("$.[*].inspiration").value(hasItem(DEFAULT_INSPIRATION)))
            .andExpect(jsonPath("$.[*].proficiencyBonus").value(hasItem(DEFAULT_PROFICIENCY_BONUS)))
            .andExpect(jsonPath("$.[*].strSavingThrow").value(hasItem(DEFAULT_STR_SAVING_THROW)))
            .andExpect(jsonPath("$.[*].dexSavingThrow").value(hasItem(DEFAULT_DEX_SAVING_THROW)))
            .andExpect(jsonPath("$.[*].constSavingThrow").value(hasItem(DEFAULT_CONST_SAVING_THROW)))
            .andExpect(jsonPath("$.[*].intSavingThrow").value(hasItem(DEFAULT_INT_SAVING_THROW)))
            .andExpect(jsonPath("$.[*].wisSavingThrow").value(hasItem(DEFAULT_WIS_SAVING_THROW)))
            .andExpect(jsonPath("$.[*].charSavingThrow").value(hasItem(DEFAULT_CHAR_SAVING_THROW)))
            .andExpect(jsonPath("$.[*].acrobatics").value(hasItem(DEFAULT_ACROBATICS)))
            .andExpect(jsonPath("$.[*].animalHandling").value(hasItem(DEFAULT_ANIMAL_HANDLING)))
            .andExpect(jsonPath("$.[*].arcana").value(hasItem(DEFAULT_ARCANA)))
            .andExpect(jsonPath("$.[*].athletics").value(hasItem(DEFAULT_ATHLETICS)))
            .andExpect(jsonPath("$.[*].deception").value(hasItem(DEFAULT_DECEPTION)))
            .andExpect(jsonPath("$.[*].history").value(hasItem(DEFAULT_HISTORY)))
            .andExpect(jsonPath("$.[*].insight").value(hasItem(DEFAULT_INSIGHT)))
            .andExpect(jsonPath("$.[*].intimidation").value(hasItem(DEFAULT_INTIMIDATION)))
            .andExpect(jsonPath("$.[*].investigation").value(hasItem(DEFAULT_INVESTIGATION)))
            .andExpect(jsonPath("$.[*].medicine").value(hasItem(DEFAULT_MEDICINE)))
            .andExpect(jsonPath("$.[*].nature").value(hasItem(DEFAULT_NATURE)))
            .andExpect(jsonPath("$.[*].perception").value(hasItem(DEFAULT_PERCEPTION)))
            .andExpect(jsonPath("$.[*].performance").value(hasItem(DEFAULT_PERFORMANCE)))
            .andExpect(jsonPath("$.[*].persuasion").value(hasItem(DEFAULT_PERSUASION)))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION)))
            .andExpect(jsonPath("$.[*].sleightOfHand").value(hasItem(DEFAULT_SLEIGHT_OF_HAND)))
            .andExpect(jsonPath("$.[*].stealth").value(hasItem(DEFAULT_STEALTH)))
            .andExpect(jsonPath("$.[*].survival").value(hasItem(DEFAULT_SURVIVAL)))
            .andExpect(jsonPath("$.[*].passiveWisdomPerception").value(hasItem(DEFAULT_PASSIVE_WISDOM_PERCEPTION)))
            .andExpect(jsonPath("$.[*].armorClass").value(hasItem(DEFAULT_ARMOR_CLASS)))
            .andExpect(jsonPath("$.[*].initiative").value(hasItem(DEFAULT_INITIATIVE)))
            .andExpect(jsonPath("$.[*].speed").value(hasItem(DEFAULT_SPEED)))
            .andExpect(jsonPath("$.[*].hpMaximum").value(hasItem(DEFAULT_HP_MAXIMUM)))
            .andExpect(jsonPath("$.[*].currentHP").value(hasItem(DEFAULT_CURRENT_HP)))
            .andExpect(jsonPath("$.[*].temporaryHP").value(hasItem(DEFAULT_TEMPORARY_HP)))
            .andExpect(jsonPath("$.[*].hitDice").value(hasItem(DEFAULT_HIT_DICE.toString())))
            .andExpect(jsonPath("$.[*].deathSaveSuccess").value(hasItem(DEFAULT_DEATH_SAVE_SUCCESS.toString())))
            .andExpect(jsonPath("$.[*].deathSaveFail").value(hasItem(DEFAULT_DEATH_SAVE_FAIL.toString())))
            .andExpect(jsonPath("$.[*].otherProficienciesAndLanguages").value(hasItem(DEFAULT_OTHER_PROFICIENCIES_AND_LANGUAGES)))
            .andExpect(jsonPath("$.[*].copperPieces").value(hasItem(DEFAULT_COPPER_PIECES)))
            .andExpect(jsonPath("$.[*].silverPieces").value(hasItem(DEFAULT_SILVER_PIECES)))
            .andExpect(jsonPath("$.[*].electrumPieces").value(hasItem(DEFAULT_ELECTRUM_PIECES)))
            .andExpect(jsonPath("$.[*].goldPieces").value(hasItem(DEFAULT_GOLD_PIECES)))
            .andExpect(jsonPath("$.[*].platinumPieces").value(hasItem(DEFAULT_PLATINUM_PIECES)))
            .andExpect(jsonPath("$.[*].equipment").value(hasItem(DEFAULT_EQUIPMENT)))
            .andExpect(jsonPath("$.[*].featuresAndTraits").value(hasItem(DEFAULT_FEATURES_AND_TRAITS)))
            .andExpect(jsonPath("$.[*].attacksAndSpellCasting").value(hasItem(DEFAULT_ATTACKS_AND_SPELL_CASTING)))
            .andExpect(jsonPath("$.[*].alliesAndOrganizations").value(hasItem(DEFAULT_ALLIES_AND_ORGANIZATIONS)))
            .andExpect(jsonPath("$.[*].additionalFeaturesAndTraits").value(hasItem(DEFAULT_ADDITIONAL_FEATURES_AND_TRAITS)))
            .andExpect(jsonPath("$.[*].treasure").value(hasItem(DEFAULT_TREASURE)))
            .andExpect(jsonPath("$.[*].characterBackstory").value(hasItem(DEFAULT_CHARACTER_BACKSTORY)))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].eyes").value(hasItem(DEFAULT_EYES)))
            .andExpect(jsonPath("$.[*].skin").value(hasItem(DEFAULT_SKIN)))
            .andExpect(jsonPath("$.[*].hair").value(hasItem(DEFAULT_HAIR)))
            .andExpect(jsonPath("$.[*].spellCastingClass").value(hasItem(DEFAULT_SPELL_CASTING_CLASS)))
            .andExpect(jsonPath("$.[*].spellCastingAbility").value(hasItem(DEFAULT_SPELL_CASTING_ABILITY)))
            .andExpect(jsonPath("$.[*].spellSaveDC").value(hasItem(DEFAULT_SPELL_SAVE_DC)))
            .andExpect(jsonPath("$.[*].spellAttackBonus").value(hasItem(DEFAULT_SPELL_ATTACK_BONUS)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CharacterSheet.class);
        CharacterSheet characterSheet1 = new CharacterSheet();
        characterSheet1.setId(1L);
        CharacterSheet characterSheet2 = new CharacterSheet();
        characterSheet2.setId(characterSheet1.getId());
        assertThat(characterSheet1).isEqualTo(characterSheet2);
        characterSheet2.setId(2L);
        assertThat(characterSheet1).isNotEqualTo(characterSheet2);
        characterSheet1.setId(null);
        assertThat(characterSheet1).isNotEqualTo(characterSheet2);
    }
}
