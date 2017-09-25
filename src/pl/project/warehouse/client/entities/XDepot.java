package pl.project.warehouse.client.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XDepot implements Serializable{
	private static final long serialVersionUID = 1L;

	int idd;
	String adress;
	int spaceAvalible;
	int spaceTaken;
	
	@XmlAttribute
	public int getIdd() {
		return idd;
	}
	public void setIdd(int idd) {
		this.idd = idd;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public int getSpaceAvalible() {
		return spaceAvalible;
	}
	public void setSpaceAvalible(int spaceAvalible) {
		this.spaceAvalible = spaceAvalible;
	}
	public int getSpaceTaken() {
		return spaceTaken;
	}
	public void setSpaceTaken(int spaceTaken) {
		this.spaceTaken = spaceTaken;
	}
}
