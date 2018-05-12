
import java.nio.file.attribute.AclEntryType;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class SortedTreeMap<K extends Comparable<? super K>, V> implements ISortedTreeMap<K, V> {
    private Entry<K,V> nil = new Entry<K,V>();
    private Entry<K,V> root = nil;


    public  SortedTreeMap(Comparator <K> kComparator){



    }

    public SortedTreeMap() {
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }


    @Override
    public Entry<K, V> min() {
        Entry<K,V> min = root;
        Entry<K,V> curr = root;
        while(curr.left!=nil){
            min = root.left;
            curr = curr.left;
        }
        return min;
    }

    @Override
    public Entry<K, V> min(Entry<K, V> rootNode) {
        Entry<K,V> min = rootNode;
        while(rootNode.left!=nil){
            min = rootNode.left;
            rootNode = rootNode.left;
        }
        return min;
    }

    /**
     * Finds the maximum entry (by key) in the map, if no key is found returns
     * null instead.
     *
     * @return maximum value
     */
    @Override
    public Entry<K, V> max() {
        return null;
    }

    /**
     * Inserts the specified value with the specified key as a new entry into the map.
     * If a value is already present for that key, return the previous value, else null.
     *
     * @param key   The key to be inserted
     * @param value The value to be inserted
     * @return Previous value
     */
    @Override
    public V add(K key, V value) {

        Entry<K,V> n = new Entry<K,V>(key,value);
        /**
         * Creates a reference to the root and initializes a nil node.
         */
        Entry<K,V> y = nil;
        Entry<K,V> x = root;
        while(!isNil(x)){
            y = x;
            if(n.key.compareTo(x.key) > 0){
                System.out.println(n.key + " is greater than " + x.key +"...Going right");
                x.numRight++;
                x=x.right;
            }
            else if(n.key.compareTo(x.key) < 0){
                System.out.println(n.key + " is lower than " + x.key +"...Going left");
                x.numLeft++;
                x = x.left;
            }
            else if(n.key.compareTo(x.key)== 0){
                System.out.println(n.key + " already in tree, overwriting.. ");
                System.out.println("Old value for key "+ x.key + " was " + x.value + ". New value is " + n.value);
                V old = x.value;
                x.value = n.value;
                return old;

            }

        }
        n.parent = y;

        // Bestemmer om node n skal være et child av y på høyre eller venstre side
        if(isNil(y)){
            root = n;
        }
        else if(n.key.compareTo(y.key)< 0){
            System.out.println("Setting n as left because "+n.key + " is less than " + y.key);
            y.left = n;
        }
        else {
            System.out.println("Setting n as right because "+n.key + " is greater than " + y.key);
            y.right = n;
        }

        // Setter children av n til nil, og setter fargen til n som rød
        n.left = nil;
        n.right = nil;

        return null;
    }

    /**
     * checks if a node is a nil-node
     * @param node node to check
     * @return true/false if nil/!nil
     */


    /**
     * Inserts the specified entry into the map. If the key is already a part of the map,
     * return the previous value, else null.
     *
     * @param entry The new entry to be inserted into the map
     * @return Previous value
     */
    @Override
    public V add(Entry<K, V> entry) {
        return add(entry.key, entry.value);
    }

    public boolean isNil(Entry<K,V> node){
        return node == nil;
    }

    /**
     * Replaces the value for key in the map as long as it is already present. If they key
     * is not present, the method throws an exception.
     *
     * @param key   The key for which the value is replaced
     * @param value The new value
     * @throws NoSuchElementException When key is not in map
     */
    @Override
    public void replace(K key, V value) throws NoSuchElementException {

    }

    /**
     * Applies a function to the value at key and replaces that value. Throws an exception
     * if the key is not present in the map.
     *
     * @param key The key for which we are replacing the value
     * @param f   The function to apply to the value
     * @throws NoSuchElementException When key is not in map
     */
    @Override
    public void replace(K key, BiFunction<K, V, V> f) throws NoSuchElementException {

    }

    /**
     * Removes the entry for key in the map. Throws an exception if the key is not present
     * in the map.
     *
     * @param key The key for the entry to remove
     * @return The removed value
     * @throws NoSuchElementException When key is not in map.
     */
    @Override
    public V remove(Object key) throws NoSuchElementException {
        if (containsKey((K)key)){

            Entry<K,V> nodeToRemove = findNodeToRemoveByKey(key);
            if(nodeToRemove!=null) {
                //Node has one child
                if (nodeToRemove.hasOneChildren()) {
                    System.out.println("HAS ONE CHILD");
                    if (nodeToRemove.parent.right == nodeToRemove) {
                        nodeToRemove.parent.right = nodeToRemove.right;
                    } else {
                        nodeToRemove.parent.left = nodeToRemove.left;
                    }

                }
                // Node has two children
                else if (nodeToRemove.hasTwoChildren()) {
                    System.out.println("HAS TWOO CHILDREN");
                    Entry<K,V> min = min(nodeToRemove.right);

                }
                // Node has no children
                else {
                    System.out.println("HAS NO CHILDREN");
                    if (nodeToRemove.parent.right == nodeToRemove) {
                        nodeToRemove.parent.right = nil;
                        return nodeToRemove.value;
                    } else {
                        nodeToRemove.parent.left = nil;
                        return nodeToRemove.value;
                    }
                }
            }
        }
            return null;
    }


    /**
     * Finds node to remove by key, and reduces the size of the tree.
     */
    public Entry<K,V> findNodeToRemoveByKey(Object key){
        K newKey = (K)key;
        Entry<K,V> y = nil;
        Entry<K,V> x = root;
        while(!isNil(x)){
            y = x;
            if(newKey.compareTo(x.key) > 0){
                x.numRight--;
                x=x.right;

            }
            else if(newKey.compareTo(x.key) < 0){
                x.numLeft--;
                x = x.left;
            }
            else if(newKey.compareTo(x.key)== 0){
                return x;
            }
        }
        return null;
    }

    /**
     * Finds node by key
     */
    public Entry<K,V> findNodeByKey(K key){
        Entry<K,V> x = root;
        while(!isNil(x)){
            if(key.compareTo(x.key) > 0){
                x = x.right;

            }
            else if(key.compareTo(x.key) < 0){
                x = x.left;
            }
            else if(key.compareTo(x.key)== 0){
                return x;
            }
        }
        return null;
    }


    /**
     * Retrieves the value for the key in the map.
     *
     * @param key The key for the value to retrieve
     * @return The value for the key
     * @throws NoSuchElementException When key is not in map
     */
    @Override
    public V getValue(Object key) throws NoSuchElementException {
        return null;
    }

    /**
     * Checks if a key is in the map.
     *
     * @param key The key to check
     * @return true if the key is in the map, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        K newKey = (K)key;
        Entry<K,V> y = nil;
        Entry<K,V> x = root;
        while(!isNil(x)){
            y = x;
            if(newKey.compareTo(x.key) > 0){
                x = x.right;
            }
            else if(newKey.compareTo(x.key) < 0){
                x = x.left;
            }
            else if(newKey.compareTo(x.key)== 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a value is in the map
     *
     * @param value the value to look for
     * @return True if the value is present, false otherwise
     */
    @Override
    public boolean containsValue(V value) {
        return false;
    }

    /**
     * Finds all the keys in the map and returns them in order.
     *
     * @return keys in order
     */
    @Override
    public Iterable<K> keys() {
        return null;
    }

    /**
     * Finds the values in order of the keys.
     *
     * @return values in order of the keys
     */
    @Override
    public Iterable<V> values() {
        return null;
    }

    /**
     * Finds all entries in the map in order of the keys.
     *
     * @return All entries in order of the keys
     */
    @Override
    public Iterable<Entry<K, V>> entries() {
        return null;
    }

    /**
     * Finds the entry for the key, if the key is not in the map returns the next
     * highest entry if such an entry exists
     *
     * @param key The key to find
     * @return The entry for the key or the next highest
     */
    @Override
    public Entry<K, V> higherOrEqualEntry(K key) {
        return null;
    }

    /**
     * Finds the entry for the key, if the key is not in the map, returns the next
     * lower entry if such an entry exists
     *
     * @param key The key to find
     * @return The entry for the key or the next lower
     */
    @Override
    public Entry<K, V> lowerOrEqualEntry(K key) {
        return null;
    }

    /**
     * Adds all entries in the other map into the current map. If a key is present
     * in both maps, the key in the other map takes precedent.
     *
     * @param other The map to add to the current map.
     */
    @Override
    public void merge(ISortedTreeMap<K, V> other) {

    }

    /**
     * Removes any entry for which the predicate holds true. The predicate can
     * trigger on both the key and value of each entry.
     *
     * @param p The predicate that tests which entries should be kept.
     */
    @Override
    public void removeIf(BiPredicate<K, V> p) {

    }

    /**
     * Checks if the map is empty
     *
     * @return True if the map is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return root == nil;
    }

    /**
     * Returns the number of entries in the map
     *
     * @return Number of entries
     */
    @Override
    public int size() {
        return root.numRight + root.numRight +1;
    }

    /**
     * Clears the map of entries.
     */
    @Override
    public void clear() {

    }
}