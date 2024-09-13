package csv.com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import tools.csv.com.LoggerUtil;

/*
 * 出力結果を表示するクラス
 */

class ResultDisplay {
	
	private static final Logger logger = Logger.getLogger(ResultDisplay.class.getName());
	private static final String CLASS_NAME = "クラス名:ResultDisplay";
	private static final String METHOD_NAME = "メソッド名:outputResultCount";
	
	static {
		logger.setUseParentHandlers(false);
	}
	
	//全何件分のデータを出力できたかを確認
	void outputResultCount(String filteredFileName) {
		
		try {
			
			Path filteredFileNameToPath = Paths.get(filteredFileName);
			long dataCount = Files.lines(filteredFileNameToPath).count();
			
			//-1でヘッダーを除いた出力件数を表示
			if(dataCount > 0) {
				System.out.println("全" + (dataCount - 1) + "件が出力されました");				
			}
			
			LoggerUtil.logInfo(ResultDisplay.class, "ResultDisplay.display_output_result_count", dataCount - 1, CLASS_NAME, METHOD_NAME);
			
		}catch(IOException e) {
			LoggerUtil.logSevere(ResultDisplay.class, "ResultDisplay.file_I/O_failure", CLASS_NAME, METHOD_NAME);
			e.printStackTrace();
		}
	}
}
