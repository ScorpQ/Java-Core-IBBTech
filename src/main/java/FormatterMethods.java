import java.util.Formatter;

public class FormatterMethods {

    public static void basicFormatter() {
        // Formatter kullanırken kapatmak zorundayız.
        Formatter formatter = new Formatter();
        formatter.format("Hello, %s", "World");
        System.out.println(formatter);
        formatter.close(); // Bellek temizlenir.
    }

    // Best Practice
    public static void formatterWithString() {
        // String.format yazarsak kapatmak zorunda kalmayız. GB otomatik kapatır.
        String formatter = String.format("Hello, %s", "World");
        System.out.println(formatter);
    }

    // Best Practice
    public static void formatterWithStringAdvance() {
        // String.format yazarsak kapatmak zorunda kalmayız. GB otomatik kapatır.
        String warhammerCharacter = "Titus";
        String side = "Imperium";
        int power = 1000;
        String weapon = "Buster Sword";
        double armor = 100.5505;
        String formatter = String
            .format("My random 40K Character: %-5s %s %d %s %.5f",
            warhammerCharacter, side, power, weapon, armor);
        System.out.println(formatter);
    }
    public static void main(String[] args) { 
        basicFormatter();
        formatterWithString();
        formatterWithStringAdvance();
    }
}
