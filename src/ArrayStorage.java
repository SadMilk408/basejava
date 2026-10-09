/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        boolean foundResume = false;

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid) || foundResume) {
                foundResume = true;

                if (storage[i] != null) {
                    storage[i] = storage[i + 1];
                }
            }
        }
        if (foundResume) {
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] filledStorage = new Resume[size];

        System.arraycopy(storage, 0, filledStorage, 0, filledStorage.length);

        return filledStorage;
    }

    int size() {
        return size;
    }
}
