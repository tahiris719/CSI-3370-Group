package com.csi3370.dnd.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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

    @Column(name = "max_slots")
    private Integer maxSlots;

    @Column(name = "current_slots")
    private Integer currentSlots;

    @OneToMany(mappedBy = "spell")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Spell> books = new HashSet<>();
    @OneToOne(mappedBy = "character")
    @JsonIgnore
    private CharacterSheet book;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaxSlots() {
        return maxSlots;
    }

    public SpellBook maxSlots(Integer maxSlots) {
        this.maxSlots = maxSlots;
        return this;
    }

    public void setMaxSlots(Integer maxSlots) {
        this.maxSlots = maxSlots;
    }

    public Integer getCurrentSlots() {
        return currentSlots;
    }

    public SpellBook currentSlots(Integer currentSlots) {
        this.currentSlots = currentSlots;
        return this;
    }

    public void setCurrentSlots(Integer currentSlots) {
        this.currentSlots = currentSlots;
    }

    public Set<Spell> getBooks() {
        return books;
    }

    public SpellBook books(Set<Spell> spells) {
        this.books = spells;
        return this;
    }

    public SpellBook addBook(Spell spell) {
        this.books.add(spell);
        spell.setSpell(this);
        return this;
    }

    public SpellBook removeBook(Spell spell) {
        this.books.remove(spell);
        spell.setSpell(null);
        return this;
    }

    public void setBooks(Set<Spell> spells) {
        this.books = spells;
    }

    public CharacterSheet getBook() {
        return book;
    }

    public SpellBook book(CharacterSheet characterSheet) {
        this.book = characterSheet;
        return this;
    }

    public void setBook(CharacterSheet characterSheet) {
        this.book = characterSheet;
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
            ", maxSlots=" + getMaxSlots() +
            ", currentSlots=" + getCurrentSlots() +
            "}";
    }
}
