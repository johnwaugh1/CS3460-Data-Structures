import java.util.Scanner;

public class RunwayReservation {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int n = kb.nextInt(); // The total number of requests.
		int k = kb.nextInt(); // Grace time between requests.

		char cmd;
		int time;
		int curtime = 0; // Current time, initialized to 0.

		Request[] reqs = new Request[n];
		int i = 0;

		while (kb.hasNext()) {
			cmd = kb.next().charAt(0);
			time = kb.nextInt();

			if (cmd == 'r') {
				String flightname = kb.next();
				String flightnumber = kb.next();
				String source = kb.next();
				String destination = kb.next();

				reqs[i++] = new Request('r', time, flightname, flightnumber, source, destination);
			} else {
				reqs[i++] = new Request('t', time);
			}
			kb.nextLine();
		}

		BST bst = new BST();

		for (Request request : reqs) {
			if (request.command == 't') {
				curtime += request.time;
				System.out.println("Current time = " + curtime + " units");
				printAndRemove(bst, curtime);
			} else if (isValidReq(bst, request.time, k)) {
				bst.insert(request.time, request);
			}
		}

		curtime = bst.max().time;
		System.out.println("Current time = " + curtime + " units");
		printAndRemove(bst, curtime);
	}

	private static boolean isValidReq(BST bst, int time, int k) {
		Node pred = bst.pred(time);
		Node succ = bst.succ(time);

		if (pred != null && time - pred.time < k) {
			return false;
		}

		if (succ != null && succ.time - time < k) {
			return false;
		}

		return true;
	}

	private static void printAndRemove(BST bst, int curtime) {
		Node node;
		while ((node = bst.min()) != null && node.time <= curtime) {
			System.out.println(node.req.airline);
			bst.delete(node.time);
		}
	}
}
