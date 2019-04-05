package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CharacterSheet;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CharacterSheet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CharacterSheetRepository extends JpaRepository<CharacterSheet, Long> {

}
