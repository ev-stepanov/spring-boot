package ru.bell.practice.springboot.view.docView;

public class DocView {
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DocView{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
