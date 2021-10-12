package db;

public class DbClassNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public  DbClassNotFoundException(String msg) {
		super(msg);
	}
}