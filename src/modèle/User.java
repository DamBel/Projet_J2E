package modèle;

import java.sql.Date;

public class User {
	
	private String pseudo, firstName, lastName, imagePath, flag, email, password;
	private String gender;
	private Date birthdate;
	private boolean banned, validation, admin;
	
	public User(){
		
	}

	public User(String pseudo, String firstName, String lastName, String imagePath, String flag, String email,
			String password, String gender, Date birthdate) {
		super();
		this.pseudo = pseudo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.imagePath = imagePath;
		this.flag = flag;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.birthdate = birthdate;
		this.banned = false;
		this.validation = true;
		this.admin = false;
	}

	public String getPseudo() {
		return pseudo;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public String getFlag() {
		return flag;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getGender() {
		return gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public boolean isBanned() {
		return banned;
	}

	public boolean isValidation() {
		return validation;
	}

	public boolean isAdmin() {
		return admin;
	}
	
}
