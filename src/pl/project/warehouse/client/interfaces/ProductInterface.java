package pl.project.warehouse.client.interfaces;

import java.util.List;

import pl.project.warehouse.client.entities.XProduct;

public interface ProductInterface {
	
	public abstract List<XProduct> get();
	
	public abstract void update(XProduct xproduct);
	
	public abstract void create(XProduct xproduct);
	
	public abstract void delete(int idp);
	
	public abstract List<XProduct> findByType(int idl);
	
	public abstract List<XProduct> findByDepot(int idd);
	
	public abstract List<XProduct> findByOrder(int ido);

}