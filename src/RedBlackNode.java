public class RedBlackNode<T extends Comparable<T>>{
    /**
     * Possible color outcomes for a red/black node
     */
    public static final int BLACK = 0;
    public static final int RED = 1;

    //key for noce
    public T key;

    //linked nodes
    RedBlackNode<T> parent;
    RedBlackNode<T> left;
    RedBlackNode<T> right;

    //Number of nodes to each side of the node
    public int numLeft;
    public int numRight;

    //Color of the node
    public int color;

    public RedBlackNode(){
        color = BLACK;
        numLeft = 0;
        numRight = 0;
        parent = null;
        left = null;
        right = null;
    }
    //Constructor which sets key to the node
    RedBlackNode(T key){
        this();
        this.key = key;
    }

}
