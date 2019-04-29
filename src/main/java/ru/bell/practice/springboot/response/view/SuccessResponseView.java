package ru.bell.practice.springboot.response.view;

/**
 * View-обертка для сообщения об успешном совершении операции
 */
public class SuccessResponseView {

    /**
     * Поле, содержащее информацию об успешном выполнении операции
     */
    private Boolean success;

    /**
     * Конструктор
     *
     * @param success успешность операции
     */
    public SuccessResponseView(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}