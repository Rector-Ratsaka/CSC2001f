import java.util.List;
/**
 * Simple hash table implementation using linear probing.
 *
 * @author Stephan Jamieson
 * @version 24/4/2015
 */
public class LPHashTable extends HashTable {

    /**
     * Create an LPHashTable with DEFAULT_SIZE table.
     */
    public LPHashTable() { super(); }

    /**
     * Create an LPHashTable with the given default size table.
     */
    public LPHashTable(final int size) { super(size); }

    /**
     * Find the index for entry: if entry is in the table, then returns its position;
     * if it is not in the table then returns the index of the first free slot.
     * Returns -1 if a slot is not found (such as when the table is full under LP).
     *
     */
    protected int findIndex(String key) {
		// Implement using linear probing.
        int hash = hashFunction(key);       //ensure that the index is within the bound
        int i=0;                            // help to check if table full
        while (table[hash] != null) {
            if (table[hash].equals(key)) {
                incProbeCount();            //probe processing
                return hash;                // found key
            }
            i++;
            hash = (hash + 1)%tableSize();  // linear probing and ensure that the index is within the bound
            incProbeCount();                //probe processing
            if (i > tableSize()) {
                return -1;                  // table is full
            }
        }
        incProbeCount();                    //probe processing
        return hash;                        // free slot found
    }
}
