import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Topic topic = getTopic();
        int lives = 7;
        boolean isActive = true;

        while (isActive) {
            boolean won = false;
            Topics currentTopic = null;
            lives = 7;

            switch (topic) {
                case ANIMALS -> currentTopic = new Animals();
                case NATURE -> currentTopic = new Nature();
                case FOOD -> currentTopic = new Food();
                case COUNTRIES -> currentTopic = new Countries();
                case CHEMICAL_ELEMENTS -> currentTopic = new ChemicalElements();
            }

            String word = currentTopic.getRandomWord();
            List<Integer> knownPositions = new ArrayList<>();
            List<String> incorrectLetters = new ArrayList<>();

            System.out.println("Количество жизней: " + lives);
            printHangman(lives);
            //Вывод полностью скрытого слова
            System.out.println(makeUnknownWord(word, new int[]{-1}));

            while (lives > 0 && !won) {
                if (!incorrectLetters.isEmpty()) {
                    System.out.println("Неверные буквы: " + convertToString(incorrectLetters));
                }

                System.out.println("Введите букву: ");

                String letter = scanner.nextLine();
                while (letter.length() > 1) {
                    System.out.println("Неверное значение! \nВведите букву: ");
                    letter = scanner.nextLine();
                }

                if (containLetter(letter.charAt(0), word)) {
                    int[] atPositions = getPositions(word, letter.charAt(0));

                    for (int atPosition : atPositions) {
                        knownPositions.add(atPosition);
                    }
                } else {
                    System.out.println("Такой буквы нет!");
                    incorrectLetters.add(letter);
                    lives--;
                }

                System.out.println("\nКоличество жизней: " + lives);
                printHangman(lives);
                
                String unknownWord = makeUnknownWord(word, convertToInt(knownPositions));
                System.out.println(unknownWord);

                if (unknownWord.equals(word)) {
                    won = true;
                }
            }

            if (won) {
                System.out.println("Слово было отгадано!");
            } else {
                System.out.println("Слово не было отгадано!");
            }

            System.out.println("\n\n\nВыберете действие: \nПродолжить:" + Constants.continueProgram
                    + "\nЗавершить:" + Constants.endProgram + "\nСменить тему:" + Constants.changeTopic);
            switch (scanner.nextLine()) {
                case Constants.continueProgram:
                    break;
                case Constants.endProgram:
                    isActive = false;
                    break;
                case Constants.changeTopic:
                    topic = getTopic();
                    break;
                default:
                    System.out.println("Неверная команда, выполнение программы будет прекращено!");
                    isActive = false;
                    break;
            }
        }
    }

    //Определение темы слова
    private static Topic getTopic() {
        System.out.println("Введите тему:");
        System.out.println("Темы: животные/ж    природа/п    еда и напитки/е    страны/с    " +
                "химические элементы/х    случайная/сл");
        String input = scanner.nextLine().toLowerCase();

        switch (input) {
            case "животные", "ж":
                return Topic.ANIMALS;
            case "природа", "п":
                return Topic.NATURE;
            case "еда", "е":
                return Topic.FOOD;
            case "страны", "с":
                return Topic.COUNTRIES;
            case "химические элементы", "х":
                return Topic.CHEMICAL_ELEMENTS;
            case "случайная", "сл":
                return getRandomTopic();
            default:
                System.out.println("Введена неверная тема! Будет выбрана случайная тема!");
                return getRandomTopic();
        }
    }

    //Получение случайной темы
    private static Topic getRandomTopic() {
        Random random = new Random();
        return Topic.values()[random.nextInt(Topic.values().length)];
    }

    //Проверка на наличие буквы в слове
    private static boolean containLetter(char letter, String word) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                return true;
            }
        }

        return false;
    }

    //Получение слова с скрытими неотгаданными буквами
    private static String makeUnknownWord(String word, int[] knownPositions) {
        StringBuilder unknownWord = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            boolean found = false;

            for (int pos : knownPositions) {
                if (i == pos) {
                    found = true;
                    break;
                }
            }

            if (found) {
                unknownWord.append(word.charAt(i));
            } else {
                unknownWord.append(Constants.unknownChar);
            }
        }

        return unknownWord.toString();
    }

    //Получение позиций буквы в слове
    private static int[] getPositions(String word, char letter) {
        List<Integer> positions = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                positions.add(i);
            }
        }

        return convertToInt(positions);
    }

    //Конвертация списка Integer в массив int
    private static int[] convertToInt(List<Integer> list) {
        int[] ints = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(i);
        }

        return ints;
    }

    //Конвертация списка в строку (перечисление элементов списка)
    private static String convertToString(List<String> list) {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                String toAdd = list.get(i) + ", ";
                string.append(toAdd);
            } else {
                string.append(list.get(i));
            }
        }

        return string.toString();
    }

    //Вывод картинки виселицы в консоль (то, какая картинка будет выведена, зависит от количества жизней)
    private static void printHangman(int lives) {
        System.out.println(Constants.hangmanPics[lives]);
    }
}
