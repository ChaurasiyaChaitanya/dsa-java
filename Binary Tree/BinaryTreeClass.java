import java.util.*;

//Build a Tree from its Preorder traversal
public class BinaryTreeClass {

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

    // Preorder Traversal --> Root, Left Subtree, Right Subtree
    public static void preorder(Node root) {
        if(root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // Inorder Traversal --> Left Subtree, Root, Right Subtree
    public static void inorder(Node root) {
        if(root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // PostOrder Traversal --> Left Subtree, Right Subtree, Root
    public static void postorder(Node root) {
        if(root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    // Level Order Traversal (BFS) --> Level wise (Root, Root's Child, ...) 
    // T.C. -> O(n)
    public static void levelorder(Node root) {
        if(root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while(!q.isEmpty()) {
            Node curNode = q.remove();
            if(curNode == null ){
                System.out.println();
                if(q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {
                System.out.print(curNode.data);
                if(curNode.left != null) {
                    q.add(curNode.left);
                }

                if(curNode.right != null) {
                    q.add(curNode.right);
                }
            }
        }
    }
        
    public static void main(String args[]) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree tree = new BinaryTree();
        
        Node root = tree.buildTree(nodes);

        System.out.println("Root Node -> " + root.data);
        System.out.print("Preorder Traversal -> ");
        preorder(root);

        System.out.println();

        System.out.print("Inorder Traversal -> ");
        inorder(root);

        System.out.println();

        System.out.print("Postorder Traversal -> ");
        postorder(root);

        System.out.println();

        System.out.println("Levelorder Traversal");
        levelorder(root);
    }

}
