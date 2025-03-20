package P1_PROJECT;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import P1_PROJECT.Enum.EnumMarine;


// Single Responsibility Principle'a aykırı:
// Birden fazla class eklemek,

public class SpaceMarineDTO implements Serializable{
    
    // Uyarı kodları
    public static final String RED = "\033[0;31m"; 
    public static final String YELLOW = "\033[0;33m";
    public static final String RESET = "\033[0m"; 
    
    static {
        System.out.println(
            YELLOW + "SpaceMarineDTO classı yüklendi." + RESET
        );
    }

    // Serileştirme
    public static final Long serialVersionNumber = 1L;
    
    // Fields
    private long id;
    private EnumMarine marineType;
    private String name;
    private float grade; // calculated with killCount and successMissionCount
    private Integer killCount;
    private String surname;
    private LocalDateTime createdDate; // Date yerine LocalDateTime kullanıyoruz çünkü Date kullanırken saat bilgisi alamıyoruz.
    private String mainWeapon;
    private Integer successMissionCount;
    private LocalDateTime birthDate; // Date yerine LocalDateTime kullanıyoruz çünkü Date kullanırken saat bilgisi alamıyoruz.
    private long marineCounter = 0L;

    // Constructor parametresiz
    public SpaceMarineDTO() {
        this.id = (long) ++marineCounter;
        this.name = "test";
        this.killCount = 4;
        this.surname = "test";
        this.createdDate = LocalDateTime.now(); // Date yerine LocalDateTime kullanıyoruz çünkü Date kullanırken saat bilgisi alamıyoruz.
        this.mainWeapon = "test";
        this.successMissionCount = 4;
        this.grade = calculateGrade();
        this.birthDate = LocalDateTime.now(); // Date yerine LocalDateTime kullanıyoruz çünkü Date kullanırken saat bilgisi alamıyoruz.
        this.marineType = EnumMarine.ULTRAMARINES;
    }

    // Constructor parametreli
    public SpaceMarineDTO(String name, LocalDateTime birthDate, String surname, String mainWeapon, int successMissionCount, int killCount, EnumMarine marineType) {
        this.id = (long) ++marineCounter; 
        this.name = name;
        this.killCount = killCount;
        this.surname = surname;
        this.createdDate = LocalDateTime.now();
        this.mainWeapon = mainWeapon;
        this.successMissionCount = successMissionCount;
        this.grade = calculateGrade();
        this.birthDate = birthDate;
        this.marineType = marineType;
    }

    public float calculateGrade() {
        if(this.killCount == null || this.successMissionCount == null) {
            return 0;
        } else {
            return (float) (this.successMissionCount * 0.6 + this.killCount * 0.4);
        }
    }

    @Override
    public String toString() {
        return String.format(
            "Space Marine [ID: %d | İsim: %s %s | Silah: %s | Kill Count: %d | Görev Sayısı: %d | Puan: %.2f | Doğum Tarihi: %s | Oluşturulma Tarihi: %s]",
            id,
            name,
            surname,
            mainWeapon,
            killCount,
            successMissionCount,
            grade,
            birthDate,
            createdDate
        );
    }

    // Methodlar  
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade() {
        this.grade = calculateGrade();
    }

    public String getMainWeapon() {
        return mainWeapon;
    }
    
    public void setMainWeapon(String mainWeapon) {
        this.mainWeapon = mainWeapon;
    }

    public int getSuccessMissionCount() {
        return successMissionCount;
    }

    public void setSuccessMissionCount(int successMissionCount) {
        if(successMissionCount < 0) {
            throw new IllegalArgumentException("Success mission count cannot be negative");
        }
        this.successMissionCount = successMissionCount;
        this.grade = calculateGrade();
    }

    public int getKillCount() {
        return killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    public EnumMarine getMarineType() {
        return marineType;
    }

    public void setMarineType(EnumMarine marineType) {
        this.marineType = marineType;
    }
}
