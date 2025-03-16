package P1_PROJECT;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SpaceMarineManagement {

    // Uyarı kodları
    public static final String RED = "\033[0;31m"; 
    public static final String YELLOW = "\033[0;33m";
    public static final String RESET = "\033[0m"; 

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
            selected = scanner.nextByte();

            switch(selected) {
                case 1:
                SpaceMarineDTO spaceMarine = new SpaceMarineDTO();
                System.out.println("Space Marine Adı:");
                spaceMarine.setName(scanner.nextLine());
                
                System.out.println("Space Marine Soyadı:");
                spaceMarine.setSurname(scanner.nextLine());
                
                System.out.println("Space Marine Doğum tarihi (yyyy-MM-dd HH:mm):");
                String dateStr = scanner.nextLine();
                spaceMarine.setBirthDate(LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    
                System.out.println("Space Marine Silahı:");
                spaceMarine.setMainWeapon(scanner.nextLine());

                System.out.println("Space Marine Puanı:");
                spaceMarine.setGrade((float) scanner.nextDouble());

                SpaceMarineDTOList.add(spaceMarine);
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
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void listSpaceMarines() {
        // Daha sonra dosyayı da açıp görüntülemeliyiz.
        if(SpaceMarineDTOList.isEmpty()) {
            System.out.println(RED + "No space marines found." + RESET);
            return;
        }
        SpaceMarineDTOList.forEach(marine->System.out.println(marine));

    }

    public void searchSpaceMarine(String name) {
        SpaceMarineDTOList.stream()
            .filter(marine->marine.getName().equalsIgnoreCase(name))
            .forEach(System.out::println);
    }

    public void updateSpaceMarine(Long id, SpaceMarineDTO newSpaceMarine) {
        for(SpaceMarineDTO marine : SpaceMarineDTOList) {
            if(marine.getId() == id) {
                marine.setName(newSpaceMarine.getName());
                marine.setMainWeapon(newSpaceMarine.getMainWeapon());
                marine.setGrade(newSpaceMarine.getGrade());
                marine.setBirthDate(newSpaceMarine.getBirthDate());

                saveSpaceMarinesToFile();

                System.out.println(
                    YELLOW + "Space Marine updated..." + RESET
                );
                return;
            }
            System.out.println(RED + "Unavabile Space Marine" + RESET);
        }
    }

    public void deleteSpaceMarine(Long id) {
        if(SpaceMarineDTOList.removeIf(marine -> marine.getId() == id)) {
            System.out.println(YELLOW + "Marine deleted" + RESET);
            saveSpaceMarinesToFile();
            return;
        }
        System.out.println(RED + "No Marine..." + RESET);
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
