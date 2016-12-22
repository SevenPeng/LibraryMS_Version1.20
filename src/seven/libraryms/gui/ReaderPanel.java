package seven.libraryms.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import seven.libraryms.bll.ReaderAdmin;
import seven.libraryms.bll.ReaderTypeAdmin;
import seven.libraryms.dal.ReaderDAL;
import seven.libraryms.gui.commons.CustomizedTableModel;
import seven.libraryms.gui.commons.ImageFilter;
import seven.libraryms.model.Reader;

/**
 * 读者面板所有操作
 * @author Seven
 * @version 2.0
 *  @Data 2016-12-21
 *  @ReamainingFunction Excel输出表格
 *  @ReamainingProblem	无法实时更新ComboBox;
 */
public class ReaderPanel extends JPanel {

	// 默认构造器类组件
	private JTextField tfUserName;
	private JTextField tfReaderID;
	private JTextField tfReaderName;
	private JPasswordField passwordField;
	private JTextField tfNumBorrowed;
	private JTextField tfStatus;
	private JTextField tfReaderRole;
	private JTextField tfReaderPhone;
	private JTextField tfEmail;
	private JTextField tfDate;
	private JTable searchResultTable;
	private JPanel searchPanel;
	private JButton btnToExcel;
	private JButton btnQuery;
	private JComboBox rdTypeComboBox;
	private JComboBox deptTypeComboBox;
	private JPanel readerInfoPanel;
	private JLabel lblPhoto;
	private JButton btnLoadPictureFile;
	private JPanel functionCtlPanel;
	private JButton btnNewReader;
	private JButton btnUpdateReader;
	private JButton btnCancelReader;
	private JPanel editCtlPanel;
	private JButton btnAddReader;
	private JButton btnSubmitUpdate;
	private JButton btnCancelEdit;
	private JScrollPane searchResultPanel;
	private JComboBox cbGender;
	private JComboBox cbReaderType;
	private JComboBox cbDeptType;

	/** 枚举类型OpStatus，表示3种窗口操作状态 OpStatus ops 用于记录当前操作状态 */
	private OpStatus ops;
	private enum OpStatus {inSelect, inNew, inChange}

	private ReaderAdmin readerBll = new ReaderAdmin();
	private ReaderTypeAdmin readerTypeBll = new ReaderTypeAdmin();
	private CustomizedTableModel<Reader> tableModel;

	/**
	 * use for decide ReaderPanel'Panel.
	 * @param panel	面板
	 * @param status 状态
	 */
	private void setComponentStatusInPanel(JPanel panel, boolean status) {
		for (Component comp : panel.getComponents()) {
			comp.setEnabled(status);
		}
	}

	/**
	 * 根据读者的权限,选择面板显示
	 * @param opst
	 */
	private void setStatus(OpStatus opst) {
		ops = opst;
		switch (ops) {
		case inSelect:
			searchPanel.setEnabled(true);
			searchResultPanel.setEnabled(true);
			functionCtlPanel.setEnabled(true);
			// 更改Panel中组件的状态
			setComponentStatusInPanel(functionCtlPanel, true);
			readerInfoPanel.setEnabled(false);
			readerInfoPanel.setVisible(false);
			editCtlPanel.setEnabled(false);
			editCtlPanel.setVisible(false);
			setComponentStatusInPanel(editCtlPanel, false);
			break;
		case inNew:
			searchPanel.setEnabled(false);
			searchResultPanel.setEnabled(false);
			functionCtlPanel.setEnabled(false);
			setComponentStatusInPanel(functionCtlPanel, false);
			readerInfoPanel.setEnabled(true);
			readerInfoPanel.setVisible(true);
			editCtlPanel.setEnabled(true);
			editCtlPanel.setVisible(true);
			setComponentStatusInPanel(editCtlPanel, true);
			btnSubmitUpdate.setEnabled(false);
			tfReaderID.setEditable(true);
			break;
		case inChange:
			searchPanel.setEnabled(false);
			searchResultPanel.setEnabled(false);
			functionCtlPanel.setEnabled(false);
			setComponentStatusInPanel(functionCtlPanel, false);
			readerInfoPanel.setEnabled(true);
			readerInfoPanel.setVisible(true);
			editCtlPanel.setEnabled(true);
			editCtlPanel.setVisible(true);
			setComponentStatusInPanel(editCtlPanel, true);
			btnAddReader.setEnabled(false);
			break;
		}
	}

	/**
	 * 初始化所有输入框和选择框
	 */
	private void initAllPanel() {
		tfReaderID.setEditable(true);
		tfReaderID.setText("");
		tfReaderName.setText("");
		passwordField.setText("");
		tfNumBorrowed.setText("");
		tfStatus.setText("");
		tfReaderRole.setText("");
		tfReaderPhone.setText("");
		tfEmail.setText("");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateNow = format.format(cal.getTime());
		tfDate.setText(dateNow);
		cbGender.setSelectedIndex(0);
		cbReaderType.setSelectedIndex(0);
		lblPhoto.setIcon(null);
	}


	/**
	 *根据当前选择框中的数据更新JTable 
	 */
	private void updateResultTable() {
		String rdType = (String) rdTypeComboBox.getSelectedItem();
		String deptType = (String) deptTypeComboBox.getSelectedItem();
		String userName = tfUserName.getText().trim();
		//根据当前选择框中的选项返回对应的读者
		Reader[] readers = readerBll.retrieveReadersString(rdType, deptType, userName);
		tableModel = (CustomizedTableModel<Reader>) searchResultTable.getModel();
		if (readers == null) {
			JOptionPane.showMessageDialog(null, "没有找到符合要求的记录");
			tableModel.setRecords(null);
			tableModel.fireTableDataChanged();
			return;
		}
		//先表格加入相应的读者
		tableModel.setRecords(readers);
		// 更新表格
		tableModel.fireTableDataChanged();
	}

	/**
	 * 提供了根据读者参数在readerInfoPanel的输入框中填写相应信息的功能,用于更改读者信息.
	 * @param reader 读者类型
	 */
	private void setReaderToText(Reader reader) {
		tfReaderID.setText(String.valueOf(reader.getRdID()));
		tfReaderName.setText(reader.getRdName());
		passwordField.setText(reader.getReaderPassword());
		tfNumBorrowed.setText(String.valueOf(reader.getRdBorrowQty()));
		tfStatus.setText(reader.getRdStatus());
		tfReaderRole.setText(String.valueOf(reader.getRdAdminRoles()));
		tfReaderPhone.setText(reader.getRdPhone());
		tfEmail.setText(reader.getRdEmail());
		tfDate.setText(reader.getRdDateReg());
		if (reader.getRdSex().equals("女")) {
			cbGender.setSelectedIndex(1);
		} else {
			cbGender.setSelectedIndex(0);
		}
		cbReaderType.setSelectedItem((readerTypeBll.getReaderType(reader.getRdType())).getRdTypeName());
		cbDeptType.setSelectedItem(reader.getRdDept());

		if (reader.getRdPhoto() != null) {
			lblPhoto.setIcon(new ImageIcon(reader.getRdPhoto()));
		}
	}

	/**
	 * 根据输入框和选择框中的信息生成一个读者对象
	 * @return 读者对象
	 */
	private Reader getReaderFromText() {
		Reader reader = new Reader(Integer.valueOf(tfReaderID.getText()));
		reader.setRdName(tfReaderName.getText().trim());
		reader.setRdPwd(String.valueOf(passwordField.getPassword()));
		reader.setRdSex((String) cbGender.getSelectedItem());
		reader.setRdType(readerBll.getReaderTypeCode((String) cbReaderType.getSelectedItem()));
		reader.setRdDept((String) cbDeptType.getSelectedItem());
		reader.setRdPhone(tfReaderPhone.getText().trim());
		reader.setRdEmail(tfEmail.getText().trim());
		if (!tfNumBorrowed.getText().trim().equals("")) {
			reader.setRdBorrowQty(Integer.valueOf(tfNumBorrowed.getText().trim()));
		}
		reader.setRdDateReg(tfDate.getText().trim());
		reader.setRdStatus(tfStatus.getText().trim());
		if (!tfReaderRole.getText().trim().equals("")) {
			reader.setRdAdminRoles(Integer.valueOf(tfReaderRole.getText().trim()));
		}
		if (lblPhoto.getIcon() != null) {
			Image image = ((ImageIcon) lblPhoto.getIcon()).getImage();
			reader.setRdPhoto(image);
		}
		return reader;
	}

	/**
	 * 默认构造方法.添加所有元素入面板
	 * @throws SQLException 数据库异常
	 */
	public ReaderPanel() throws SQLException {
		setSize(new Dimension(975, 535));
		setLayout(null);

		searchPanel = new JPanel();
		searchPanel.setLayout(null);
		searchPanel.setBounds(0, 0, 975, 42);
		add(searchPanel);

		JLabel label = new JLabel("\u8BFB\u8005\u7C7B\u522B \uFF1A");
		label.setBounds(25, 10, 68, 21);
		searchPanel.add(label);

		JLabel label_1 = new JLabel("\u5355\u4F4D \uFF1A");
		label_1.setBounds(211, 10, 54, 21);
		searchPanel.add(label_1);

		JLabel label_2 = new JLabel("\u59D3\u540D \uFF1A");
		label_2.setBounds(410, 10, 54, 21);
		searchPanel.add(label_2);
		
		rdTypeComboBox = new JComboBox(readerTypeBll.getReaderTypesName());
		rdTypeComboBox.setBounds(96, 10, 105, 21);
		searchPanel.add(rdTypeComboBox);

		deptTypeComboBox = new JComboBox(readerBll.getAllReaderDepartment());
		System.out.println(readerBll.getAllReaderDepartment());
		deptTypeComboBox.setBounds(264, 10, 116, 21);
		searchPanel.add(deptTypeComboBox);

		tfUserName = new JTextField();
		tfUserName.setColumns(10);
		tfUserName.setBounds(467, 10, 97, 21);
		searchPanel.add(tfUserName);

//		btnToExcel = new JButton("Excel");
//		btnToExcel.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		btnToExcel.setBounds(706, 10, 76, 21);
//		searchPanel.add(btnToExcel);

		btnQuery = new JButton("\u67E5\u627E");
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 更新查询结果列表
				updateResultTable();
			}
		});
		btnQuery.setBounds(605, 10, 76, 21);
		searchPanel.add(btnQuery);

		readerInfoPanel = new JPanel();
		readerInfoPanel.setLayout(null);
		readerInfoPanel.setBorder(
				new TitledBorder(null, "\u8BFB\u8005\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		readerInfoPanel.setBounds(605, 52, 345, 385);
		add(readerInfoPanel);

		btnLoadPictureFile = new JButton("\u56FE\u7247\u6587\u4EF6");
		btnLoadPictureFile.setBounds(215, 215, 93, 23);
		//加载选择的头像事件
		btnLoadPictureFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new ImageFilter());
				// 实现文件选取功能并添加选择图片文件类型的过滤器ImageFilter
				int returnVal = fc.showOpenDialog(ReaderPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						BufferedImage img = ImageIO.read(file);
						Image dimg = img.getScaledInstance(lblPhoto.getWidth(), lblPhoto.getHeight(),
								Image.SCALE_SMOOTH);
						ImageIcon icon = new ImageIcon(dimg);
						lblPhoto.setIcon(icon);
					} catch (IOException ea) {
						ea.printStackTrace();
					}
				}
			}
		});
		readerInfoPanel.add(btnLoadPictureFile);

		lblPhoto = new JLabel("");
		lblPhoto.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPhoto.setBounds(202, 19, 122, 186);
		readerInfoPanel.add(lblPhoto);

		JLabel label_4 = new JLabel("\u501F\u4E66\u8BC1\u53F7");
		label_4.setBounds(10, 23, 54, 15);
		readerInfoPanel.add(label_4);

		tfReaderID = new JTextField();
		tfReaderID.setEditable(false);
		tfReaderID.setColumns(10);
		tfReaderID.setBounds(75, 20, 117, 18);
		readerInfoPanel.add(tfReaderID);

		tfReaderName = new JTextField();
		tfReaderName.setColumns(10);
		tfReaderName.setBounds(75, 49, 117, 18);
		readerInfoPanel.add(tfReaderName);

		JLabel label_5 = new JLabel("\u59D3\u540D");
		label_5.setBounds(10, 52, 54, 15);
		readerInfoPanel.add(label_5);

		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(75, 77, 117, 18);
		readerInfoPanel.add(passwordField);

		JLabel label_6 = new JLabel("\u5BC6\u7801");
		label_6.setBounds(10, 80, 54, 15);
		readerInfoPanel.add(label_6);

		JLabel label_7 = new JLabel("\u6027\u522B");
		label_7.setBounds(10, 105, 54, 15);
		readerInfoPanel.add(label_7);

		JLabel label_8 = new JLabel("\u5DF2\u501F\u4E66");
		label_8.setBounds(10, 135, 54, 15);
		readerInfoPanel.add(label_8);

		JLabel label_9 = new JLabel("\u8BC1\u4EF6\u72B6\u6001");
		label_9.setBounds(10, 165, 54, 15);
		readerInfoPanel.add(label_9);

		JLabel label_10 = new JLabel("\u8BFB\u8005\u6743\u9650");
		label_10.setBounds(10, 195, 54, 15);
		readerInfoPanel.add(label_10);

		JLabel label_11 = new JLabel("\u8BFB\u8005\u7C7B\u578B");
		label_11.setBounds(10, 225, 54, 15);
		readerInfoPanel.add(label_11);

		JLabel label_12 = new JLabel("\u5355\u4F4D");
		label_12.setBounds(10, 255, 54, 15);
		readerInfoPanel.add(label_12);

		JLabel label_13 = new JLabel("\u7535\u8BDD\u53F7\u7801");
		label_13.setBounds(10, 285, 54, 15);
		readerInfoPanel.add(label_13);

		JLabel label_14 = new JLabel("\u7535\u5B50\u90AE\u4EF6");
		label_14.setBounds(10, 315, 54, 15);
		readerInfoPanel.add(label_14);

		JLabel label_15 = new JLabel("\u529E\u8BC1\u65E5\u671F");
		label_15.setBounds(10, 345, 54, 15);
		readerInfoPanel.add(label_15);

		tfNumBorrowed = new JTextField();
		tfNumBorrowed.setColumns(10);
		tfNumBorrowed.setBounds(75, 135, 117, 18);
		readerInfoPanel.add(tfNumBorrowed);

		tfStatus = new JTextField();
		tfStatus.setColumns(10);
		tfStatus.setBounds(75, 164, 117, 18);
		readerInfoPanel.add(tfStatus);

		tfReaderRole = new JTextField();
		tfReaderRole.setColumns(10);
		tfReaderRole.setBounds(75, 193, 117, 18);
		readerInfoPanel.add(tfReaderRole);

		tfReaderPhone = new JTextField();
		tfReaderPhone.setColumns(10);
		tfReaderPhone.setBounds(75, 283, 202, 18);
		readerInfoPanel.add(tfReaderPhone);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(75, 313, 202, 18);
		readerInfoPanel.add(tfEmail);

		tfDate = new JTextField();
		tfDate.setColumns(10);
		tfDate.setBounds(74, 343, 202, 18);
		readerInfoPanel.add(tfDate);

		cbGender = new JComboBox();
		cbGender.setModel(new DefaultComboBoxModel(new String[] { "\u7537", "\u5973" }));
		cbGender.setBounds(75, 102, 117, 23);
		readerInfoPanel.add(cbGender);

		cbReaderType = new JComboBox(readerTypeBll.getReaderTypesName());
		cbReaderType.setBounds(75, 222, 117, 23);
		readerInfoPanel.add(cbReaderType);

		cbDeptType = new JComboBox(readerBll.getAllReaderDepartment());
		cbDeptType.setEditable(true);

		cbDeptType.setBounds(75, 252, 202, 23);
		readerInfoPanel.add(cbDeptType);

		functionCtlPanel = new JPanel();
		functionCtlPanel.setBounds(10, 448, 590, 65);
		add(functionCtlPanel);

		btnNewReader = new JButton("\u529E\u7406\u501F\u4E66\u8BC1");
		btnNewReader.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setStatus(OpStatus.inNew);
				initAllPanel();
			}
		});
		functionCtlPanel.add(btnNewReader);

		btnUpdateReader = new JButton("\u53D8\u66F4\u501F\u4E66\u8BC1");
		btnUpdateReader.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectedRow = searchResultTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "请先选中一条记录!");
					return;
				} else {
					tfReaderID.setEditable(false);
					setReaderToText(
							((CustomizedTableModel<Reader>) searchResultTable.getModel()).getObjectAt(selectedRow));
					setStatus(OpStatus.inChange);
				}
			}
		});
		functionCtlPanel.add(btnUpdateReader);

		btnCancelReader = new JButton("\u6CE8\u9500\u501F\u4E66\u8BC1");
		btnCancelReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = searchResultTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "请先选中一条记录!");
					return;
				} else {
					Reader concle = ((CustomizedTableModel<Reader>) searchResultTable.getModel())
							.getObjectAt(selectedRow);
					ReaderDAL dal = new ReaderDAL();
					try {
						dal.delete(concle);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "注销成功!");
					// 更新查询结果列表
					updateResultTable();
				}
			}
		});

		JButton btnLost = new JButton("\u6302\u5931\u501F\u4E66\u8BC1");
		btnLost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = searchResultTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "请先选中一个借书证!");
					return;
				} else {
					//获得对应的读者对象
					Reader concle = ((CustomizedTableModel<Reader>) searchResultTable.getModel())
							.getObjectAt(selectedRow);
					concle.setRdStatus("挂失");
					ReaderDAL dal = new ReaderDAL();
					try {
						dal.update(concle);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "挂失成功!");
					// 更新查询结果列表
					updateResultTable();
				}
			}
		});
		functionCtlPanel.add(btnLost);

		JButton btnFound = new JButton("\u89E3\u9664\u6302\u5931");
		btnFound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = searchResultTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "请先选中一个借书证!");
					return;
				} else {
					//获得对应的读者对象
					Reader concle = ((CustomizedTableModel<Reader>) searchResultTable.getModel())
							.getObjectAt(selectedRow);
					//更改读者对象信息
					concle.setRdStatus("有效");
					ReaderDAL dal = new ReaderDAL();
					try {
						dal.update(concle);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// 更新查询结果列表
					updateResultTable();
					JOptionPane.showMessageDialog(null, "解除挂失成功!");
				}
			}
		});
		functionCtlPanel.add(btnFound);
		functionCtlPanel.add(btnCancelReader);

		editCtlPanel = new JPanel();
		editCtlPanel.setBounds(605, 447, 345, 65);
		add(editCtlPanel);

		btnAddReader = new JButton("\u786E\u8BA4\u529E\u8BC1");
		btnAddReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfReaderID.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "必须先输入借书证号");
				} else {
					Reader reader = getReaderFromText();
					ReaderDAL dal = new ReaderDAL();
					try {
						dal.add(reader);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// 更新查询结果列表
					updateResultTable();
					// 清除已输入的信息
					initAllPanel();
					JOptionPane.showMessageDialog(null, "办证成功!");
				}
			}
		});
		editCtlPanel.add(btnAddReader);

		btnSubmitUpdate = new JButton("\u786E\u8BA4\u53D8\u66F4");
		btnSubmitUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Reader reader = getReaderFromText();
				readerBll.updateReader(reader);
				// 更新查询结果列表
				updateResultTable();
				JOptionPane.showMessageDialog(null, "更改成功!");
			}
		});
		editCtlPanel.add(btnSubmitUpdate);

		btnCancelEdit = new JButton("\u53D6\u6D88");
		btnCancelEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setStatus(OpStatus.inSelect);
			}
		});
		editCtlPanel.add(btnCancelEdit);

		searchResultPanel = new JScrollPane((Component) null);
		searchResultPanel.setBorder(
				new TitledBorder(null, "\u67E5\u8BE2\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		searchResultPanel.setBounds(10, 64, 590, 373);
		
		tableModel = new CustomizedTableModel<Reader>(readerBll.getDisplayColumnNames(), readerBll.getMethodNames());
		searchResultTable = new JTable(tableModel);
		searchResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		searchResultPanel.setViewportView(searchResultTable);
		add(searchResultPanel);

		// 设置初始操作状态
		setStatus(OpStatus.inSelect);

	}

	public static void main(String[] args) throws SQLException {
		//用于便捷测试
		JFrame jf = new JFrame("Test");
		jf.setSize(1000, 550);
		ReaderPanel rp = new ReaderPanel();
		jf.getContentPane().add(rp);
		jf.setVisible(true);
	}
}
