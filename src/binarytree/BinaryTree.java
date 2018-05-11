package binarytree;

public class BinaryTree <T extends Comparable<T>> {
    private BinaryTreeNode<T> nil = new BinaryTreeNode<T>();
    private BinaryTreeNode<T> root = nil;

    public BinaryTree(){
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }

    public void insert(BinaryTreeNode<T> n){
        /**
         * Creates a reference to the root and initializes a nil node.
         */
        BinaryTreeNode<T> y = nil;
        BinaryTreeNode<T> x = root;
        while(!isNil(x)){
            y = x;
            if(n.key.compareTo(x.key) > 0){
                System.out.println(n.key + " is greater than " + x.key +"... Going right");
                x.numRight++;
                x=x.right;
            }
            else if(n.key.compareTo(x.key) < 0){
                System.out.println(n.key + " is lower than " + x.key +"... Going left");
                x.numLeft++;
                x = x.left;
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
        n.right=nil;
    }

    public boolean isNil(BinaryTreeNode<T> node){
        return node == nil;
    }

    public BinaryTreeNode getRoot() {
        return root;
    }

    public int size() {
        return root.numRight + root.numLeft +1;
    }
}
