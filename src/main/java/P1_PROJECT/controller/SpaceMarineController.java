package P1_PROJECT.controller;

import java.util.ArrayList;

import P1_PROJECT.dao.IDaoGeneric;
import P1_PROJECT.dto.SpaceMarineDTO;
import P1_PROJECT.dao.SpaceMarineDao;

public class SpaceMarineController implements IDaoGeneric<SpaceMarineDTO> {

    // Injection
    private SpaceMarineDao spaceMarineDao;

    // Parametresiz CTR
    public SpaceMarineController() {
        this.spaceMarineDao = new SpaceMarineDao();
    }

    public void choose() {
        spaceMarineDao.choose();
    }
 
    public SpaceMarineDTO addSpaceMarine(SpaceMarineDTO spaceMarine) {
        return spaceMarineDao.addSpaceMarine(spaceMarine);
    }

    public ArrayList<SpaceMarineDTO> listSpaceMarines() {
        return spaceMarineDao.listSpaceMarines();
    }

    public SpaceMarineDTO searchSpaceMarine(String name) {
        return spaceMarineDao.searchSpaceMarine(name);
    }

    public SpaceMarineDTO updateSpaceMarine(Long id, SpaceMarineDTO newSpaceMarine) {
        return spaceMarineDao.updateSpaceMarine(id, newSpaceMarine);
    }

    public SpaceMarineDTO deleteSpaceMarine(Long id) {
        return spaceMarineDao.deleteSpaceMarine(id);
    }
}
