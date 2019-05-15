package ru.bell.practice.springboot.view.organizationView;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class OrganizationUpdateView {
    @NotNull
    private Long id;

    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Zа-яА-Я \\d*]{1,50}")
    private String name;

    @NotNull
    @Size(max = 255)
    @Pattern(regexp = "[a-zA-Zа-яА-Я0-9 ]{1,255}")
    private String fullName;

    @NotNull
    @Size(max = 10)
    @Pattern(regexp = "[0-9]{1,10}")
    private String inn;

    @NotNull
    @Size(max = 10)
    @Pattern(regexp = "[0-9]{1,10}")
    private String kpp;

    @NotNull
    @Size(max = 255)
    @Pattern(regexp = "[a-zA-Zа-яА-Я0-9 ,./-]{1,255}")
    private String address;

    private String phone;

    private Boolean isActive;

    public OrganizationUpdateView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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