package main;

import redblacktree.RedBlackNode;
import redblacktree.RedBlackTree;

public class Main {
    public static void main(String[] args) {
        //Creates a RBT (Red black tree)
        RedBlackTree<Integer> rbtree = new RedBlackTree<Integer>();

        //Creates nodes and inserts them into the tree.
        RedBlackNode<Integer> a = new RedBlackNode<Integer>(3);
        RedBlackNode<Integer> b = new RedBlackNode<Integer>(10);
        RedBlackNode<Integer> c = new RedBlackNode<Integer>(14);
        RedBlackNode<Integer> d = new RedBlackNode<Integer>(22);
        RedBlackNode<Integer> e = new RedBlackNode<Integer>(4);
        RedBlackNode<Integer> f = new RedBlackNode<Integer>(6);
        RedBlackNode<Integer> g = new RedBlackNode<Integer>(5);
        RedBlackNode<Integer> h = new RedBlackNode<Integer>(2);
        RedBlackNode<Integer> i = new RedBlackNode<Integer>(1);
        rbtree.insert(a);
        rbtree.insert(b);
        rbtree.insert(c);
        rbtree.insert(d);
        rbtree.insert(e);
        rbtree.insert(f);
        rbtree.insert(g);
        rbtree.insert(h);
        rbtree.insert(i);


        System.out.println("Root is nil: " + rbtree.isNil(rbtree.getRoot()));
        System.out.println("Root key: " + rbtree.getRoot().key);
        System.out.println("Size of tree: " + rbtree.size());


        rbtree.printRightSide();
       // rbtree.search(rbtree.getRoot(), 5);

    }
}
