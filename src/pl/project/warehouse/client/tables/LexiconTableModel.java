package pl.project.warehouse.client.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import pl.project.warehouse.client.interfaces.ProductInterface;
import pl.project.warehouse.client.interfaces.ProductLexiconInterface;
import pl.project.warehouse.client.remotes.ProductLexiconRemote;
import pl.project.warehouse.client.remotes.ProductRemote;
import pl.project.warehouse.client.entities.XProduct;
import pl.project.warehouse.client.entities.XProductLexicon;

public class LexiconTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;

	ProductLexiconInterface intrface;
	List<XProductLexicon> data = new ArrayList<XProductLexicon>();
	
	public LexiconTableModel() {
		intrface = new ProductLexiconRemote();
		refresh();
	}
	
	public void refresh() {
		data = intrface.get();
	}
	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {		
		switch(columnIndex) {
			case 0: return data.get(rowIndex).getIdl();
			case 1: return data.get(rowIndex).getName();
			case 2: return data.get(rowIndex).getDescription();
		}
		return null;
	}

	public String getColumnName(int columnIndex) {
		switch(columnIndex) {
			case 0: return "ID";
			case 1: return "Name";
			case 2: return "Description";
		}
		return "";
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	
	@Override
	public void setValueAt(Object o,int rowIndex, int columnIndex) {
		try {
			XProductLexicon xlexicon = data.get(rowIndex);
			
			switch (columnIndex) {
				case 0: xlexicon.setIdl(Integer.parseInt((String)o));break;
				case 1: xlexicon.setName((String)o);break;
				case 2: xlexicon.setDescription((String)o);break;
			}
			intrface.update(xlexicon);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Wrong data type inputed. Try again.","ERROR!",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void addRow() {
		XProductLexicon xlexicon = new XProductLexicon();
		intrface.create(xlexicon);
		refresh();
		fireTableDataChanged();
	}
	
	public void deleteRow(int rowIndex) {
		ProductInterface tempIntr = new ProductRemote();
		List<XProduct> tempData = new ArrayList<XProduct>();
		
		tempData = tempIntr.findByType(data.get(rowIndex).getIdl());
		for (XProduct obj:tempData) {
			tempIntr.delete(obj.getIdp());
		}
		
		intrface.delete(data.get(rowIndex).getIdl());
		refresh();
		fireTableDataChanged();
	}
	
	public int getSelectedId(int index) {
		return data.get(index).getIdl();
	}
}
