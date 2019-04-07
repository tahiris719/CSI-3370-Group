package com.csi3370.dnd.repository.search;

import com.csi3370.dnd.domain.CharacterSheet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CharacterSheet entity.
 */
public interface CharacterSheetSearchRepository extends ElasticsearchRepository<CharacterSheet, Long> {
}
