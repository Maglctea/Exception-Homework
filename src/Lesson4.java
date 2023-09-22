import java.util.NoSuchElementException;
import java.util.Scanner;

public class Lesson4 {
    public static String input() {
        System.out.println("Введите значение");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();

        if (value.isEmpty())
            throw new NoSuchElementException();

        return value;
    }

    public static void main(String[] args) {
        input();
    }

}