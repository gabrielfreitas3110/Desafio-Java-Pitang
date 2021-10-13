package application;

import java.util.ArrayList;
import java.util.List;

import bean.Cellphone;
import bean.User;
import dao.CellphoneDao;
import dao.DaoFactory;
import dao.UserDao;

public class UserTestConfig {

	public static void main(String[] args) {
		
		UserDao userDao = DaoFactory.createUserDao();
		CellphoneDao cellphoneDao = DaoFactory.createCellphoneDao();
		
		User obj = new User(null, "Gabriel", "gabriel.freitas3110@gmail.com", "mypassword");
		List<User> users = new ArrayList<>();
		Integer id = null;
		
		System.out.println("<<====== TEST 1: user insert ======>>");
		userDao.insert(obj);
		id = obj.getId();
		
		Cellphone cell = new Cellphone(null, 64, "996662498", "Mobile", null);
		Cellphone cell2 = new Cellphone(null, 32, "40028922", "Fix", null);
		
		cell.setUser_id(id);
		cell2.setUser_id(id);
		
		cellphoneDao.insert(cell);
		cellphoneDao.insert(cell2);
		
		Integer idCell = null;
		Integer idCell2 = null;

		idCell = cell.getId();
		idCell2 = cell2.getId();
		
		if(id != null && idCell != null) {
			System.out.println("TEST 1 OK! New id = " + obj.getId());
		} else {
			System.out.println("TEST 1 FAILED!");
		}
		
		System.out.println("\n<<====== TEST 2: find by id =======>>");
		obj = null;
		obj = userDao.findById(id);
		System.out.println(obj);
		if(id == obj.getId()) {
			System.out.println("TEST 2 OK! User found");
		} else {
			System.out.println("TEST 2 FAILED!");
		}
		
		System.out.println("\n<<====== TEST 3: find all =========>>");
		users = userDao.findAll();
		if(users.size() > 0) {
			System.out.println("TEST 3 OK! List not empty");
		} else {
			System.out.println("TEST 3 FAILED!");
		}

		System.out.println("\n<<====== TEST 4: update user ======>>");
		User newUser = new User(null, "Augusto", null, null);
		obj.setName(newUser.getName());
		userDao.update(id, obj);
		obj = userDao.findById(id);
		if(obj.getName().equals(newUser.getName())) {
			System.out.println("TEST 4 OK! Name updated");
		} else {
			System.out.println("TEST 4 FAILED!");
		}

		System.out.println("\n<<====== TEST 5: delete user ======>>");
		cellphoneDao.delete(idCell);
		cellphoneDao.delete(idCell2);
		userDao.delete(id);
		obj = null;
		cell = null;
		obj = userDao.findById(id);
		cell = cellphoneDao.findById(idCell);
		if(obj == null && cell == null) {
			System.out.println("TEST 5 OK! User deleted");
		} else {
			System.out.println("TEST 5 FAILED!");
		}
		
		System.out.println("------------------------------------");
		users = userDao.findAll();
		System.out.println(users);
	}
}