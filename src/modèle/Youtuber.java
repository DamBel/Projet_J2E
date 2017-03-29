package modèle;

import java.sql.Date;

public class Youtuber extends User{
	
	private String description, urlOWPC;
	
	public Youtuber(){
		
	}

	public Youtuber(String pseudo, String firstName, String lastName, String imagePath, String flag, String email,
			String password, char gender, Date birthdate, boolean banned, boolean validation, boolean admin, String description, String urlOWPC) {
		super(pseudo, firstName, lastName, imagePath, flag, email, password, gender, birthdate, banned, validation, admin);
		this.description = description;
		this.urlOWPC = urlOWPC;
	}

	public String getDescription() {
		return description;
	}

	public String getUrlOWPC() {
		return urlOWPC;
	}

}
