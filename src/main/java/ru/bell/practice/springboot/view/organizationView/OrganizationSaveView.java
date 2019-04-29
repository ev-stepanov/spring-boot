package ru.bell.practice.springboot.view.organizationView;

import javax.validation.constraints.NotEmpty;

public class OrganizationSaveView {

    @NotEmpty(message = "name cannot be null")
    private String name;

    @NotEmpty(message = "fullName cannot be null")
    private String fullName;

    @NotEmpty(message = "inn cannot be null")
    private String inn;

    @NotEmpty(message = "kpp cannot be null")
    private String kpp;

    @NotEmpty(message = "address cannot be null")
    private String address;

    private String phone;

    private Boolean isActive;

    public OrganizationSaveView() {
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