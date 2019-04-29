package ru.bell.practice.springboot.response.view;

/**
 * View-обертка для сообщения об ошибке при совершении операции
 */
public class ErrorResponseView {

    /**
     * Сообщение об ошибке
     */
    private String error;

    /**
     * Конструктор
     *
     * @param error сообщение об ошибке
     */
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
