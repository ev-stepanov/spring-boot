package ru.bell.practice.springboot.response.view;

/**
 * View-обертка для отправляемых в ответе данных
 */
public class DataResponseView {

    /**
     * Поле, содержащие данные ответа
     */
    private Object data;

    /**
     * Конструктор
     *
     * @param data данные
     */
    public DataResponseView(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
