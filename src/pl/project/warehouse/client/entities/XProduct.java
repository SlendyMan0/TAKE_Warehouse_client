package pl.project.warehouse.client.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XProduct implements Serializable{
	private static final long serialVersionUID = 1L;

	int idp;
	int amount;
	int cost;
	XProductLexicon type;
	XOrder xorder;
	XDepot xdepot;
	
	@XmlAttribute
	public int getIdp() {
		return idp;
	}
	public void setIdp(int idp) {
		this.idp = idp;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public XProductLexicon getType() {
		return type;
	}
	public void setType(XProductLexicon type) {
		this.type = type;
	}
	public XOrder getXorder() {
		return xorder;
	}
	public void setXorder(XOrder xorder) {
		this.xorder = xorder;
	}
	public XDepot getXdepot() {
		return xdepot;
	}
	public void setXdepot(XDepot xdepot) {
		this.xdepot = xdepot;
	}
}
