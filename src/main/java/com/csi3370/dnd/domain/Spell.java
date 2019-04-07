package com.csi3370.dnd.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Spell.
 */
@Entity
@Table(name = "spell")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "spell")
public class Spell implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book")
    private String book;

    @Column(name = "jhi_level")
    private Integer level;

    @Column(name = "school")
    private String school;

    @Column(name = "jhi_time")
    private String time;

    @Column(name = "jhi_range")
    private String range;

    @Column(name = "components")
    private String components;

    @Column(name = "duration")
    private String duration;

    @Column(name = "classes")
    private String classes;

    @Column(name = "description")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public Spell book(String book) {
        this.book = book;
        return this;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public Integer getLevel() {
        return level;
    }

    public Spell level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getSchool() {
        return school;
    }

    public Spell school(String school) {
        this.school = school;
        return this;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTime() {
        return time;
    }

    public Spell time(String time) {
        this.time = time;
        return this;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRange() {
        return range;
    }

    public Spell range(String range) {
        this.range = range;
        return this;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getComponents() {
        return components;
    }

    public Spell components(String components) {
        this.components = components;
        return this;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getDuration() {
        return duration;
    }

    public Spell duration(String duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getClasses() {
        return classes;
    }

    public Spell classes(String classes) {
        this.classes = classes;
        return this;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getDescription() {
        return description;
    }

    public Spell description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Spell spell = (Spell) o;
        if (spell.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), spell.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Spell{" +
            "id=" + getId() +
            ", book='" + getBook() + "'" +
            ", level=" + getLevel() +
            ", school='" + getSchool() + "'" +
            ", time='" + getTime() + "'" +
            ", range='" + getRange() + "'" +
            ", components='" + getComponents() + "'" +
            ", duration='" + getDuration() + "'" +
            ", classes='" + getClasses() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
