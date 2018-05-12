public class Entry<K, V> {
    public K key;
    public V value;

    Entry<K,V> parent;
    Entry<K,V> left;
    Entry<K,V> right;

    //Number of nodes to each side of the node
    public int numLeft;
    public int numRight;

    public Entry(){
        numLeft = 0;
        numRight = 0;
        parent = null;
        left = null;
        right = null;
    }

    //Contructor which sets a key and value
    public Entry(K key, V value) {
        this();
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Entry) {
            Entry other = (Entry)o;
            return this.key.equals(other.key) && this.value.equals(other.value);
        }
        return false;
    }

    public boolean hasOneChildren(){
        return  (left !=null && right ==null) || (left==null && right !=null);
    }
    public boolean hasTwoChildren(){
        return left.key!=null && right.key!=null;
    }
}