package dao;

import java.util.List;

import bean.User;

public interface UserDao {

	List<User> findAll();

	User findById(Integer id);

	void insert(User obj);

	void update(User obj);

	void delete(Integer id);
}