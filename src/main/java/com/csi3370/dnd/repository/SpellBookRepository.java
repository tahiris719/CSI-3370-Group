package com.csi3370.dnd.repository;

import com.csi3370.dnd.domain.SpellBook;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the SpellBook entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpellBookRepository extends JpaRepository<SpellBook, Long> {
	List<SpellBook> findByUserIsCurrentUser();
}
