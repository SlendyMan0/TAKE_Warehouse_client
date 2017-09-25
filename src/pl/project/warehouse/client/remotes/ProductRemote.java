package pl.project.warehouse.client.remotes;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXB;

import pl.project.warehouse.client.HttpHelper;
import pl.project.warehouse.client.containers.Products;
import pl.project.warehouse.client.entities.XProduct;
import pl.project.warehouse.client.interfaces.ProductInterface;

public class ProductRemote implements ProductInterface{

	String url = "http://localhost:8080/warehouse/product";
	
	@Override
	public List<XProduct> get() {
		String response = HttpHelper.doGet(url+"/get");	
		Products list = JAXB.unmarshal(new StringReader(response), Products.class);
		return list.getProducts();
	}
	
	@Override
	public void update(XProduct xproduct) {
		StringWriter request = new StringWriter();
		JAXB.marshal(xproduct, request);
		HttpHelper.doPost(url+"/update",request.toString(),"application/xml");
	}
	
	@Override
	public void create(XProduct xproduct) {
		StringWriter request = new StringWriter();
		JAXB.marshal(xproduct, request);
		HttpHelper.doPost(url+"/create",request.toString(),"application/xml");
	}
	
	@Override
	public void delete(int idp) {
		HttpHelper.doGet(url+"/delete/"+idp);
	}
	
	@Override
	public List<XProduct> findByType(int idl) {
		String response = HttpHelper.doGet(url+"/find/lexicon/"+idl);	
		Products list = JAXB.unmarshal(new StringReader(response), Products.class);
		return list.getProducts();
	}
	
	@Override
	public List<XProduct> findByDepot(int idd) {
		String response = HttpHelper.doGet(url+"/find/depot/"+idd);	
		Products list = JAXB.unmarshal(new StringReader(response), Products.class);
		return list.getProducts();
	}
	
	@Override
	public List<XProduct> findByOrder(int ido) {
		String response = HttpHelper.doGet(url+"/find/order/"+ido);	
		Products list = JAXB.unmarshal(new StringReader(response), Products.class);
		return list.getProducts();
	}
}