package AlgorithmSolving;

import java.util.Scanner;

public class ProblemSolver {

    Scanner scanner = new Scanner(System.in); 

    // 2. Example 
    public double oneVariableEquationSolving() {
        System
        .out
        .println("""
        Basit tek bilinmeyenli denklem çözümü
        sizden a ve b değerlerini alarak ax + b = 0 denklemini çözeceğim.
        Lütfen sırasıyla 'a' ve 'b' (TAM SAYI) değerlerinizi giriniz...
        """);
    
        double valueFromUserFirst = scanner.nextInt();
        double valueFromUserSecond = scanner.nextInt();

        double result = (-valueFromUserSecond / valueFromUserFirst);
        
        scanner.close();
        return result;
    }

    public String returnContinueBreak() {
        System
        .out
        .println("""
            
            Kullanıcının verdiği pozitif sayıya kadar toplayan algoritma.
            Ama girilen sayı 100'den büyükse 100'e kadar toplasın, ayrıca toplanırken sayıların arasında
            47 varsa 47'yi toplamanın dışında tutsun.

            Lütfen bir sayı giriniz.
            """);

        int valueFromUser = scanner.nextInt();


        if(Math.abs(valueFromUser) != valueFromUser) {
            return "Sayı negatif olamaz.";
        }

        valueFromUser = Math.min(100, valueFromUser);

        int sumResult = 0;
        for(int i = 1; i <= valueFromUser; i++) {                
            if(i == 47) {
                System.out.println("Verilen sayı 47 içerdiği için toplama eklenmedi.");
                continue;
            }
       
            sumResult += i;
        }
        return ("Sonuç: " + sumResult + ".\nSayı" + (sumResult%2 == 0 ? " Çift" : " Tek"));
    }
}