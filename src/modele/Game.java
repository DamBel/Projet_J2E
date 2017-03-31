package modele;

import java.sql.Date;

/**
 * Classe qui sera utilisée pour une future mise à jour du site
 * @author Damien
 *
 */
public class Game {
	
	private String name, imgPath;
	private Date releaseDate;
	
	public Game(String name, String imgPath, Date releaseDate) {
		super();
		this.name = name;
		this.imgPath = imgPath;
		this.releaseDate = releaseDate;
	}

	public String getName() {
		return name;
	}

	public String getImgPath() {
		return imgPath;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}	

}
