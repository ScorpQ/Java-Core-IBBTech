package com.core.java_basics;
  
public class javaTypes {

    // 'psvm' ile main methodu hızlıca oluşturulabilir.
    public static void main(String[] args) {

        // 'sout' ile print fonksiyonu hızlıca oluşturulabilir.
        System.out.println("Hello World");

        // Variable naming rules;
        // 1-) camelCase kullanılır.
        // 2-) _ veya $ ile başlanabilir.
        // 3-) Sayı ile bitebilir ama sayı ile başlanmaz.

        // 1. Primitive Types: 8 adettir. Hepsi küçük harfle başlar. 'NULL' değeri alamazlar.
            // Tam sayılar (4 ADET)
                byte b1 = 127;  byte b2 = -128;
                short s1 = 12345; short s2 = -12345;
                int i1 = 1234567890; int i2 = -1234567890;
                long l1 = 1234567890123456789L; long l2 = -1234567890123456789L;

            // Ondalıklı sayılar (2 ADET)
                float f1 = 123.456f; float f2 = -123.456f;
                double d1 = 123.4567890; double d2 = -123.4567890;

            // Karakter (1 ADET)
                char c = 'A';

            // Mantıksal (1 ADET)
                boolean b = false;

        // 2. Non-Primitive Types 
        // 3. Reference Types 
        // 4. Object Types  
        // 5. Array Types 
        // 6. Collection Types


        // 'final' Constant değişken oluştururken kullanılır.
        final int itemCount = 260;
        
    }
}

