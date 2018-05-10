public class RedBlackTree<T extends Comparable<T>> {
    private RedBlackNode<T> nil = new RedBlackNode<T>();
    private RedBlackNode<T> root = nil;


    public RedBlackTree(){
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }

}
