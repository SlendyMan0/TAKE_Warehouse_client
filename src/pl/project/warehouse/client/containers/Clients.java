package pl.project.warehouse.client.containers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import pl.project.warehouse.client.entities.XClient;

@XmlRootElement
public class Clients {
	private List<XClient> clients = new ArrayList<XClient>();

	public Clients(List<XClient> clients) {
		super();
		this.clients = clients;
	}
	
	public Clients() {
		
	}
	
	public List<XClient> getClients() {
		return clients;
	}

	public void setClients(List<XClient> clients) {
		this.clients = clients;
	}
}
