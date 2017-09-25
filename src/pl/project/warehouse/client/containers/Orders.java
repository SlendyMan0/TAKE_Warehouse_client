package pl.project.warehouse.client.containers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import pl.project.warehouse.client.entities.XOrder;

@XmlRootElement
public class Orders {
	private List<XOrder> orders = new ArrayList<XOrder>();

	public Orders(List<XOrder> orders) {
		super();
		this.orders = orders;
	}
	
	public Orders() {
		
	}
	
	public List<XOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<XOrder> orders) {
		this.orders = orders;
	}
}
