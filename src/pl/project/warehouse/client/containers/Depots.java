package pl.project.warehouse.client.containers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import pl.project.warehouse.client.entities.XDepot;

@XmlRootElement
public class Depots {
	private List<XDepot> depots = new ArrayList<XDepot>();

	public Depots(List<XDepot> depots) {
		super();
		this.depots = depots;
	}
	
	public Depots() {
		
	}
	
	public List<XDepot> getDepots() {
		return depots;
	}

	public void setDepots(List<XDepot> depots) {
		this.depots = depots;
	}
}
