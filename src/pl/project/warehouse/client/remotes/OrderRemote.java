package pl.project.warehouse.client.remotes;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXB;

import pl.project.warehouse.client.HttpHelper;
import pl.project.warehouse.client.containers.Orders;
import pl.project.warehouse.client.entities.XOrder;
import pl.project.warehouse.client.interfaces.OrderInterface;

public class OrderRemote implements OrderInterface{

	String url = "http://localhost:8080/warehouse/order";
	
	@Override
	public List<XOrder> get() {
		String response = HttpHelper.doGet(url+"/get");
		Orders list = JAXB.unmarshal(new StringReader(response), Orders.class);
		return list.getOrders();
	}
	
	@Override
	public void update(XOrder xorder) {
		StringWriter request = new StringWriter();
		JAXB.marshal(xorder, request);
		HttpHelper.doPost(url+"/update",request.toString(),"application/xml");
	}
	
	@Override
	public void create(XOrder xorder) {
		StringWriter request = new StringWriter();
		JAXB.marshal(xorder, request);
		HttpHelper.doPost(url+"/create",request.toString(),"application/xml");
	}
	
	@Override
	public void delete(int ido) {
		HttpHelper.doGet(url+"/delete/"+ido);
	}
	
	@Override
	public List<XOrder> findByClient(int idc) {
		String response = HttpHelper.doGet(url+"/find/client/"+idc);
		Orders list = JAXB.unmarshal(new StringReader(response), Orders.class);
		return list.getOrders();
	}
}