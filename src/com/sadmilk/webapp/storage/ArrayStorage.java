package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        var i = getElementPos(r.uuid());
        if (i != -1) {
            storage[i] = r;
        } else {
            System.out.println("Storage doesn't exist element with uuid: " + r.uuid());
        }
    }

    public void save(Resume r) {
        var elementPos = getElementPos(r.uuid());
        if (elementPos != -1) {
            System.out.println("Storage is already exist element with uuid: " + r.uuid());
            return;
        }
        if (size >= 10000) {
            System.out.println("Storage is already full! " + r.uuid() + "doesn't added");
            return;
        }
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        var elementPos = getElementPos(uuid);
        if (elementPos == -1) {
            System.out.println("Storage doesn't exist element with uuid: " + uuid);
            return null;
        }
        return storage[elementPos];
    }

    public void delete(String uuid) {
        var elementPos = getElementPos(uuid);
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

    private int getElementPos(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
