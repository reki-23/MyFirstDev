package csv.com;

import java.time.LocalDate;
import java.util.logging.Logger;

public class PersonalInfo{
	
	private String id;
	private String familyName;
	private String firstName;
	private int age;
	private LocalDate dateOfBirth;
	private String placeOfBirth;
	private String occupation;
	private String currAddress;
	private String email;
	private String phoneNumber;
	
	PersonalInfo(String id, String familyName, String firstName, int age, LocalDate dateOfBirth, String placeOfBirth,
			String occupation, String currAddress, String email, String phoneNumber) {
		super();
		this.id = id;
		this.familyName = familyName;
		this.firstName = firstName;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.placeOfBirth = placeOfBirth;
		this.occupation = occupation;
		this.currAddress = currAddress;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate LocalDateOfBirth) {
		this.dateOfBirth = LocalDateOfBirth;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getCurrAddress() {
		return currAddress;
	}

	public void setCurrAddress(String currAddress) {
		this.currAddress = currAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return id + "," + familyName + "," + firstName + "," + age + "," + dateOfBirth + "," + placeOfBirth + ","
				+ occupation + "," + currAddress + "," + email + "," + phoneNumber;
	}
}