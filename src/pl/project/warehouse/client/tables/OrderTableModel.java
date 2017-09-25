package pl.project.warehouse.client.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import pl.project.warehouse.client.interfaces.OrderInterface;
import pl.project.warehouse.client.interfaces.ProductInterface;
import pl.project.warehouse.client.remotes.OrderRemote;
import pl.project.warehouse.client.remotes.ProductRemote;
import pl.project.warehouse.client.entities.XClient;
import pl.project.warehouse.client.entities.XOrder;
import pl.project.warehouse.client.entities.XProduct;

public class OrderTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;

	OrderInterface intrface;
	List<XOrder> data = new ArrayList<XOrder>();
	
	public OrderTableModel() {
		intrface = new OrderRemote();
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
			case 0: return data.get(rowIndex).getIdo();
			case 1: 
				if (data.get(rowIndex).getClient() == null) {
					return null;
				} 
				else {
					return data.get(rowIndex).getClient().getIdc();
				}
			case 2: return data.get(rowIndex).getTotalCost();
		}
		return null;
	}

	public String getColumnName(int columnIndex) {
		switch(columnIndex) {
			case 0: return "ID";
			case 1: return "Client ID";
			case 2: return "Total cost";
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
			XOrder xorder = data.get(rowIndex);
			
			switch (columnIndex) {
				case 0: xorder.setIdo(Integer.parseInt((String)o));break;
				case 1:
					XClient xclient = new XClient();
					xclient.setIdc(Integer.parseInt((String)o));
					xorder.setClient(xclient);
					break;
				case 2: xorder.setTotalCost(Integer.parseInt((String)o));break;
			}
			intrface.update(xorder);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Wrong data type inputed. Try again.","ERROR!",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void addRow() {
		XOrder xorder = new XOrder();
		intrface.create(xorder);
		refresh();
		fireTableDataChanged();
	}
	
	public void deleteRow(int rowIndex) {
		ProductInterface tempIntr = new ProductRemote();
		List<XProduct> tempData = new ArrayList<XProduct>();
		
		tempData = tempIntr.findByOrder(data.get(rowIndex).getIdo());
		for (XProduct obj:tempData) {
			tempIntr.delete(obj.getIdp());
		}
		
		intrface.delete(data.get(rowIndex).getIdo());
		refresh();
		fireTableDataChanged();
	}
	
	public void showByClient(int idc) {
		data = intrface.findByClient(idc);
		fireTableDataChanged();
	}
	
	public int getSelectedId(int index) {
		return data.get(index).getIdo();
	}
}
