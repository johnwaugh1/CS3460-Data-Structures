package Midterm;
import java.util.Scanner;

public class Trique<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arr;
    private int size;
    private int front;
    private int rear;

    public static void main(String[] args) {
        Trique<Integer> trique = new Trique<>();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String command = scanner.next();

            switch (command) {
                case "push_back":
                    trique.pushBack(scanner.nextInt());
                    break;
                case "push_front":
                    trique.pushFront(scanner.nextInt());
                    break;
                case "push_mid":
                    trique.pushMid(scanner.nextInt());
                    break;
                case "get":
                    System.out.println(trique.get(scanner.nextInt()));
                    break;
                case "end":
                    return;
                default:
                    System.out.println("Invalid command: " + command);
                    scanner.nextLine();
            }
        }
    }

    public Trique() {
        arr = new Object[DEFAULT_CAPACITY];
        size = 0;
        front = -1;
        rear = -1;
    }

    public void pushFront(T element) {
        ensureCapacity();
        if (isEmpty()) {
            front = rear = 0;
        } else {
            front = (front - 1 + arr.length) % arr.length;
        }
        arr[front] = element;
        size++;
    }

    public void pushMid(T element) {
        ensureCapacity();
        if (isEmpty()) {
            front = rear = 0;
        } else {
            int mid = (front + (size + 1) / 2) % arr.length;

            for (int i = rear; i != mid; i = (i - 1 + arr.length) % arr.length) {
                arr[i] = arr[(i - 1 + arr.length) % arr.length];
            }

            arr[mid] = element;

            rear = (rear + 1) % arr.length;
        }
        size++;
    }

    public void pushBack(T element) {
        ensureCapacity();
        if (isEmpty()) {
            front = rear = 0;
        } else {
            rear = (rear) % arr.length;
        }
        arr[rear + 1] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        int actualIndex = (front + index) % arr.length;
        return (T) arr[actualIndex];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == arr.length) {
            int newCap = arr.length * 2;
            Object[] newArr = new Object[newCap];
            int index = 0;
            for (int i = front; i != rear; i = (i + 1) % arr.length) {
                newArr[index++] = arr[i];
            }
            newArr[index] = arr[rear];
            front = 0;
            rear = index;
            arr = newArr;
        }
    }
}
