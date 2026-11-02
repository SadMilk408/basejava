package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.exception.StorageException;
import com.sadmilk.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected int size = 0;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public List<Resume> getAllSorted() {
        Resume[] res = Arrays.copyOf(storage, size);
        List<Resume> result = new ArrayList<>(Arrays.asList(res));
        
        result.sort(RESUME_COMPARATOR);
        return result;
    }

    @Override
    protected void doSave(Resume r, Object key) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage is already full!", r.uuid());
        } else {
            insertElement(r, (int) key);
            size++;
        }
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(int) index];
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage[(int) index] = r;
    }

    @Override
    protected void doDelete(Object index) {
        fillDeletedElement((int) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return getIndex(uuid);
    }

    @Override
    protected boolean isExist(Object index) {
        return (int) index >= 0;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);

    protected abstract Integer getIndex(String uuid);
}
