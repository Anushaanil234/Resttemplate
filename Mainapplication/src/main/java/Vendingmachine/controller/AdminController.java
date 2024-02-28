package Vendingmachine.controller;

import Vendingmachine.service.AdminService;
import Vendingmachine.dto.InventoryDTO;
import Vendingmachine.model.InitialBalanceAndPurchaseHistory;
import Vendingmachine.model.Inventry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "ADMIN PROCESS")
public class AdminController {

    private final AdminService adminServices;

    public AdminController(AdminService adminServices) {
        this.adminServices = adminServices;
    }

    @DeleteMapping("/products/{id}")
    @Operation(summary = "DELETE Inventory item ")
    public String deleteProductById(@PathVariable int id) {
        return String.format("%d Product(s) delete from the database", adminServices.deleteProductById(id));
    }

    @GetMapping("/purchase-history")
    @Operation(summary = "LIST OF ALL purchased Inventry item")
    public List<InitialBalanceAndPurchaseHistory> getListOfAllPurchaseHistory() {
        return adminServices.getListOfAllPurchaseHistory();
    }

    @PostMapping("/add-product")
    @Operation(summary = "Add Inventory item ")
    public String saveInventory(@RequestBody InventoryDTO inventoryDTO) {
        return String.format("%s product added successfully", adminServices.saveInventory(inventoryDTO));
    }

    @PutMapping("/update-product/{id}")
    @Operation(summary = "Update Inventory item ")
    public String updateInventory(@RequestBody Inventry inventry, @PathVariable int id) {
        return String.format("%d Product(s) updated successfully", adminServices.updateInventory(inventry, id));
    }
}
