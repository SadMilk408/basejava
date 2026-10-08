/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
            if (storage[i].uuid.equals(r.uuid)) {
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume == null) {
                break;
            }
            if (resume.uuid.equals(uuid)) {
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        boolean findElement = false;

        for (int i = 0; i < storage.length; i++) {
            if(storage[i] == null){
                break;
            }
            if (storage[i].uuid.equals(uuid) || findElement) {
                findElement = true;

                if (storage[i] != null) {
                    if(i + 1 < storage.length){
                        storage[i] = storage[i + 1];

                        if (storage[i + 1] == null) {
                            break;
                        }
                    } else {
                        storage[i] = null;
                    }
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] filledStorage = new Resume[size()];

        System.arraycopy(storage, 0, filledStorage, 0, filledStorage.length);

        return filledStorage;
    }

    int size() {
        int size = 0;

        for (Resume resume : storage) {
            if (resume == null) {
                break;
            }
            size += 1;
        }

        return size;
    }
}
