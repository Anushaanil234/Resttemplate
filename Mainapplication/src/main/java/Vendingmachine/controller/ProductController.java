package Vendingmachine.controller;

import Vendingmachine.dto.CustomerInputDTO;
import Vendingmachine.dto.InventoryDTO;
import Vendingmachine.service.InventoryService;
import Vendingmachine.dto.VendingMachineOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "CUSTOMER PROCESS")
public class ProductController {

    private final InventoryService inventoryService;

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    public ProductController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/products")
    @Operation(summary = "Get LIST OF ALL Inventry item")
    public List<InventoryDTO> getListOfAllInventory() {
        return inventoryService.getListOfAllInventory();
    }

    @GetMapping("/product/{id}")
    @Operation(summary = "Get Inventry item by id")
    public InventoryDTO getProductById(@PathVariable int id) {
        return inventoryService.getProductById(id);
    }

    @PutMapping("/purchase-product")
    @Operation(summary = "purchase Inventry item")
    public VendingMachineOutputDTO productPurchase(@RequestBody CustomerInputDTO customerInputDTO) {
        log.info("Number of products to purchase: {}", customerInputDTO.getProducts().size());
        return inventoryService.purchaseProduct(customerInputDTO);
    }
}
