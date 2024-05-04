public class BST {
	private Node root;

	public BST() {
		this.root = null;
	}

	public void insert(int time, Request req) {
		root = insert(root, time, req);
	}

	private Node insert(Node root, int time, Request req) {
		if (root == null) {
			root = new Node(time, req);
			return root;
		}
		if (time < root.time) {
			root.left = insert(root.left, time, req);
		} else if (time > root.time) {
			root.right = insert(root.right, time, req);
		}
		return root;
	}

	public Node getParent(Node node) {
		return getParent(root, node);
	}
	
	private Node getParent(Node root, Node node) {
		if (root == null || root == node) {
			return null;
		}
		if (root.left == node || root.right == node) {
			return root;
		}
		Node parent = getParent(root.left, node);
		if (parent != null) {
			return parent;
		}
		return getParent(root.right, node);
	}

	public Node pred(int time) {
		return pred(root, time);
	}
	
	private Node pred(Node root, int time) {
		Node pred = null;
		while (root != null) {
			if (root.time < time) {
				pred = root;
				root = root.right;
			} else if (root.time == time) {
				if (root.left == null) {
					while (root != null && root.time >= time) {
						root = getParent(root);
					}
					return root;
				}
				return pred(root.left, time);
			} else {
				root = root.left;
			}
		}
		return pred;
	}
	
	public Node succ(int time) {
		return succ(root, time);
	}
	
	private Node succ(Node root, int time) {
		Node succ = null;
		while (root != null) {
			if (root.time > time) {
				succ = root;
				root = root.left;
			} else if (root.time == time) {
				if (root.right == null) {
					while (root != null && root.time <= time) {
						root = getParent(root);
					}
					return root;
				}
				return succ(root.right, time);
			} else {
				root = root.right;
			}
		}
		return succ;
	}
	
	public Node min() {
		if (root == null) {
			return null;
		}
		Node current = root;
		while (current.left != null) {
			current = current.left;
		}
		return current;
	}

	public Node max() {
		if (root == null) {
			return null;
		}
		Node current = root;
		while (current.right != null) {
			current = current.right;
		}
		return current;
	}

	public void delete(int time) {
		root = delete(root, time);
	}

	private Node delete(Node root, int time) {
		if (root == null) {
			return root;
		}
		if (time < root.time) {
			root.left = delete(root.left, time);
		} else if (time > root.time) {
			root.right = delete(root.right, time);
		} else {
			if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			}
			root.time = minVal(root.right);
			root.right = delete(root.right, root.time);
		}
		return root;
	}

	private int minVal(Node root) {
		int minVal = root.time;
		while (root.left != null) {
			minVal = root.left.time;
			root = root.left;
		}
		return minVal;
	}

	public void print() {
		print(root);
	}

	private void print(Node root) {
		if (root != null) {
			print(root.left);
			System.out.println(root.time + " " + root.req);
			print(root.right);
		}
	}
}
