import java.io.IOException;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLDom {
	
	public static Document artworkXML;
	
	public static void main(String[] arguments)
	{
		try { 
			//xml = XML.XMLRead("src/output.xml");
		
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	         
	        // Creates a new document for output.
	        Document document = builder.newDocument();

	        // Creates a parsed document for input.
	        Document parsed = XML.openAuthorsXML();
	        //Document parsed = builder.parse("src/output.xml");
	        
	        artworkXML = XML.openArtworksDocument();
	         
			Map<String, Author> authors = XMLMapCreation.getAuthorsFromDocument(parsed);
			
			Map<String, Artwork> artworks = XMLMapCreation.getArtworkFromDocument(artworkXML);
			
			Element body = setupOutputHTML(document);
		
			insertAuthorTable(document, body, authors, artworks);
		
			writeFile(document);
		}
		catch (Exception exception)
		{
			System.out.println("Error: " + exception.getMessage());
			exception.printStackTrace();
		}
	}
		
	public static Element getAuthorColumnHeadingsHTML(Document document)
	{
		Element tr = document.createElement("tr");
		
		Element th = document.createElement("th");		
		
		th.setTextContent("Name");
		tr.appendChild(th);
		
		th = document.createElement("th");
		th.setTextContent("Nationality");
		tr.appendChild(th);
		
		th = document.createElement("th");
		th.setTextContent("Born");
		tr.appendChild(th);
		
		th = document.createElement("th");
		th.setTextContent("Died");
		tr.appendChild(th);
		
		th = document.createElement("th");
		th.setTextContent("Artworks Form");
		tr.appendChild(th);
		
		return tr;
	}
	
	public static Element setupOutputHTML(Document document)
	{
		Element head = document.createElement("head");
		
		Element title = document.createElement("title");
		title.setTextContent("Artworks by Author");
		
		Element style = document.createElement("style");
		style.setAttribute("type", "text/css");
		style.setTextContent("\n" +
										"\n 		body" +
										"\n 		{" +
										"\n 			font-family: Verdana,Sans-Serif;" +
										"\n 		}" +
										"\n " +
										"\n 		th, td"+
										"\n 		{" +
										"\n 			font-weight: normal;" +
										"\n 			padding: .5em;" +
										"\n 		}" +
										"\n" +
										"\n         th[colspan]\n" +
										"\n         {" +
										"\n            font-weight: bold;" +
										"\n            background-color: #99CC66;" +
										"\n            text-align: left;" +
										"\n         }" +
										"\n" +
										"\n         td[colspan=\"3\"]\n" +
										"\n         {" +
										"\n            text-align: right;" +
										"\n            font-weight: bold;" +
										"\n         }" +
										"\n" +
										"\n         .right" +
										"\n         {" +
										"\n            text-align: right;" +
										"\n         }" +
										"\n\n");
										
		head.appendChild(title);
		head.appendChild(style);
		
		Element body = document.createElement("body");
		
		Element html = document.createElement("html");
		
		html.appendChild(head);
		html.appendChild(body);
		
		document.appendChild(html);
		
		return body;
	}
	
	public static void insertAuthorTable(Document document, 
			                             Element body, 
			                             Map<String, Author> authors,
			                             Map<String, Artwork> artworks)
	{
		Element h1 = document.createElement("h1");
		h1.setTextContent("Artworks by Author");
		
		Element table = document.createElement("table");
		table.setAttribute("border", "1");
		body.appendChild(h1);
		body.appendChild(table);
		
		Element tbody = (Element) table.appendChild(document.createElement("tbody"));
		
		Element authorHeading = getAuthorColumnHeadingsHTML(document);
		
		tbody.appendChild(authorHeading.cloneNode(true));
		
		//Element authorDetails = getAuthorDetails(document);
		
		for (Author author : authors.values())
		{
			Element td = document.createElement("td");
			
			Element tr = document.createElement("tr");
			
			td.setTextContent(author.getAuthorName());
			tr.appendChild(td);
			
			td = document.createElement("td");
			td.setTextContent(author.getNationality());
			tr.appendChild(td);
			
			td = document.createElement("td");
			td.setTextContent(author.getBorn());
			tr.appendChild(td);
			
			td = document.createElement("td");
			td.setTextContent(author.getDied());
			tr.appendChild(td);
			
			for (Artwork artwork : artworks.values())
			{
				String authorName = author.getAuthorName();
				String artworkAuthor = artwork.getArtAuthor();
				
				if (authorName.equals(artworkAuthor.toString()))
				{
					td = document.createElement("td");
					td.setTextContent(artwork.getArtTechnique());
					tr.appendChild(td);
					
					break;
				}
			}
			
			tbody.appendChild(tr.cloneNode(true));
		}
	}

	public static void writeFile(Document document) throws IOException, TransformerException
	{
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		
		transformer.setOutputProperty(OutputKeys.METHOD, "html");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "-//W3C//DTD HTML 4.01//EN");
	    transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://www.w3.org/TR/html4/strict.dtd");
	      
	    transformer.transform(new DOMSource(document), new StreamResult("src/output.html"));
	}
}