package com.csi3370.dnd.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of SpellBookSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class SpellBookSearchRepositoryMockConfiguration {

    @MockBean
    private SpellBookSearchRepository mockSpellBookSearchRepository;

}
