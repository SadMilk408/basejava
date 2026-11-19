package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.exception.ExistStorageException;
import com.sadmilk.webapp.exception.NotExistStorageException;
import com.sadmilk.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {
    @Override
    public void save(Resume r) {
        SK searchKey = getNotExistedSearchKey(r.getUuid());

        doSave(r, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = getExistedSearchKey(uuid);

        return doGet(searchKey);
    }

    @Override
    public void update(Resume r) {
        SK searchKey = getExistedSearchKey(r.getUuid());

        doUpdate(r, searchKey);
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = getExistedSearchKey(uuid);

        doDelete(searchKey);
    }

    private SK getExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);

        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }

        return searchKey;
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);

        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }

        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }

    protected abstract void doSave(Resume r, SK key);

    protected abstract Resume doGet(SK key);

    protected abstract void doUpdate(Resume r, SK key);

    protected abstract void doDelete(SK key);

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract List<Resume> doCopyAll();
}
