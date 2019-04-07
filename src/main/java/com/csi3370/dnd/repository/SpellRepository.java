package com.csi3370.dnd.repository;

import com.csi3370.dnd.domain.Spell;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Spell entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpellRepository extends JpaRepository<Spell, Long> {

}
