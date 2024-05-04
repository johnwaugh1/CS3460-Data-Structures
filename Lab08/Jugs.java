import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jugs {

    private static int A;
    private static int B;
    private static int C;
    private static boolean[][] visited;
    private static List<String> transition;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter A: ");
        A = scanner.nextInt();
        System.out.print("Enter B: ");
        B = scanner.nextInt();
        System.out.print("Enter C: ");
        C = scanner.nextInt();

        transition = new ArrayList<>();
        visited = new boolean[A + 1][B + 1];

        if (dfs(0, 0)) {
            System.out.println("Possible!");
            printtransition();
        } else {
            System.out.println("Impossible!");
        }

        scanner.close();

    }

    static void printtransition() {
        for (String action : transition) {
            System.out.println(action);
        }
    }

    static boolean dfs(int x, int y) {
        if ((x == C && y == 0) || (y == C && x == 0) || (x + y == C && x != 0 && y != 0)) {
            return true;
        }

        visited[x][y] = true;

        if (!visited[A][y]) {
            addAction("A", "Fill Jug 1", A, y);
            if (dfs(A, y))
                return true;
            transition.remove(transition.size() - 1);
        }

        if (!visited[x][B]) {
            addAction("B", "Fill Jug 2", x, B);
            if (dfs(x, B))
                return true;
            transition.remove(transition.size() - 1);
        }

        int pour = Math.min(x, B - y);
        if (!visited[x - pour][y + pour]) {
            addAction("E", "Pour Jug 1 -> Jug 2", x - pour, y + pour);
            if (dfs(x - pour, y + pour))
                return true;
            transition.remove(transition.size() - 1);
        }

        pour = Math.min(A - x, y);
        if (!visited[x + pour][y - pour]) {
            addAction("F", "Pour Jug 2 -> Jug 1", x + pour, y - pour);
            if (dfs(x + pour, y - pour))
                return true;
            transition.remove(transition.size() - 1);
        }

        if (!visited[0][y]) {
            addAction("C", "Empty Jug 1", 0, y);
            if (dfs(0, y))
                return true;
            transition.remove(transition.size() - 1);
        }

        if (!visited[x][0]) {
            addAction("D", "Empty Jug 2", x, 0);
            if (dfs(x, 0))
                return true;
            transition.remove(transition.size() - 1);
        }

        return false;
    }

    static void addAction(String code, String action, int a, int b) {
        transition.add(code + ": " + action + " [a = " + a + ", b = " + b + "]");
    }
}
