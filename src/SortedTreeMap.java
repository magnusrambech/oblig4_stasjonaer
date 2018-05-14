
import java.nio.file.attribute.AclEntryType;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class SortedTreeMap<K extends Comparable<? super K>, V> implements ISortedTreeMap<K, V> {
    private Entry<K,V> nil = new Entry<K,V>();
    private Entry<K,V> root = nil;
    private ArrayList<K> keys = new ArrayList<K>();

    public  SortedTreeMap(Comparator <K> kComparator){
        Comparator<K> kComparator1 = kComparator;
    }

    /**
     * Constructor
     */
    public SortedTreeMap() {
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }

    /**
     * Finds the minimum entry (by key) in the map, if no entry is found, returns
     * null instead.
     * @return minimum entry
     */
    @Override
    public Entry<K, V> min() {
        if(root == nil){
            return null;
        }
        Entry<K,V> min = root;
        while(min.left!=nil){
            min = min.left;
        }
        return min;
    }

    /**
     * Finds the minimum entry (by key) in the map from a given node, if no entry is found, returns
     * null instead.
     * @return minimum entry
     */
    @Override
    public Entry<K, V> min(Entry<K, V> rootNode) {
        Entry<K,V> min = rootNode;
        if(rootNode.left == null){
            return min;
        }
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
        if(root == nil){
            return null;
        }
        Entry<K,V> max = root;
        while (max.right != nil ){
            max = max.right;
        }
        return max;
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
                x=x.right;
            }
            else if(n.key.compareTo(x.key) < 0){
                x = x.left;
            }
            else if(n.key.compareTo(x.key)== 0){
                V old = x.value;
                x.value = n.value;
                return old;

            }

        }
        n.parent = y;

        //Decides if the node n should be on the left/right side of y.
        if(isNil(y)){
            root = n;
        }
        else if(n.key.compareTo(y.key)< 0){
            y.left = n;
        }
        else {
            y.right = n;
        }

        //Sets the children of n to nil
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

        if (!containsKey(key))
        {
            throw new NoSuchElementException();
        }
            findNodeByKey(key).value = value;
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
        if (!containsKey(key))
        {
            throw new NoSuchElementException();
        }
        else {
            findNodeByKey(key).value = f.apply(findNodeByKey(key).key,findNodeByKey(key).value);
        }

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
        V result = getValue(key);
        root = removeEntry((K) key, root);
        return result;
    }

    /**
     *
     * @param key
     * @param toRemove
     * @return toRemove
     */
    private Entry<K,V> removeEntry(K key, Entry<K, V> toRemove){
        if(toRemove == nil) {
            return null;
        }

        int comparison = key.compareTo(toRemove.key);

        if(comparison < 0){
            toRemove.left = (removeEntry(key, toRemove.left));
        }
        else if(comparison > 0){
            toRemove.right = removeEntry(key, toRemove.right);
        }
        else {
            if(toRemove.left == nil){
                return toRemove.right;
            }
            else if(toRemove.right == nil){
                return toRemove.left;
            }

            toRemove.key = min(toRemove.right).key;
            toRemove.value = min(toRemove.right).value;
            toRemove.right = (removeEntry(toRemove.key, toRemove.right));
        }
        return toRemove;
    }


    /**
     * Finds node by key
     * @param key key of node to find
     * @return node if it exists in tree, null otherwise
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
        if (containsKey((K) key)){
            return findNodeByKey((K) key).value;
        }
        throw new NoSuchElementException();
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
    public boolean containsValue(V value){
        ArrayList<K> keys = (ArrayList<K>) keys();
        for(K key : keys){
            if(findNodeByKey(key).value == value){
                return true;
            }
        }
        return false;
    }

    /**
     * Finds all the keys in the map and returns them in order.
     *
     * @return keys in order
     */
    @Override
    public Iterable<K> keys() {
        keys.clear();
        ArrayList<K> sorted = gatherKeysFromNode(root);
        if(sorted!=null){
            Collections.sort(sorted);
            return sorted;
        }
        sorted = new ArrayList<K>();
        return sorted;
    }

    public ArrayList<K> gatherKeysFromNode(Entry<K,V> current){
        if (current == nil){
            return null;
        }
        keys.add(current.key);
        if(current.right != nil){
            gatherKeysFromNode(current.right);
        }
        if(current.left != nil){
            gatherKeysFromNode(current.left);
        }

        return keys;
    }

    /**
     * Finds the values in order of the keys.
     *
     * @return values in order of the keys
     */
    @Override
    public Iterable<V> values() {
        // Refreshes list of keys
        keys();
        ArrayList<V> values = new ArrayList<V>();
        for(K key : keys){
            values.add(findNodeByKey(key).value);
        }
        return values;
    }

    /**
     * Finds all entries in the map in order of the keys.
     *
     * @return All entries in order of the keys
     */
    @Override
    public Iterable<Entry<K, V>> entries() {
        ArrayList<Entry<K,V>> entries = new ArrayList<>();
        ArrayList<K> keys = (ArrayList<K>) keys();
        for(K key : keys){
            entries.add(new Entry<K,V>(key, findNodeByKey(key).value));
        }
        return entries;
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
        ArrayList<K> keys = (ArrayList<K>) keys();

        for(K currKey : keys){
            if(currKey.compareTo(key) == 0){
                return findNodeByKey(currKey);

            }
            else if(currKey.compareTo(key) < 0){

            }
            else {

                return findNodeByKey(currKey);
            }
        }
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
        ArrayList<K> keys = (ArrayList<K>) keys();
        Collections.reverse(keys);

        for(K currKey : keys){
            if(currKey.compareTo(key) == 0){
                return findNodeByKey(currKey);

            }
            else if(currKey.compareTo(key) < 0){
                return findNodeByKey(currKey);
            }
            else {

            }
        }
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
        SortedTreeMap<K,V> otherTree = (SortedTreeMap<K,V>) other;
        for(Entry<K,V> entry : otherTree.entries()){
            if(entry != null || entry != nil){
                add(entry);
            }
        }



    }

    /**
     * Removes any entry for which the predicate holds true. The predicate can
     * trigger on both the key and value of each entry.
     *
     * @param p The predicate that tests which entries should be kept.
     */
    @Override
    public void removeIf(BiPredicate<K, V> p) {
        ArrayList<Entry<K, V>> entries = (ArrayList<Entry<K, V>>) entries();

        for (Entry<K,V> entry : entries){
            if(p.test(entry.key, entry.value)){
                remove(entry.key);
            }
        }
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
        return nodes(root);
    }

    /**
     * Recursive method that counts all nodes in tree.
     * @param current
     * @return
     */
    private int nodes(Entry<K,V> current) {
        // if it's null, it doesn't exist, return 0
        if (current == nil){
            return 0;
        }
        int c = 1;
        if(current.right != nil){
            c += nodes(current.right);
        }
        if(current.left != nil){
            c += nodes(current.left);
        }
        return c;

    }


    /**
     * Clears the map of entries.
     */
    @Override
    public void clear() {
        root=nil;
    }

    /**
     * Returns rootnode
     * @return root
     */
    @Override
    public Entry<K, V> getRoot() {
        return root;
    }


}