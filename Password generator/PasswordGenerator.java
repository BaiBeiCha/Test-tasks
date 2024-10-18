import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {

    //Массивы доступных символов
    private static final char[] availableCharacters = getCharacters(33, 126);
    private static final char[] upperCaseLetters = getCharacters(65, 90);
    private static final char[] lowerCaseLetters = getCharacters(97, 128);
    private static final char[] numbers = getCharacters(48, 57);
    private static final char[] specialChars = getSpecialChars();

    public static void main(String[] args) {
        //Задание длины пароля
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите длину пароля (Длина должна находиться в диапазоне от 8 до 12 символов):");
        int passwordLength = scanner.nextInt();
        
        while (passwordLength < 8 || passwordLength > 12) {
            System.out.println("Неверная длина пароля!");
            System.out.println("Введите длину пароля (Длина должна находиться в диапазоне от 8 до 12 символов):");
            passwordLength = scanner.nextInt();
        }
        scanner.close();

        System.out.println("Пароль: " + generateValidatePassword(passwordLength));
    }

    //Создание конечного пароля
    private static String generateValidatePassword(int passwordLength) {
        String password = generatePassword(passwordLength);

        while (!isValidatePassword(password)) {
            password = generatePassword(passwordLength);
        }

        return password;
    }

    //Создание возможного пароля
    private static String generatePassword(int passwordLength) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < passwordLength; i++) {
            password.append(availableCharacters[random.nextInt(availableCharacters.length)]);
        }

        return password.toString();
    }

    /*Проверка пароля на соответствие критериям:
        - Наличие заглавных букв
        - Наличие строчных букв
        - Наличие цифр
        - Наличие специальных символов*/
    private static boolean isValidatePassword(String password) {
        return containChars(password, upperCaseLetters) && containChars(password, lowerCaseLetters)
                && containChars(password, numbers) && containChars(password, specialChars);
    }

    //Проверка пароля на наличие символов из диапазона requiredChars
    private static boolean containChars(String password, char[] requiredChars) {
        boolean haveChars = false;

        for (int i = 0; i < password.length(); i++) {
            for (char requiredChar : requiredChars) {
                if (password.charAt(i) == requiredChar) {
                    haveChars = true;
                    break;
                }
            }
        }

        return haveChars;
    }

    //Получение массива символов в диапазоне: [начало промежутка; конец промежутка]
    private static char[] getCharacters(int rangeBeginning, int rangeEnding) {
        char[] characters = new char[rangeEnding - rangeBeginning + 1];

        for (int i = rangeBeginning; i <= rangeEnding; i++) {
            characters[i - rangeBeginning] = (char) i;
        }

        return characters;
    }

    //Заполнение массива специальных символов
    private static char[] getSpecialChars() {
        char[] specialChars = new char[32];
        char[] range1 = getCharacters(33, 47);      //0 - 14
        char[] range2 = getCharacters(58, 64);      //15 - 21
        char[] range3 = getCharacters(91, 96);      //22 - 27
        char[] range4 = getCharacters(123, 126);    //28 - 31

        for (int i = 0; i < specialChars.length; i++) {
            if (i < range1.length) {
                specialChars[i] = range1[i];
            } else if (i < range1.length + range2.length) {
                specialChars[i] = range2[i - range1.length];
            } else if (i < range1.length + range2.length + range3.length) {
                specialChars[i] = range3[i - (range1.length + range2.length)];
            } else {
                specialChars[i] = range4[i - (range1.length + range2.length + range3.length)];
            }
        }

        return specialChars;
    }
}
