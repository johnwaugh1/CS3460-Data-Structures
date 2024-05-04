package Midterm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        // Change the desktopPath to your desktop path
        String desktopPath = System.getProperty("user.home") + "/Desktop/";
        String filename = desktopPath + "numbers.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 1; i <= 5000000; i++) {
                writer.write(Integer.toString(i));
                writer.newLine();
            }

            System.out.println("File created successfully: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}