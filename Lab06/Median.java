import java.util.Scanner;

public class Median {
	public static void main(String[] args) {
		BinaryHeap small = new BinaryHeap();
		BinaryHeap large = new BinaryHeap();
		Scanner scan = new Scanner(System.in);

		int m = 0, v = 0;
		while (scan.hasNext()) {
			char command = scan.next().charAt(0);
			if (command == 'i') {
				v = scan.nextInt();
				// TODO: insert value v into the dataset
				if (large.size() == 0 || v > large.find_min()) {
					large.insert(v);
				} else {
					small.insert(-v);
				}
				while(large.size() > small.size() + 1){
					int move = large.remove_min();
					small.insert(-move);
				}
				while(small.size() > large.size()){
					int move = -small.remove_min();
					large.insert(move);
				}
			} else if (command == 'q') {
				// TODO: calculate the median, store it in m
				if(small.size() == large.size()){
					int smallMax = -small.find_min();
					int largeMin = large.find_min();
					m = (smallMax + largeMin) / 2;
				}
				else{
					m = large.find_min();
				}
				System.out.println(m);
			} else
				break;
		}
	}
}
