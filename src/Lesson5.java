package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.regex.*;
import java.io.File;

public class Main5 {

    public static void main(String[] args) throws Exception {
        System.out.println(inputData());
    }

    public static void Print(String data) throws Exception{
        String[] parts = data.split("\\|");
        if (parts.length == 2) {
            String surname = parts[0];
            String allData = parts[1];
            try {
                File f = new File(surname + ".txt");
                if (f.createNewFile()) {
                    System.out.println("Файл создан");
                } else {
                    System.out.println("Файл уже существует");
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(f));
                writer.write(allData);
                writer.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        } else {
            throw new Exception("Ошибка разбора данных. Не удалось найти разделитель '|'.");
        }
    }



    public static String parseString(String[] data) throws Exception {
        Pattern phone = Pattern.compile("^\\d+([^.])*$");
        Pattern dBirth = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
        Pattern seX = Pattern.compile("[fm]");
        Pattern dayPattern = Pattern.compile("0[1-9]|[12][0-9]|3[01]");
        Pattern monthPattern = Pattern.compile("0[1-9]|1[0-2]");



        String fio = "";
        String birthday = "";
        String phoneNumber = "";
        String sex = "";

        for (int i = 0; i < data.length; i++) {
            if (seX.matcher(data[i]).find()) {
                sex = data[i];
                continue;
            }
            if (phone.matcher(data[i]).find()) {
                phoneNumber = data[i];
                continue;
            }
            if (dBirth.matcher(data[i]).find()) {
                birthday = data[i];
                String[] dateParts = birthday.split("\\.");
                if (dayPattern.matcher(dateParts[0]).matches() && monthPattern.matcher(dateParts[1]).matches()) {
                    continue;
                } else {
                    throw new NonCorrectDateExcaption("Некорректный ввод числа");
                }
            }

                fio += data[i]+" ";

        }

        if (sex.isEmpty()) {
            throw new NonSexExcaption("Вы не ввели пол");
        }

        if (phoneNumber.isEmpty()) {
            throw new NonPhoneExcaption("Вы не ввели телефон");
        }

        if (birthday.isEmpty()) {
            throw new NonDateBirthExcaption("Вы не ввели дату");
        }

        if (fio.split(" ").length != 3) {
            throw new FIOExcaption("Вы неправильно ввели ФИО");
        }

        String surname = fio.split(" ")[0];
        String dataToPrint = surname+"|"+fio+birthday+" "+phoneNumber+" "+sex;

        Print(dataToPrint);
        return "Ваш файл с названием '" + surname +"' успешно создан!";
    }

    public static String inputData() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Фамилия Имя Отчество дата рождения номер телефона пол в любом порядке, разделенным пробелом.\n" +
                "Например, фамилия, имя, отчество - строки\n" +
                "дата рождения - строка формата dd.mm.yyyy\n" +
                "номер телефона - целое беззнаковое число без форматирования\n" +
                "пол - символ латиницей f или m.");
        String s = scanner.nextLine();
        if (s.split(" ").length == 6) {
            return parseString(s.split(" "));
        } else {
            throw new NonFormatDataExcaption("Введите корректный набор данных");
        }

    }

    public static class NonFormatDataExcaption extends Exception {
        public NonFormatDataExcaption(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class NonSexExcaption extends Exception {
        public NonSexExcaption(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class NonPhoneExcaption extends Exception {
        public NonPhoneExcaption(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class NonDateBirthExcaption extends Exception {
        public NonDateBirthExcaption(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class FIOExcaption extends Exception {
        public FIOExcaption(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class NonCorrectDateExcaption extends Exception {
        public NonCorrectDateExcaption(String errorMessage) {
            super(errorMessage);
        }
    }
}
