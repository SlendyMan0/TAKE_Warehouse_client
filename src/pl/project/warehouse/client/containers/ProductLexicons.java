package pl.project.warehouse.client.containers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import pl.project.warehouse.client.entities.XProductLexicon;

@XmlRootElement
public class ProductLexicons {
	private List<XProductLexicon> lexicons = new ArrayList<XProductLexicon>();

	public ProductLexicons(List<XProductLexicon> lexicons) {
		super();
		this.lexicons = lexicons;
	}
	
	public ProductLexicons() {
		
	}
	
	public List<XProductLexicon> getProductLexicons() {
		return lexicons;
	}

	public void setProductLexicons(List<XProductLexicon> lexicons) {
		this.lexicons = lexicons;
	}
}
