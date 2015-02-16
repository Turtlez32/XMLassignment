
public class Author {

	private String authorName;
	private String born;
	private String died;
	private String form;
	private String nationality;

	public Author(String authorName, String authorBorn, String authorDied,
			String nationality) {
		setAuthorName(authorName);
		setBorn(authorBorn);
		setDied(authorDied);
		setNationality(nationality);
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBorn() {
		return born;
	}

	public void setBorn(String born) {
		this.born = born;
	}

	public String getDied() {
		return died;
	}

	public void setDied(String died) {
		this.died = died;
	}

	public String getArtwork() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addAuthor(Author newAuthor) {
		
		
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}
}
