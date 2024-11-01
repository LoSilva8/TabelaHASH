public class AdvancedHashTable extends AbstractHashTable {
    @Override
    protected int hash(String key) {
        long hash = 5381;
        for (char c : key.toCharArray()) {
            hash = ((hash << 5) + hash) + c;
        }
        return Math.abs((int)(hash % TABLE_SIZE));
    }
}
