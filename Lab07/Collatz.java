import java.util.Scanner;
import java.util.HashMap;

public class Collatz {

    private static HashMap<Long, Integer> memo = new HashMap<>();

    public static int collatzLength(long x) {
        if (memo.containsKey(x)) {
            return memo.get(x);
        }
        if (x == 1) {
            return 1;
        }
        int length;
        if (x % 2 == 0) {
            length = 1 + collatzLength(x / 2);
        } else {
            length = 1 + collatzLength(3 * x + 1);
        }
        memo.put(x, length);
        return length;
    }

    public static long longestInRange(long a, long b) {
        long longestNumber = a;
        int maxLength = collatzLength(a);
        for (long i = a + 1; i <= b; i++) {
            int length = collatzLength(i);
            if (length > maxLength) {
                maxLength = length;
                longestNumber = i;
            }
        }
        return longestNumber;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the range: ");
        int a = input.nextInt();
        int b = input.nextInt();
        long result = longestInRange(a, b);
        int length = collatzLength(result);

        System.out.println("The number with the greatest Collatz length in this range: " + result);
        System.out.println("The Collatz length of " + result + " is " + length);

    }
}