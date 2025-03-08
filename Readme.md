### **JAVA DOSYASI NASIL ÇALIŞTIRILIR?**

https://stackoverflow.com/questions/16137713/how-do-i-run-a-java-program-from-the-command-line-on-windows

- CD src/ yapmadan kök dizinden çalıştırmak istersek aşağıdaki komutlar yeterli.
 ```java
### Derleme için
javac src/main/java/AlgorithmSo/ProblemSolver.java

### Çalıştırma için
java -cp src/main/java AlgorithmSo.ProblemSolver
```

- CD src/ yaparak hedef dizine gidersek aşağıdaki komutlar yeterli
 ```java
### Derleme için
javac AlgorithmSo/ProblemSolver.java

### Çalıştırma için
java AlgorithmSo.ProblemSolver
```

- Java 8'den itibaren javac ile derlemeye gerek yok doğrudan java ile run edilebilir.
Çünkü ...

- Not: Eğer iki farklı ve birbiriyle bağlantılı dosyada çalıştıysak aynı anda derlememiz lazım, daha sonra tekrardan merkez dosyayı çalıştırırız.

```java
### Derleme için
javac src/main/java/AlgorithmSolving/ProblemSolver.java   src/main/java/App.java

### Çalıştırma için
java -cp src/main/java App

```

**YA DA** Tüm bunlarla uğraşmak yerine doğrudan MAVEN ile çalıştırabilirsin böylece dosyaları topluca derleyip sonra da çalıştırmana gerek kalmaz.

```java
### Derlemek için
mvn compile // Sanırım mvn exec:java zaten derliyor?

### Derlemek ve Çalıştırma için
mvn exec:java -Dexec.mainClass="App"

//src/main/java'nın altında, paketin içinde olsaydı 'Package.App' olmalıydı.
```

- Terminal yerine F5 ile çalıştırabilirsin (MacOS: world fn + f5)

----------------------------------------------------------------------------------------------------------

### **1. MAVEN Nedir?**

Maven, Java projenizin dosya yapısını oluşturur, kütüphaneleri yükler, projenizi derler ve çalıştırır ayrıca projenizi paketler (***jar or war***).

**Hatırlatıcı Kodlar**

 Terminal komutları için Maven yüklenir `brew install maven`

Maven'ın doğru yüklendiğini kontrol eder `mvn --version`

Maven ile projeyi çalıştırır `mvn exec:java -Dexec.mainClass="com.core.X.Y"`




### **2. POM.XML Nedir?**

Projenin bağımlılıklarını ve projenin çalıştırılması için gerekli olan diğer bilgileri içerir.


### **3. JDK Nedir?**

JDK, Java Development Kit'in kısaltmasıdır. Java programlama dilinin geliştirilmesi için gerekli olan araçları içerir.

- **JRE** : Java Runtime Environment, java kodlarının çalıştırılması için ortamı hazırlar ama sadece çalıştırır. Kod yazmamızı sağlamaz ve derleyemez.

    **NOT: Sadece java ile çalışan bir program çalıştırmak istersek JRE yüklemek yeterlidir.
JDK zaten JRE'yi içerdiği için JDK her türlü çalıştırır.**

- **JVM** : Java Virtual Machine, JRE ile run edilecek kodun local yerine JVM adında sanal bir makine üzerinde çalıştırılmasını sağlar.
**HATTA**, Java kod dosyaları localde .java ile biterken, JVM içerisinde çalıştırılırken .class ile biter.
  - JVM GÖREVLERİ; 
    - Byte içeren .class dosyasını JRE çalıştırmak.
    - Bellek Yönetimi (Garbage Collection)
    - Multithreading yani sync/async işlemleri kapsar
    - Platform bağımsızlığı sağlar. Çünkü JVM'in kendisi bir sanal bir makinedir.

- **JAVAC (Compiler - Tercüman)** : JAVAC, Java kodlarını derlemek yani byte koduna çevirmek için kullanılır.

    **NOT: Yani Java aslında bir Compiler da sayılır. Aynı zamanda bir Interpreter da sayılır.
    Çünkü Java kodlarını JVM üzerinde çalıştırırken, derleme ve yorumlama işlemi yapar.**



----------------------------------------------------------------------------------------------------------


### **Naming Conventions**

https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html
 
- Değişkenler için;
  - CamelCase kullanılır, '_' veya '$' ile başlanabilir.
  - Sayı ile bitebilir ama sayı ile başlanmaz.

- Sınıflar için;
  - PascalCase kullanılır. Yani ilk harf büyük, diğer harfler küçük olur.
  - Sadece harflerden oluşur.

  
- Sabitler için;
  - Tüm harfler büyük olur. Aralarında '_' kullanılır.

  ----------------------------------------------------------------------------------------------------------


### **JAVA DATA TYPES**

  1. **Primitive Types** : Java'nın built-in veri tipleri. NULL değeri alamazlar ve default değer olarka '0' atanırlar. Obje değildirler ayrıca
  Bellekte doğrudan tutulurlar. Değer bazlıdır pointer'ı bulunmaz.
   
    - Tam sayı tipleri: `byte`, `short`, `int`, `long`
    - Ondalıklı sayılar: `float`, `double`
    - Karakter tipi: `char`
    - Mantıksal tip: `boolean`

  2. **Wrapper Classes** : Primitive tiplerin object versiyonları. NULL değeri alabilirler.

     | Primitive Type | Wrapper Class |
     |---------------|---------------|
     | byte          | Byte          |
     | short         | Short         |
     | int           | Integer       |
     | long          | Long          |
     | float         | Float         |
     | double        | Double        |
     | char          | Character     |
     | boolean       | Boolean       |

  Wrapper'lar büyük harf ile tanımlanırlar, avantajları ise;
  - NULL değeri alabilirler.
  - String'e çevirebilirler.
  - Collection'larda kullanılabilirler.
  - Autoboxing ve unboxing işlemleri yapılabilirler.
  
  Örnek Kullanım:
  ```java
  // Primitive Type
  int sayi = 42;                  // NULL olamaz, metodları yok
  
  // Wrapper Type
  Integer wrapperSayi = 42;       // NULL olabilir
  String metin = wrapperSayi.toString();  // Metod kullanabilir
  Integer nullDeger = null;       // NULL atanabilir
  ```

  ### **Autoboxing ve Unboxing**
  
  **Autoboxing**: Primitive type'ın otomatik olarak Wrapper Class'a dönüştürülmesi
  
  **Unboxing**: Wrapper Class'ın otomatik olarak primitive type'a dönüştürülmesi
  
  ```java
  // Autoboxing Örnekleri (primitive -> wrapper)
  int primitiveInt = 42;
  Integer wrapperInt = primitiveInt;    // Otomatik dönüşüm

  // Unboxing Örnekleri (wrapper -> primitive)
  Integer wrapperSayi = new Integer(100);
  int primitiveSayi = wrapperSayi;      // Otomatik dönüşüm
  ```

  ----------------------------------------------------------------------------------------------------------

  ### **String Yapısı**
  Javascript'te benzer çok methodu bulunuyor:
    
    .trim()
    .equals()
    .concat()
    .endsWith()
    .substring()
    .startsWith()
    .equalsIgnoreCase()
  
  **Java'da "==" ile ".equals()" farkı**

   ```java
  String word1 = 'TEST'
  String word2 = 'TEST'

  word1 == word2 
  // Bu ifade bize true döndürecek ama sebebi içeriğin aynı olması değil.

  word1.equals(word2)
  // Bu ifade de bize true döndürecek çünkü içerik aynı.
  ```

  Temel Fark :
  - `==` : Referans karşılaştırması yapar (hafızadaki adreslerini karşılaştırır)

  - `equals()` : İçerik karşılaştırması yapar (metinlerin değerlerini karşılaştırır)

  ```java
    String a = "merhaba"; // String Pool'da oluşturulur
    String b = "merhaba"; // Aynı String Pool'daki referansı kullanır
    String c = new String("merhaba"); // Heap'te yeni bir nesne oluşturur

    System.out.println(a == b);       // true (aynı referans)
    System.out.println(a == c);       // false (farklı referans)
    System.out.println(a.equals(c));  // true (aynı değer)

    HAFIZA
    |----------------------------------|
    |                                  |
    |   HEAP (Genel Hafıza Alanı)     |
    |   |-----------------------|     |
    |   |  String Pool         |     |
    |   |  |--------------"|   |     |
    |   |  | "Ahmet"   <--|---|--------- string1
    |   |  |            <--|---|--------- string2
    |   |  |              |   |     |
    |   |  |--------------"|   |     |
    |   |                     |     |
    |   |  "Ahmet" <------------|-------- object1
    |   |  "Ahmet" <------------|-------- object1
    |   |                     |     |
    |   |-----------------------|     |
    |                                  |
    |----------------------------------|
  ```

  - String Pool, aynı değere sahip String'lerin bellekte tek bir kopya olarak saklanmasını sağlar.
  
  - Bellek kullanımını optimize eder.
  - Literal String'ler otomatik olarak String Pool'da saklanır.
  - new String() kullanımı String Pool'u bypass eder.


----------------------------------------------------------------------------------------------------------

  ### **CASTING**
  Tipler arasında dönüşüm yapmamızı sağlar.  

  ```java
  // Widening Cast - Implicit Cast
  byte castType1 = 100;
  int castType2 = cartType1;

  //  Narrowing Cast - Explicit Cast
  int castType1 = 99999999
  byte castType2 = castType1
  // Bu kısımda "castType2" -1 olur.

  // Char To Int
  char charType = '&'
  int ascii = charType
  // ascii = 38 olur.
  int ascii = 38
  char charTpe = ascii
  // charType = & olur.

  // String to Int
  String castString = "22"
  int castInt1 = Integer.valueOf(castString)
  int castInt1 = Integer.parseInt(castString)
  ```
  ### 'valueOf' ile 'parseInt' farkı nedir? 

  - Şöyle, parseInt primitive int döndürürken
  valueOf Integer objesi döndürür. 

### parseInt vs valueOf Karşılaştırması

1. **parseInt:**
   - Primitive int döndürür
   - Biraz daha hızlıdır
   - Cache mekanizması yoktur
   - Sadece sayısal dönüşüm için kullanılır

2. **valueOf:**
   - Integer objesi döndürür
   - Cache mekanizması vardır (-128 ile 127 arası)
   - Nesne yönelimli programlamada tercih edilir
   - Daha esnek kullanım sunar

3. **Genel Kural:**
   - Primitive tip yeterliyse `parseInt()`
   - Obje gerekiyorsa `valueOf()`
   - Cache önemliyse `valueOf()` 


----------------------------------------------------------------------------------------------------------

  ### **FINAL**

 - Değişkende kulllanıyorsak asla yeni bir değer atanamaz hale gelir.

 - Eğer class'ta kullandıysam extend edemeyiz.

 - Eğer method'ta kullandıysam override yapamayız. (Ezemeyiz)

 ----------------------------------------------------------------------------------------------------------

   ### **JAVA'DA ADRESLEME**


  #### STACK 
  - LIFO mantığı ile çalışır. Primitive türler stack'te tutulur. 

  ```java
    | C++ Kitabı     | <- En son eklenen (pop ile ilk bu çıkar)
    |----------------|
    | Python Kitabı  | <- Ortadaki kitap
    |----------------|
    | Java Kitabı    | <- İlk eklenen (en son bu çıkar)
    |----------------| 


  // Stack'te oluşturulur.
  int x = 5;        
  // Stack'te oluşturulur.
  int y = 10;      
  // Stack'te tutulur.
  int toplam = x + y;
  ```

  #### HEAP 

  - LIFO mantığı yoktur. İstenilen veri çıkarılabilir.  Object'ler, Primitive türlerin dizileri ve Autoboxing yapılan değişkenler Heap'te tutulur. 

```java
       1
      / \
     2   8
    /
   5

   // Heap - Herhangi bir elemana erişim
  PriorityQueue<Integer> heap = new PriorityQueue<>();
  heap.add(1);
  heap.add(2);
  heap.add(8);
  int enKucuk = heap.poll(5); // 5 çıkar (MinHeap olduğu için)
```
- Yukarıda heap benzeri bir görsel koyuldu.  Özet:
  Stack:
    - LIFO (Son Giren İlk Çıkar)
    
    - Hızlı erişim
    
    - Sınırlı boyut
    
    - Otomatik bellek yönetimi
    
  Heap:
    - Sıralı veya sırasız erişim
    
    - Dinamik boyut
    
    - Daha yavaş erişim
    
    - Garbage Collector ile yönetim

     ----------------------------------------------------------------------------------------------------------

### **GARBAGE COLLECTOR**
