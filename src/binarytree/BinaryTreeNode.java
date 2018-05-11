package binarytree;

public class BinaryTreeNode<T extends Comparable<T>> {

    //Key for node
    public T key;

    //Linked nodes
    BinaryTreeNode<T> parent;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;

    //Number of nodes to each side of the node
    public int numLeft;
    public int numRight;

    public BinaryTreeNode(){
        numLeft = 0;
        numRight = 0;
        parent = null;
        left = null;
        right = null;
    }

    //Contructor which sets a key
    public BinaryTreeNode(T key){
        this();
        this.key = key;
    }

}
