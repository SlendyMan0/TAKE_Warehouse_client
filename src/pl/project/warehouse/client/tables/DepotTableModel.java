package pl.project.warehouse.client.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import pl.project.warehouse.client.interfaces.DepotInterface;
import pl.project.warehouse.client.interfaces.ProductInterface;
import pl.project.warehouse.client.remotes.DepotRemote;
import pl.project.warehouse.client.remotes.ProductRemote;
import pl.project.warehouse.client.entities.XDepot;
import pl.project.warehouse.client.entities.XProduct;

public class DepotTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;

	DepotInterface intrface;
	List<XDepot> data = new ArrayList<XDepot>();
	
	public DepotTableModel() {
		intrface = new DepotRemote();
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
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0: return data.get(rowIndex).getIdd();
		case 1: return data.get(rowIndex).getAdress();
		case 2: return data.get(rowIndex).getSpaceTaken();
		case 3: return data.get(rowIndex).getSpaceAvalible();
		}
		return null;
	}

	public String getColumnName(int columnIndex) {
		switch(columnIndex) {
		case 0: return "ID";
		case 1: return "Adress";
		case 2: return "Space taken";
		case 3: return "Space avalible";
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
			XDepot xdepot = data.get(rowIndex);
			
			switch (columnIndex) {
				case 0: xdepot.setIdd(Integer.parseInt((String)o));break;
				case 1: xdepot.setAdress((String)o);break;
				case 2: xdepot.setSpaceTaken(Integer.parseInt((String)o));break;
				case 3: xdepot.setSpaceAvalible(Integer.parseInt((String)o));break;
			}
			intrface.update(xdepot);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Wrong data type inputed. Try again.","ERROR!",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void addRow() {
		XDepot xdepot = new XDepot();
		intrface.create(xdepot);
		refresh();
		fireTableDataChanged();
	}
	
	public void deleteRow(int rowIndex) {
		ProductInterface tempIntr = new ProductRemote();
		List<XProduct> tempData = new ArrayList<XProduct>();
		
		tempData = tempIntr.findByDepot(data.get(rowIndex).getIdd());
		for (XProduct obj:tempData) {
			tempIntr.delete(obj.getIdp());
		}
		
		
		intrface.delete(data.get(rowIndex).getIdd());
		refresh();
		fireTableDataChanged();
	}
	
	public int getSelectedId(int index) {
		return data.get(index).getIdd();
	}
}
