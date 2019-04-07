package com.csi3370.dnd.repository.search;

import com.csi3370.dnd.domain.Spell;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Spell entity.
 */
public interface SpellSearchRepository extends ElasticsearchRepository<Spell, Long> {
}
