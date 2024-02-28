//package Vendingmachine.service;
//
//
//import Vendingmachine.dao.InitialBalanceDAO;
//import Vendingmachine.dao.InventoryDAO;
//import Vendingmachine.dto.InventoryDTO;
//import Vendingmachine.exception.InvalidProductId;
//import Vendingmachine.model.InitialBalanceAndPurchaseHistory;
//import Vendingmachine.model.Inventry;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class AdminService {
//
//
//    private final InventoryDAO inventoryDAO;
//    private final InitialBalanceDAO initialBalanceDAO;
//
//    public AdminService(InitialBalanceDAO initialBalanceDAO,InventoryDAO inventoryDAO) {
//
//        this.initialBalanceDAO=initialBalanceDAO;
//        this.inventoryDAO=inventoryDAO;
//    }
//
//    public int saveInventory(InventoryDTO inventry){
//        return inventoryDAO.save(inventry); }
//
//
//    public int updateInventory(Inventry inventry, int productId) {
//        if (inventoryDAO.findById(productId).isEmpty()) {
//            throw new InvalidProductId("Invalid product ID provided for update: " + productId);
//        }
//        return inventoryDAO.update(inventry, productId);
//    }
//
//    public int deleteProductById(int productId){
//
//        if (inventoryDAO.findById(productId).isEmpty()) {
//            throw new InvalidProductId("Invalid product ID provided for update: " + productId);
//        }
//        return inventoryDAO.deleteById(productId);
//    }
//
//
//    public List<InitialBalanceAndPurchaseHistory> getListOfAllPurchaseHistory(){
//        return allProductToPurchaseHistory(initialBalanceDAO.getAllPurchaseHistory());
//    }
//
//    public List<InitialBalanceAndPurchaseHistory> allProductToPurchaseHistory(List<InitialBalanceAndPurchaseHistory> allInvProduct){
//        return  allInvProduct.stream().map(this::productToUserProductHistory).collect(Collectors.toList());
//    }
//
//    public InitialBalanceAndPurchaseHistory productToUserProductHistory(InitialBalanceAndPurchaseHistory inventory){
//        return new InitialBalanceAndPurchaseHistory(inventory.getId(),inventory.getProductId(),inventory.getProduct(),inventory.getProductPrice(),inventory.getCustomerInputAmount(), inventory.getVendingMachineBalance(), inventory.getInitialBalance());
//    }
//}
package Vendingmachine.service;

import Vendingmachine.dao.InitialBalanceDAO;
import Vendingmachine.dao.InventoryDAO;
import Vendingmachine.dto.InventoryDTO;
import Vendingmachine.exception.InvalidProductId;
import Vendingmachine.model.InitialBalanceAndPurchaseHistory;
import Vendingmachine.model.Inventry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final InventoryDAO inventoryDAO;
    private final InitialBalanceDAO initialBalanceDAO;

    public AdminService(InitialBalanceDAO initialBalanceDAO, InventoryDAO inventoryDAO) {
        this.initialBalanceDAO = initialBalanceDAO;
        this.inventoryDAO = inventoryDAO;
    }

//    public int saveInventory(InventoryDTO inventoryDTO) {
//        return inventoryDAO.save(inventoryDTO);
//    }

    public int saveInventory(InventoryDTO inventry){
   return inventoryDAO.save(inventry); }

    public int updateInventory(Inventry inventory, int productId) {
        if (inventoryDAO.findById(productId).isEmpty()) {
            throw new InvalidProductId("Invalid product ID provided for update: " + productId);
        }
        return inventoryDAO.update(inventory, productId);
    }

    public int deleteProductById(int productId) {
        if (inventoryDAO.findById(productId).isEmpty()) {
            throw new InvalidProductId("Invalid product ID provided for delete: " + productId);
        }
        return inventoryDAO.deleteById(productId);
    }

    public List<InitialBalanceAndPurchaseHistory> getListOfAllPurchaseHistory() {
        List<InitialBalanceAndPurchaseHistory> purchaseHistory = initialBalanceDAO.getAllPurchaseHistory();
        if (purchaseHistory.isEmpty()) {
            throw new RuntimeException("Purchase history is empty");
        }
        return purchaseHistory.stream()
                .map(this::productToUserProductHistory)
                .collect(Collectors.toList());
    }

    public InitialBalanceAndPurchaseHistory productToUserProductHistory(InitialBalanceAndPurchaseHistory inventory) {
        return new InitialBalanceAndPurchaseHistory(
                inventory.getId(),
                inventory.getProductId(),
                inventory.getProduct(),
                inventory.getProductPrice(),
                inventory.getCustomerInputAmount(),
                inventory.getVendingMachineBalance(),
                inventory.getInitialBalance()
        );
    }
}
