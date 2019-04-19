package ru.bell.practice.springboot.model;

import javax.persistence.*;

@Entity(name = "Country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    /**
     * Специальное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Код гражданства
     */
    @Column(name = "citizenship_code")
    private Long code;

    /**
     * Гражданство
     */
    @Column(name = "citizenship_name")
    private String name;

    public Country() {

    }

    public Country(Long code, String name) {
        this.code = code;
        this.name = name;
        this.version = 0;
    }

    public Long getId() {
        return id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}