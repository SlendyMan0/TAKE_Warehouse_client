package pl.project.warehouse.client.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import pl.project.warehouse.client.interfaces.ClientInterface;
import pl.project.warehouse.client.interfaces.OrderInterface;
import pl.project.warehouse.client.interfaces.ProductInterface;
import pl.project.warehouse.client.remotes.ClientRemote;
import pl.project.warehouse.client.remotes.OrderRemote;
import pl.project.warehouse.client.remotes.ProductRemote;
import pl.project.warehouse.client.entities.XClient;
import pl.project.warehouse.client.entities.XOrder;
import pl.project.warehouse.client.entities.XProduct;

public class ClientTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;

	ClientInterface intrface;
	List<XClient> data = new ArrayList<XClient>();
	
	public ClientTableModel() {
		intrface = new ClientRemote();
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
			case 0: return data.get(rowIndex).getIdc();
			case 1: return data.get(rowIndex).getName();
			case 2: return data.get(rowIndex).getSurname();
			case 3: return data.get(rowIndex).getCompanyName();
		}
		return null;
	}

	public String getColumnName(int columnIndex) {
		switch(columnIndex) {
			case 0: return "ID";
			case 1: return "Name";
			case 2: return "Surname";
			case 3: return "Company";
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
			XClient xclient = data.get(rowIndex);
			
			switch (columnIndex) {
				case 0: xclient.setIdc(Integer.parseInt((String)o));break;
				case 1: xclient.setName((String)o);break;
				case 2: xclient.setSurname((String)o);break;
				case 3: xclient.setCompanyName((String)o);break;
			}
			intrface.update(xclient);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Wrong data type inputed. Try again.","ERROR!",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void addRow() {
		XClient xclient = new XClient();
		intrface.create(xclient);
		refresh();
		fireTableDataChanged();
	}
	
	public void deleteRow(int rowIndex) {
		OrderInterface tempIntr = new OrderRemote();
		List<XOrder> tempData = new ArrayList<XOrder>();
		
		ProductInterface tempIntr2 = new ProductRemote();
		List<XProduct> tempData2 = new ArrayList<XProduct>();
		
		tempData = tempIntr.findByClient(data.get(rowIndex).getIdc());
		for (XOrder obj:tempData) {
			tempData2 = tempIntr2.findByOrder(obj.getIdo());
			for (XProduct obj2:tempData2) {
				tempIntr2.delete(obj2.getIdp());
			}	
			tempIntr.delete(obj.getIdo());
		}
			
		intrface.delete(data.get(rowIndex).getIdc());
		refresh();
		fireTableDataChanged();
	}
	
	public int getSelectedId(int index) {
		return data.get(index).getIdc();
	}
}
