package P1_PROJECT.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Scanner;

import P1_PROJECT.utils.EnumMarine;
import P1_PROJECT.utils.errorColors;

// Single Responsibility Principle'a aykırı:
// Birden fazla class eklemek,

public class SpaceMarineDTO extends HumanDTO implements Serializable{
    
    static {
        System.out.println(
            errorColors.YELLOW + "SpaceMarineDTO classı yüklendi." + errorColors.RESET
        );
    }

    // Serileştirme
    public static final Long serialVersionNumber = 1L;
    
    // Fields
    private EnumMarine marineType;
    private String mainWeapon;
    private float grade; // killCount ve successMissionCount ile hesaplanır. (..resultTerm ile aynı..)
    private Integer killCount;
    private Integer successMissionCount;
    private String isGrandMaster; // Belli bir Grade üstünde ise Grand Master sayılır.
    private long marineCounter = 0L;

    // Constructor parametresiz
    public SpaceMarineDTO() {
        super();
        this.killCount = 4;
        this.mainWeapon = "test";
        this.successMissionCount = 4;
        this.grade = calculateGrade();
        this.isGrandMaster = isGrandMaster();
        this.marineType = EnumMarine.ULTRAMARINES;
    }

    // Constructor parametreli
    public SpaceMarineDTO(long id, String name, String surname, LocalDateTime birthDate, String mainWeapon, int successMissionCount, int killCount, EnumMarine marineType) {
        super(id, name, surname, birthDate);
        this.mainWeapon = mainWeapon;
        this.killCount = killCount;
        this.successMissionCount = successMissionCount;
        this.grade = calculateGrade();
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
            super.toString(),
            mainWeapon,
            killCount,
            successMissionCount,
            grade,
            marineType
        );
    }

    ///////////////////////////// Methods /////////////////////////////
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
