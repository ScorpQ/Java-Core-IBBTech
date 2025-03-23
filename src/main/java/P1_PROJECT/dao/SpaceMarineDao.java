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
import P1_PROJECT.utils.errorColors;

public class SpaceMarineDao implements IDaoGeneric<SpaceMarineDTO>{
    private final Scanner scanner = new Scanner(System.in);

    // Fields
    // ArrayList heap'te saklandığı için primitive tipleri tutamaz,
    // Çünkü primitive tipler stack'te saklanır.
    private ArrayList<SpaceMarineDTO> SpaceMarineDTOList = new ArrayList<>();
    private static final String FILE_NAME = "spaceMarinesFile.txt";

    static {
        System.out.println(errorColors.BLUE + "Space Marine Management System" + errorColors.RESET);
    }

    // Constructor
    public SpaceMarineDao() {
        // Eğer spaceMarineFile yok ise oluştursun ve hata vermesin program ilk başlarken...
        //createFileIfNotExists();
        loadSpaceMarinesFromFile();
    }

    /////////////////////////////////////////
    public void chooseMarineAdd() {
        SpaceMarineDTO spaceMarine = new SpaceMarineDTO();

        // En yüksek ID'yi bul
        spaceMarine.setId(
            SpaceMarineDTOList.stream()
            .mapToLong(SpaceMarineDTO::getId)
            .max()
            .orElse(0) + 1);

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

        try {
            this.updateSpaceMarine(id, new SpaceMarineDTO(id, name, surname, birthDate , mainWeapon, successMissionCount, killCount, setMarineType()));
        } catch (SpaceMarineNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void chooseMarineDelete() {
        System.out.println("Silinecek Space Marine ID'sini giriniz...");
        this.deleteSpaceMarine(scanner.nextLong()); 
    } 

    public void chooseMarineCount() {
        System.out.println(errorColors.BLUE + String.format("Total Space Marine Count %n", SpaceMarineDTOList.size()) + errorColors.RESET);
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

                case 10:
                    listOnlyGrandMasters();
                break;
            }

            if(selected == 0) {
                break;
            }
        }
    }

    public boolean loadSpaceMarinesFromFile() {
        
        // Listedeki verileri temizle
        SpaceMarineDTOList.clear();

        try (ObjectInputStream objectInputStream = new ObjectInputStream(
            new FileInputStream(FILE_NAME))) {
            SpaceMarineDTOList = (ArrayList<SpaceMarineDTO>) objectInputStream.readObject();
            System.out.println(errorColors.BLUE + String.format("Loaded %d space marines from file.", SpaceMarineDTOList.size()) + errorColors.RESET);
            return true;
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(errorColors.RED + " Dosyadan yüklenen Space Marine Kaydı bulunamadı " + errorColors.RESET);
            fileNotFoundException.printStackTrace();
            return false;
        } catch (IOException io) {
            System.out.println(errorColors.RED + " Dosya Okuma Hatası" + errorColors.RESET);
            io.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println(errorColors.RED + " Sınıf bulunamadı hatası" + errorColors.RESET);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<SpaceMarineDTO> listSpaceMarines() {
        if(SpaceMarineDTOList.isEmpty()) {
            throw new SpaceMarineNotFoundException(errorColors.RED + "No space marines found." + errorColors.RESET);
        }
        System.out.println(errorColors.BLUE + "Space Marines:" + errorColors.RESET);
        SpaceMarineDTOList.forEach(marine->System.out.println(marine));
        return SpaceMarineDTOList;
    }

    @Override
    public SpaceMarineDTO searchSpaceMarine(String name) {
        Optional<SpaceMarineDTO> spaceMarine = SpaceMarineDTOList.stream()
            .filter(marine->marine.getName().equalsIgnoreCase(name))
            .findFirst();
        return spaceMarine.orElseThrow(
            () -> new SpaceMarineNotFoundException(errorColors.RED + 
            String.format("Space Marine that have name %s not found", name) 
            + errorColors.RESET));
    } 

    @Override
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
                    errorColors.BLUE + String.format("Space Marine %s updated...", newSpaceMarine.getName()) + errorColors.RESET
                );
                return newSpaceMarine;
            }
        }
        System.out.println(errorColors.RED + "Space Marine not found" + errorColors.RESET);
        return null;
    }

    public SpaceMarineDTO addSpaceMarine(SpaceMarineDTO spaceMarine) {
        try {
            // Validasyon
            validateSpaceMarine(spaceMarine);

            // Listeye Ekleme
            SpaceMarineDTOList.add(spaceMarine);

            // Dosyaya Kayıt
            saveSpaceMarinesToFile();   
            return spaceMarine;
        } catch (Exception e) {
            System.out.println(errorColors.RED + "An error occurred." + errorColors.RESET);
            e.printStackTrace();
            return null;
        }
    }

    private void validateSpaceMarine(SpaceMarineDTO spaceMarine) {
        if(spaceMarine.getId() <= 1) {
            throw new IllegalArgumentException("Space Marine ID have to be greater than 1.");
        }
        if(spaceMarine.getName() == null || spaceMarine.getName().isEmpty() || !spaceMarine.getName().matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Space Marine name can not contain numbers or special characters.");
        }
        if(spaceMarine.getSurname() == null || spaceMarine.getSurname().isEmpty() || !spaceMarine.getSurname().matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Space Marine surname can not contain numbers or special characters.");
        }
        if(spaceMarine.getMainWeapon() == null || spaceMarine.getMainWeapon().isEmpty()) {
            throw new IllegalArgumentException("Space Marine main weapon can not be empty.");
        }
        if(spaceMarine.getSuccessMissionCount() <= 0) {
            throw new IllegalArgumentException("Space Marine success mission count have to be greater than 0.");
        }
        if(spaceMarine.getBirthDate().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Space Marine birth date can not be in the future.");
        }
        if(spaceMarine.getKillCount() <= 0) {
            throw new IllegalArgumentException("Space Marine kill count have to be greater than 0.");
        }
        if(spaceMarine.getMarineType() == null) {
            throw new IllegalArgumentException("Space Marine marine type can not be empty.");
        }
    }

    public SpaceMarineDTO deleteSpaceMarine(Long id) {
        if(SpaceMarineDTOList.removeIf(marine -> marine.getId() == id)) {
            System.out.println(errorColors.YELLOW + String.format("Marine %s deleted", id) + errorColors.RESET);
            saveSpaceMarinesToFile();
            return null;
        }
        System.out.println(errorColors.RED + "No Marine Deleted..." + errorColors.RESET);
        return null;
    }

    public boolean saveSpaceMarinesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(SpaceMarineDTOList);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(errorColors.RED + "Dosya bulunamadı." + errorColors.RESET);
            return false;
        } catch (IOException e) {
            System.out.println(errorColors.RED + "Dosya yazma hatası." + errorColors.RESET);
            return false;
        } 
        catch (Exception e) {
            System.out.println(errorColors.RED + "An error occurred." + errorColors.RESET);
            e.printStackTrace();
            return false;
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

            System.out.println(errorColors.BLUE + "En Yüksek Grade'e Sahip Space Marine: " + maxSpaceMarine + errorColors.RESET);
            System.out.println(errorColors.BLUE + "En Düşük Grade'e Sahip Space Marine: " + minSpaceMarine + errorColors.RESET);
        } else {
            System.out.println(errorColors.RED + "Space Marine listesi boş." + errorColors.RESET);
        }
    }

    private ArrayList<SpaceMarineDTO> listOnlyGrandMasters() {
   
        return SpaceMarineDTOList;
    }   

    private String marineDataToCSV(SpaceMarineDTO spaceMarine) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", 
        spaceMarine.getId(),
        spaceMarine.getName(),
        spaceMarine.getSurname(),
        spaceMarine.getMarineType(),
        spaceMarine.getIsGrandMaster(),
        spaceMarine.getGrade(),
        spaceMarine.getSuccessMissionCount(),
        spaceMarine.getKillCount(),
        spaceMarine.getMainWeapon(),
        spaceMarine.getBirthDate());
    }

    private String marineDataToCSV(ArrayList<SpaceMarineDTO> spaceMarines) {
        return "zort";
    }
    
    public EnumMarine setMarineType(){
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
