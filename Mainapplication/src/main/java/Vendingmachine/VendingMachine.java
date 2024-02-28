package Vendingmachine;


import Vendingmachine.Logger.LoggingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VendingMachine {
	public static void main(String[] args) {
		SpringApplication.run(VendingMachine.class, args);
		new LoggingController().index();
	}
}
