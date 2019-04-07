package com.csi3370.dnd.repository;

import com.csi3370.dnd.domain.CharacterSheet;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the CharacterSheet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CharacterSheetRepository extends JpaRepository<CharacterSheet, Long> {
	List<CharacterSheet> findByUserIsCurrentUser();
}
