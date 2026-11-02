package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::uuid);

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
    protected Integer getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }
}
