package exception.csv.com;

import java.io.IOException;

public class NoElementsOnFileException extends IOException{
	
	private static final long serialVersionUID = 1L;
	String message;

	public NoElementsOnFileException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
