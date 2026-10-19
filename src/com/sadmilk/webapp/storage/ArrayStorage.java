package com.sadmilk.webapp.storage;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected int indexForInsert(int tempIndex) {
        return size;
    }

    @Override
    public void delete(String uuid) {
        var index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Storage doesn't exist element with uuid: " + uuid);
            return;
        }

        storage[index] = storage[index + 1];
        storage[size] = null;

        size--;
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
