import java.util.Scanner;

public class NQueens {

    public static int solve(int n) {
        int[] queens = new int[n];
        return solve(queens, 0);
    }

    private static int solve(int[] queens, int row) {
        int count = 0;
        if (row == queens.length) {
            return 1;
        }
        for (int col = 0; col < queens.length; col++) {
            if (isValid(queens, row, col)) {
                queens[row] = col;
                count += solve(queens, row + 1);
            }
        }
        return count;
    }

    private static boolean isValid(int[] queens, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || queens[i] - i == col - row || queens[i] + i == col + row) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of queens: ");
        int queens = input.nextInt();
        int result = solve(queens);
        System.out.println("The number of valid arrangements is " + result);
    }
}
