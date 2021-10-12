package application;

import java.util.ArrayList;
import java.util.List;

import bean.User;
import dao.DaoFactory;
import dao.UserDao;

public class Program {

	public static void main(String[] args) {
		
		UserDao userDao = DaoFactory.createUserDao();
		User obj = new User(null, "Gabriel", "gabriel.freitas3110@gmail.com", "mypassword");
		List<User> users = new ArrayList<>();
		Integer id = null;
		
		System.out.println("<<====== TEST 1: user insert ======>>");
		userDao.insert(obj);
		id = obj.getId();
		if(id != null) {
			System.out.println("TEST 1 OK! New id = " + obj.getId());
		} else {
			System.out.println("TEST 1 FAILED!");
		}
		
		System.out.println("\n<<====== TEST 2: find by id =======>>");
		obj = null;
		obj = userDao.findById(id);
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
		userDao.delete(id);
		obj = null;
		obj = userDao.findById(id);
		if(obj == null) {
			System.out.println("TEST 5 OK! User deleted");
		} else {
			System.out.println("TEST 5 FAILED!");
		}
	}
}