package ru.bell.practice.springboot.exception;

/**
 * Исключение генерируется в случае отсутствия искомой записи в базе данных
 */
public class RecordNotFoundException extends RuntimeException {

    /**
     * Конструктор
     *
     * @param message сообщение с описанием исключительной ситуации
     */
    public RecordNotFoundException(String message) {
        super(message);
    }
}
