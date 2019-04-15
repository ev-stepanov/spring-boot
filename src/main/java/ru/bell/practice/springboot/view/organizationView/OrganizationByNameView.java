package ru.bell.practice.springboot.view.organizationView;

import javax.validation.constraints.NotNull;

public class OrganizationByNameView {

    private  Long id;

    @NotNull(message = "name cannot be null")
    private String name;

    private String inn;

    private Boolean isActive;

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

    @Override
    public String toString() {
        return "OrganizationByNameView{" +
                "name='" + name + '\'' +
                ", inn='" + inn + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
