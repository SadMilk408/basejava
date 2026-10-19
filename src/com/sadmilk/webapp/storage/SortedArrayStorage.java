package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int indexForInsert(int tempIndex) {
        var pos = -tempIndex - 1;

        System.arraycopy(storage, pos, storage, pos + 1, size - pos);

        return pos;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }
}
