



public class Main {


    public static void main(String[] args) {
        launchWithBinaryTree();




    }


    public static void launchWithBinaryTree(){

        System.out.println("________________________TREE 1______________________________");

        // Tree 1
        SortedTreeMap<Integer, String> test = new SortedTreeMap<>();
        test.add(5, "Magnus");
        test.add(2, "Tord");
        test.add(12,"Thomas");
        test.add(3,"dfhhaws");
        test.add(-4,"jdfhj");
        test.add(9,"dshsh");
        test.add(21,"hshd");
        test.add(19,"Sindsdfsdfri");
        test.add(25,"hsdfds");

        System.out.println("_______________________________________");
        System.out.println("Size: " + test.size());
        System.out.println("Root: " + test.getRoot().key);
        test.remove(12);
        System.out.println("Contains key removed: " + test.containsKey(12));
        System.out.println("Size: " + test.size());
        System.out.println("Root: " + test.getRoot().key);


        System.out.println("________________________TREE 2______________________________");

        // Tree 2
        SortedTreeMap<Integer, String> test2 = new SortedTreeMap<>();
        test2.add(5, "Magnus");
        test2.add(2, "Tord");
        test2.add(6,"Thomas");
        test2.add(10,"Thomas");
        test2.add(7,"Thomas");
        test2.add(15,"Thomas");

        System.out.println("_______________________________________");
        System.out.println("Size: " + test2.size());
        System.out.println("Root: " + test2.getRoot().key);
        test2.remove(2);
        System.out.println("Contains key removed: " + test2.containsKey(2));
        System.out.println("Size: " + test2.size());
        System.out.println("Root: " + test2.getRoot().key);



        System.out.println("________________________TREE 3______________________________");

        // Tree 3
        SortedTreeMap<Integer, String> test3 = new SortedTreeMap<>();
        test3.add(5, "Magnus");
        System.out.println("Root: " + test3.getRoot().key);
        System.out.println(test3.size());
        test3.remove(5);
        System.out.println(test3.size());
        System.out.println("Root: " + test3.getRoot().key);


        System.out.println("________________________TREE 4______________________________");
        SortedTreeMap<Integer, String> test4 = new SortedTreeMap<>();
        test4.add(5, "Magnus");
        test4.add(2, "Tord");
        test4.add(12,"Thomas");
        test4.add(3,"dfhhaws");
        test4.add(-4,"jdfhj");
        test4.add(9,"dshsh");
        test4.add(21,"hshd");
        test4.add(19,"Sindsdfsdfri");
        test4.add(25,"hsdfds");
        test4.add(8, "Magnus");
        test4.add(-14, "Tord");
        test4.add(68,"Thomas");
        test4.add(32,"dfhhaws");
        test4.add(-44,"jdfhj");
        test4.add(92,"dshsh");
        test4.add(22,"hshd");
        test4.add(98,"Sindsdfsdfri");
        test4.add(-32,"hsdfds");
        test4.add(-45,"25fa");

        System.out.println("SIZE: " + test4.size());
        test4.remove(25);
        System.out.println(test4.containsKey(25));
        System.out.println("SIZE: " +test4.size());

        System.out.println("________________________TREE 5______________________________");
        SortedTreeMap<Integer, String> test5 = new SortedTreeMap<>();
        test5.add(5,"hei");
        test5.add(0,"på");
        test5.add(-5,"dei");
        test5.add(-4,"støgging");

        System.out.println("SIZE: " + test5.size());
        test5.remove(5);
        System.out.println(test5.containsKey(5));
        System.out.println("SIZE: " +test5.size());
        System.out.println("ROOT: " + test5.getRoot().key);

        System.out.println("________________________TREE 6______________________________");
        SortedTreeMap<Integer, String> test6 = new SortedTreeMap<>();
        test6.add(-3,"hei");
        test6.add(1,"på");


        System.out.println("SIZE: " + test6.size());
        test6.remove(-3);
        System.out.println(test6.containsKey(-3));
        System.out.println("SIZE: " +test6.size());
        System.out.println("ROOT: " + test6.getRoot().key);


        System.out.println("________________________TREE 7______________________________");
        SortedTreeMap<Integer, String> test7 = new SortedTreeMap<>();
        test7.add(4,"24");
        test7.add(9,"241");
        test7.add(-6,"51i");
        test7.add(-5,"2515");
        test7.add(7,"521");
        test7.add(5,"515");
        test7.add(6,"5256");

        System.out.println("SIZE: " + test7.size());
        test7.remove(4);
        System.out.println("SIZE: " + test7.size());
        System.out.println("MAX: " + test7.max().key);

        System.out.println(test7.keys());

        System.out.println("________________________TREE 8______________________________");
        SortedTreeMap<Integer, String> test8 = new SortedTreeMap<>();
        test8.add(1,"ein");
        System.out.println("KEYS: " + test8.keys());
        test8.remove(1);
        System.out.println("KEYS: " + test8.keys());

        System.out.println("________________________TREE 9______________________________");
        SortedTreeMap<Integer, String> test9 = new SortedTreeMap<>();
        test9.add(-2,"hjgk");
        test9.add(0,"ghjeah");
        test9.add(-1,"huyui");
        test9.add(8,"jiojio");
        test9.remove(-2);


        System.out.println("________________________TREE 10______________________________");
        SortedTreeMap<Integer, String> test10 = new SortedTreeMap<>();
        test10.add(1,"gjegk");
        System.out.println(test10.size());
        test10.replace(2,"hei");
        System.out.println(test10.getRoot().key);
    }
}
