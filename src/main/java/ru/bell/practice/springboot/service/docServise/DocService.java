package ru.bell.practice.springboot.service.docServise;

import ru.bell.practice.springboot.view.docView.DocView;

import java.util.List;

/**
 * Интерфейс сервиса удовстверений
 */
public interface DocService {

    /**
     * Возвращает список документов и их кодов.
     * @return список документов и их кодов
     */
    List<DocView> list();
}
