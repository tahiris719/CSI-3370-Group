package com.csi3370.dnd.repository.search;

import com.csi3370.dnd.domain.SpellBook;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SpellBook entity.
 */
public interface SpellBookSearchRepository extends ElasticsearchRepository<SpellBook, Long> {
}
