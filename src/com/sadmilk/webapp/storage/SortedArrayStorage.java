package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insertElement(Resume r, int tempIndex) {
        var pos = -tempIndex - 1;

        System.arraycopy(storage, pos, storage, pos + 1, size - pos);

        storage[pos] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }
}
