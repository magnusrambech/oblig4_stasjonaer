
import java.nio.file.attribute.AclEntryType;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class SortedTreeMap<K extends Comparable<? super K>, V> implements ISortedTreeMap<K, V> {
    private Entry<K,V> nil = new Entry<K,V>();
    private Entry<K,V> root = nil;
    private Comparator<K> kComparator;
    private ArrayList<K> keys = new ArrayList<K>();

    public  SortedTreeMap(Comparator <K> kComparator){
        this.kComparator = kComparator;
    }

    public SortedTreeMap() {
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }


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
                System.out.println(n.key + " is greater than " + x.key +"...Going right");
                x=x.right;
            }
            else if(n.key.compareTo(x.key) < 0){
                System.out.println(n.key + " is lower than " + x.key +"...Going left");
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
            System.out.println("Setting " + n.key +" as root");
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
        if(root == nil){
            throw new NoSuchElementException();
        }
        if(containsKey(key)){
            findNodeByKey(key).value = value;
        }
        else{
            throw new NoSuchElementException();
        }
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
        //Sjekker om objektet finnes
        if (containsKey((K)key)){
            Entry<K,V> nodeToRemove = findNodeByKey((K)key);
            System.out.println("REMOVING " + nodeToRemove.key);
            System.out.println("VALUE: " + nodeToRemove.value);

            //Node has one child
            if (nodeToRemove.hasOneChildren(nil)) {
                System.out.print("HAS ONE CHILD, ");
                //If the child is right
                if (nodeToRemove.right != nil) {
                    System.out.println(nodeToRemove.right.key + " . Child is right node");
                    if(nodeToRemove == root){
                        root = nodeToRemove.right;
                        root.parent = nil;
                        return nodeToRemove.value;
                    }

                    //If the node to remove is the left node of its parent
                    if(nodeToRemove.parent.left == nodeToRemove){
                        nodeToRemove.parent.left = nodeToRemove.right;
                    }
                    else{
                        nodeToRemove.parent.right = nodeToRemove.right;
                    }
                }
                //If the child is left
                else if(nodeToRemove.left != nil) {
                    System.out.println("child is left node");

                    if(nodeToRemove==root){
                        root = nodeToRemove.left;
                        root.parent = nil;
                        return nodeToRemove.value;
                    }
                    //If the node to remove is the left node of its parent
                    if(nodeToRemove.parent.left == nodeToRemove){
                        nodeToRemove.parent.left = nodeToRemove.left;
                    }
                    else{
                        nodeToRemove.parent.right = nodeToRemove.left;
                    }
                }

            }


            // Node has two children
            else if (nodeToRemove.hasTwoChildren(nil)){
                Entry<K,V> min = min(nodeToRemove.right);
                System.out.println("HAS TWOO CHILDREN: " + nodeToRemove.left.key +", " + nodeToRemove.right.key );
                System.out.println("MIN IN RIGHT SUB TREE: " + min.key);
                // If the lowest node in right sub tree is the node we're removing's right child
                if(nodeToRemove==root){
                    System.out.println("PRØVER Å FJERNE ROOT");
                    System.out.println(min.key + " SKAL TA OVER!");
                    if(min == nodeToRemove.right){
                        min.left = root.left;
                        root.left.parent = min;
                        root=min;
                    }
                    else{
                        if(min.hasOneChildren(nil)){
                            if(min.right!=nil){
                                System.out.println(min.key + " har 1 child på høyre side, " + min.right.key);
                                System.out.println("MIN sin parent er " + min.parent.key);
                                min.right.parent = min.parent;
                                min.parent.left = min.right;
                                min.right = root.right;
                                min.left=root.left;
                                root = min;
                                return nodeToRemove.value;
                            }
                            else{
                                System.out.println(min.key + " har 1 child på venstre side");
                                min.left.parent = min.parent;
                                min.parent.left = min.left;
                                min.right = root.right;
                                min.left = root.left;
                                root=min;
                                return nodeToRemove.value;
                            }

                        }
                        min.left = root.left;
                        min.right = root.right;
                        root.left.parent = min;
                        root.right.parent = min;
                        root = min;
                        min.parent.left = nil;
                    }

                }
                else if(min == nodeToRemove.right){
                   nodeToRemove.left.parent = min;
                   min.parent = nodeToRemove.parent;
                   min.left = nodeToRemove.left;

                   //If the node we're removing was the left child
                    if (nodeToRemove.parent.left == nodeToRemove) {
                        nodeToRemove.parent.left = min;
                    }
                    //If the node we're removing was the left child
                    else if (nodeToRemove.parent.right == nodeToRemove) {
                        nodeToRemove.parent.right = min;
                    }


                }
                else if(min==nodeToRemove.left){
                    //Sets both children parent as the new node, which was the lowest in right sub tree.
                    nodeToRemove.right.parent = min;
                    nodeToRemove.left.parent = min;
                        //If the node we're removing was the left child
                        if (nodeToRemove.parent.left == nodeToRemove) {
                            nodeToRemove.parent.left = min;
                        }
                        //If the node we're removing was the left child
                        else if (nodeToRemove.parent.right == nodeToRemove) {
                            nodeToRemove.parent.right = min;
                        }
                        min.parent.left = nil;
                        min.parent = nodeToRemove.parent;
                        min.left = nodeToRemove.left;
                        min.right = nodeToRemove.right;


                }
                else{
                    if(min.hasOneChildren(nil)){
                        if(min.right!=nil){
                            System.out.println(min.key + " har 1 child på høyre side, " + min.right.key);
                            System.out.println("MIN sin parent er " + min.parent.key);
                            min.right.parent = min.parent;
                            min.parent.left = min.right;
                            min.left = nodeToRemove.left;
                            min.right = nodeToRemove.right;
                            //If the node we're removing was the left child
                            if (nodeToRemove.parent.left == nodeToRemove) {
                                nodeToRemove.parent.left = min;
                            }
                            //If the node we're removing was the left child
                            else if (nodeToRemove.parent.right == nodeToRemove) {
                                nodeToRemove.parent.right = min;
                            }
                            nodeToRemove.parent.right = min;
                            nodeToRemove.left.parent = min;
                            nodeToRemove.right.parent = min;

                            V old = nodeToRemove.value;
                            nodeToRemove = min;
                            return old;
                        }
                        // Vil aldri hende
                        else{
                            System.out.println(min.key + " har 1 child på venstre side");
                            min.left.parent = min.parent;
                            min.parent.left = min.left;
                            min.left = nodeToRemove.left;
                            min.right = nodeToRemove.right;
                            min.parent = nodeToRemove.parent;

                            V old = nodeToRemove.value;
                            nodeToRemove = min;
                            return old;
                        }

                    }
                    min.parent.left = nil;
                    min.parent = nodeToRemove.parent;
                    if (nodeToRemove.parent.left == nodeToRemove) {
                        nodeToRemove.parent.left = min;
                    }
                    //If the node we're removing was the left child
                    else if (nodeToRemove.parent.right == nodeToRemove) {
                        nodeToRemove.parent.right = min;
                    }
                    min.right = nodeToRemove.right;
                    min.left = nodeToRemove.left;
                    min.right.parent = min;




                }

            }
            // Node has no children
            else {
                System.out.println("HAS NO CHILDREN");
                if(nodeToRemove==root){
                   root = nil;
                   root.key = null;
                   root.value = null;
                   root.left = nil;
                   root.right = nil;
                }
                else{
                    if (nodeToRemove.parent.right == nodeToRemove) {
                        nodeToRemove.parent.right = nil;

                    } else {
                        nodeToRemove.parent.left = nil;

                    }
                }

            }
            return nodeToRemove.value;
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
        // if it's null, it doesn't exist, return 0
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
        System.out.println("looking for higher or equal to " + key);
        ArrayList<K> keys = (ArrayList<K>) keys();

        for(K currKey : keys){
            if(currKey.compareTo(key) == 0){
                System.out.println("key finnes! ");
                return findNodeByKey(currKey);

            }
            else if(currKey.compareTo(key) < 0){

            }
            else {
                System.out.println("fant høyere!" + currKey + " er høyere enn " + key);
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
        System.out.println("looking for lower or equal to " + key);
        ArrayList<K> keys = (ArrayList<K>) keys();
        Collections.reverse(keys);

        for(K currKey : keys){
            if(currKey.compareTo(key) == 0){
                System.out.println("key finnes! ");
                return findNodeByKey(currKey);

            }
            else if(currKey.compareTo(key) < 0){
                System.out.println(currKey + " er lavere enn " + key);
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

    @Override
    public Entry<K, V> getRoot() {
        return root;
    }
}