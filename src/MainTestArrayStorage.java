import com.sadmilk.webapp.model.Resume;
import com.sadmilk.webapp.storage.ArrayStorage;
import com.sadmilk.webapp.storage.SortedArrayStorage;
import com.sadmilk.webapp.storage.Storage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    public static void main(String[] args) {
        testStorage(new ArrayStorage());
        testStorage(new SortedArrayStorage());
    }

    private static void testStorage(Storage storage) {
        System.out.println("\n=== " + storage.getClass().getSimpleName() + " ===");

        Resume r1 = new Resume("uuid3");
        Resume r2 = new Resume("uuid1");
        Resume r3 = new Resume("uuid2");

        // save
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);

        check(storage.size() == 3, "size after save");
        check(storage.get("uuid1") == r2, "get existing resume");

        // duplicate save
        storage.save(new Resume("uuid1"));
        check(storage.size() == 3, "duplicate must not be saved");

        // update
        Resume updated = new Resume("uuid2");
        storage.update(updated);

        check(storage.get("uuid2") == updated, "update resume");
        check(storage.size() == 3, "size must not change after update");

        // delete from middle
        storage.delete("uuid2");

        check(storage.size() == 2, "size after delete");
        check(storage.get("uuid2") == null, "deleted resume must not exist");
        check(storage.get("uuid1") != null, "other resume must remain");
        check(storage.get("uuid3") != null, "other resume must remain");

        // getAll
        Resume[] all = storage.getAll();

        check(all.length == 2, "getAll length");

        // clear
        storage.clear();

        check(storage.size() == 0, "size after clear");
        check(storage.getAll().length == 0, "getAll after clear");

        System.out.println("All checks passed");

        testSortedStorage();
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new RuntimeException("FAILED: " + message);
        }
    }

    private static void testSortedStorage() {
        SortedArrayStorage storage = new SortedArrayStorage();

        storage.save(new Resume("C"));
        storage.save(new Resume("A"));
        storage.save(new Resume("D"));
        storage.save(new Resume("B"));

        Resume[] all = storage.getAll();

        check(all[0].uuid().equals("A"), "sorted index 0");
        check(all[1].uuid().equals("B"), "sorted index 1");
        check(all[2].uuid().equals("C"), "sorted index 2");
        check(all[3].uuid().equals("D"), "sorted index 3");

        storage.delete("B");

        all = storage.getAll();

        check(all[0].uuid().equals("A"), "sorted after delete index 0");
        check(all[1].uuid().equals("C"), "sorted after delete index 1");
        check(all[2].uuid().equals("D"), "sorted after delete index 2");
    }
}