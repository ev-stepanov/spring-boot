package ru.bell.practice.springboot.response.view;

/**
 * View-обертка для сообщения об успешном совершении операции
 */
public class SuccessResponseView {

    /**
     * Поле, содержащее информацию об успешном выполнении операции
     */
    private String result;

    /**
     * Конструктор
     *
     * @param result успешность операции
     */
    public SuccessResponseView(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}