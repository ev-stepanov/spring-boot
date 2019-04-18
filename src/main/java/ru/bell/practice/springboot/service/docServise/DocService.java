package ru.bell.practice.springboot.service.docServise;

import ru.bell.practice.springboot.view.docView.DocView;

import java.util.List;

public interface DocService {
    List<DocView> list();
}
