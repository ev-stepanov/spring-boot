package ru.bell.practice.springboot.view.officeView;

import javax.validation.constraints.*;

public class OfficeUpdateView {

    @NotNull
    private Long id;

    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Zа-яА-Я \\d*]{1,50}")
    private String name;

    @NotNull
    @Size(max = 255)
    @Pattern(regexp = "[a-zA-Zа-яА-Я0-9 ,./-]{1,255}")
    private String address;

    @Size(min = 7, max = 12)
    @Pattern(regexp = "(\\+?)\\d{7,12}")
    private String phone;

    private Boolean isActive = true;

    public OfficeUpdateView() {
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
