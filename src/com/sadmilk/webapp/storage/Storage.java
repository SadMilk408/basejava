package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.model.Resume;

import java.util.List;

public interface Storage {
    int size();

    void clear();

    void save(Resume r);

    Resume get(String uuid);

    List<Resume> getAllSorted();

    void update(Resume r);

    void delete(String uuid);
}
