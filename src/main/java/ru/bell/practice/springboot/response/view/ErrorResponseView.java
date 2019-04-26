package ru.bell.practice.springboot.response.view;

public class ErrorResponseView {
    private String error;

    public ErrorResponseView(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
