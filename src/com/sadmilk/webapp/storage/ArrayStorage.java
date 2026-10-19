package com.sadmilk.webapp.storage;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getInsertIndex(int tempIndex) {
        return size;
    }

    @Override
    protected void deleteByAlgo(int index) {
        storage[index] = storage[index + 1];
        storage[index] = storage[size - 1];
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
