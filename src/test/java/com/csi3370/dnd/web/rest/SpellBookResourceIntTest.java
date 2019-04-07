package com.csi3370.dnd.web.rest;

import com.csi3370.dnd.DungeonsAndDatabasesApp;

import com.csi3370.dnd.domain.SpellBook;
import com.csi3370.dnd.repository.SpellBookRepository;
import com.csi3370.dnd.repository.search.SpellBookSearchRepository;
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

/**
 * Test class for the SpellBookResource REST controller.
 *
 * @see SpellBookResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DungeonsAndDatabasesApp.class)
public class SpellBookResourceIntTest {

    private static final Integer DEFAULT_MAX_SLOTS = 1;
    private static final Integer UPDATED_MAX_SLOTS = 2;

    private static final Integer DEFAULT_CURRENT_SLOTS = 1;
    private static final Integer UPDATED_CURRENT_SLOTS = 2;

    @Autowired
    private SpellBookRepository spellBookRepository;

    /**
     * This repository is mocked in the com.csi3370.dnd.repository.search test package.
     *
     * @see com.csi3370.dnd.repository.search.SpellBookSearchRepositoryMockConfiguration
     */
    @Autowired
    private SpellBookSearchRepository mockSpellBookSearchRepository;

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

    private MockMvc restSpellBookMockMvc;

    private SpellBook spellBook;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SpellBookResource spellBookResource = new SpellBookResource(spellBookRepository, mockSpellBookSearchRepository);
        this.restSpellBookMockMvc = MockMvcBuilders.standaloneSetup(spellBookResource)
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
    public static SpellBook createEntity(EntityManager em) {
        SpellBook spellBook = new SpellBook()
            .maxSlots(DEFAULT_MAX_SLOTS)
            .currentSlots(DEFAULT_CURRENT_SLOTS);
        return spellBook;
    }

    @Before
    public void initTest() {
        spellBook = createEntity(em);
    }

    @Test
    @Transactional
    public void createSpellBook() throws Exception {
        int databaseSizeBeforeCreate = spellBookRepository.findAll().size();

        // Create the SpellBook
        restSpellBookMockMvc.perform(post("/api/spell-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(spellBook)))
            .andExpect(status().isCreated());

        // Validate the SpellBook in the database
        List<SpellBook> spellBookList = spellBookRepository.findAll();
        assertThat(spellBookList).hasSize(databaseSizeBeforeCreate + 1);
        SpellBook testSpellBook = spellBookList.get(spellBookList.size() - 1);
        assertThat(testSpellBook.getMaxSlots()).isEqualTo(DEFAULT_MAX_SLOTS);
        assertThat(testSpellBook.getCurrentSlots()).isEqualTo(DEFAULT_CURRENT_SLOTS);

        // Validate the SpellBook in Elasticsearch
        verify(mockSpellBookSearchRepository, times(1)).save(testSpellBook);
    }

    @Test
    @Transactional
    public void createSpellBookWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = spellBookRepository.findAll().size();

        // Create the SpellBook with an existing ID
        spellBook.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpellBookMockMvc.perform(post("/api/spell-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(spellBook)))
            .andExpect(status().isBadRequest());

        // Validate the SpellBook in the database
        List<SpellBook> spellBookList = spellBookRepository.findAll();
        assertThat(spellBookList).hasSize(databaseSizeBeforeCreate);

        // Validate the SpellBook in Elasticsearch
        verify(mockSpellBookSearchRepository, times(0)).save(spellBook);
    }

    @Test
    @Transactional
    public void getAllSpellBooks() throws Exception {
        // Initialize the database
        spellBookRepository.saveAndFlush(spellBook);

        // Get all the spellBookList
        restSpellBookMockMvc.perform(get("/api/spell-books?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(spellBook.getId().intValue())))
            .andExpect(jsonPath("$.[*].maxSlots").value(hasItem(DEFAULT_MAX_SLOTS)))
            .andExpect(jsonPath("$.[*].currentSlots").value(hasItem(DEFAULT_CURRENT_SLOTS)));
    }
    
    @Test
    @Transactional
    public void getSpellBook() throws Exception {
        // Initialize the database
        spellBookRepository.saveAndFlush(spellBook);

        // Get the spellBook
        restSpellBookMockMvc.perform(get("/api/spell-books/{id}", spellBook.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(spellBook.getId().intValue()))
            .andExpect(jsonPath("$.maxSlots").value(DEFAULT_MAX_SLOTS))
            .andExpect(jsonPath("$.currentSlots").value(DEFAULT_CURRENT_SLOTS));
    }

    @Test
    @Transactional
    public void getNonExistingSpellBook() throws Exception {
        // Get the spellBook
        restSpellBookMockMvc.perform(get("/api/spell-books/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSpellBook() throws Exception {
        // Initialize the database
        spellBookRepository.saveAndFlush(spellBook);

        int databaseSizeBeforeUpdate = spellBookRepository.findAll().size();

        // Update the spellBook
        SpellBook updatedSpellBook = spellBookRepository.findById(spellBook.getId()).get();
        // Disconnect from session so that the updates on updatedSpellBook are not directly saved in db
        em.detach(updatedSpellBook);
        updatedSpellBook
            .maxSlots(UPDATED_MAX_SLOTS)
            .currentSlots(UPDATED_CURRENT_SLOTS);

        restSpellBookMockMvc.perform(put("/api/spell-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSpellBook)))
            .andExpect(status().isOk());

        // Validate the SpellBook in the database
        List<SpellBook> spellBookList = spellBookRepository.findAll();
        assertThat(spellBookList).hasSize(databaseSizeBeforeUpdate);
        SpellBook testSpellBook = spellBookList.get(spellBookList.size() - 1);
        assertThat(testSpellBook.getMaxSlots()).isEqualTo(UPDATED_MAX_SLOTS);
        assertThat(testSpellBook.getCurrentSlots()).isEqualTo(UPDATED_CURRENT_SLOTS);

        // Validate the SpellBook in Elasticsearch
        verify(mockSpellBookSearchRepository, times(1)).save(testSpellBook);
    }

    @Test
    @Transactional
    public void updateNonExistingSpellBook() throws Exception {
        int databaseSizeBeforeUpdate = spellBookRepository.findAll().size();

        // Create the SpellBook

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpellBookMockMvc.perform(put("/api/spell-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(spellBook)))
            .andExpect(status().isBadRequest());

        // Validate the SpellBook in the database
        List<SpellBook> spellBookList = spellBookRepository.findAll();
        assertThat(spellBookList).hasSize(databaseSizeBeforeUpdate);

        // Validate the SpellBook in Elasticsearch
        verify(mockSpellBookSearchRepository, times(0)).save(spellBook);
    }

    @Test
    @Transactional
    public void deleteSpellBook() throws Exception {
        // Initialize the database
        spellBookRepository.saveAndFlush(spellBook);

        int databaseSizeBeforeDelete = spellBookRepository.findAll().size();

        // Delete the spellBook
        restSpellBookMockMvc.perform(delete("/api/spell-books/{id}", spellBook.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SpellBook> spellBookList = spellBookRepository.findAll();
        assertThat(spellBookList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the SpellBook in Elasticsearch
        verify(mockSpellBookSearchRepository, times(1)).deleteById(spellBook.getId());
    }

    @Test
    @Transactional
    public void searchSpellBook() throws Exception {
        // Initialize the database
        spellBookRepository.saveAndFlush(spellBook);
        when(mockSpellBookSearchRepository.search(queryStringQuery("id:" + spellBook.getId())))
            .thenReturn(Collections.singletonList(spellBook));
        // Search the spellBook
        restSpellBookMockMvc.perform(get("/api/_search/spell-books?query=id:" + spellBook.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(spellBook.getId().intValue())))
            .andExpect(jsonPath("$.[*].maxSlots").value(hasItem(DEFAULT_MAX_SLOTS)))
            .andExpect(jsonPath("$.[*].currentSlots").value(hasItem(DEFAULT_CURRENT_SLOTS)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SpellBook.class);
        SpellBook spellBook1 = new SpellBook();
        spellBook1.setId(1L);
        SpellBook spellBook2 = new SpellBook();
        spellBook2.setId(spellBook1.getId());
        assertThat(spellBook1).isEqualTo(spellBook2);
        spellBook2.setId(2L);
        assertThat(spellBook1).isNotEqualTo(spellBook2);
        spellBook1.setId(null);
        assertThat(spellBook1).isNotEqualTo(spellBook2);
    }
}
