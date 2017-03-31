package modèle;

import java.sql.Date;

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
