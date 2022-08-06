package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("==== TEST 1: seller findById ====");
		Seller seller = sellerDao.findById(2);
		System.out.println(seller);
		
		System.out.println("\n==== TEST 2: seller findByDepartment ====");
		Department dep = new Department(3, null);
		List<Seller> list = sellerDao.findByDepartment(dep);
		for (Seller x : list) {
			System.out.println(x);
		}
		
		System.out.println("\n==== TEST 3: seller findAll ====");
		list = sellerDao.findAll();
		for (Seller x : list) {
			System.out.println(x);
		}
		
		System.out.println("\n==== TEST 4: seller insert ====");
		Seller newSeller = new Seller(null, "Greg", "Greg@gmail.com", new Date(), 4000.0, dep);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());
	}

}
