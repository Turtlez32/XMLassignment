
public class Artwork {

	protected String artDate;
	private String artAuthor;
	protected String artTitle;
	private String artTechnique;
	protected String artLocation;
	
	public Artwork(String tech, String authorName)
	{
		setArtTechnique(tech);
		setArtAuthor(authorName);
		
	}

	public String getArtTechnique() {
		return artTechnique;
	}

	public void setArtTechnique(String artTechnique) {
		this.artTechnique = artTechnique;
	}

	public String getArtAuthor() {
		return artAuthor;
	}

	public void setArtAuthor(String artAuthor) {
		this.artAuthor = artAuthor;
	}

	public void addArtwork(Artwork newArtwork) {
		
	}
}