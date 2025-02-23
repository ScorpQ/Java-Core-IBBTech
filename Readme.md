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