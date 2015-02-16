import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XML {
	
	public static Document doc;
	public static Document XMLRead(String location)
	{
		try {
			/* Try to open the file at the location provided */
			File file = new File(location);
	
			/* Try to create the correct file format and then parse the file
			     to a given format */
			DocumentBuilder db = DocumentBuilderFactory
					             .newInstance()
					             .newDocumentBuilder();
			doc = db.newDocument();
			doc = db.parse(file);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		/* Return the file to be usable with other functions */
		return doc;
	}

	public static Document openAuthorsXML() throws SAXException, IOException
	{
		Document parsedDoc = null;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		
			parsedDoc = builder.parse("src/output.xml");
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		
		return parsedDoc;
	}
	
	public static Document openArtworksDocument() throws SAXException, IOException
	{
		Document artworkParsed = null;
		
		try {
			DocumentBuilder artwork = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			artworkParsed = artwork.parse("src/artworks.xml");
			
		} catch (ParserConfigurationException e) {
			System.out.println("Error Opening artworks.xml");
			e.printStackTrace();
		}
		
		return artworkParsed;
	}
}