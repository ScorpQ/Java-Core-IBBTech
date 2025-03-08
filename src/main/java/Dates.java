import java.util.Date;

public class Dates {
    public static void dateMethods() {
        Date now = new Date();

        // Date sınıfının basit metotları fakat deprecated... 
        System.out.println("Bugünün tarihi: " + now);
        System.out.println("Haftanın günü: " + now.getDay());
        System.out.println("Ayın kaçıncı günü: " + now.getDate());
        System.out.println("Ay: " + ( 1 +now.getMonth()));
        System.out.println("Yıl: " + (1900 + now.getYear()));
    }

    // 1. Kötü Yöntem
    public static String dateFormattingPrimal() throws NullPointerException {
        Date now = new Date();

        String specialFormat = "Zaman: "
        .concat(Integer.toString(now.getHours()))
        .concat(":")
        .concat(Integer.toString(now.getMinutes()))
        .concat(":")
        .concat(Integer.toString(now.getSeconds()));

        return specialFormat;
    }

    // 2. Modern Yöntem
    public static String dateFormattingModern() throws NullPointerException {
        Date now = new Date();
        return String.format(
            "Zaman: %2d:%2d:%2d", 
            now.getHours(), 
            now.getMinutes(), 
            now.getSeconds()
        );
    }

    // 3. Best Practice Yöntem
    public static String dateFormattingBestPractice() throws NullPointerException {
        return new Date(String.format(
            "Zaman: %2d:%2d:%2d", 
            now.getHours(), 
            now.getMinutes(), 
            now.getSeconds()
        )).toString();
    }

    public static void main(String[] args) {
        System.out.println(dateFormattingModern());
    }
}
