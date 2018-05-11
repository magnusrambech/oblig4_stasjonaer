package redblacktree;

public class RedBlackTree<T extends Comparable<T>> {

    private RedBlackNode<T> nil = new RedBlackNode<T>();
    private RedBlackNode<T> root = nil;

    public RedBlackTree(){
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }

    public void insert(RedBlackNode<T> n){
        /**
         * Lager en referanse til root-noden, og lager en nil-node.
         */
        RedBlackNode<T> y = nil;
        RedBlackNode<T> x = root;
            while(!isNil(x)){
                y = x;
                if(n.key.compareTo(x.key) > 0){
                    System.out.println(n.key + " is greater than " + x.key +". Going right");
                    x.numRight++;
                    x=x.right;
                }
                else if(n.key.compareTo(x.key) < 0){
                    System.out.println(n.key + " is lower than " + x.key +". Going left");
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
            n.color = RedBlackNode.RED;

            //Retter opp i brudd i RBT'et etter å ha satt inn n
            insertFixup(n);

    }

    /**
     * Retter opp i brudd etter å ha satt inn en ny node
     * @param node node som ble satt inn
     */
    private void insertFixup(RedBlackNode<T> node){
        RedBlackNode<T> temp = nil;

        //While there are violations inr the RBT
        while(node.parent.color == RedBlackNode.RED){
            //Hvis noden sin forelder er det venstre barnet av dens forelder (nodens bestefar)
            if(node.parent == node.parent.parent.left){

                //Finner nodens onkel
                temp = node.parent.parent.right;

                //Case 0: Hvis onkelen er rød, endre farger på onkel, forelder og besteforelder
                if(temp.color == RedBlackNode.RED){
                    node.parent.color = RedBlackNode.BLACK;
                    temp.color = RedBlackNode.BLACK;
                    node.parent.parent.color = RedBlackNode.RED;

                    //Hopper opp i treet til neste gjennomgang
                    node = node.parent.parent;
                }
                //Case 1:
                //Case 2:
                //Case 3:
            }

            //Hvis noden sin forelder er det høyre barnet av dens forelder (nodens bestefar)
            else{
                //Finner nodens onkel
                temp = node.parent.parent.left;

                //Case 0:
                //Case 1:
                //Case 2:
                //Case 3:

            }
            node.parent.color = RedBlackNode.BLACK;

        }
    }

    /**
     * Checks whether a node is nil or not
     * @param node node to check
     * @return nil / !nil
     */
    public boolean isNil(RedBlackNode<T> node){
        return node == nil;
    }

    /**
     * Returns size of tree
     * @return
     */
    public int size(){
        // Returns number of children on left & right side, +1 for the root node itself.
        return root.numRight + root.numLeft +1;
    }

    public RedBlackNode<T> getRoot() {
        return root;
    }

    public void printRightSide() {
        RedBlackNode<T> curr = root;
        while(!isNil(curr)){
            if(curr==root){
                System.out.println(curr.key + "    <-- ROOT");
                curr = curr.right;
            }
            else {
                System.out.println(curr.key);
                curr = curr.right;
            }
        }
    }

    // A utility function to search a given key in BST
    public RedBlackNode search(RedBlackNode root, int keysearch)
    {
        // Base Cases: root is null or key is present at root
        if (root==null || (Integer)root.key == keysearch){
            System.out.println("Found " + root.key);
            return root;
        }


        // val is greater than root's key
        else if ((Integer)root.key > keysearch){
            System.out.println("Found " + search(root.left, keysearch).key);
            return search(root.left, keysearch);
        }


        // val is less than root's key
        else{
            System.out.println("Found " +search(root.right, keysearch).key);
            return search(root.right, keysearch);
        }

    }
}

