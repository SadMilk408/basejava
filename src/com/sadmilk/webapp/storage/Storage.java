package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.model.Resume;

public interface Storage {
    int size();

    void clear();

    void save(Resume r);

    Resume get(String uuid);

    Resume[] getAll();

    void update(Resume r);

    void delete(String uuid);
}
