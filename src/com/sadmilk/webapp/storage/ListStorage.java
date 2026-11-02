package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> arrayList = new ArrayList<>();

    @Override
    protected void doSave(Resume r, Object key) {
        arrayList.add(r);
    }

    @Override
    protected Resume doGet(Object key) {
        return arrayList.get((int) key);
    }

    @Override
    protected void doUpdate(Resume r, Object key) {
        arrayList.set((int) key, r);
    }

    @Override
    protected void doDelete(Object key) {
        arrayList.remove(((Integer) key).intValue());
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).uuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    public void clear() {
        arrayList.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        arrayList.sort(RESUME_COMPARATOR);
        return arrayList;
    }
}
