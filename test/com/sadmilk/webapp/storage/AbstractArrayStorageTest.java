package com.sadmilk.webapp.storage;

import com.sadmilk.webapp.exception.ExistStorageException;
import com.sadmilk.webapp.exception.NotExistStorageException;
import com.sadmilk.webapp.exception.StorageException;
import com.sadmilk.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());

        Resume[] actual = storage.getAll();
        assertEquals(0, actual.length);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void getAll() {
        Resume[] actual = storage.getAll();

        assertEquals(3, actual.length);

        List<Resume> resumes = Arrays.asList(actual);

        assertTrue(resumes.contains(RESUME_1));
        assertTrue(resumes.contains(RESUME_2));
        assertTrue(resumes.contains(RESUME_3));
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    public void saveExist() throws Exception {
        assertThrows(
                ExistStorageException.class,
                () -> storage.save(RESUME_1)
        );
    }

    @Test
    public void saveOverflow() {
        for (var i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
            try {
                storage.save(new Resume());
            } catch (StorageException e) {
                fail("Overflow array before check");
            }
        }
        assertSize(AbstractArrayStorage.STORAGE_LIMIT);
        assertThrows(
                StorageException.class,
                () -> storage.save(new Resume())
        );
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void getNotExist() {
        assertThrows(
                NotExistStorageException.class,
                () -> storage.get("cringe")
        );
    }

    @Test
    public void update() {
        Resume updated = new Resume(UUID_1);

        storage.update(updated);

        assertSame(updated, storage.get(UUID_1));
    }

    @Test
    public void updateNotExist() {
        Resume newResume = new Resume();

        assertThrows(
                NotExistStorageException.class,
                () -> storage.update(newResume)
        );
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        assertEquals(RESUME_2, storage.get(UUID_2));
        assertEquals(RESUME_3, storage.get(UUID_3));
        assertThrows(
                NotExistStorageException.class,
                () -> storage.delete(UUID_1)
        );
    }

    @Test
    public void deleteNotExist() {
        assertThrows(
                NotExistStorageException.class,
                () -> storage.delete("cringe")
        );
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.uuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}
