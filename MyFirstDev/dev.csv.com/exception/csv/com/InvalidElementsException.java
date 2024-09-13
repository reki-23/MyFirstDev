package exception.csv.com;

public class InvalidElementsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	String message;
	
	public InvalidElementsException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
