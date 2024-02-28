package Vendingmachine.dao;



import Vendingmachine.model.InitialBalanceAndPurchaseHistory;

import java.util.List;

public interface InitialBalanceDAO {

    InitialBalanceAndPurchaseHistory getChange();

    List<InitialBalanceAndPurchaseHistory> getAllPurchaseHistory();

    int saveTransaction(InitialBalanceAndPurchaseHistory initialBalanceAndPurchaseHistory);

}
