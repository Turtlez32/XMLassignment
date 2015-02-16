import org.w3c.dom.Element;

public class XMLHelper
{
	public static Artwork newArtwork(Element element)
	{
		Artwork artwork = new Artwork(getArtworkTech(element),
									  getArtworkAuthor(element));
		
		//artwork.setDate(getAuthorName(element));
		//artwork.setArtTechnique(getAuthorTech(element));
		
		return artwork;
	}
	
	private static String getArtworkTech(Element element) {
		
		return element.getElementsByTagName("form")
				      .item(0)
				      .getTextContent();
	}

	public static Author newAuthor(Element element)
	{
		Author author = new Author(getAuthorName(element), 
				                   getAuthorBorn(element), 
				                   getAuthorDied(element), 
				                   getAuthorNationality(element));
		
		//author.addArtwork(newArtwork(element));
		
		return author;
	}
	
	private static String getAuthorNationality(Element element) {
		return element.getElementsByTagName("nationality")
				      .item(0)
				      .getTextContent();
	}

	public static boolean isAuthor(Element element)
	{		
		if (element.getElementsByTagName("name").item(0) == null)
	    {
	       return false;
	    }
		
		return true;
	}
	
	public static boolean isArtwork(Element element)
	{
		if (element.getElementsByTagName("title").item(0) == null)
		{
			return false;
		}
		
		return true;
	}
	
	public static String getAuthorName(Element element)
	{
		return element.getElementsByTagName("name").item(0).getTextContent();
	}
	
	public static String getArtworkAuthor(Element element)
	{
		return element.getElementsByTagName("author").item(0).getTextContent();
	}
	
	public static String getAuthorBorn(Element element)
	{
		String born = element.getElementsByTagName("born-died").item(0).getTextContent();
		String[] parts = born.split(",");
		
		String splitString;
		
		splitString = parts[0];
		String[] numbers = splitString.split("[^0-9]+");
		
		String returnNumber = numbers[1];
		
		return returnNumber;
	}

	public static String getAuthorDied(Element element)
	{
		
		String died = null;
		String test = element.getElementsByTagName("born-died").item(0).getTextContent();

		String[] diedNumber = test.split("[^0-9]+");

		if(diedNumber[2].length() == 2)
		{
			died = diedNumber[1];
		}
		else
		{
			died = diedNumber[2];
		}
		
		return died;
	}
	
	public static String getAuthorBiography(Element element)
	{
		return element.getElementsByTagName("biography").item(0).getTextContent();
	}
	
}