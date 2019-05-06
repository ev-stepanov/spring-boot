package ru.bell.practice.springboot.exception;

/**
 *  Исклчение генерируется в случае отсутствия обязательных полей или их некорректном значении в запросе.
 */
public class WrongRequestException extends RuntimeException {

    /**
     * Конструктор
     *
     * @param message сообщение с описанием исключительной ситуации
     */
    public WrongRequestException(String message) {
        super(message);
    }
    public WrongRequestException(){};
}
