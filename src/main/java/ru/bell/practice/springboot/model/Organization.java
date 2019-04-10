package ru.bell.practice.springboot.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Компания
 */
@Entity(name = "Organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Название компании
     */
    @Column(length = 50, nullable = false)
    private String name;

    /**
     * Полное название компании
     */
    @Column(name = "full_name", nullable = false)
    private String fullName;

    /**
     * ИНН
     */
    @Column(length = 10, nullable = false)
    private String inn;

    /**
     * КПП
     */
    @Column(length = 10, nullable = false)
    private String kpp;

    /**
     * Адрес компании
     */
    @Column(nullable = false)
    private String address;

    /**
     * Телефон
     */
    @Column(length = 12)
    private String phone;

    /**
     * Действует организация?
     */
    @Column(name = "is_active")
    private Boolean isActive = true;

    public Organization() {

    }

    public Organization(String name, String fullName, String inn, String kpp, String address) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.version = 0;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}