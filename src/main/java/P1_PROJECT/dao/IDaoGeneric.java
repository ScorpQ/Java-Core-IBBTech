package P1_PROJECT.dao;

import java.sql.Connection;
import java.util.ArrayList;
public interface IDaoGeneric<T> {

   public ArrayList<T> listSpaceMarines();

   public void choose();

   public T addSpaceMarine(T t);

   public T searchSpaceMarine(String name);

   public T updateSpaceMarine(Long id, T t);

   public T deleteSpaceMarine(Long id);

   default Connection getConnection() {
      return null;
   }
     
}
