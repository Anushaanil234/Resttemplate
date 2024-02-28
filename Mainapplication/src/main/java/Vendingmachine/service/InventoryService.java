package Vendingmachine.service;

import Vendingmachine.dao.InventoryDAO;
import Vendingmachine.dao.InitialBalanceDAO;
import Vendingmachine.dto.CustomerInputDTO;
import Vendingmachine.dto.InventoryDTO;
import Vendingmachine.dto.PurchaseInputDTO;
import Vendingmachine.dto.VendingMachineOutputDTO;
import Vendingmachine.exception.InsufficientInitialBalance;
import Vendingmachine.exception.InsufficientInputCash;
import Vendingmachine.exception.ProductIdNotFound;
import Vendingmachine.exception.ProductUnavailable;
import Vendingmachine.model.InitialBalanceAndPurchaseHistory;
import Vendingmachine.model.Inventry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryDAO inventoryDAO;
    private final InitialBalanceDAO initialBalanceDAO;

    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);

    public InventoryService(InventoryDAO inventoryDAO, InitialBalanceDAO initialBalanceDAO) {
        this.inventoryDAO = inventoryDAO;
        this.initialBalanceDAO = initialBalanceDAO;
    }

    public List<InventoryDTO> getListOfAllInventory() {
        return inventoryDAO.findAll().stream()
                .map(this::productToUserProduct)
                .collect(Collectors.toList());
    }

    public InventoryDTO getProductById(int productId) {
        log.info("product id in get product by id == {} ", productId);

        return inventoryDAO.findById(productId)
                .stream()
                .findFirst()
                .filter(inventory -> inventory.getProductInventoryCount() > 0)
                .map(this::productToUserProduct)
                .orElseThrow(() -> new ProductIdNotFound("Product not found or out of stock for ID: " + productId));
    }

    public InventoryDTO productToUserProduct(Inventry inventory) {
        return new InventoryDTO(inventory.getProductId(), inventory.getName(), inventory.getProductPrice(), inventory.getProductInventoryCount());
    }

    public VendingMachineOutputDTO purchaseProduct(CustomerInputDTO customerInputDTO) {
        int totalChange = customerInputDTO.getProducts().stream()
                .mapToInt(this::processPurchaseInput)
                .sum();

        String message = (totalChange > 1) ? "Multiple products purchased" : "Item purchased";

        return new VendingMachineOutputDTO(message, 0, totalChange);
    }

    public int processPurchaseInput(PurchaseInputDTO purchaseInputDTO) {
        int productId = purchaseInputDTO.getProductId();
        int inputPrice = purchaseInputDTO.getPrice() * purchaseInputDTO.getQuantity();

        log.info("Purchasing product with ID: {}", productId);

        List<Inventry> inventoryList = inventoryDAO.findById(productId);

        if (inventoryList.isEmpty()) {
            throw new ProductIdNotFound("Product not found for ID: " + productId);
        }

        Inventry inventory = inventoryList.get(0);

        if (inputPrice < inventory.getProductPrice() * purchaseInputDTO.getQuantity()) {
            throw new InsufficientInputCash(inputPrice + " rupees is not enough for " + inventory.getName());
        }

        int change = inputPrice - (inventory.getProductPrice() * purchaseInputDTO.getQuantity());

        if (initialBalanceDAO.getChange().getInitialBalance() < change) {
            throw new InsufficientInitialBalance("Sorry, insufficient balance for this purchase!!");
        }

        log.info("Change amount for this purchase: {}", change);

        int newInventoryCount = inventory.getProductInventoryCount() - purchaseInputDTO.getQuantity();
        int newInitialBalance = initialBalanceDAO.getChange().getInitialBalance() - inputPrice; // Deduct the total price

        InitialBalanceAndPurchaseHistory currentTransaction = new InitialBalanceAndPurchaseHistory(
                0, inventory.getProductId(), inventory.getName(),
                inventory.getProductPrice(), inputPrice, change, newInitialBalance);

        inventoryDAO.updatedStock(inventory.getProductId(), newInventoryCount);
        initialBalanceDAO.saveTransaction(currentTransaction);

        return change;
    }
}
