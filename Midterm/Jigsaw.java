package Midterm;

import java.util.Scanner;

public class Jigsaw {

    private Node[] table;

    private static class Node {
        public int key;
        public Node next;

        public Node(int key, Node next) {
            this.key = key;
            this.next = next;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String firstLine = scanner.nextLine();
        String[] kn = firstLine.split(" ");
        int k = Integer.parseInt(kn[0]);
        int n = Integer.parseInt(kn[1]);

        Jigsaw jigsaw = new Jigsaw(n); // Create an instance of Jigsaw

        while (scanner.hasNextLine()) {
            int key = Integer.parseInt(scanner.nextLine());
            jigsaw.insert(key); // Call insert on the instance
        }
        scanner.close();
        jigsaw.check(k); // Call check on the instance
    }

    public Jigsaw(int size) {
        table = new Node[size];
    }

    public void check(int length) {
        int count = 0;

        for (int i = 1; i <= length; i++) {
            Node result = find(i);
            if (result == null) {
                count++;
            }
        }

        if (count == 0) {
            System.out.println("The puzzle is complete.");
        } else {
            System.out.println("Missing " + count + " jigsaw piece(s).");
        }
    }

    public Node find(int key) {
        int h = hash(key);
        for (Node c = table[h]; c != null; c = c.next) {
            if (c.key == key)
                return c;
        }
        return null;
    }

    public void insert(int key) {
        if (find(key) != null)
            return;

        int h = hash(key);
        table[h] = new Node(key, table[h]);
    }

    private int hash(int key) {
        return Math.abs(919 * key) % table.length;
    }
}
