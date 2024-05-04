import java.util.Scanner;

public class Islands{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        char[][] grid = new char[n][n];

        for(int i = 0; i < n; i++){
            String line = input.nextLine();
            for(int j = 0; j < n; j++){
                grid[i][j] = line.charAt(j);
            }
        }

        int islands = count(grid);
        System.out.println(islands);
    }

    private static int count(char[][] grid){
        int count = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                if(grid[i][j] == '*'){
                    count++;
                    explore(grid, i, j);
                }
            }
        }
        return count;
    }

    private static void explore(char[][] grid, int row, int col){
        if(row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == '.'){
            return;
        }
        grid[row][col] = '.';
        explore(grid, row + 1, col);
        explore(grid, row - 1, col);
        explore(grid, row, col + 1);
        explore(grid, row, col - 1);
        explore(grid, row + 1, col + 1);
        explore(grid, row + 1, col - 1);
        explore(grid, row - 1, col + 1);
        explore(grid, row - 1, col - 1);
    }
}