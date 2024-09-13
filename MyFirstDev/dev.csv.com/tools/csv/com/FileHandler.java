package tools.csv.com;

import java.io.IOException;
import java.util.List;

import csv.com.PersonalInfo;
import exception.csv.com.FileIsNotExistException;
import exception.csv.com.InvalidElementsException;
import exception.csv.com.NoElementsOnFileException;

public interface FileHandler {
	
	//ファイルに異常がないかを確認
	void confirmAbnormalities(String fileName) throws FileIsNotExistException, NoElementsOnFileException, IOException;
	
	
	//ファイル読みこみ
	List<PersonalInfo> readCsv(String fileName) throws IOException, InvalidElementsException;
	
	
	//ファイル書き込み
	void writeToCSV(List<PersonalInfo> personalInfo, String filteredFileName) throws IOException, NoElementsOnFileException;
}
