--
--INSERT INTO productlist ('productId','name','productPrice','productInventryCount')
--VALUES (1,"dietcoke",35,3),
--       (2,"ThumbsUp",40,10),
--       (3,"coldcoffee",20,12),
--       (4,"lays",10,20),
--       (5,"cheetos",10,0),
--       (6,"cocoCola",40,15);
--
--
--INSERT INTO purchasehistory_table
--('id',
--'productId',
SET IDENTITY_INSERT productlist ON;

INSERT INTO productlist (productId, name, productPrice, productInventoryCount)
VALUES (1, 'dietcoke', 35, 3),
       (2, 'ThumbsUp', 40, 10),
       (3, 'coldcoffee', 20, 12),
       (4, 'lays', 10, 20),
       (5, 'cheetos', 10, 0),
       (6, 'cocoCola', 40, 15);

SET IDENTITY_INSERT productlist OFF;


INSERT INTO purchasehistory_table
(productId, product, productPrice, customerInputAmount, vendingMachineBalance, initialBalance)
VALUES
(000, 'Admin_Update', 0, 0, 0, 10000);
