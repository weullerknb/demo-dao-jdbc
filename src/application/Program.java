package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Department dp = new Department(1, "Books");
		Seller seller = new Seller(1, "Sasuke", "sasuke@gmail.com", new Date(), 3000.0, dp);
		
		System.out.println(seller);
	}

}
