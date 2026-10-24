package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.exception.ExistStorageException;
import com.sadmilk.webapp.exception.NotExistStorageException;
import com.sadmilk.webapp.exception.StorageException;
import com.sadmilk.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] expected = {
                new Resume(UUID_1),
                new Resume(UUID_2),
                new Resume(UUID_3)
        };

        Assert.assertArrayEquals(expected, storage.getAll());
        Assert.assertEquals(3, storage.getAll().length);
    }

    @Test
    public void save() {
        storage.save(new Resume(UUID_4));

        Assert.assertEquals(4, storage.size());

        Assert.assertThrows(
                ExistStorageException.class,
                () -> storage.save(new Resume(UUID_4))
        );
    }

    @Test
    public void saveOverflow() {
        for (var i = 0; i < 9997; i++) {
            try {
                storage.save(new Resume());
            } catch (Exception e) {
                Assert.fail("Overflow array before check");
            }
        }
        Assert.assertEquals(10000, storage.size());
        Assert.assertThrows(
                StorageException.class,
                () -> storage.save(new Resume())
        );
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
        Assert.assertThrows(
                NotExistStorageException.class,
                () -> storage.get(UUID_4)
        );
    }

    @Test
    public void update() {
        storage.update(new Resume(UUID_1));
        Assert.assertThrows(
                NotExistStorageException.class,
                () -> storage.get(UUID_4)
        );
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        Assert.assertThrows(
                NotExistStorageException.class,
                () -> storage.get(UUID_1)
        );
        Assert.assertEquals(new Resume(UUID_2), storage.get(UUID_2));
        Assert.assertEquals(new Resume(UUID_3), storage.get(UUID_3));
    }
}
