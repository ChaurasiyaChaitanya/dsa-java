import java.util.ArrayList;

public class BinarySearchTree {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node insert(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            return root;
        }

        if (root.data > val) {
            // left subtree
            root.left = insert(root.left, val);
        } else {
            // right subtree
            root.right = insert(root.right, val);
        }

        return root;
    }

    public static void inorder(Node root) {
        if (root == null)
            return;

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static boolean search(Node root, int key) {
        if (root == null)
            return false;

        if (root.data == key) {
            return true;
        } else if (root.data > key) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    public static Node delete(Node root, int val) {

        if (root.data > val) {
            root.left = delete(root.left, val);
        } else if (root.data < val) {
            root.right = delete(root.right, val);
        } else {
            // case 1
            if (root.left == null && root.right == null)
                return null;

            // case 2
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // case 3
            Node inSucc = inorderSuccessor(root.right);
            root.data = inSucc.data;
            root.right = delete(root.right, inSucc.data);
        }

        return root;
    }

    public static Node inorderSuccessor(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static void printInRange(Node root, int X, int Y) {
        if (root == null)
            return;

        if (root.data >= X && root.data <= Y) {
            printInRange(root.left, X, Y);
            System.out.print(root.data + " ");
            printInRange(root.right, X, Y);
        } else if (root.data >= Y) {
            printInRange(root.left, X, Y);
        } else {
            printInRange(root.right, X, Y);
        }
    }

    public static void printRootToLeaf(Node root, ArrayList<Integer> path) {
        if (root == null)
            return;

        path.add(root.data);

        // leaf node condition
        if (root.left == null && root.right == null) {
            printPath(path);
        } else { // non leaf node condition
            printRootToLeaf(root.left, path);
            printRootToLeaf(root.right, path);
        }
        path.remove(path.size() - 1);
    }

    public static void printPath(ArrayList<Integer> path) {
        for (int i : path) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int values[] = { 8, 5, 3, 1, 4, 6, 10, 11, 14 };
        // {5,1,3,4,2,7};

        Node root = null;

        for (int i = 0; i < values.length; i++) {
            root = insert(root, values[i]);
        }

        System.out.print("Inorder Traversal of BST : ");

        inorder(root);

        System.out.println();

        if (search(root, 1)) {
            System.out.println("Node 1 is found in BST");
        } else {
            System.out.println("Node 1 is not found in BST");
        }

        delete(root, 5);

        inorder(root);

        System.out.println();

        System.out.print("Print in Range : ");

        printInRange(root, 3, 11);

        System.out.println();

        System.out.println("Print Root to Leaf : ");

        printRootToLeaf(root, new ArrayList<>());
    }

}