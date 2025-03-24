package P1_PROJECT.dao;

import java.sql.Connection;
import java.util.ArrayList;
public interface IDaoGeneric<T> {

   public ArrayList<T> List();

   public void choose();

   public T add(T t);

   public T searchByName(String name);

   public T updateById(Long id, T t);

   public T deleteById(Long id);

   default Connection getConnection() {
      return null;
   }
     
}
