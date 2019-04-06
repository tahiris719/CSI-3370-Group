package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Spells;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Spells entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpellsRepository extends JpaRepository<Spells, Long> {

}
