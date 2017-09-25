package pl.project.warehouse.client.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	
	int ido;
	int totalCost;
	XClient client;
	
	@XmlAttribute
	public int getIdo() {
		return ido;
	}
	public void setIdo(int ido) {
		this.ido = ido;
	}
	public int getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	public XClient getClient() {
		return client;
	}
	public void setClient(XClient client) {
		this.client = client;
	}
}
