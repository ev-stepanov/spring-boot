package ru.bell.practice.springboot.model;

import javax.persistence.*;

@Entity(name = "Country")
public class Country {

    @Id
    @Column(name = "citizenship_code")
    private Long citizenshipCode;

    /**
     * Специальное поле Hibernate
     */
    @Version
    private Integer version;

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

    public Long getCitizenshipCode() {
        return citizenshipCode;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "citizenshipCode=" + citizenshipCode +
                ", citizenshipName='" + citizenshipName + '\'' +
                '}';
    }
}