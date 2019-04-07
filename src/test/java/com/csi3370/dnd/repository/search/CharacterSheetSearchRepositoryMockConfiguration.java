package com.csi3370.dnd.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of CharacterSheetSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class CharacterSheetSearchRepositoryMockConfiguration {

    @MockBean
    private CharacterSheetSearchRepository mockCharacterSheetSearchRepository;

}
