package ru.bell.practice.springboot.service.docServise;

import ru.bell.practice.springboot.view.docView.DocView;

import java.util.List;

/**
 * Сервис для работы с удовстверениями
 */
public interface DocService {

    /**
     * Возвращает список документов и их кодов.
     * @return список документов и их кодов
     */
    List<DocView> list();
}
