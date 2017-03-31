package modèle;

/**
 * Classe utilisé pour représenter les outils
 * 
 * @deprecated Suite au passage à angular, l'utilisation de cette classe n'est plus utile
 * @see ./WebContent/vue/js/app.js
 * 
 * @author Damien
 */
public class Tool {
	
	private String name, imgPath, url;

	public Tool(String name, String imgPath, String url) {
		super();
		this.name = name;
		this.imgPath = imgPath;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public String getImgPath() {
		return imgPath;
	}

	public String getUrl() {
		return url;
	}	

}
