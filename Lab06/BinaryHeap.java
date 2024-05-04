public class BinaryHeap {
	public int[] data; // data array
	public int numItems; // number of items stored in the data array

	public BinaryHeap() {
		data = new int[100];
		numItems = 0;
	}

	public void insert(int k) {
		// TODO: insert into the binary heap
		// insert at the last location, increment size, and sift-up
		// might require a resize if heap is full
		if (numItems == data.length) {
			resize();
		}
		data[numItems] = k;
		numItems++;
		sift_up(numItems - 1);
	}

	public int remove_min() {
		// TODO: return smallest value (stored at root) and remove
		// swap with last location, decrement size, then sift-down the new root
		if (numItems == 0) {
			throw new IllegalStateException("Heap is empty.");
		}
		int min = data[0];
		data[0] = data[numItems - 1];
		numItems--;
		sift_down(0);
		return min;
	}

	public int find_min() {
		// TODO: return smallest value (stored at root)
		if (numItems == 0) {
			throw new IllegalStateException("Heap is empty.");
		}
		return data[0];
	}

	public int size() {
		return numItems;
	}

	private void sift_up(int i) {
		// TODO: fix heap violations at location i by swapping with parent if needed
		while (i > 0 && data[parent(i)] > data[i]) {
			swap(i, parent(i));
			i = parent(i);
		}
	}

	private void sift_down(int i) {
		// TODO: fix heap violations at location i by swapping with smallest child
		int minIndex = i;
		int leftChild = leftChild(i);
		int rightChild = rightChild(i);

		if (leftChild < numItems && data[leftChild] < data[minIndex]) {
			minIndex = leftChild;
		}
		if (rightChild < numItems && data[rightChild] < data[minIndex]) {
			minIndex = rightChild;
		}
		if (minIndex != i) {
			swap(i, minIndex);
			sift_down(minIndex);
		}
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private int leftChild(int i) {
		return 2 * i + 1;
	}

	private int rightChild(int i) {
		return 2 * i + 2;
	}

	private void swap(int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	private void resize() {
		int[] newData = new int[data.length * 2];
		System.arraycopy(data, 0, newData, 0, data.length);
		data = newData;
	}
}
