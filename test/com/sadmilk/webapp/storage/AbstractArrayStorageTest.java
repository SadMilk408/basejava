package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.exception.StorageException;
import com.sadmilk.webapp.model.Resume;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void saveOverflow() {
        for (var i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
            try {
                storage.save(new Resume("Name 1"));
            } catch (StorageException e) {
                fail("Overflow array before check");
            }
        }
        assertEquals(AbstractArrayStorage.STORAGE_LIMIT, storage.size());
        assertThrows(
                StorageException.class,
                () -> storage.save(new Resume("Overflow"))
        );
    }
}