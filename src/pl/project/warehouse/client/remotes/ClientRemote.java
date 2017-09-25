package pl.project.warehouse.client.remotes;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXB;

import pl.project.warehouse.client.HttpHelper;
import pl.project.warehouse.client.containers.Clients;
import pl.project.warehouse.client.entities.XClient;
import pl.project.warehouse.client.interfaces.ClientInterface;

public class ClientRemote implements ClientInterface{

	String url = "http://localhost:8080/warehouse/client";
	
	@Override
	public List<XClient> get() {
		String response = HttpHelper.doGet(url+"/get");
		Clients list = JAXB.unmarshal(new StringReader(response), Clients.class);
		return list.getClients();
	}
	
	@Override
	public void update(XClient xclient) {
		StringWriter request = new StringWriter();
		JAXB.marshal(xclient, request);
		HttpHelper.doPost(url+"/update",request.toString(),"application/xml");
	}
	
	@Override
	public void create(XClient xclient) {
		StringWriter request = new StringWriter();
		JAXB.marshal(xclient, request);
		HttpHelper.doPost(url+"/create",request.toString(),"application/xml");
	}
	
	@Override
	public void delete(int idc) {
		HttpHelper.doGet(url+"/delete/"+idc);
	}
}
