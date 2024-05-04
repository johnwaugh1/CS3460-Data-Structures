import java.util.Scanner;

public class Heights {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int q = input.nextInt();

        BST bst = new BST();

        for(int i = 0; i < n; i++){
            int height = input.nextInt();
            bst.insert(height);
        }

        for(int i = 0; i < q; i++){
            int permit = input.nextInt();
            int nextTallest = bst.nextTallest(permit);
            System.out.println(permit + " " + nextTallest);
        }

        input.close();
    }
}
