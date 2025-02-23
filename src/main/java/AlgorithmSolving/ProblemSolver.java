package AlgorithmSolving;

import java.util.Scanner;

public class ProblemSolver {

    Scanner scanner = new Scanner(System.in); 

    public double oneVariableEquationSolving() {
        
        System
        .out
        .println("""
        Basit tek bilinmeyenli denklem çözümü
        sizden a ve b değerlerini alarak ax + b = 0 denklemini çözeceğim.
        Lütfen sırasıyla 'a' ve 'b' (TAM SAYI) değerlerinizi giriniz...
        """);
    
        double a = scanner.nextInt();
        double b = scanner.nextInt();

        double result = (-b / a);
        
        scanner.close();
        return result;
    }
}
