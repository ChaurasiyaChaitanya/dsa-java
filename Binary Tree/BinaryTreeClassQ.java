//Build a Tree from its Preorder traversal
public class BinaryTreeClassQ {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        static int index = -1;
        public static Node buildTree(int nodes[]) {
            index++;
            if(nodes[index] == -1) {
                return null;
            }
            Node newNode = new Node(nodes[index]); 
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
    }

    static class TreeInfo {
        int height;
        int diameter;

        TreeInfo(int height, int diameter) {
            this.height = height;
            this.diameter = diameter;
        }
    }

    // Height of Binary Tree  T.C. -> O(n)
    public static int height(Node root) {
        if(root == null) {
            return 0;
        }
 
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Count of Nodes in Binary Tree  T.C. -> O(n)
    public static int countOfNodes(Node root) {
        if(root == null) {
            return 0;
        }
 
        int leftNodes = countOfNodes(root.left);
        int rightNodes = countOfNodes(root.right);
        return leftNodes + rightNodes + 1;
    }

    // Sum of Nodes in Binary Tree  T.C. -> O(n)
    public static int sumOfNodes(Node root) {
        if(root == null) {
            return 0;
        }
 
        int leftSum = sumOfNodes(root.left);
        int rightSum = sumOfNodes(root.right);
        return leftSum + rightSum + root.data;
    }

    // Diameter of Binary Tree O(n^2)
    public static int diameter1(Node root) {
        if(root == null) {
            return 0;
        }
 
        int diam1 = height(root.left) + height(root.right) + 1;
        int diam2 = diameter1(root.left);
        int diam3 = diameter1(root.right);
 
        return Math.max(diam1, Math.max(diam2, diam3));
    }
    
    // Diameter of Binary Tree O(n)
    public static TreeInfo diameter2(Node root) {
        if(root == null) {
            return new TreeInfo(0, 0);
        }
 
        TreeInfo leftTI = diameter2(root.left);
        TreeInfo rightTI = diameter2(root.right);
 
        int myHeight = Math.max(leftTI.height, rightTI.height) + 1;
 
        int diam1 = leftTI.height + rightTI.height + 1;
        int diam2 = leftTI.diameter;
        int diam3 = rightTI.diameter;
 
        int myDiam = Math.max(diam1, Math.max(diam2, diam3));
 
        return new TreeInfo(myHeight, myDiam);
    }

    public static void main(String args[]) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree tree = new BinaryTree();
        
        Node root = tree.buildTree(nodes);

        System.out.println("Height of Binary Tree : " + height(root));

        System.out.println("Count of Nodes in Binary Tree : " + countOfNodes(root));

        System.out.println("Sum of Nodes in Binary Tree : " + sumOfNodes(root));

        System.out.println("Diameter of Binary Tree O(n^2) : " + diameter1(root));

        System.out.println("Diameter of Binary Tree O(n) : " + diameter2(root).diameter);
    }
}