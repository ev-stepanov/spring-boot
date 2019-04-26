package ru.bell.practice.springboot.response.view;

public class SuccessResponseView {
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public SuccessResponseView(Boolean success) {
        this.success = success;
    }
}