package pl.project.warehouse.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import pl.project.warehouse.client.tables.*;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class WarehouseClient {

	private JFrame frame;
	private JTable tblClient;
	private JTable tblOrder;
	private JTable tblDepot;
	private JTable tblLexicon;
	private JTable tblProduct;
	ClientTableModel clientModel = new ClientTableModel();
	OrderTableModel orderModel = new OrderTableModel();
	ProductTableModel productModel = new ProductTableModel();
	DepotTableModel depotModel = new DepotTableModel();
	LexiconTableModel lexiconModel = new LexiconTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WarehouseClient window = new WarehouseClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WarehouseClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		JPanel pnlClient = new JPanel();
		tabbedPane.addTab("Client", null, pnlClient, null);
		pnlClient.setLayout(null);
		
		JScrollPane scrPaneClient = new JScrollPane();
		scrPaneClient.setBounds(0, 0, 479, 263);
		pnlClient.add(scrPaneClient);
		
		tblClient = new JTable();
		tblClient.setModel(clientModel);
		tblClient.getColumnModel().getColumn(0).setPreferredWidth(15);
		scrPaneClient.setViewportView(tblClient);
		
		JButton btnDeleteClient = new JButton("Delete");
		btnDeleteClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientModel.deleteRow(tblClient.getSelectedRow());
			}
		});
		btnDeleteClient.setBounds(380, 300, 89, 23);
		pnlClient.add(btnDeleteClient);
		
		JButton btnNewClient = new JButton("New");
		btnNewClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientModel.addRow();
			}
		});
		btnNewClient.setBounds(380, 274, 89, 23);
		pnlClient.add(btnNewClient);
		
		JButton btnRefreshClient = new JButton("Refresh");
		btnRefreshClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientModel.refresh();
				clientModel.fireTableDataChanged();
			}
		});
		btnRefreshClient.setBounds(281, 300, 89, 23);
		pnlClient.add(btnRefreshClient);
		
		JButton btnGoToOrders = new JButton("Go to Orders");
		btnGoToOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tblClient.getSelectedRow();
				
				if (index != -1) {
					tabbedPane.setSelectedIndex(1);
					orderModel.showByClient(clientModel.getSelectedId(index));
				}
			}
		});
		btnGoToOrders.setBounds(10, 300, 120, 23);
		pnlClient.add(btnGoToOrders);
		
		JPanel pnlOrder = new JPanel();
		tabbedPane.addTab("Order", null, pnlOrder, null);
		pnlOrder.setLayout(null);
		
		JScrollPane scrPaneOrder = new JScrollPane();
		scrPaneOrder.setBounds(0, 0, 479, 263);
		pnlOrder.add(scrPaneOrder);
		
		tblOrder = new JTable();
		tblOrder.setModel(orderModel);
		scrPaneOrder.setViewportView(tblOrder);
		
		JButton btnDeleteOrder = new JButton("Delete");
		btnDeleteOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderModel.deleteRow(tblOrder.getSelectedRow());
			}
		});
		btnDeleteOrder.setBounds(380, 300, 89, 23);
		pnlOrder.add(btnDeleteOrder);
		
		JButton btnNewOrder = new JButton("New");
		btnNewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderModel.addRow();
			}
		});
		btnNewOrder.setBounds(380, 274, 89, 23);
		pnlOrder.add(btnNewOrder);
		
		JButton btnRefreshOrder = new JButton("Refresh");
		btnRefreshOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderModel.refresh();
				orderModel.fireTableDataChanged();
			}
		});
		btnRefreshOrder.setBounds(281, 300, 89, 23);
		pnlOrder.add(btnRefreshOrder);
		
		JButton btnGoToProducts = new JButton("Go to Products");
		btnGoToProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tblOrder.getSelectedRow();
				
				if (index != -1) {
					tabbedPane.setSelectedIndex(2);
					productModel.showByOrder(orderModel.getSelectedId(index));
				}
			}
		});
		btnGoToProducts.setBounds(10, 300, 120, 23);
		pnlOrder.add(btnGoToProducts);
		
		JPanel pnlProduct = new JPanel();
		tabbedPane.addTab("Product", null, pnlProduct, null);
		pnlProduct.setLayout(null);
		
		JScrollPane scrPaneProduct = new JScrollPane();
		scrPaneProduct.setBounds(0, 0, 479, 263);
		pnlProduct.add(scrPaneProduct);
		
		tblProduct = new JTable();
		tblProduct.setModel(productModel);
		scrPaneProduct.setViewportView(tblProduct);
		
		JButton btnDeleteProduct = new JButton("Delete");
		btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productModel.deleteRow(tblProduct.getSelectedRow());
			}
		});
		btnDeleteProduct.setBounds(380, 300, 89, 23);
		pnlProduct.add(btnDeleteProduct);
		
		JButton btnNewProduct = new JButton("New");
		btnNewProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productModel.addRow();
			}
		});
		btnNewProduct.setBounds(380, 274, 89, 23);
		pnlProduct.add(btnNewProduct);
		
		JButton btnRefreshProduct = new JButton("Refresh");
		btnRefreshProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productModel.refresh();
				productModel.fireTableDataChanged();
			}
		});
		btnRefreshProduct.setBounds(281, 300, 89, 23);
		pnlProduct.add(btnRefreshProduct);
		
		JPanel pnlDepot = new JPanel();
		tabbedPane.addTab("Depot", null, pnlDepot, null);
		pnlDepot.setLayout(null);
		
		JScrollPane scrPaneDepot = new JScrollPane();
		scrPaneDepot.setBounds(0, 0, 479, 263);
		pnlDepot.add(scrPaneDepot);
		
		tblDepot = new JTable();
		tblDepot.setModel(depotModel);
		scrPaneDepot.setViewportView(tblDepot);
		
		JButton btnDeleteDepot = new JButton("Delete");
		btnDeleteDepot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depotModel.deleteRow(tblDepot.getSelectedRow());
			}
		});
		btnDeleteDepot.setBounds(380, 300, 89, 23);
		pnlDepot.add(btnDeleteDepot);
		
		JButton btnNewDepot = new JButton("New");
		btnNewDepot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depotModel.addRow();
			}
		});
		btnNewDepot.setBounds(380, 274, 89, 23);
		pnlDepot.add(btnNewDepot);
		
		JButton btnRefreshDepot = new JButton("Refresh");
		btnRefreshDepot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depotModel.refresh();
				depotModel.fireTableDataChanged();
			}
		});
		btnRefreshDepot.setBounds(281, 300, 89, 23);
		pnlDepot.add(btnRefreshDepot);
		
		JButton btnGoToProducts2 = new JButton("Go to Products");
		btnGoToProducts2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tblDepot.getSelectedRow();
				
				if (index != -1) {
					tabbedPane.setSelectedIndex(2);
					productModel.showByDepot(depotModel.getSelectedId(index));
				}
			}
		});
		btnGoToProducts2.setBounds(10, 300, 120, 23);
		pnlDepot.add(btnGoToProducts2);
		
		JPanel pnlLexicon = new JPanel();
		tabbedPane.addTab("Lexicon", null, pnlLexicon, null);
		pnlLexicon.setLayout(null);
		
		JScrollPane scrPaneLexicon = new JScrollPane();
		scrPaneLexicon.setBounds(0, 0, 479, 263);
		pnlLexicon.add(scrPaneLexicon);
		
		tblLexicon = new JTable();
		tblLexicon.setModel(lexiconModel);
		scrPaneLexicon.setViewportView(tblLexicon);
		
		JButton btnDeleteLexicon = new JButton("Delete");
		btnDeleteLexicon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lexiconModel.deleteRow(tblLexicon.getSelectedRow());
			}
		});
		btnDeleteLexicon.setBounds(380, 300, 89, 23);
		pnlLexicon.add(btnDeleteLexicon);
		
		JButton btnNewLexicon = new JButton("New");
		btnNewLexicon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lexiconModel.addRow();
			}
		});
		btnNewLexicon.setBounds(380, 274, 89, 23);
		pnlLexicon.add(btnNewLexicon);
		
		JButton btnRefreshLexicon = new JButton("Refresh");
		btnRefreshLexicon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lexiconModel.refresh();
				lexiconModel.fireTableDataChanged();
			}
		});
		btnRefreshLexicon.setBounds(281, 300, 89, 23);
		pnlLexicon.add(btnRefreshLexicon);
		
		JButton btnGoToProducts3 = new JButton("Go to Products");
		btnGoToProducts3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tblLexicon.getSelectedRow();
				
				if (index != -1) {
					tabbedPane.setSelectedIndex(2);
					productModel.showByType(lexiconModel.getSelectedId(index));
				}
			}
		});
		btnGoToProducts3.setBounds(10, 300, 120, 23);
		pnlLexicon.add(btnGoToProducts3);
	}
}
