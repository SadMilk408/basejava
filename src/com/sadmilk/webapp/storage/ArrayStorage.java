package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    public void update(Resume r) {
        var i = getIndex(r.uuid());
        if (i != -1) {
            storage[i] = r;
        } else {
            System.out.println("Storage doesn't exist element with uuid: " + r.uuid());
        }
    }

    @Override
    protected int indexForInsert(int tempIndex) {
        return size;
    }

    @Override
    public void delete(String uuid) {
        var elementPos = getIndex(uuid);
        if (elementPos == -1) {
            System.out.println("Storage doesn't exist element with uuid: " + uuid);
            return;
        }
        boolean foundResume = false;

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid().equals(uuid) || foundResume) {
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
