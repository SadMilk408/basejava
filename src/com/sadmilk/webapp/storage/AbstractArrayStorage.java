package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void save(Resume r) {
        int index = getIndex(r.uuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("Storage is already full! " + r.uuid() + "doesn't added");
            return;
        }
        if (index >= 0) {
            System.out.println("Storage is already exist element with uuid: " + r.uuid());
            return;
        }
        insertElement(r, index);
        size++;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index <= -1) {
            System.out.println("Storage doesn't exist element with uuid: " + uuid);
            return null;
        }
        return storage[index];
    }

    public void update(Resume r) {
        int index = getIndex(r.uuid());
        if (index <= -1) {
            System.out.println("Storage doesn't exist element with uuid: " + r.uuid());
            return;
        }
        storage[index] = r;
    }

    public void delete(String uuid) {
        var index = getIndex(uuid);
        if (index <= -1) {
            System.out.println("Storage doesn't exist element with uuid: " + uuid);
            return;
        }
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);

    protected abstract int getIndex(String uuid);
}
