import java.util.Random;

public class Animals implements Topics{

    private static final String[] words = {
            "собака", "кошка", "лошадь", "корова", "свинья", "овца", "коза", "курица",
            "утка", "гусь", "индюк", "петух", "цыпленок", "кролик", "хомяк", "крыса",
            "мышь", "попугай", "канарейка", "воробей", "голубь", "сокол", "животное",
            "орел", "ястреб", "филин", "сова", "ворон", "лебедь", "фламинго", "пингвин", "страус",
            "жираф", "слон", "тигр", "лев", "леопард", "гепард", "зебра", "носорог", "бегемот", "обезьяна",
            "шимпанзе", "горилла", "кенгуру", "коала", "панда", "медведь", "волк", "лиса", "рыба", "птица"
    };

    @Override
    public String getRandomWord() {
        Random rand = new Random();
        return words[rand.nextInt(words.length)];
    }
}