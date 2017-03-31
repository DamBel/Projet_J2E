package modele;

import java.sql.Date;

/**
 * Classe utilisé pour représenter les youtubers
 * 
 * @deprecated Suite au passage à angular, l'utilisation de cette classe n'est plus utile
 * @see ./WebContent/vue/js/app.js
 * @author Damien & Tom
 */
public class Youtuber extends User{
	
	private String description, urlOWPC;
	
	public Youtuber(String pseudo, String description, String urlOWPC){
		
		super(pseudo, null, null, null, null, null, null, null, null);
		
	}

	public Youtuber(String pseudo, String firstName, String lastName, String imagePath, String flag, String email,
			String password, String gender, Date birthdate, boolean banned, boolean validation, boolean admin, String description, String urlOWPC) {
		super(pseudo, firstName, lastName, imagePath, flag, email, password, gender, birthdate);
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
