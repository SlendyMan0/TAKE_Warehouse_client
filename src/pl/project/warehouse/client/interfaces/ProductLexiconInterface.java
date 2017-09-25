package pl.project.warehouse.client.interfaces;

import java.util.List;

import pl.project.warehouse.client.entities.XProductLexicon;

public interface ProductLexiconInterface {

	public abstract List<XProductLexicon> get();
	
	public abstract void update(XProductLexicon xlexicon);
	
	public abstract void create(XProductLexicon xlexicon);
	
	public abstract void delete(int idl);
	
}