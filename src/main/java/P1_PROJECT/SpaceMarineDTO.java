package P1_PROJECT;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;


// Single Responsibility Principle'a aykırı:
// Birden fazla class eklemek,

public class SpaceMarineDTO implements Serializable{

    // Serileştirme
    public static final Long serialVersionNumber = 1L;

    // Fields
    private int id;
    private String name;
    private LocalDateTime birthDate;
    private String mainWeapon;
    private Date createdDate;
    private float grade;

    // Constructor parametresiz
    public SpaceMarineDTO() {
        this.id = 0;
        this.name = "";
        this.birthDate = LocalDateTime.now();
        this.grade = 0;
        this.mainWeapon = "";
    }

    // Constructor parametreli
    public SpaceMarineDTO(int id, String name, LocalDateTime birthDate, float grade) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.grade = grade;
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

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public float getGrade() {
        return grade;
    }
    
    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getMainWeapon() {
        return mainWeapon;
    }
    
    public void setMainWeapon(String mainWeapon) {
        this.mainWeapon = mainWeapon;
    }
}
