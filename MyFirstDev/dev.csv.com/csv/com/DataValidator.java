package csv.com;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.csv.com.InvalidElementsException;

/*
 *形式通りか確認するクラス 
 */

class DataValidator {
	
	//生年月日の形式
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	
	//csvに欠けているフィールドがある場合、空文字を返すように配列操作の安全性を担保する
	static String getFieldsSafely(String[] fields, int index) {
		
		if(index < fields.length) {
			return fields[index].replaceAll("\"", "");
		}else {
			return "";
		}
	}
	
	
	
	//有効なidかを確認
	static boolean isValidIdCheck(String id) throws InvalidElementsException{
			
		if(id.isEmpty()) {
			return false;
		}
		
		return id.matches("[1-9]\\d*");	

	}
	
	
	//有効なidを返す
	static String returnValidId(String id) throws InvalidElementsException{
	
		if(!DataValidator.isValidIdCheck(id)) {
			if(id.isEmpty()) {
				throw new InvalidElementsException("idが含まれていないデータがあります。");
			}
			throw new InvalidElementsException("無効なidが含まれています。:" + id);
			
		}
		return id;
		
	}
	
	
	//姓が存在すれば返す
	static String returnValidFamilyName(String familyName) throws InvalidElementsException{
		
		if(familyName.isEmpty()) {
			throw new InvalidElementsException("姓が含まれていないデータがあります。");
			
		}
		return familyName;
		
	}
	
	
	//名が存在すれば返す
	static String returnValidFirstName(String firstName) throws InvalidElementsException{
		
		if(firstName.isEmpty()) {
			throw new InvalidElementsException("名が含まれていないデータがあります。");
		}
		return firstName;
	}
	
	
	//出身地が存在すれば返す
	static String returnValidPlaceOfBirth(String placeOfBirth) throws InvalidElementsException{
		
		if(placeOfBirth.isEmpty()) {
			throw new InvalidElementsException("出身地が含まれていないデータがあります。");
		}
		return placeOfBirth;
	}
	
	
	//職業が存在すれば返す
	static String returnValidOccupation(String occupation) throws InvalidElementsException{
		
		if(occupation.isEmpty()) {
			throw new InvalidElementsException("職業が含まれていないデータがあります。");
		}
		return occupation;
	}
	
	
	//現住所が存在すれば返す
	static String returnValidCurrentAddress(String currentAddress) throws InvalidElementsException{
		
		if(currentAddress.isEmpty()) {
			throw new InvalidElementsException("現住所が含まれてないデータがあります。");
		}
		return currentAddress;
	}
	
	
	
	//年齢の形式と範囲チェック
	static boolean isValidAgeFormat(String tmpAge) {
		
		try {
			
			int age = Integer.parseInt(tmpAge);
			if(age < 0 || 130 < age) {
				return false;				
			}
			return true;
			
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	
	//年齢の型チェック
    static int returnValidAge(String tmpAge) throws InvalidElementsException{
    	
    	if (!DataValidator.isValidAgeFormat(tmpAge)) {
    		if(tmpAge.isEmpty()) {
    			throw new InvalidElementsException("年齢が含まれていないデータがあります。");
    		}
            throw new InvalidElementsException("不正な形式または範囲外の年齢が含まれています:" + tmpAge);
        }
        int age = Integer.parseInt(tmpAge);
    	
    	return age;
    }
	
	
	//生年月日の形式チェック
	static boolean isValidDateFormat(String dateOfBirthStr, DateTimeFormatter formatter){
		
	    try {
	        LocalDate.parse(dateOfBirthStr, formatter);
	        return true;
	   
	    } catch (DateTimeParseException e) {
	        return false;
	    }
	}
	
    
    //生年月日の型チェック
    static LocalDate returnValidDateOfBirth(String dateOfBirthStr) throws InvalidElementsException{
    	
        if (!DataValidator.isValidDateFormat(dateOfBirthStr, DATE_FORMATTER)) {
        	if(dateOfBirthStr.isEmpty()) {
        		throw new InvalidElementsException("生年月日が含まれていないデータがあります。");
        	}
            throw new InvalidElementsException("不正な形式の生年月日が含まれています。:" + dateOfBirthStr);
        }
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr, DATE_FORMATTER);
        
        return dateOfBirth;
    }
	
	
	//Eメールの形式チェック
	static boolean isValidEmailFormat(String email){
		
		String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
		
		return email.matches(emailRegex);
	}
	
	
	//メールアドレスが存在したら返す
	static String returnValidEmail(String email) throws InvalidElementsException{
			
		if(!DataValidator.isValidEmailFormat(email)) {
			if(email.isEmpty()) {
				throw new InvalidElementsException("メールアドレスが含まれていないデータがあります。");
			}
			throw new InvalidElementsException("不正な形式のメールアドレスが含まれています。:" + email);
		}
		
		return email;
		
	}
			
	
	//電話番号の形式チェック
	static boolean isValidPhoneNumberFormat(String phoneNumber) {
		
		String phoneRegex = "\\d{2,3}-\\d{4,5}-\\d{4}";
		
		return phoneNumber.matches(phoneRegex);
		
	}
	
	
	//電話番号が存在したら返す
	static String returnValidPhoneNumber(String phoneNumber) throws InvalidElementsException{
		
		if(!DataValidator.isValidPhoneNumberFormat(phoneNumber)) {
			if(phoneNumber.isEmpty()) {
				throw new InvalidElementsException("電話番号が含まれていないデータがあります。");
			}
			throw new InvalidElementsException("不正な形式の電話番号が含まれています。:" + phoneNumber);
		}
		
		return phoneNumber;
	}
	
}
