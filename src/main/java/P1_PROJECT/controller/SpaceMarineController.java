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

    @Override
    public void choose() {
        spaceMarineDao.choose();
    }
 
    @Override
    public SpaceMarineDTO add(SpaceMarineDTO spaceMarine) {
        return spaceMarineDao.add(spaceMarine);
    }

    @Override
    public ArrayList<SpaceMarineDTO> List() {
        return spaceMarineDao.List();
    }

    @Override
    public SpaceMarineDTO searchByName(String name) {
        return spaceMarineDao.searchByName(name);
    }

    @Override
    public SpaceMarineDTO updateById(Long id, SpaceMarineDTO newSpaceMarine) {
        return spaceMarineDao.updateById(id, newSpaceMarine);
    }

    @Override
    public SpaceMarineDTO deleteById(Long id) {
        return spaceMarineDao.deleteById(id);
    }
}
