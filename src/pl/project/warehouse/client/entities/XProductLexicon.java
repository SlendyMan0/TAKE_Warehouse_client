package pl.project.warehouse.client.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XProductLexicon implements Serializable{
	private static final long serialVersionUID = 1L;
	
	int idl;
	String name;
	String description;
	
	@XmlAttribute
	public int getIdl() {
		return idl;
	}
	public void setIdl(int idl) {
		this.idl = idl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
