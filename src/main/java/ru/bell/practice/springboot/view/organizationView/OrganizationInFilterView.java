package ru.bell.practice.springboot.view.organizationView;

import javax.validation.constraints.*;

public class OrganizationInFilterView {

    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Zа-яА-Я \\d*]{0,50}")
    private String name;

    @Size(max = 10)
    @Pattern(regexp = "[0-9]{1,10}")
    private String inn;

    private Boolean isActive;

    public OrganizationInFilterView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}