public class BST {
    Node root;

    public BST() {
        root = null;
    }

    public void insert(int height) {
        root = insert(root, height);
    }

    private Node insert(Node root, int height) {
        if (root == null) {
            root = new Node(height);
            return root;
        }
        if (height < root.height) {
            root.left = insert(root.left, height);
        } else if (height > root.height) {
            root.right = insert(root.right, height);
        }
        return root;
    }

    public int nextTallest(int height) {
        return nextTallest(root, height);
    }
    
    private int nextTallest(Node node, int height) {
        int nextTallest = 0;
        while (node != null) {
            if (node.height < height) {
                nextTallest = node.height;
                node = node.right;
            } else if (node.height > height) {
                nextTallest = node.height;
                node = node.left;
            } else {
                node = node.left;
            }
        }
        return nextTallest;
    }
    
}