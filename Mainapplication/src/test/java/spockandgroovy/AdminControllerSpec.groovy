package Vendingmachine.controller;
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Shared
import Vendingmachine.service.AdminService
import Vendingmachine.dto.InventoryDTO
import Vendingmachine.model.InitialBalanceAndPurchaseHistory
import Vendingmachine.model.Inventry

class AdminControllerSpec extends Specification {

    @Subject
    AdminController adminController

    @Shared
    AdminService adminServiceMock

    def setup() {
        adminServiceMock = Mock(AdminService)
        adminController = new AdminController(adminServiceMock)
    }

    def "deleteProductById should return correct message"() {
        given:
        def productId = 1
        adminServiceMock.deleteProductById(productId) >> 2

        when:
        def result = adminController.deleteProductById(productId)

        then:
        result == "2 Product(s) delete from the database"
    }

    def "getListOfAllPurchaseHistory should return a list of purchase history"() {
        given:
        def purchaseHistory = [
                new InitialBalanceAndPurchaseHistory(id: 1, productId: 1, product: "Coke", productPrice: 1.50, customerInputAmount: 2.0, vendingMachineBalance: 5.0, initialBalance: 10.0),
                new InitialBalanceAndPurchaseHistory(id: 2, productId: 2, product: "Chips", productPrice: 1.20, customerInputAmount: 1.5, vendingMachineBalance: 3.0, initialBalance: 8.0)
        ]
        adminServiceMock.getListOfAllPurchaseHistory() >> purchaseHistory

        when:
        def result = adminController.getListOfAllPurchaseHistory()

        then:
        result == purchaseHistory
    }

    def "saveInventory should return a success message"() {
        given:
        def inventoryDTO = new InventoryDTO(name: "dietcoke", productPrice: 35,productInventoryCount: 2
        )
        adminServiceMock.saveInventory(inventoryDTO) >> 1

        when:
        def result = adminController.saveInventory(inventoryDTO)

        then:
        result == "1 product added successfully"
    }



    def "updateInventory should return a success message"() {
        given:
        def inventry = new Inventry(name: "Chips", productPrice: 1.20, productInventoryCount: 15)
        def productId = 1
        adminServiceMock.updateInventory(inventry, productId) >> 3

        when:
        def result = adminController.updateInventory(inventry, productId)

        then:
        result == "3 Product(s) updated successfully"
    }
}
