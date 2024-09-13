package csv.com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import exception.csv.com.FileIsNotExistException;
import exception.csv.com.InvalidElementsException;
import exception.csv.com.NoElementsOnFileException;
import tools.csv.com.FileHandler;
import tools.csv.com.LoggerUtil;


/*
 * CSVに異常がないかをチェックし、CSVを読み書きするクラス
 */

class CsvFileHandler implements FileHandler{
	
	private static final Logger logger = Logger.getLogger(CsvFileHandler.class.getName());
	private static final String[] METHOD_NAME = {"メソッド:confirmAbnormalities", "メソッド:readCsv", "メソッド:separatedFieldsWithComma", "メソッド:writeToCSV"};
	private static final String CLASS_NAME = "クラス:CsvFileHandler";
	
	static {
        logger.setUseParentHandlers(false);  // 親ハンドラーを無効化
    }

	//読み込むファイルに異常がないか確認(読み込む前の確認)
	@Override
	public void confirmAbnormalities(String fileName) 
			throws FileIsNotExistException, NoElementsOnFileException, IOException{
		
		
		LoggerUtil.logInfo(CsvFileHandler.class, "CsvFileHandler.confirm_start", CLASS_NAME, METHOD_NAME[0], fileName);
		
		
		Path fileNameToPath = Paths.get(fileName);

		//ファイルが存在しているか
		if(!Files.exists(fileNameToPath)) {
			LoggerUtil.logWarning(CsvFileHandler.class, "CsvFileHandler.file_not_found", CLASS_NAME, METHOD_NAME[0], fileName);
			throw new FileIsNotExistException("ファイルが存在しません。");
		}
		
		//ファイルのサイズが0
		if(Files.size(fileNameToPath) == 0) {
			LoggerUtil.logWarning(CsvFileHandler.class, "CsvFileHandler.no_data", CLASS_NAME, METHOD_NAME[0], fileName);
			throw new NoElementsOnFileException("ファイルにデータが存在しません。");
		}
		
		//ファイルにデータが存在しない場合
		try(BufferedReader reader = Files.newBufferedReader(fileNameToPath)){
			
			String header = reader.readLine();  //ヘッダー行
			String secondLine = reader.readLine();  //2行目のデータ
			
			//ヘッダーが存在していないか、2行目が存在していない場合
			if(header == null || secondLine == null) {
				LoggerUtil.logWarning(CsvFileHandler.class, "CsvFileHandler.no_data", CLASS_NAME, METHOD_NAME[0], fileName);
				throw new NoElementsOnFileException("ファイルにデータが存在しません。");
			}
			
		}
		
		LoggerUtil.logInfo(CsvFileHandler.class, "CsvFileHandler.confirm_end", CLASS_NAME, METHOD_NAME[0], fileName);
	}
	
	
	
	//ファイルの読み込み
    @Override
    public List<PersonalInfo> readCsv(String fileName) throws IOException, InvalidElementsException{

    	LoggerUtil.logInfo(CsvFileHandler.class, "CsvFileHandler.read_start", CLASS_NAME, METHOD_NAME[1], fileName);
    	
    	List<PersonalInfo> personalInfoList = new ArrayList<PersonalInfo>();
    	
        try (BufferedReader fileLines = new BufferedReader(new FileReader(fileName))) {
        	
        	personalInfoList = fileLines
        			.lines()
        			.skip(1)  //ヘッダー無視
        			.map(fileLine -> fileLine.split(","))
        			.map(this::separatedFieldsWithComma)
        			.collect(Collectors.toList());
               
        }catch(InvalidElementsException e) {
        	LoggerUtil.logWarning(CsvFileHandler.class, "CsvFileHandler.invalid_data_found", CLASS_NAME, METHOD_NAME[1], fileName);
        	throw e;
        	
        }catch(IOException e) {
        	LoggerUtil.logSevere(CsvFileHandler.class, "CsvFileHandler.file_I/O_failure", CLASS_NAME, METHOD_NAME[1], fileName);
        	throw e;
       
        }
        
        LoggerUtil.logInfo(CsvFileHandler.class, "CsvFileHandler.read_complete", CLASS_NAME, METHOD_NAME[1], fileName);
        
        return personalInfoList;
    
    }
    
    
    
    
    //各フィールドをカンマで区切る
    private PersonalInfo separatedFieldsWithComma(String[] fields) throws InvalidElementsException{
    	
    	try {
	        //id
	        String id = DataValidator.returnValidId(DataValidator.getFieldsSafely(fields, 0));
	        
	        //姓
	        String familyName = DataValidator.returnValidFamilyName(DataValidator.getFieldsSafely(fields, 1));
	        
	        //名
	        String firstName = DataValidator.returnValidFirstName(DataValidator.getFieldsSafely(fields, 2));
	        
	        //年齢
	        int age = DataValidator.returnValidAge(DataValidator.getFieldsSafely(fields, 3));
	        
	        //生年月日
	        LocalDate dateOfBirth = DataValidator.returnValidDateOfBirth(DataValidator.getFieldsSafely(fields, 4));
	        
	        //出身地
	        String placeOfBirth = DataValidator.returnValidPlaceOfBirth(DataValidator.getFieldsSafely(fields, 5));
	        
	        //職業
	        String occupation = DataValidator.returnValidOccupation(DataValidator.getFieldsSafely(fields, 6));
	        		
	        //現住所
	        String currentAddress = DataValidator.returnValidCurrentAddress(DataValidator.getFieldsSafely(fields, 7));
	        
	        //メールアドレス
	        String email = DataValidator.returnValidEmail(DataValidator.getFieldsSafely(fields, 8));
	        
	        //電話番号
	        String phoneNumber = DataValidator.returnValidPhoneNumber(DataValidator.getFieldsSafely(fields, 9));
	        
    	
	        return new PersonalInfo(id, familyName, firstName, age, dateOfBirth, placeOfBirth, occupation, currentAddress, email, phoneNumber);
	        
    	}catch(InvalidElementsException e) {
	    	throw e;
	    }
	        
	   
    }
	
	
	
	//CSVとして出力
	@Override
	public void writeToCSV(List<PersonalInfo> personList, String filteredFileName) throws IOException{
		
		LoggerUtil.logInfo(CsvFileHandler.class, "CsvFileHandler.write_start", CLASS_NAME, METHOD_NAME[3], filteredFileName);
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filteredFileName))){
			
			//ヘッダーを追加
			bw.write("ID,姓,名,年齢,生年月日,出身地,職業,現住所,メールアドレス,電話番号");
			bw.newLine();
			
			for(PersonalInfo person : personList) {
				bw.write(person.toString().trim());
				bw.newLine();
			}
			
			bw.flush();
		
		}
		catch(IOException e) {
			LoggerUtil.logSevere(CsvFileHandler.class, "CsvFileHandler.write_failure", CLASS_NAME, METHOD_NAME[3], filteredFileName);
			LoggerUtil.logSevere(CsvFileHandler.class, "CsvFileHandler.file_I/O_failure", CLASS_NAME, METHOD_NAME[1], filteredFileName);
			throw e;
		}
		
		LoggerUtil.logInfo(CsvFileHandler.class, "CsvFileHandler.write_complete", CLASS_NAME, METHOD_NAME[3], filteredFileName);
	}
}
