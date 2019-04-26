package ru.bell.practice.springboot.response.view;

public class DataResponseView {
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public DataResponseView(Object data) {
        this.data = data;
    }
}
