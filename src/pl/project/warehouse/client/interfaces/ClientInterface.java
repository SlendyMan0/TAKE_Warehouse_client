package pl.project.warehouse.client.interfaces;

import java.util.List;

import pl.project.warehouse.client.entities.XClient;

public interface ClientInterface {
	
	public abstract List<XClient> get();
	
	public abstract void update(XClient xclient);
	
	public abstract void create(XClient xclient);
	
	public abstract void delete(int idc);

}
