import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Closest {

    private static final String POINTS = "points.txt";
    private static int b = 1000;
    private static Node[][] grid = new Node[b][b];

    public static void main(String[] args) {
        try {
            File file = new File(POINTS);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] coords = line.split(" ");
                double x = Double.parseDouble(coords[0]);
                double y = Double.parseDouble(coords[1]);
                put(x, y);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file " + POINTS);
            e.printStackTrace();
        }
        compare();
    }

    public static Node insert(Node node, double x, double y) 
    {
        if(node == null)
        {
            return new Node(x, y, null);
        }
        node.next = insert(node.next, x, y);
        return node;
    }

    public static void put(double x, double y) {
        int hx = hash(x);
        int hy = hash(y);
        grid[hx][hy] = insert(grid[hx][hy], x, y);
    }

    public static void compare() {
        double smallestDistance = Double.MAX_VALUE;

        for (int i = 0; i < b; i++) 
        {
            for (int j = 0; j < b; j++) 
            {
                if (grid[i][j] != null) 
                {
                    Node current1 = grid[i][j];
                    smallestDistance = updateSmallestDistance(current1, i, j, smallestDistance);
                }
            }
        }
        System.out.println(smallestDistance);
    }

    private static double updateSmallestDistance(Node current1, int i, int j, double currentSmallestDistance)
    {
        for(int x = i - 1; x <= i + 1; x++)
        {
            for(int y = j - 1; y <= j + 1; y++)
            {
                if(x != i || y != j)
                {
                    currentSmallestDistance = checkDistance(current1, x, y, currentSmallestDistance);
                }
            }
        }
        return currentSmallestDistance;
    }

    private static double checkDistance(Node current1, int x, int y, double currentSmallestDistance)
    {
        if(validCell(x, y))
        {
            System.out.println("Valid Cell");
            Node current2 = grid[x][y];
            while(current1 != null)
            {
                while(current2 != null) 
                {
                    double distance = calcDistance(current1.x, current1.y, current2.x, current2.y);
                    currentSmallestDistance = Math.min(currentSmallestDistance, distance);
                    current2 = current2.next;
                }
                current1 = current1.next;
            }
        }
        else{
            System.out.println("Invalid Cell");
        }
        return currentSmallestDistance;
    }   

    private static boolean validCell(int x, int y)
    {
        return x >= 0 && x < b && y >= 0 && y < b;
    }

    private static double calcDistance(double x1, double y1, double x2, double y2)
    {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static int hash(double value) {
        int cellSize = 1000000/b;
        for(int i = 1; i <= b; i++)
        {
            int low = cellSize * (i - 1);
            int high = cellSize * i;
            if(value >= low && value < high)
            {
                 return i - 1;
            }
        }
        return -1;
    }

    public static class Node {
        public double x, y;
        public Node next;

        public Node(double x, double y, Node next) {
            this.x = x;
            this.y = y;
            this.next = next;
        }
    }
}
