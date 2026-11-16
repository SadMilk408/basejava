package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {
    private final Map<String, Resume> map = new HashMap<>();

    @Override
    protected void doSave(Resume r, String searchKey) {
        map.put(searchKey, r);
    }

    @Override
    protected Resume doGet(String searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, String searchKey) {
        map.put(searchKey, r);
    }

    @Override
    protected void doDelete(String searchKey) {
        map.remove(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }
}
