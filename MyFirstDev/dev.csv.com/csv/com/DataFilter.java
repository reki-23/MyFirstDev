package csv.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import exception.csv.com.InvalidElementsException;
import exception.csv.com.NoElementsOnFileException;
import tools.csv.com.LoggerUtil;

/*
 * CSVにフィルターをかけるクラス
 * id,姓、名、年齢、出身地、職業
 */

class DataFilter {	
	
	private static final Logger logger = Logger.getLogger(DataFilter.class.getName());
	private static final String METHOD_NAME = "メソッド:filteredFinalResult";
	private static final String CLASS_NAME = "クラス:DataFilter";
	
	
	static {
        logger.setUseParentHandlers(false);  // 親ハンドラーを無効化
    }
	
	
	//以下の各フィルターメソッドを呼び出す
	static List<PersonalInfo> filteredFinalResult(List<PersonalInfo> personalInfo) throws NoElementsOnFileException, InvalidElementsException{
		
		LoggerUtil.logInfo(DataFilter.class, "DataFilter.filter_start", CLASS_NAME, METHOD_NAME);
		
		List<PersonalInfo> filteredList = new ArrayList<PersonalInfo>();
		
		try(Scanner scan = new Scanner(System.in);){
			
			
			System.out.println("idでデータを絞りますか？");
			String input = scan.nextLine();

			
			//idで絞る場合
			if(input.equals("yes")) {

				System.out.println("idを入力してください。");
				String id = scan.nextLine();

				Predicate<PersonalInfo> personalIdFilter = person -> person.getId().equals(id);

				filteredList = personalInfo.stream().filter(personalIdFilter).collect(Collectors.toList());

			
				
			//idで絞らない場合	
			}else if(input.equals("no")){
			
				System.out.print("姓を入力して下さい:");
				String familyName = scan.nextLine();
				
				System.out.print("名を入力して下さい:");
				String firstName = scan.nextLine();
				
				System.out.print("年齢を入力して下さい:");
				int age = DataValidator.returnValidAge(scan.nextLine());
				
				System.out.print("出身地を入力して下さい:");
				String placeOfBirth = scan.nextLine();
				
				System.out.print("職業を入力して下さい:");
				String occpation = scan.nextLine();
				
				Predicate<PersonalInfo> personalFieldsFilter = person -> 
											person.getFamilyName().contains(familyName) &&
											person.getFirstName().contains(firstName) &&
											person.getAge() == age &&
											person.getPlaceOfBirth().contains(placeOfBirth) &&
											person.getOccupation().contains(occpation);
							

				filteredList = personalInfo.stream().filter(personalFieldsFilter).collect(Collectors.toList());
			
				
			//yes,no以外が入力された場合
			}else {
				LoggerUtil.logWarning(DataFilter.class, "DataFilter.filter_failure", CLASS_NAME, METHOD_NAME);
				throw new NoElementsOnFileException("お探しのデータはありません。");	
			}
			
			if(filteredList.isEmpty()) {
				LoggerUtil.logWarning(DataFilter.class, "DataFilter.filter_failure", CLASS_NAME, METHOD_NAME);
				throw new NoElementsOnFileException("お探しのデータはありません。");
			}
			
			LoggerUtil.logInfo(DataFilter.class, "DataFilter.filter_complete", CLASS_NAME, METHOD_NAME);
			
			return filteredList;
			
		}
		
	}
}
