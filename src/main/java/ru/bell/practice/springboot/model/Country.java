package ru.bell.practice.springboot.model;

import javax.persistence.*;

@Entity(name = "Country")
public class Country {

    @Id
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
    private Long citizenshipCode;

    /**
     * Гражданство
     */
    @Column(name = "citizenship_name")
    private String citizenshipName;

    public Country() {

    }

    public Country(Long citizenshipCode, String citizenshipName) {
        this.citizenshipCode = citizenshipCode;
        this.citizenshipName = citizenshipName;
        this.version = 0;
    }

    public Long getId() {
        return id;
    }

    public Long getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Long citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", citizenshipCode=" + citizenshipCode +
                ", citizenshipName='" + citizenshipName + '\'' +
                '}';
    }
}