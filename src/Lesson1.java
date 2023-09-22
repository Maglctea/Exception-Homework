import java.util.Scanner;


public class Lesson1 {

    //ЗАДАНИЕ 3
    public static Float getFloat() {
        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        while (true) {
            System.out.println("Введите значение");
            String valueString = scanner.nextLine();
            try {
                return Float.valueOf(valueString);
            } catch (NumberFormatException e) {
                System.out.println("Невернеый ввод!");
            }
        }
    }

    public static void main(String[] args) {
        getFloat();
    }
}