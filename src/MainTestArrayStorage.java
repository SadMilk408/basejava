import com.sadmilk.webapp.model.Resume;
import com.sadmilk.webapp.storage.SortedArrayStorage;
import com.sadmilk.webapp.storage.Storage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new SortedArrayStorage();
    static final Storage ARRAY_SORTED_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.uuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll(ARRAY_STORAGE);
        ARRAY_STORAGE.delete(r1.uuid());
        printAll(ARRAY_STORAGE);
        ARRAY_STORAGE.clear();
        printAll(ARRAY_STORAGE);

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll(Storage s) {
        System.out.println("\nGet All");
        for (Resume r : s.getAll()) {
            System.out.println(r);
        }
    }
}
