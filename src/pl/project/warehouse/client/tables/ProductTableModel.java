package pl.project.warehouse.client.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import pl.project.warehouse.client.interfaces.ProductInterface;
import pl.project.warehouse.client.remotes.ProductRemote;
import pl.project.warehouse.client.entities.XDepot;
import pl.project.warehouse.client.entities.XOrder;
import pl.project.warehouse.client.entities.XProduct;
import pl.project.warehouse.client.entities.XProductLexicon;

public class ProductTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;

	ProductInterface intrface;
	List<XProduct> data = new ArrayList<XProduct>();
	
	public ProductTableModel() {
		intrface = new ProductRemote();
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
		return 6;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0: return data.get(rowIndex).getIdp();
		case 1: 
			if (data.get(rowIndex).getType() == null) {
				return null;
			} 
			else {
				return data.get(rowIndex).getType().getIdl();
			}
		case 2: 
			if (data.get(rowIndex).getXorder() == null) {
				return null;
			} 
			else {
				return data.get(rowIndex).getXorder().getIdo();
			}
		case 3: 
			if (data.get(rowIndex).getXdepot() == null) {
				return null;
			} 
			else {
				return data.get(rowIndex).getXdepot().getIdd();
			}
		case 4: return data.get(rowIndex).getAmount();
		case 5: return data.get(rowIndex).getCost();
		}
		return null;
	}

	public String getColumnName(int columnIndex) {
		switch(columnIndex) {
		case 0: return "ID";
		case 1: return "Type ID";
		case 2: return "Order ID";
		case 3: return "Depot ID";
		case 5: return "Amount";
		case 6: return "Cost";
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
			XProduct xproduct = data.get(rowIndex);
			
			switch (columnIndex) {
				case 0: xproduct.setIdp(Integer.parseInt((String)o));break;
				case 1: 
					XProductLexicon xlexicon = new XProductLexicon();
					xlexicon.setIdl(Integer.parseInt((String)o));
					xproduct.setType(xlexicon);
					break;
				case 2: 
					XOrder xorder = new XOrder();
					xorder.setIdo(Integer.parseInt((String)o));
					xproduct.setXorder(xorder);
					break;
				case 3: 
					XDepot xdepot = new XDepot();
					xdepot.setIdd(Integer.parseInt((String)o));
					xproduct.setXdepot(xdepot);
					break;
				case 4: xproduct.setAmount(Integer.parseInt((String)o));break;
				case 5: xproduct.setCost(Integer.parseInt((String)o));break;
			}
			intrface.update(xproduct);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Wrong data type inputed. Try again.","ERROR!",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void addRow() {
		XProduct xproduct = new XProduct();
		intrface.create(xproduct);
		refresh();
		fireTableDataChanged();
	}
	
	public void deleteRow(int rowIndex) {
		intrface.delete(data.get(rowIndex).getIdp());
		refresh();
		fireTableDataChanged();
	}
	
	public void showByOrder(int ido) {
		data = intrface.findByOrder(ido);
		fireTableDataChanged();
	}
	
	public void showByDepot(int idd) {
		data = intrface.findByDepot(idd);
		fireTableDataChanged();
	}
	
	public void showByType(int idl) {
		data = intrface.findByType(idl);
		fireTableDataChanged();
	}
}
