package exception.csv.com;

import java.io.FileNotFoundException;

/*
 * ファイルが存在していない場合の例外クラス
 */
public class FileIsNotExistException extends FileNotFoundException{
	
	private static final long serialVersionUID = 1L;
	String message;
	
	public FileIsNotExistException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
