package bean;

import java.io.Serializable;
import java.util.Objects;

public class Cellphone implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer ddd;
	private String number;
	private String type;
	private Integer user_id;
	
	public Cellphone() {
	}
	
	public Cellphone(Integer id, Integer ddd, String number, String type, Integer user_id) {
		this.id = id;
		this.ddd = ddd;
		this.number = number;
		this.type = type;
		this.user_id = user_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cellphone other = (Cellphone) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return id + ", (" + ddd + ") " + number + ", " + type + ", " + user_id + "\n";
	}
}