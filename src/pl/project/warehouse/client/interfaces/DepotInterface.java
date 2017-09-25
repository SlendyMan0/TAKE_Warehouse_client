package pl.project.warehouse.client.interfaces;

import java.util.List;

import pl.project.warehouse.client.entities.XDepot;

public interface DepotInterface {
	
	public abstract List<XDepot> get();
	
	public abstract void update(XDepot xdepot);
	
	public abstract void create(XDepot xdepot);
	
	public abstract void delete(int idd);

}