import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;


public class ArtworkFilter implements NodeFilter {
	
	public short acceptNode(Node node)
	{
		if (node.getNodeType() == Node.ELEMENT_NODE)
		{
			Element element = (Element) node;
			
			if (element.getNodeName().equals("artwork"))
			{
				return NodeFilter.FILTER_ACCEPT;
			}
			
		}
		
		return NodeFilter.FILTER_SKIP;
	}

}
