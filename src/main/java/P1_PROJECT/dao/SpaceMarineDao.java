package P1_PROJECT.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import P1_PROJECT.customExceptions.SpaceMarineNotFoundException;
import P1_PROJECT.dto.SpaceMarineDTO;
import P1_PROJECT.utils.EnumMarine;

public class SpaceMarineDao implements IDaoGeneric<SpaceMarineDTO>{

    // Uyarı kodları
    public static final String RED = "\033[0;31m"; 
    public static final String YELLOW = "\033[0;33m";
    public static final String RESET = "\033[0m"; 
    public static final String BLUE = "\033[0;34m";

    Scanner scanner = new Scanner(System.in);

    // Fields
    // ArrayList heap'te saklandığı için primitive tipleri tutamaz,
    // Çünkü primitive tipler stack'te saklanır.
    private ArrayList<SpaceMarineDTO> SpaceMarineDTOList = new ArrayList<>();
    private static final String FILE_NAME = "spaceMarinesFile.txt";

    static {
        System.out.println(BLUE + "Space Marine Management System" + RESET);
    }

    // Constructor
    public SpaceMarineDao() {
        System.out.println(BLUE + "...YÜKLEME..." + RESET);
        loadSpaceMarinesFromFile();
    }

    /////////////////////////////////////////
    public void chooseMarineAdd() {
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

        spaceMarine.setGrade(); //Buna gerek olmayabilir. Kontrol edicez dosyadan.
        
        this.addSpaceMarine(spaceMarine);
    }

    public void chooseMarineList() {
        this.listSpaceMarines();
    }

    public void chooseMarineSearch() {
        System.out.println("Space Marine Name...");
        this.searchSpaceMarine(scanner.nextLine());
    }

    public void chooseMarineUpdate() {
        System.out.println("Güncellenecek İstediğin Space Marine ID'sini giriniz...");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Space Marine Name...");
        String name = scanner.nextLine();
        System.out.println("Space Marine Surname...");
        String surname = scanner.nextLine();
        System.out.println("Space Marine Birth Date (yyyy-MM-dd HH:mm)...");
        String dateInput = scanner.nextLine();
        LocalDateTime birthDate = LocalDateTime.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.println("Space Marine Main Weapon...");
        String mainWeapon = scanner.nextLine();
        System.out.println("Space Marine Kill Count...");
        int killCount = scanner.nextInt();
        System.out.println("Space Marine Success Mission Count...");
        int successMissionCount = scanner.nextInt();
        /// BURADA KALDIN setMarineType() BUNU GÜNCELLEMEDE VE DİĞER YERLERDE KULLANMAM LAZIM....
        try {
            this.updateSpaceMarine(id, new SpaceMarineDTO(name, birthDate, surname, mainWeapon, successMissionCount, killCount, setMarineType()));
        } catch (SpaceMarineNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void chooseMarineDelete() {
        System.out.println("Silinecek Space Marine ID'sini giriniz...");
        this.deleteSpaceMarine(scanner.nextLong()); 
    } 

    public void chooseMarineCount() {
        System.out.println(BLUE + String.format("Total Space Marine Count %n", SpaceMarineDTOList.size()) + RESET);
    }

    public void chooseMarineRandom() {
        System.out.println("Rastgele Space Marine Getir...");
        this.getRandomSpaceMarine();
    }

    public void choose() {
        byte selected = 0;
        while(true) {
            System.out.println(
            "İşlem seçiniz\n" +
                "\t\n 1 - Space Marine Ekle" +
                "\t\n 2 - Space Marine Listele" +
                "\t\n 3 - Space Marine Ara" +
                "\t\n 4 - Space Marine Güncelle" +
                "\t\n 5 - Space Marine Sil" +
                "\t\n 6 - Toplam Space Marine Sayısı" +
                "\t\n 7 - Dosya kaydetme" +
                "\t\n 8 - Space Marine Rastgele Getir" +
                "\t\n 9 - Space Marine Güç Hesaplama" 
            );

            // Seçim yapılır 
            selected = scanner.nextByte();
            scanner.nextLine(); // nextByte kullandıktan sonra kalan '\n' ifadesini okumak için lazım.

            switch(selected) {
                case 1:
                    chooseMarineAdd();
                break;

                case 22:
                    System.out.println("direkt ekleme yapınız...");
                    this.addSpaceMarine(new SpaceMarineDTO());
                break;

                case 2:
                    chooseMarineList();
                break;

                case 3:
                    chooseMarineSearch();
                break;

                case 4:
                    chooseMarineUpdate();
                break;

                case 5:
                    chooseMarineDelete();
                break;

                case 6:
                    chooseMarineCount();
                break;

                case 7:
                    saveSpaceMarinesToFile();
                break;

                case 8:
                    chooseMarineRandom();
                break;

                case 9:
                    chooseSpaceMarineMinAndMaxGrade();
                break;
            }

            if(selected == 0) {
                break;
            }
        }
    }

    public void loadSpaceMarinesFromFile() {
        
        // Listedeki verileri temizle
        SpaceMarineDTOList.clear();

        try (ObjectInputStream objectInputStream = new ObjectInputStream(
            new FileInputStream(FILE_NAME))) {
            SpaceMarineDTOList = (ArrayList<SpaceMarineDTO>) objectInputStream.readObject();

            System.out.println(BLUE + String.format("Loaded %d space marines from file.", SpaceMarineDTOList.size()) + RESET);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(RED + " Dosyadan yüklenen Space Marine Kaydı bulunamadı " + RESET);
            fileNotFoundException.printStackTrace();
            
        } catch (IOException io) {
            System.out.println(RED + " Dosya Okuma Hatası" + RESET);
            io.printStackTrace();
            
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } 
    }

    public ArrayList<SpaceMarineDTO> listSpaceMarines() {
        // Daha sonra dosyayı da açıp görüntülemeliyiz.
        if(SpaceMarineDTOList.isEmpty()) {
            System.out.println(RED + "No spzace marines found." + RESET);
            throw new SpaceMarineNotFoundException(RED + "No space marines found." + RESET);
        }
        System.out.println(BLUE + "Space Marines:" + RESET);
        SpaceMarineDTOList.forEach(marine->System.out.println(marine));
        return SpaceMarineDTOList;
    }

    public SpaceMarineDTO addSpaceMarine(SpaceMarineDTO spaceMarine) {
        SpaceMarineDTOList.add(new SpaceMarineDTO(
            spaceMarine.getName(),
            spaceMarine.getBirthDate(),
            spaceMarine.getSurname(),
            spaceMarine.getMainWeapon(), 
            spaceMarine.getSuccessMissionCount(), 
            spaceMarine.getKillCount(),
            spaceMarine.getMarineType()));
        saveSpaceMarinesToFile();   
        return spaceMarine;
    }

    public SpaceMarineDTO searchSpaceMarine(String name) {
        Optional<SpaceMarineDTO> spaceMarine = SpaceMarineDTOList.stream()
            .filter(marine->marine.getName().equalsIgnoreCase(name))
            .findFirst();
        return spaceMarine.orElseThrow(
            () -> new SpaceMarineNotFoundException(RED + 
            String.format("Space Marine that have name %s not found", name) 
            + RESET));
    }

    public SpaceMarineDTO updateSpaceMarine(Long id, SpaceMarineDTO newSpaceMarine) {
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
                return newSpaceMarine;
            }
        }
        System.out.println(RED + "Unavabile Space Marine" + RESET);
        return null;
    }

    public SpaceMarineDTO deleteSpaceMarine(Long id) {
        if(SpaceMarineDTOList.removeIf(marine -> marine.getId() == id)) {
            System.out.println(YELLOW + String.format("Marine %s deleted", id) + RESET);
            saveSpaceMarinesToFile();
            return null;
        }
        System.out.println(RED + "No Marine Deleted..." + RESET);
        return null;
    }

    // Bu methodu daha sonra private yapacağız.
    public void saveSpaceMarinesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(SpaceMarineDTOList);
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public SpaceMarineDTO getRandomSpaceMarine() {
        try {
            int randomIndex = new Random().nextInt(SpaceMarineDTOList.size());
            return SpaceMarineDTOList.get(randomIndex);
        } catch (Exception e) {
            System.out.println("Sistemde yeterince Space Marine bulunamadı.");
            return null;
        }
    }

        public void chooseSpaceMarineMinAndMaxGrade() {
        if (!SpaceMarineDTOList.isEmpty()) {
            SpaceMarineDTO maxSpaceMarine = SpaceMarineDTOList.stream()
                .max((s1, s2) -> Double.compare(s1.getGrade(), s2.getGrade()))
                .orElse(null);

            SpaceMarineDTO minSpaceMarine = SpaceMarineDTOList.stream()
                .min((s1, s2) -> Double.compare(s1.getGrade(), s2.getGrade()))
                .orElse(null);

            System.out.println(BLUE + "En Yüksek Grade'e Sahip Space Marine: " + maxSpaceMarine + RESET);
            System.out.println(BLUE + "En Düşük Grade'e Sahip Space Marine: " + minSpaceMarine + RESET);
        } else {
            System.out.println(RED + "Space Marine listesi boş." + RESET);
        }
    }

    public EnumMarine setMarineType(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Marine Type...\n 1- Ultramarines\n 2- Terrormarines\n 3- Deathmarines\n 4- Inceptor\n 5- Terminator");
        int marineType = scanner.nextInt();
        EnumMarine switchMarineType = switch(marineType) {
            case 1 -> EnumMarine.ULTRAMARINES;
            case 2 -> EnumMarine.TERRORMARINES;
            case 3 -> EnumMarine.DEATHMARINES;
            case 4 -> EnumMarine.INCEPTORMARINES;
            case 5 -> EnumMarine.TERMINATORMARINES;
            default -> throw new IllegalArgumentException("Invalid marine type");
        };
        return switchMarineType;
    }
}
