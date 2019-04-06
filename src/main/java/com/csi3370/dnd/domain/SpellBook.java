package com.csi3370.dnd.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A SpellBook.
 */
@Entity
@Table(name = "spell_book")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "spellbook")
public class SpellBook implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "max_spells")
    private Integer maxSpells;

    @Column(name = "spells")
    private String spells;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaxSpells() {
        return maxSpells;
    }

    public SpellBook maxSpells(Integer maxSpells) {
        this.maxSpells = maxSpells;
        return this;
    }

    public void setMaxSpells(Integer maxSpells) {
        this.maxSpells = maxSpells;
    }

    public String getSpells() {
        return spells;
    }

    public SpellBook spells(String spells) {
        this.spells = spells;
        return this;
    }

    public void setSpells(String spells) {
        this.spells = spells;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpellBook spellBook = (SpellBook) o;
        if (spellBook.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), spellBook.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SpellBook{" +
            "id=" + getId() +
            ", maxSpells=" + getMaxSpells() +
            ", spells='" + getSpells() + "'" +
            "}";
    }
}
