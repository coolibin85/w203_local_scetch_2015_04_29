package invoketionsTest;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class TestFrame extends JFrame {
	OpenProjectAction openPrjAct = null;
	
	public TestFrame() {
		initUI();
	}
	
	
	private void createMenuAndToolbar() {
		openPrjAct = new OpenProjectAction(this);
		
		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem eMenuItem = new JMenuItem(openPrjAct);
		eMenuItem.setMnemonic(KeyEvent.VK_O);
		eMenuItem.setToolTipText("Open RUS localization project");
		
		file.add(eMenuItem);
		menubar.add(file);
		setJMenuBar(menubar);
	}
	
	public void initUI() {
		createMenuAndToolbar();
		
		JPanel panel = new JPanel(new BorderLayout());
		
		JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);
		toolbar.setRollover(true);
		
		ImageIcon icon = new ImageIcon("open-file-icon.png");
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);

		JButton exitButton = new JButton(newIcon);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		toolbar.add(exitButton);
		
		ImageIcon icon1 = new ImageIcon("Save.png");
		Image img1 = icon1.getImage();
		Image newimg1 = img1.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon1 = new ImageIcon(newimg1);
		
		JButton saveButton = new JButton(newIcon1);
		
	        
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		toolbar.add(saveButton);
		
		panel.add(toolbar, BorderLayout.NORTH);
		add(panel);
		
		setTitle("EDT test");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JButton btnLoad = new JButton("Load file..");
/*
		List<String> entries = new ArrayList<String>();
		StringBuffer sb;

		for(int i = 0; i < 10 ; i++)
		{

			sb = new StringBuffer();
			sb.append(Integer.toString(i));
			sb.append("_");
			sb.append("lng0");
			sb.append('\u0000');
			sb.append("lng1");
			sb.append('\u0000');
			sb.append("lng2");
			sb.append('\u0000');
			sb.append("lng3");
			sb.append('\u0000');
			sb.append('\u0000');
			entries.add(sb.toString());
		}
		
		
		
		JTable table = new JTable(new TestTableModel(entries));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		table.setDefaultRenderer(String.class, new TestCell());
//		table.setDefaultEditor(String.class, new TestCell());
//		table.getColumnModel().getColumn(1).setCellRenderer(new TestCell());
		table.getColumnModel().getColumn(1).setCellEditor(new TestCell());
		
		table.setRowHeight(40);
		add(new JScrollPane(table));
		*/
	}
	
	public class FileOpenAction extends AbstractAction {
		FileOpenAction(String text) {
			super(text);
		}
		
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater( new Runnable() {
			@Override
			public void run() {
				TestFrame tf = new TestFrame();
				tf.setVisible(true);
			}
		});
	}

	
	public class TestTableModel extends AbstractTableModel {
		List<String> dataList;
		
		public TestTableModel(List<String> dataList) {
			this.dataList = dataList;
		}
		
		public Class getColumnClass(int columnIndex) { System.out.println("getColumnClass()"); return String.class; }
		public int getColumnCount() { System.out.println("getColumnCount()"); return 2; }
		public String getColumnName(int columnIndex) { System.out.println("getColumnName()"); return "Entry"; }
		public int getRowCount() { System.out.println("getRowCount()"); return (dataList == null) ? 0 : dataList.size(); }
		public Object getValueAt(int rowIndex, int columnIndex) {
			System.out.println("getValueAt(" + Integer.toString(rowIndex) + ")");
			return (dataList == null) ? null : dataList.get(rowIndex);
		}
		public boolean isCellEditable(int columnIndex, int rowIndex) {
			System.out.println("isCellEditable(" + Integer.toString(rowIndex) + ")");
			return true;
		}
	}
	
	public class TestCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
		JPanel 		panel;
		JButton 	btnAct;
		
		String 		entry;
		
		public TestCell() {
			btnAct = new JButton("detail..");
			btnAct.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, entry);
				}
			});
			 
			panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			panel.add(btnAct);
		}
		
		private void updateData(Object value, boolean isSelected, JTable table) {
		    this.entry = (String)value;
	
		    if (isSelected) {
				panel.setBackground(table.getSelectionBackground());
			} else {
				panel.setBackground(table.getBackground());
			}
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value,
							boolean isSelected, boolean hasFocus, int row, int column) {
			updateData(value, isSelected, table);
			System.out.println("RENDERER : getTableCellRendererComponent(" + entry + ")");
			return panel;
		}
		
		public Component getTableCellEditorComponent(JTable table, Object value,
				boolean isSelected, int row, int column) {
			updateData(value, true, table);
			System.out.println("EDITOR : getTableCellEditorComponent(" + entry + ")");
			return panel;
		}
			 
		public Object getCellEditorValue() {
			System.out.println("EDITOR : getCellEditorValue(" + entry + ")");
			return null;
		}
	}
}
