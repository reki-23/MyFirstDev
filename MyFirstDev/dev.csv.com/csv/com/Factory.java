package csv.com;

/*
 * 各クラスのインスタンスを返すFactoryクラス
 * あまり意味をなしていないので要修正
 */

public class Factory {
	
	public CsvFileHandler createCsvFileHandler() {
        return new CsvFileHandler();
    }
    
	
	public DataValidator createDataValidator() {
        return new DataValidator();
    }
    
	
	public DataFilter createDataFilter() {
        return new DataFilter();
    }
    
	
	public ResultDisplay createResultDisplay() {
        return new ResultDisplay();
    }
	
}
