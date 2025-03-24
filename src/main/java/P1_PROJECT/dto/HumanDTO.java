package P1_PROJECT.dto;

import java.util.Date;
import java.time.LocalDateTime;

// humanDTO classı, SpaceMarineDTO ve PersonDTO classlarının ortak özelliklerini içerir.
// Ayrıca bu class'ı doğrudan kullanmayacağımız için 'ABSTRACT' olarak belirlememiz daha mantıklıdır.
// Bu sayede; humanDTO humandto = new humanDTO(); gibi bir kullanım yani bir nesne üretemeyiz.
abstract public class HumanDTO {

    private long id;
    private String name;
    private String surname;
    private LocalDateTime birthDate; // Date yerine LocalDateTime kullanıyoruz çünkü Date kullanırken saat bilgisi alamıyoruz.
    private Date createdDate; // Date yerine LocalDateTime kullanıyoruz çünkü Date kullanırken saat bilgisi alamıyoruz.

    // Bütün methodlar protected olarak belirtilmiştir. 
    //Böylece sadece extend edilen class'lar bu methodları kullanabilir.

    // parametresiz constructor
    public HumanDTO() {
        this.id = 0;
        this.name = "name unknow";
        this.surname = "surname unknow";
        this.birthDate = LocalDateTime.now();
        this.createdDate = new Date(System.currentTimeMillis());
    }

    // parametreli constructor
    public HumanDTO(long id, String name, String surname, LocalDateTime birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.createdDate = new Date(System.currentTimeMillis());
    }

    // Methods
    public String toString() {
        return "personDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", createdDate=" + createdDate +
                '}';
    };


    // Getter - Setter
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
