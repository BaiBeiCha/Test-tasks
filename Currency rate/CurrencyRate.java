import java.util.Scanner;

public class CurrencyRate {

    private static final Scanner scanner = new Scanner(System.in);

    private static final String continueProgram = "п";
    private static final String endProgram = "з";

    public static void main(String[] args) {
        //Получение курсов валют
        System.out.println("Введите курсы валют в виде:" +
                            "\nкурс валюты №1 курс валюты №2" +
                            "\nкурс валюты №1 :  курс валюты №2" +
                            "\nкурс валюты №1 - курс валюты №2");

        float[] euro = getCurrencyRate("EUR (Евро)");
        float[] dollar = getCurrencyRate("USD (Доллар)");
        float[] ruble = getCurrencyRate("RUB (Российский рубль)");
        float[] yuan = getCurrencyRate("RMB (Юань)");
        float[] pound = getCurrencyRate("GBP (Фунт стерлингов)");

        System.out.println("\n\n\n");

        boolean isActive = true;
        while (isActive) {
            System.out.println("Введите сумму и валюту:");
            String currency, input = scanner.nextLine();
            String[] values = input.split(" ");
            currency = values[1];
            float usd, rub, rmb, gbp, eur, byn, sum = Float.parseFloat(values[0].replace(',', '.'));

            //Конвертация валют
            switch (currency) {
                case "EUR", "eur", "euro":
                    eur = sum;
                    byn = eur * (euro[1] / euro[0]);
                    usd = byn * (dollar[0] / dollar[1]);
                    rub = byn * (ruble[0] / ruble[1]);
                    rmb = byn * (yuan[0] / yuan[1]);
                    gbp = byn * (pound[0] / pound[1]);
                    break;
                case "USD", "usd", "dollar":
                    usd = sum;
                    byn = usd * (dollar[1] / dollar[0]);
                    eur = byn * (euro[0] / euro[1]);
                    rub = byn * (ruble[0] / ruble[1]);
                    rmb = byn * (yuan[0] / yuan[1]);
                    gbp = byn * (pound[0] / pound[1]);
                    break;
                case "RUB", "rub", "Russian ruble", "ruble":
                    rub = sum;
                    byn = rub * (ruble[1] / ruble[0]);
                    eur = byn * (euro[0] / euro[1]);
                    usd = byn * (dollar[0] / dollar[1]);
                    rmb = byn * (yuan[0] / yuan[1]);
                    gbp = byn * (pound[0] / pound[1]);
                    break;
                case "RMB", "rmb", "yuan":
                    rmb = sum;
                    byn = rmb * (yuan[1] / yuan[0]);
                    eur = byn * (euro[0] / euro[1]);
                    usd = byn * (dollar[0] / dollar[1]);
                    rub = byn * (ruble[0] / ruble[1]);
                    gbp = byn * (pound[0] / pound[1]);
                    break;
                case "GBP", "gbp", "pound":
                    gbp = sum;
                    byn = gbp * (pound[1] / pound[0]);
                    eur = byn * (euro[0] / euro[1]);
                    usd = byn * (dollar[0] / dollar[1]);
                    rub = byn * (ruble[0] / ruble[1]);
                    rmb = byn * (yuan[0] / yuan[1]);
                    break;
                case "BYN", "byn", "Belarusian ruble":
                    byn = sum;
                    eur = byn * (euro[0] / euro[1]);
                    usd = byn * (dollar[0] / dollar[1]);
                    rub = byn * (ruble[0] / ruble[1]);
                    rmb = byn * (yuan[0] / yuan[1]);
                    gbp = byn * (pound[0] / pound[1]);
                    break;
                default:
                    System.out.println("Неверная валюта!");
                    byn = 0;
                    eur = 0;
                    usd = 0;
                    rub = 0;
                    rmb = 0;
                    gbp = 0;
                    break;
            }

            String message =  "BYN: " + byn + "\n"
                            + "EUR: " + eur + "\n"
                            + "USD: " + usd + "\n"
                            + "RUB: " + rub + "\n"
                            + "RMB: " + rmb + "\n"
                            + "GBP: " + gbp + "\n";
            System.out.println(message);

            System.out.println("Выберите: продолжить " + continueProgram + " или закончить " + endProgram);
            switch (scanner.nextLine()) {
                case continueProgram:
                    break;
                case endProgram:
                    isActive = false;
                    break;
                default:
                    System.out.println("Неверная команда, выполнение программы будет прекращено!");
                    isActive = false;
                    break;
            }
        }
    }

    //Получение курса валюты к BYN
    private static float[] getCurrencyRate(String currency1) {
        System.out.println("Введите курс:\n" + currency1 + " : " + "BYN");
        String input = scanner.nextLine();
        return getValues(input.replace(',', '.'));
    }

    //Получение массива значений валют
    private static float[] getValues(String input) {
        String[] inputArray;

        input = input.replace(" : ", " ")
                .replace(":", " ")
                .replace(" - ", " ")
                .replace("-", " ");

        try {
            inputArray = input.split(" ");
        } catch (Exception e) {
            System.out.println("Введено неверное значение!");
            inputArray = new String[]{"1", "1"};
        }

        float[] values = new float[2];
        for (int i = 0; i < inputArray.length; i++) {
            if (!inputArray[i].isEmpty()) {
                if (i <= values.length - 1) {
                    values[i] = Float.parseFloat(inputArray[i]);
                } else {
                    values[values.length - 1] = Float.parseFloat(inputArray[i]);
                }
            }
        }

        return values;
    }
}
