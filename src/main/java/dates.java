import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

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

    // 2. Ortalama Yöntem
    public static String dateFormattingModern() throws NullPointerException {
        Date now = new Date();
        return String.format(
            "Zaman: %2d:%2d:%2d", 
            now.getHours(), 
            now.getMinutes(), 
            now.getSeconds()
        );
    }

    // 3. Best Practice Yöntem eğer Java 8 öncesi kullanıyorsak :)
    public static String dateFormattingBestPractice() throws NullPointerException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMMM-dd HH:mm:ss", new Locale("tr", "TR"));
        return String.format("Şimdiki zaman: %s", df.format(new Date()));
    }



    //-------------------------------- Gerçek Modern Date Formatting --------------------------------

    public static void localDateTimeFormattingGET() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Gün: " + now.getDayOfMonth());
        System.out.println("Ay: " + now.getMonthValue());
        System.out.println("Yıl: " + now.getYear());
        System.out.println("Saat: " + now.getHour());
        System.out.println("Dakika: " + now.getMinute());
        System.out.println("Saniye: " + now.getSecond());
    }

    public static void localDateTimeFormattingSET() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newSetterDate = now.withDayOfMonth(10)
        .withMonth(10)
        .withYear(2025)
        .withHour(10)
        .withMinute(10)
        .withSecond(10);
        System.out.println("Değiştirilmiş tarih: " + newSetterDate);
    }

    public static void main(String[] args) {
        localDateTimeFormattingGET();
        localDateTimeFormattingSET();
    }
}
