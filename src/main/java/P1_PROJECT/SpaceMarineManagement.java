package P1_PROJECT;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

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
    private Long counter = 0L;


    // Constructor
    public SpaceMarineManagement() {
        loadSpaceMarinesFromFile();
    }

    public void loadSpaceMarinesFromFile() {
        try(FileInputStream reader = new FileInputStream(FILE_NAME)) {
            SpaceMarineDTOList = reader.read();
            System.out.println(reader.read());
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
