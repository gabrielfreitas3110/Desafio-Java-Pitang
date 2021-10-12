package dao;

import java.util.List;

import bean.Cellphone;

public interface CellphoneDao {

	List<Cellphone> findAll();

	Cellphone findById(Integer id);

	void insert(Cellphone obj);

	void update(Integer id, Cellphone obj);

	void delete(Integer id);
}