package pl.project.warehouse.client.remotes;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXB;

import pl.project.warehouse.client.HttpHelper;
import pl.project.warehouse.client.containers.ProductLexicons;
import pl.project.warehouse.client.entities.XProductLexicon;
import pl.project.warehouse.client.interfaces.ProductLexiconInterface;

public class ProductLexiconRemote implements ProductLexiconInterface{

	String url = "http://localhost:8080/warehouse/lexicon";
	
	@Override
	public List<XProductLexicon> get() {
		String response = HttpHelper.doGet(url+"/get");
		ProductLexicons list = JAXB.unmarshal(new StringReader(response), ProductLexicons.class);
		return list.getProductLexicons();
	}
	
	@Override
	public void update(XProductLexicon xlexicon) {
		StringWriter request = new StringWriter();
		JAXB.marshal(xlexicon, request);
		HttpHelper.doPost(url+"/update",request.toString(),"application/xml");
	}
	
	@Override
	public void create(XProductLexicon xlexicon) {
		StringWriter request = new StringWriter();
		JAXB.marshal(xlexicon, request);
		HttpHelper.doPost(url+"/create",request.toString(),"application/xml");
	}
	
	@Override
	public void delete(int idl) {
		HttpHelper.doGet(url+"/delete/"+idl);
	}
}