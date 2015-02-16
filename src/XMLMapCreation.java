import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;


public class XMLMapCreation {

	public static Map<String, Author> getAuthorsFromDocument(Document document) throws Exception
	{
		Map<String, Author> authors = new TreeMap<String, Author>();
		
		TreeWalker walker = (TreeWalker) ((DocumentTraversal) document)
										.createTreeWalker(document.getDocumentElement(),
										NodeFilter.SHOW_ELEMENT, new AuthorFilter(), true);
		
		Element element = null;
		
		while ((element = (Element) walker.nextNode()) != null)
		{
			if (!XMLHelper.isAuthor(element))
			{
				throw new Exception("Author is not valid. Missing attributes and/or child element.");
			}
			
			String author = XMLHelper.getAuthorName(element);
			
			//System.out.println("element = " + author.toString());
			
			if (authors.containsKey(author))
			{
				authors.get(author).addAuthor(XMLHelper.newAuthor(element));
			}
			else
			{
				authors.put(author, XMLHelper.newAuthor(element));
			}
		}
		
		return authors;
	}

	public static Map<String, Artwork> getArtworkFromDocument(Document document) throws Exception
	{
		Map<String, Artwork> artwork = new TreeMap<String, Artwork>();
		
		TreeWalker walker = (TreeWalker) ((DocumentTraversal) document)
				                         .createTreeWalker(document.getDocumentElement(),
				                        		           NodeFilter.SHOW_ELEMENT, 
				                        		           new ArtworkFilter(),
				                        		           true);
		Element element = null;
		
		while ((element = (Element) walker.nextNode()) != null)
		{
			if (!XMLHelper.isArtwork(element))
			{
				throw new Exception("Artwork is not valid. Missing attributes and /or children");
			}
			
			String author = XMLHelper.getArtworkAuthor(element);
	
			
			if (artwork.containsKey(author))
			{
				artwork.get(author).addArtwork(XMLHelper.newArtwork(element));
			}
			else
			{
				artwork.put(author, XMLHelper.newArtwork(element));
			}
		}
		
		return artwork;
	}
}
