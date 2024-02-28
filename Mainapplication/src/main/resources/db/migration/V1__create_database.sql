

CREATE TABLE productlist (
  productId INT IDENTITY(1,1) NOT NULL,
  name VARCHAR(45) NOT NULL,
  productPrice INT NOT NULL,
  productInventoryCount INT NOT NULL,
  PRIMARY KEY (productId)
);

CREATE TABLE purchasehistory_table (
  id INT IDENTITY(1,1) NOT NULL,
  productId INT NOT NULL,
  product VARCHAR(45) NOT NULL,
  productPrice INT NOT NULL,
  customerInputAmount INT NOT NULL,
  vendingMachineBalance INT NOT NULL,
  initialBalance INT NOT NULL,
  PRIMARY KEY (id)
);
