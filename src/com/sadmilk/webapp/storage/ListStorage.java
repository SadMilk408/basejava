package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    protected List<Resume> arrayList = new ArrayList<>();

    @Override
    protected void doSave(Resume r, Integer key) {
        arrayList.add(r);
    }

    @Override
    protected Resume doGet(Integer key) {
        return arrayList.get(key);
    }

    @Override
    protected void doUpdate(Resume r, Integer key) {
        arrayList.set(key, r);
    }

    @Override
    protected void doDelete(Integer key) {
        arrayList.remove((key).intValue());
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).uuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
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
    public List<Resume> doCopyAll() {
        return new ArrayList<>(arrayList);
    }
}
