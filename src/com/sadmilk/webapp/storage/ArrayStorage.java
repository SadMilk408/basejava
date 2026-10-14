package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        boolean foundResume = false;

        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid) || foundResume) {
                foundResume = true;

                if (storage[i] != null) {
                    storage[i] = storage[i + 1];
                }
            }
        }
        if (foundResume) {
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] filledStorage = new Resume[size];

        System.arraycopy(storage, 0, filledStorage, 0, filledStorage.length);

        return filledStorage;
    }

    public int size() {
        return size;
    }
}
