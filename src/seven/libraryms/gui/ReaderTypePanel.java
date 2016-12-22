package seven.libraryms.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import seven.libraryms.bll.ReaderTypeAdmin;
import seven.libraryms.dal.ReaderDAL;
import seven.libraryms.dal.ReaderTypeDAL;
import seven.libraryms.gui.commons.CustomizedTableModel;
import seven.libraryms.model.Reader;
import seven.libraryms.model.ReaderType;

/**
 * 读者类型面板
 * @author Seven
 * @Data 216-12-21
 * @version 2.0
 */
public class ReaderTypePanel extends JPanel {
	// 默认构造器
	private JPanel updatePanel;
	private JTextField tfRdType;
	private JTextField tfRdTypeName;
	private JTextField tfCanContinueTimes;
	private JTextField tfCanLendQty;
	private JTextField tfPunishRate;
	private JTextField tfCanLendDay;
	private JTextField tfDateValid;
	private JScrollPane searchResultPanel;
	private JTable searchResultTable;
	private JPanel functionCtlPanel;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	
	private ReaderTypeAdmin readerTypeBll = new ReaderTypeAdmin();

	/**
	 * 根据选中项,设置输入框的信息
	 * @param readerType 读者类型
	 */
	private void setReaderTypeToText(ReaderType readerType) {
		tfRdType.setText(String.valueOf(readerType.getRdType()));
		tfRdTypeName.setText(readerType.getRdTypeName());
		tfCanLendQty.setText(String.valueOf(readerType.getCanLendQty()));
		tfCanLendDay.setText(String.valueOf(readerType.getCanLendDay()));
		tfCanContinueTimes.setText(String.valueOf(readerType.getCanContinueTimes()));
		tfPunishRate.setText(String.valueOf(readerType.getPunishRate()));
		tfDateValid.setText(String.valueOf(readerType.getDateValid()));
	}

	/**根据输入框中的信息返回一个新建的读者类型对象
	 * @return 读者类型对象
	 */
	private ReaderType getReaderTypeFromText() {
		ReaderType readerType = new ReaderType(Integer.valueOf(tfRdType.getText()));
		readerType.setRdTypeName(tfRdTypeName.getText().trim());
		readerType.setCanLendQty(Integer.valueOf(tfCanLendQty.getText().trim()));
		readerType.setCanLendDay(Integer.valueOf(tfCanLendDay.getText().trim()));
		readerType.setCanContinueTimes(Integer.valueOf(tfCanContinueTimes.getText().trim()));
		readerType.setPunishRate(Float.valueOf(tfPunishRate.getText().trim()));
		readerType.setDateValid(Integer.valueOf(tfDateValid.getText().trim()));
		return readerType;
	}
	/**
	 * 更新表格JTable
	 */
	private void updateResultTable() {
		// TODO Auto-generated method stub
		CustomizedTableModel<ReaderType> tableModel = (CustomizedTableModel<ReaderType>) searchResultTable.getModel();
		//取得所有的读者类型对象
		ReaderType[] hits = readerTypeBll.retrieveReaderTypes();
		//加入MODEL
		tableModel.setRecords(hits);
		// 更新表格
		tableModel.fireTableDataChanged();
	}

	/**
	 * 默认构造器
	 */
	public ReaderTypePanel() {
		setSize(new Dimension(975, 535));
		setLayout(null);

		updatePanel = new JPanel();
		updatePanel.setBounds(0, 348, 975, 113);
		updatePanel.setLayout(null);

		JLabel label = new JLabel("\u8BFB\u8005\u7C7B\u522B\uFF1A");
		label.setBounds(37, 30, 78, 15);
		updatePanel.add(label);

		JLabel label_1 = new JLabel("\u53EF\u7EED\u501F\u6B21\u6570\uFF1A");
		label_1.setBounds(97, 82, 78, 15);
		updatePanel.add(label_1);

		JLabel label_2 = new JLabel("\u53EF\u501F\u4E66\u6570\u91CF\uFF1A");
		label_2.setBounds(534, 30, 78, 15);
		updatePanel.add(label_2);

		JLabel label_3 = new JLabel("\u7F5A\u6B3E\u7387\uFF1A");
		label_3.setBounds(411, 85, 78, 15);
		updatePanel.add(label_3);

		JLabel label_4 = new JLabel("\u53EF\u501F\u4E66\u5929\u6570\uFF1A");
		label_4.setBounds(771, 33, 78, 15);
		updatePanel.add(label_4);

		JLabel label_5 = new JLabel("\u8BC1\u4EF6\u6709\u6548\u671F\uFF1A");
		label_5.setBounds(728, 82, 78, 15);
		updatePanel.add(label_5);

		tfRdType = new JTextField();
		tfRdType.setBounds(118, 27, 86, 21);
		updatePanel.add(tfRdType);
		tfRdType.setColumns(10);

		tfCanContinueTimes = new JTextField();
		tfCanContinueTimes.setColumns(10);
		tfCanContinueTimes.setBounds(178, 79, 86, 21);
		updatePanel.add(tfCanContinueTimes);

		tfCanLendQty = new JTextField();
		tfCanLendQty.setColumns(10);
		tfCanLendQty.setBounds(608, 27, 86, 21);
		updatePanel.add(tfCanLendQty);

		tfPunishRate = new JTextField();
		tfPunishRate.setColumns(10);
		tfPunishRate.setBounds(478, 82, 86, 21);
		updatePanel.add(tfPunishRate);

		tfCanLendDay = new JTextField();
		tfCanLendDay.setColumns(10);
		tfCanLendDay.setBounds(843, 30, 86, 21);
		updatePanel.add(tfCanLendDay);

		tfDateValid = new JTextField();
		tfDateValid.setColumns(10);
		tfDateValid.setBounds(800, 79, 86, 21);
		updatePanel.add(tfDateValid);
		add(updatePanel);

		tfRdTypeName = new JTextField();
		tfRdTypeName.setColumns(10);
		tfRdTypeName.setBounds(365, 27, 86, 21);
		updatePanel.add(tfRdTypeName);

		JLabel label_6 = new JLabel("\u8BFB\u8005\u7C7B\u578B\u540D\u79F0\uFF1A");
		label_6.setBounds(275, 30, 107, 15);
		updatePanel.add(label_6);

		functionCtlPanel = new JPanel();
		functionCtlPanel.setBounds(0, 466, 975, 59);
		functionCtlPanel.setLayout(null);

		btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReaderType readerType =getReaderTypeFromText();
				ReaderTypeDAL dal = new ReaderTypeDAL();
				try {
					int result = dal.add(readerType);
					if (result < 0) {
						JOptionPane.showMessageDialog(null, "添加失败! 请重新输入 !");
					} else {
						JOptionPane.showMessageDialog(null, "添加成功!");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//更新JTable
				updateResultTable();

			}
		});
		btnAdd.setBounds(222, 10, 71, 23);
		functionCtlPanel.add(btnAdd);

		btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = searchResultTable.getRowCount();
				if (selectedRow >= 0) {
					ReaderType readerType = getReaderTypeFromText();
					readerTypeBll.updateReaderType(readerType);
					JOptionPane.showMessageDialog(null, "更改成功!");
					updateResultTable();
				}else {
					JOptionPane.showMessageDialog(null, "更改失败!");
				}
			}
		});
		btnUpdate.setBounds(429, 10, 71, 23);
		functionCtlPanel.add(btnUpdate);

		btnDelete = new JButton("\u5220\u9664");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = searchResultTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "请先选中一条记录!");
					return;
				} else {
					ReaderType concle = ((CustomizedTableModel<ReaderType>) searchResultTable.getModel())
							.getObjectAt(selectedRow);
					ReaderTypeDAL dal = new ReaderTypeDAL();
					try {
						dal.delete(concle);
						updateResultTable();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "删除成功!");
				}
			}
		});
		btnDelete.setBounds(630, 10, 71, 23);
		functionCtlPanel.add(btnDelete);
		add(functionCtlPanel);

		CustomizedTableModel<ReaderType> tableModel = new CustomizedTableModel<ReaderType>(
				readerTypeBll.getDisplayColumnNames(), readerTypeBll.getMethodNames());

		searchResultPanel = new JScrollPane((Component) null);
		searchResultPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"\u67E5\u8BE2\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		searchResultPanel.setBounds(10, 13, 929, 325);
		searchResultTable = new JTable(tableModel);
		searchResultTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = searchResultTable.getSelectedRow();
				if (selectedRow >= 0) {
					setReaderTypeToText(((CustomizedTableModel<ReaderType>) searchResultTable.getModel()).getObjectAt(selectedRow));
				}
			}
		});
		searchResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		searchResultPanel.setViewportView(searchResultTable);
		add(searchResultPanel);
		
		updateResultTable();
	}
	
	public static void main(String[] args) throws SQLException {
		JFrame jf = new JFrame("Test");
		jf.setSize(1000, 550);
		ReaderTypePanel rp = new ReaderTypePanel();
		jf.getContentPane().add(rp);
		jf.setVisible(true);
	}
}
