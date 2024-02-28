import Vendingmachine.controller.ProductController
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Shared
import Vendingmachine.service.InventoryService
import Vendingmachine.dto.InventoryDTO
import Vendingmachine.dto.CustomerInputDTO
import Vendingmachine.dto.PurchaseInputDTO
import Vendingmachine.dto.VendingMachineOutputDTO

class ProductControllerSpec extends Specification {

    @Subject
    ProductController productController

    @Shared
    InventoryService inventoryServiceMock

    def setup() {
        inventoryServiceMock = Mock(InventoryService)
        productController = new ProductController(inventoryServiceMock)
    }

    def "should get list of all inventory"() {
        given:
        def inventoryList = [
                new InventoryDTO(productId: 1, name: "Coke", productPrice: 1.50, productInventoryCount: 10),
                new InventoryDTO(productId: 2, name: "Chips", productPrice: 1.20, productInventoryCount: 15)
        ]
        inventoryServiceMock.getListOfAllInventory() >> inventoryList

        when:
        def result = productController.getListOfAllInventory()

        then:
        result == inventoryList
    }

    def "should get inventory item by id"() {
        given:
        def inventoryItem = new InventoryDTO(productId: 1, name: "Coke", productPrice: 1.50, productInventoryCount: 10)
        inventoryServiceMock.getProductById(1) >> inventoryItem

        when:
        def result = productController.getProductById(1)

        then:
        result == inventoryItem
    }

    def "should purchase inventory items"() {
        given:
        def customerInputDTO = new CustomerInputDTO(products: [
                new PurchaseInputDTO(productId: 1, quantity: 20, price: 19),
                new PurchaseInputDTO(productId: 2, quantity: 10, price: 18)
        ])
        def vendingMachineOutputDTO = new VendingMachineOutputDTO(message: "Items purchased", change: 2)
        inventoryServiceMock.purchaseProduct(customerInputDTO) >> vendingMachineOutputDTO

        when:
        def result = productController.productPurchase(customerInputDTO)

        then:
        result == vendingMachineOutputDTO
    }
}
