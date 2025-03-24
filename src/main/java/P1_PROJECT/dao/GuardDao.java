package P1_PROJECT.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.function.Predicate;

import P1_PROJECT.customExceptions.GuardNotFoundException;
import P1_PROJECT.dto.GuardDTO;
import P1_PROJECT.dto.HumanDTO;
import P1_PROJECT.utils.errorColors;

public class GuardDao implements IDaoGeneric<GuardDTO> {

    // Fields
    // ArrayList heap'te saklandığı için primitive tipleri tutamaz,
    // Çünkü primitive tipler stack'te saklanır.
    private ArrayList<GuardDTO> GuardDTOList = new ArrayList<>();
    private static final String FILE_NAME = "guards.txt";

    static {
        System.out.println(errorColors.BLUE + "Guard Management System" + errorColors.RESET);
    }

    // Constructor
    public GuardDao() {
        // Eğer spaceMarineFile yok ise oluştursun ve hata vermesin program ilk başlarken...
        //createFileIfNotExists();
        loadGuardsFromFile();
    }

    // Methods
    public boolean loadGuardsFromFile() {
        // Listedeki verileri temizle
        GuardDTOList.clear();

        try (ObjectInputStream objectInputStream = new ObjectInputStream(
            new FileInputStream(FILE_NAME))) {
            GuardDTOList = (ArrayList<GuardDTO>) objectInputStream.readObject();
            System.out.println(errorColors.BLUE + String.format("Loaded %d guards from file.", GuardDTOList.size()) + errorColors.RESET);
            return true;
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(errorColors.RED + " Dosyadan yüklenen Guard Kaydı bulunamadı " + errorColors.RESET);
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

    public boolean saveGuardsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(GuardDTOList);
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
    
    
    

    @Override
    public ArrayList<GuardDTO> List() {
        return null;
    }

    @Override
    public GuardDTO add(GuardDTO guardDTO) {
        try {
            GuardDTOList.add(guardDTO);
            saveGuardsToFile();
            return guardDTO;
        } catch (Exception e) {
            System.out.println(errorColors.RED + "An error occurred." + errorColors.RESET);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GuardDTO searchByName(String name) {
        if(GuardDTOList.isEmpty()) {
            throw new GuardNotFoundException(errorColors.RED + "No guards found." + errorColors.RESET);
        }
        return GuardDTOList.stream()
        .filter(guard -> guard.human().getName().equalsIgnoreCase(name))
        .findFirst()
        .orElseThrow(
            () -> new GuardNotFoundException(errorColors.RED + 
            String.format("Guard that have name %s not found", name) 
            + errorColors.RESET));
    }

    @Override
    public GuardDTO updateById(Long id, GuardDTO newGuard) {
        return null;
    }

    @Override
    public GuardDTO deleteById(Long id) {
        try {
            GuardDTOList.removeIf(guard -> guard.human().getId() == id);
            saveGuardsToFile();
            return null;
        } catch (Exception e) {
            System.out.println(errorColors.RED + "An error occurred." + errorColors.RESET);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void choose() {
        // Menu işlemleri burada implement edilecek
    }
}
