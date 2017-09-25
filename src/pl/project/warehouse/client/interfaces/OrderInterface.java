package pl.project.warehouse.client.interfaces;

import java.util.List;

import pl.project.warehouse.client.entities.XOrder;

public interface OrderInterface {

	public abstract List<XOrder> get();
	
	public abstract void update(XOrder xorder);
	
	public abstract void create(XOrder xorder);
	
	public abstract void delete(int ido);
	
	public abstract List<XOrder> findByClient(int idc);
	
}