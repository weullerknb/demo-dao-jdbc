package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		
		System.out.println("==== TEST 1: department findById ====");
		Department dep = depDao.findById(1);
		System.out.println(dep);
		
		System.out.println("\n==== TEST 2: department findAll ====");
		List<Department> list = depDao.findAll();
		for (Department x : list) {
			System.out.println(x);
		}
		
		System.out.println("\n==== TEST 3: department insert ====");
		Department newDepartment = new Department(null, "Music");
		depDao.insert(newDepartment);
		System.out.println(newDepartment);
		
		System.out.println("\n==== TEST 4: department update ====");
		dep = depDao.findById(1);
		dep.setName("Music");
		depDao.update(dep);
		System.out.println("Update completed");
		
		System.out.println("\n==== TEST 5: department deleteById ====");
		depDao.deleteById(10);
		System.out.println("Delete completed");
	}
	
}
