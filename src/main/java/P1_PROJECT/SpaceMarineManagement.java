package P1_PROJECT;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import P1_PROJECT.CustomExceptions.SpaceMarineNotFoundException;
public class SpaceMarineManagement {

    // Uyarı kodları
    public static final String RED = "\033[0;31m"; 
    public static final String YELLOW = "\033[0;33m";
    public static final String RESET = "\033[0m"; 
    public static final String BLUE = "\033[0;34m";

    // Fields
    // ArrayList heap'te saklandığı için primitive tipleri tutamaz,
    // Çünkü primitive tipler stack'te saklanır.
    private ArrayList<SpaceMarineDTO> SpaceMarineDTOList = new ArrayList<>();
    private static final String FILE_NAME = "spaceMarinesFile.txt";
    private long marineCounter = 0L;


    // Constructor
    public SpaceMarineManagement() {
        loadSpaceMarinesFromFile();
    }

    public void choose() {
        Scanner scanner = new Scanner(System.in);
        byte selected = 0;
        while(true) {
            System.out.println(
            "İşlem seçiniz\n" +
                "\t 1 - Space Marine Ekle" +
                "\t 2 - Space Marine Listele" +
                "\t 3 - Space Marine Ara" +
                "\t 4 - Space Marine Güncelle" +
                "\t 5 - Space Marine Sil" +
                "\t 6 - Toplam Space Marine Sayısı" +
                "\t 7 - Dosya kaydetme" +
                "\t 8 - Space Marine Rastgele Getir" +
                "\t 9 - Space Marine Güç Hesaplama" +
                "\t 10 - Space Marine En Çok Kill Count ve En az kill count" +
                "\t 11 - Space Marine Doğum Tarihlerine Göre Sıralama"
            );

            // Seçim yapılır 
            selected = scanner.nextByte();
            //scanner.nextLine(); NEDEN LAZIM?????

            switch(selected) {
                case 1:
                    SpaceMarineDTO spaceMarine = new SpaceMarineDTO();

                    System.out.println("Space Marine Name...");
                    spaceMarine.setName(scanner.nextLine());
                    
                    System.out.println("Space Marine Surname...");
                    spaceMarine.setSurname(scanner.nextLine());
                    
                    System.out.println("Space Marine Birth Date (yyyy-MM-dd HH:mm)...");
                    String dateStr = scanner.nextLine();
                    spaceMarine.setBirthDate(LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                        
                    System.out.println("Space Marine Main Weapon...");
                    spaceMarine.setMainWeapon(scanner.nextLine());

                    System.out.println("Space Marine Kill Count...");
                    spaceMarine.setKillCount(scanner.nextInt());

                    System.out.println("Space Marine Success Mission Count...");
                    spaceMarine.setSuccessMissionCount(scanner.nextInt());

                    System.out.println("Space Marine Puanı:");
                    spaceMarine.setGrade();

                    this.addSpaceMarine(spaceMarine);
                break;

                case 2:
                    this.listSpaceMarines();
                break;

                case 3:
                    System.out.println("Space Marine Name...");
                    this.searchSpaceMarine(scanner.nextLine());
                break;
            }

            if(selected == 0) {
                break;
            }
        }
    }

    public void loadSpaceMarinesFromFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
            new FileInputStream(FILE_NAME))) {
            SpaceMarineDTOList = (ArrayList<SpaceMarineDTO>) objectInputStream.readObject();
            marineCounter = SpaceMarineDTOList.size();

            System.out.println(BLUE + String.format("Loaded %d space marines from file.", SpaceMarineDTOList.size()) + RESET);
        } catch (Exception e) {
            // TODO: handle exception
        }
/*         catch (FileNotFoundException fileNotFoundException) {
            System.out.println(RED + " Dosyadan yüklenen Space Marine Kaydı bulunamadı " + RESET);
            fileNotFoundException.printStackTrace();
            
        } catch (IOException io) {
            System.out.println(RED + " Dosya Okuma Hatası" + RESET);
            io.printStackTrace();
            
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } */
    }

    public void listSpaceMarines() {
        // Daha sonra dosyayı da açıp görüntülemeliyiz.
        if(SpaceMarineDTOList.isEmpty()) {
            System.out.println(RED + "No space marines found." + RESET);
            return;
        }
        System.out.println(BLUE + "Space Marines:" + RESET);
        SpaceMarineDTOList.forEach(marine->System.out.println(marine));

    }

    public void addSpaceMarine(SpaceMarineDTO spaceMarine) {
        SpaceMarineDTOList.add(new SpaceMarineDTO(
            spaceMarine.getName(),
            spaceMarine.getBirthDate(),
            spaceMarine.getGrade(),
            spaceMarine.getSurname(),
            spaceMarine.getMainWeapon(), 
            spaceMarine.getSuccessMissionCount(), 
            spaceMarine.getKillCount()));
        this.marineCounter++;
        saveSpaceMarinesToFile();   
    }

    public void searchSpaceMarine(String name) {
        Boolean found = SpaceMarineDTOList.stream()
            .filter(marine->marine.getName().equalsIgnoreCase(name))
            .peek(System.out::println)
            .findAny()
            .isPresent();
        if(!found) {
            throw new SpaceMarineNotFoundException(RED + 
            String.format("Space Marine that have name %s not found", name) 
            + RESET);
        }
    }

    public void updateSpaceMarine(Long id, SpaceMarineDTO newSpaceMarine) {
        for(SpaceMarineDTO marine : SpaceMarineDTOList) {
            if(marine.getId() == id) {
                marine.setName(newSpaceMarine.getName());
                marine.setSurname(newSpaceMarine.getSurname());
                marine.setKillCount(newSpaceMarine.getKillCount());
                marine.setSuccessMissionCount(newSpaceMarine.getSuccessMissionCount());
                marine.setMainWeapon(newSpaceMarine.getMainWeapon());
                marine.setGrade();
                marine.setBirthDate(newSpaceMarine.getBirthDate());

                saveSpaceMarinesToFile();

                System.out.println(
                    BLUE + String.format("Space Marine %s updated...", newSpaceMarine.getName()) + RESET
                );
                return;
            }
            System.out.println(RED + "Unavabile Space Marine" + RESET);
        }
    }

    public void deleteSpaceMarine(Long id) {
        if(SpaceMarineDTOList.removeIf(marine -> marine.getId() == id)) {
            System.out.println(YELLOW + String.format("Marine %s deleted", id) + RESET);
            saveSpaceMarinesToFile();
            return;
        }
        System.out.println(RED + "No Marine Deleted..." + RESET);
    }

    // Bu methodu daha sonra private yapacağız.
    public void saveSpaceMarinesToFile() {
        try(FileOutputStream myWriter = new FileOutputStream(FILE_NAME, true)) {
            myWriter.write((SpaceMarineDTOList.toString() + "\n").getBytes());
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
