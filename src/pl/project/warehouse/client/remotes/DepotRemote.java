package pl.project.warehouse.client.remotes;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXB;

import pl.project.warehouse.client.HttpHelper;
import pl.project.warehouse.client.containers.Depots;
import pl.project.warehouse.client.entities.XDepot;
import pl.project.warehouse.client.interfaces.DepotInterface;

public class DepotRemote implements DepotInterface{

	String url = "http://localhost:8080/warehouse/depot";
	
	@Override
	public List<XDepot> get() {
		String response = HttpHelper.doGet(url+"/get");	
		Depots list = JAXB.unmarshal(new StringReader(response), Depots.class);
		return list.getDepots();
	}
	
	@Override
	public void update(XDepot xdepot) {
		StringWriter request = new StringWriter();
		JAXB.marshal(xdepot, request);
		HttpHelper.doPost(url+"/update",request.toString(),"application/xml");
	}
	
	@Override
	public void create(XDepot xdepot) {
		StringWriter request = new StringWriter();
		JAXB.marshal(xdepot, request);
		HttpHelper.doPost(url+"/create",request.toString(),"application/xml");
	}
	
	@Override
	public void delete(int idd) {
		HttpHelper.doGet(url+"/delete/"+idd);
	}
}