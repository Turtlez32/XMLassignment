import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;


public class AuthorFilter implements NodeFilter
{
		@Override
		public short acceptNode(Node node)
		{
				if ( node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element element = (Element) node;
					
					if (element.getNodeName().equals("author"))
					{
						return NodeFilter.FILTER_ACCEPT;
					}
				}
				
				return NodeFilter.FILTER_SKIP;
		}
}