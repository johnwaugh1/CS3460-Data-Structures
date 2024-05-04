import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class islandsTest {
    public static void main(String[] args) {
        String fileName = "map-2-in.txt";
        
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            // Loop to append text to the file
            for (int i = 0; i < 100000; i++) { // Change 10 to the desired number of iterations
                String text = "" + "\n";
                bufferedWriter.write(text);
            }
            
            // Close the writer
            bufferedWriter.close();
            
            System.out.println("Text has been written to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
