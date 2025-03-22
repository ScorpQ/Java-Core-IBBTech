package P1_PROJECT.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Scanner;

import P1_PROJECT.utils.EnumMarine;
import P1_PROJECT.utils.errorColors;

// Single Responsibility Principle'a aykırı:
// Birden fazla class eklemek,

public class SpaceMarineDTO implements Serializable{
    
    static {
        System.out.println(
            errorColors.YELLOW + "SpaceMarineDTO classı yüklendi." + errorColors.RESET
        );
    }

    // Serileştirme
    public static final Long serialVersionNumber = 1L;
    
    // Fields
    private long id;
    private String name;
    private String surname;
    private EnumMarine marineType;
    private String mainWeapon;
    private float grade; // killCount ve successMissionCount ile hesaplanır. (..resultTerm ile aynı..)
    private Integer killCount;
    private Integer successMissionCount;
    private String isGrandMaster; // Belli bir Grade üstünde ise Grand Master sayılır.
    private LocalDateTime birthDate; // Date yerine LocalDateTime kullanıyoruz çünkü Date kullanırken saat bilgisi alamıyoruz.
    private LocalDateTime createdDate; // Date yerine LocalDateTime kullanıyoruz çünkü Date kullanırken saat bilgisi alamıyoruz.
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
        this.isGrandMaster = isGrandMaster();
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

    public String isGrandMaster() {
        if(this.grade >= 85) {
            return "Grand Master";
        } else {
            return "Not Grand Master";
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
            createdDate,
            marineType
        );
    }

    ///////////////////////////// Methods /////////////////////////////
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

    public String getIsGrandMaster() {
        return isGrandMaster;
    }

    public void setIsGrandMaster(String isGrandMaster) {
        this.isGrandMaster = isGrandMaster;
    }
}
