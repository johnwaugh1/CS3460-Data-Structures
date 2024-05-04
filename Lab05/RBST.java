import java.util.Random;

public class RBST {
	private Node root; // Head node of the tree.
	private Random rand; // A random object - required to randomly insert nodes into the tree.

	// Constructors
	public RBST() {
		this(null);
	}

	public RBST(Node root) {
		this.root = root;
		this.rand = new Random();
	}

	public void print() {
		print(root);
		System.out.println();
	}

	private void print(Node node) {
		// TODO: implement a recursive in-order traversal
		if (node != null) {
			print(node.left);
			System.out.println(node.value + " ");
			print(node.right);
		}
	}

	public void insertFast(int value, int rank) {
		root = insertFast(root, value, rank);
	}

	private Node insertFast(Node node, int value, int rank) {
		// TODO: base case - inserting into a null tree
		if (node == null) {
			return new Node(value);
		}

		assert (rank >= 1 && rank <= node.size + 1) : "rank should be between 1 and n+1 <" + (node.size + 1) + ">";

		// TODO: recursively insert into left subtree if rank <= rank of root. otherwise
		// insert into right subtree.

		int leftSize = node.left != null ? node.left.size : 0;
		if (rank <= leftSize + 1) {
			node.left = insertFast(node.left, value, rank);
		} else {
			node.right = insertFast(node.right, value, rank - (leftSize + 1));
		}

		node.updateSize();
		return node; // TODO: return the actual resulting tree
	}

	public Node[] split(int rank) {
		return split(root, rank);
	}

	private Node[] split(Node T, int rank) {
		Node[] ret = { null, null }; // ret[0] is the root node to the left side of the split, ret[1] is the right
										// side.

		// TODO: your code here for the split method. implement this recursively!
		// your base case will be an empty tree. your recursive case will have three
		// cases -- think
		// what happens if the rank of the root == rank, or if rank is smaller or larger
		// than the rank
		// of the root

		if (T == null) {
			return ret;
		}

		int leftSize = T.left != null ? T.left.size : 0;

		if (rank <= leftSize) {
			Node[] leftSplit = split(T.left, rank);
			ret[0] = leftSplit[0];
			T.left = leftSplit[1];
			ret[1] = T;
		} else {
			Node[] rightSplit = split(T.right, rank - (leftSize + 1));
			T.right = rightSplit[0];
			ret[0] = T;
			ret[1] = rightSplit[1];
		}

		T.updateSize();
		return ret;
	}

	public void insert(int value, int rank) {
		root = insert(root, value, rank);
	}

	private Node insert(Node node, int value, int rank) {
		// TODO: base case - inserting into a null tree
		if (node == null) {
			return new Node(value);
		}

		assert (rank >= 1 && rank <= node.size + 1) : "rank should be between 1 and n+1 <" + (node.size + 1) + ">";

		// TODO: with probability 1/(node.size + 1), the new node becomes the root
		// otherwise recursively insert into left or right subtree.

		if (rand.nextInt(node.size + 1) == 0) {
			Node[] split = split(node, rank - 1);
			Node newNode = new Node(value);
			newNode.left = split[0];
			newNode.right = split[1];
			newNode.updateSize();
			return newNode;
		} else {
			int leftSize = node.left != null ? node.left.size : 0;
			if (rank <= leftSize + 1) {
				node.left = insert(node.left, value, rank);
			} else {
				node.right = insert(node.right, value, rank - (leftSize + 1));
			}
			node.updateSize();
			return node;
		}
	}

	public Node select(int rank) {
		return select(root, rank);
	}

	private Node select(Node node, int rank) {
		// TODO: base case - return null if the tree is empty
		if (node == null) {
			return null;
		}

		assert (rank >= 1 && rank <= node.size + 1) : "rank should be between 1 and n+1 <" + (node.size + 1) + ">";

		// TODO: recursive case. return T if rank is equal to the rank of the root.
		// otherwise,
		// recursively select in either the left tree (rank < rank of root) or the right
		// tree (rank > rank of the root).

		int leftSize = node.left != null ? node.left.size : 0;

		if (rank == leftSize + 1) {
			return node;
		} else if (rank <= leftSize) {
			return select(node.left, rank);
		} else {
			return select(node.right, rank - (leftSize + 1));
		}
	}

	public int getSize() {
		return this.root == null ? 0 : this.root.size;
	}
}
