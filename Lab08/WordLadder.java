import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordLadder {

    private static Map<String, String> wordMap;

    public static void main(String[] args) {
        loadDictionary("dictionary4.txt");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the start word: ");
        String start = scanner.next();
        System.out.print("Enter the end word: ");
        String end = scanner.next();

        List<String> ladder = findShortestPath(start, end);
        if (ladder != null) {
            System.out.println("Possible!");
            for (String word : ladder) {
                System.out.println(word);
            }
        } else {
            System.out.println("Impossible!");
        }
    }

    private static void loadDictionary(String filename) {
        wordMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String word;
            while ((word = br.readLine()) != null) {
                wordMap.put(word, "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> findShortestPath(String start, String end) {
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> distance = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        queue.offer(start);
        distance.put(start, 0);
        parent.put(start, null);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(end)) {
                return reconstructPath(start, end, parent);
            }

            List<String> neighbors = getNeighbors(current);
            for (String neighbor : neighbors) {
                if (!distance.containsKey(neighbor)) {
                    distance.put(neighbor, distance.get(current) + 1);
                    parent.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }

        return null;
    }

    private static List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            char originalChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != originalChar) {
                    chars[i] = c;
                    String newWord = new String(chars);
                    if (wordMap.containsKey(newWord)) {
                        neighbors.add(newWord);
                    }
                }
            }
            chars[i] = originalChar;
        }
        return neighbors;
    }

    private static List<String> reconstructPath(String start, String end, Map<String, String> parent) {
        List<String> path = new ArrayList<>();
        String current = end;
        while (!current.equals(start)) {
            path.add(0, current);
            current = parent.get(current);
        }
        path.add(0, start);
        return path;
    }
}
