package P1_PROJECT;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;


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
    private int id;
    private String name;
    private float grade; // calculated with killCount and successMissionCount
    private Integer killCount;
    private String surname;
    private Date createdDate;
    private String mainWeapon;
    private Integer successMissionCount;
    private LocalDateTime birthDate;

    // Constructor parametresiz
    public SpaceMarineDTO() {
        this.id = 5;
        this.name = "";
        this.grade = 0;
        this.killCount = 0;
        this.surname = "";
        this.createdDate = new Date(System.currentTimeMillis());
        this.mainWeapon = "";
        this.successMissionCount = 0;
        this.birthDate = LocalDateTime.now();
    }

    // Constructor parametreli
    public SpaceMarineDTO(String name, LocalDateTime birthDate, float grade, String surname, String mainWeapon, int successMissionCount, int killCount) {
        this.id = 5;
        this.name = name;
        this.grade = calculateGrade();
        this.killCount = killCount;
        this.surname = surname;
        this.createdDate = new Date(System.currentTimeMillis());
        this.mainWeapon = mainWeapon;
        this.successMissionCount = successMissionCount;
        this.birthDate = birthDate;
    }

    public float calculateGrade() {
        if(killCount == null || successMissionCount == null) {
            return 0;
        } else {
            return (float) (this.successMissionCount * 0.6 + this.killCount * 0.4);
        }
    }

    // Methodlar  
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
