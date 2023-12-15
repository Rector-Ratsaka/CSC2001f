/**
 * Simple hash table implementation using quadratic probing.
 *
 * @author Stephan Jamieson
 * @version 24/4/2015
 */
public class QPHashTable  extends HashTable {


    /**
     * Create an QPHashTable with DEFAULT_SIZE table.
     */
    public QPHashTable() { super(); }

    /**
     * Create an QPHashTable with the given default size table.
     */
    public QPHashTable(final int size) { super(size); }

    /**
     * Find the index for entry: if entry is in the table, then returns its position;
     * if it is not in the table then returns the index of the first free slot.
     * Returns -1 if a slot is not found (such as when the table is full under LP).
     *
     */
    protected int findIndex(String key) {
		// Implement using quadratic probing.
        int h;                             // help to ensure my hash doesn't change
        int hash = h = hashFunction(key);  //hash for a given key
        int i = 1;                     // increment for Quadratic probing
        while (table[hash] != null) {
            if (table[hash].equals(key)) {
                incProbeCount();       //probe processing
                return hash;           // found key
            }
            hash = (h + i*i)%tableSize();   // quadratic probing and ensure that the index is within the bound
            i++;
            incProbeCount();               //probe processing
            if (i > tableSize()) {
                return -1;              // table is full
            }
        }
        incProbeCount();                //probe processing
        return hash;                    // free slot found
    }
}
