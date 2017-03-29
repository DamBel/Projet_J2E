package modèle;

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
