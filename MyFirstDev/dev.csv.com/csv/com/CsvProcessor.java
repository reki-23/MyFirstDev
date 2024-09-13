package csv.com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import exception.csv.com.FileIsNotExistException;
import exception.csv.com.InvalidElementsException;
import exception.csv.com.NoElementsOnFileException;
import tools.csv.com.LoggerUtil;


/*
 * 全体のフローを管理するクラス(ここがメインクラス)
 */


class CsvProcessor{

	private static final Logger logger = Logger.getLogger(CsvProcessor.class.getName());
	private static final String CLASS_NAME = "クラス:CsvProcessor";
	
	public static void main(String[] args) {

		
		try {

			logger.setUseParentHandlers(false);
			
			final String fileName = "PersonalInfo.csv";  //読みこみたいファイル
			final String filteredFileName = "PersonalInfo_bk.csv";  //フィルター後のファイル
			
			LoggerUtil.logInfo(CsvProcessor.class, "CsvProcessor.start", CLASS_NAME);
			
			Factory factory = new Factory();  //各インスタンスを生成するためのクラス
			CsvFileHandler fileHandlers = factory.createCsvFileHandler();
			ResultDisplay resultDisplay = factory.createResultDisplay();	        
			List<PersonalInfo> personalInfo = new ArrayList<PersonalInfo>();	
			
			
			LoggerUtil.logInfo(CsvProcessor.class, "CsvProcessor.confirm_start", CLASS_NAME ,fileName);
			fileHandlers.confirmAbnormalities(fileName);  //異常がないか確認
			
			
			LoggerUtil.logInfo(CsvProcessor.class, "CsvProcessor.file_read_start", CLASS_NAME ,fileName);
			personalInfo = fileHandlers.readCsv(fileName);  //ファイルの読み込み
			
			
			LoggerUtil.logInfo(CsvProcessor.class, "CsvProcessor.file_filter_start", CLASS_NAME ,fileName);
			List<PersonalInfo> personList = DataFilter.filteredFinalResult(personalInfo);  //条件で絞込み
			
			
			LoggerUtil.logInfo(CsvProcessor.class, "CsvProcessor.file_write_start", CLASS_NAME ,filteredFileName);
			fileHandlers.writeToCSV(personList, filteredFileName);  //ファイルへの書き込み
			
			
			resultDisplay.outputResultCount(filteredFileName);  //出力件数の表示
			
			
		}catch(FileIsNotExistException | NoElementsOnFileException | InvalidElementsException e){
			LoggerUtil.logWarning(CsvProcessor.class, "CsvFileHandler.file_read_write_failure", e);
			return;
			
		}catch(IOException e) {
			LoggerUtil.logSevere(CsvProcessor.class, "CsvFileHandler.file_I/O_failure", e);
			return;
		
		}catch(Exception e) {
			LoggerUtil.logSevere(CsvProcessor.class, "CsvFileHandler.file_error", e);
			return;
		}
		
		LoggerUtil.logInfo(CsvProcessor.class, "CsvProcessor.end", CLASS_NAME);
		
	}
}