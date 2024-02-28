package Vendingmachine.dao;



import Vendingmachine.dto.InventoryDTO;
import Vendingmachine.model.Inventry;

import java.util.List;

public interface InventoryDAO {

   public List<Inventry> findAll();

   public List<Inventry> findById(int productId);

   public int deleteById(int productId);

   public int save(InventoryDTO e);

   public int update(Inventry e, int productId);

   public void updatedStock(int productId, int productInventryCount) ;

   public List<Inventry> findByInventryCount(int productInventryCount);
   
}
