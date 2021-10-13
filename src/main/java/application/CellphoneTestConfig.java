package application;

import java.util.ArrayList;
import java.util.List;

import bean.Cellphone;
import dao.CellphoneDao;
import dao.DaoFactory;

public class CellphoneTestConfig {

	public static void main(String[] args) {
		
		CellphoneDao cellphoneDao = DaoFactory.createCellphoneDao();
		Cellphone obj = new Cellphone(null, 64, "996662498", "Mobile", 20);
		List<Cellphone> cellphones = new ArrayList<>();
		Integer id = null;
		
		System.out.println("<<==== TEST 1: cellphone insert ====>>");
		cellphoneDao.insert(obj);
		id = obj.getId();
		if(id != null) {
			System.out.println("TEST 1 OK! New id = " + obj.getId());
		} else {
			System.out.println("TEST 1 FAILED!");
		}

		System.out.println("<<==== TEST 2: find by id ==========>>");
		obj = null;
		obj = cellphoneDao.findById(id);
		if(id.equals(obj.getId())) {
			System.out.println("TEST 2 OK! Cellphone found");
		} else {
			System.out.println("TEST 2 FAILED!");
		}

		System.out.println("\n<<==== TEST 3: find all ============>>");
		cellphones = cellphoneDao.findAll();
		if(cellphones.size() > 0) {
			System.out.println("TEST 3 OK! List not empty");
		} else {
			System.out.println("TEST 3 FAILED!");
		}

		System.out.println("\n<<==== TEST 4: update cell =========>>");
		Cellphone newCellphone = new Cellphone(null, 32, null, null, null);
		obj.setDdd(newCellphone.getDdd());
		cellphoneDao.update(obj);
		obj = cellphoneDao.findById(id);
		if(obj.getDdd().equals(newCellphone.getDdd())) {
			System.out.println("TEST 4 OK! Ddd updated");
		} else {
			System.out.println("TEST 4 FAILED!");
		}

		System.out.println("\n<<==== TEST 5: delete cell =========>>");
		cellphoneDao.delete(id);
		obj = null;
		obj = cellphoneDao.findById(id);
		if(obj == null) {
			System.out.println("TEST 5 OK! Cellphone deleted");
		} else {
			System.out.println("TEST 5 FAILED!");
		}
		

		cellphones = cellphoneDao.findAll();
		System.out.println(cellphones);
	}
}