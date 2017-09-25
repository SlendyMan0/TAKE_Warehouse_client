package pl.project.warehouse.client.containers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import pl.project.warehouse.client.entities.XProduct;

@XmlRootElement
public class Products {
	private List<XProduct> products = new ArrayList<XProduct>();

	public Products(List<XProduct> products) {
		super();
		this.products = products;
	}
	
	public Products() {
		
	}
	
	public List<XProduct> getProducts() {
		return products;
	}

	public void setProducts(List<XProduct> products) {
		this.products = products;
	}
}