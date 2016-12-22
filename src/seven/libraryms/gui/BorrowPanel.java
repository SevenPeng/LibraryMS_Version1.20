package seven.libraryms.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import seven.libraryms.bll.BookAdmin;
import seven.libraryms.bll.BorrowAdmin;
import seven.libraryms.bll.ReaderAdmin;
import seven.libraryms.bll.ReaderTypeAdmin;
import seven.libraryms.dal.BorrowDAL;
import seven.libraryms.gui.commons.CustomizedTableModel;
import seven.libraryms.model.Book;
import seven.libraryms.model.Borrow;
import seven.libraryms.model.Reader;
import seven.libraryms.model.ReaderType;
/**
 * 借阅管理面板.只初始化了相关组件  未实现功能.
 * @author Seven
 * 	@Data 2016-12-12
 * @version 1.00
 *
 */
public class BorrowPanel extends JPanel {
	
	private JTextField tfRdID;
	private JTextField tfReaderName;
	private JTextField tfCanLendQty;
	private JTextField tfRdDept;
	private JTextField tfCanLendDay;
	private JTextField tfRdType;
	private JTextField tfRdBorrowQty;
	private JTextField tfBkID;
	private JTextField tfBkName;
	private JScrollPane bookSearchResultPanel;
	private JTable bookSearchResultTable;
	private JTable borrowSearchResultTable;
	private CustomizedTableModel<Book> bookTableModel;
	private CustomizedTableModel<Borrow> borrowTableModel;

	private BookAdmin bookBll = new BookAdmin();
	private BorrowAdmin borrowBll = new BorrowAdmin();
	private ReaderAdmin readerBll = new ReaderAdmin();
	private ReaderTypeAdmin readerTypeBll = new ReaderTypeAdmin();
	
	private String CanLendQty;
	private String CanLendDay;
	
	public BorrowPanel() {
		setSize(new Dimension(975, 535));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8BFB\u8005\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 975, 85);
		add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u501F\u4E66\u8BC1\u53F7");
		label.setBounds(10, 30, 54, 15);
		panel.add(label);
		
		tfRdID = new JTextField();
		tfRdID.setBounds(74, 27, 103, 21);
		panel.add(tfRdID);
		tfRdID.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8BFB\u8005\u59D3\u540D");
		label_1.setBounds(326, 13, 68, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u53EF\u501F\u4E66\u6570\u91CF");
		label_2.setBounds(326, 48, 68, 15);
		panel.add(label_2);
		
		tfReaderName = new JTextField();
		tfReaderName.setBounds(390, 10, 116, 21);
		panel.add(tfReaderName);
		tfReaderName.setColumns(10);
		
		tfCanLendQty = new JTextField();
		tfCanLendQty.setBounds(390, 45, 116, 21);
		panel.add(tfCanLendQty);
		tfCanLendQty.setColumns(10);
		
		JLabel label_3 = new JLabel("\u8BFB\u8005\u5355\u4F4D");
		label_3.setBounds(542, 16, 78, 15);
		panel.add(label_3);
		
		tfRdDept = new JTextField();
		tfRdDept.setColumns(10);
		tfRdDept.setBounds(606, 13, 116, 21);
		panel.add(tfRdDept);
		
		JLabel label_4 = new JLabel("\u53EF\u501F\u4E66\u5929\u6570");
		label_4.setBounds(542, 51, 68, 15);
		panel.add(label_4);
		
		tfCanLendDay = new JTextField();
		tfCanLendDay.setColumns(10);
		tfCanLendDay.setBounds(606, 48, 116, 21);
		panel.add(tfCanLendDay);
		
		JLabel label_5 = new JLabel("\u8BFB\u8005\u7C7B\u578B");
		label_5.setBounds(761, 16, 68, 15);
		panel.add(label_5);
		
		tfRdType = new JTextField();
		tfRdType.setColumns(10);
		tfRdType.setBounds(825, 13, 116, 21);
		panel.add(tfRdType);
		
		JLabel label_6 = new JLabel("\u5DF2\u501F\u6570\u91CF");
		label_6.setBounds(761, 51, 68, 15);
		panel.add(label_6);
		
		tfRdBorrowQty = new JTextField();
		tfRdBorrowQty.setColumns(10);
		tfRdBorrowQty.setBounds(825, 48, 116, 21);
		panel.add(tfRdBorrowQty);
		
		JButton btnRdSearch = new JButton("\u67E5\u8BE2");
		btnRdSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfRdID == null){
					JOptionPane.showInternalMessageDialog(null, "请先输入借书证号!");
				}
				int rdID = Integer.valueOf(tfRdID.getText().trim());
				Reader reader = readerBll.getReader(rdID);
				ReaderType rt = readerTypeBll.getReaderType(reader.getRdType());
				CanLendQty = String.valueOf(rt.getCanLendQty());
				CanLendDay = String.valueOf(rt.getCanLendDay());
				tfReaderName.setText(reader.getRdName());
				tfRdDept.setText(reader.getRdDept());
				tfRdType.setText(String.valueOf(reader.getRdType()));
				tfRdBorrowQty.setText(String.valueOf(reader.getRdBorrowQty()));
				tfCanLendDay.setText(CanLendDay);
				tfCanLendQty.setText(CanLendQty);
				Borrow[] hits = borrowBll.retrieveBorrowsStringByRdID(rdID);
				updateBorrowResultTable(hits);
			}
		});
		btnRdSearch.setBounds(202, 26, 68, 23);
		panel.add(btnRdSearch);
		
		JScrollPane borrowSearchResultPanel = new JScrollPane((Component) null);
		borrowSearchResultPanel.setBorder(new TitledBorder(null, "\u5DF2\u501F\u56FE\u4E66", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		borrowSearchResultPanel.setBounds(0, 95, 975, 147);
		borrowTableModel = new CustomizedTableModel<Borrow>(
				borrowBll.getDisplayColumnNames(),
				borrowBll.getMethodNames());
		
		borrowSearchResultTable = new JTable(borrowTableModel);
		borrowSearchResultTable.setBounds(70, 69, 1, 1);
		borrowSearchResultPanel.setViewportView(borrowSearchResultTable);
		add(borrowSearchResultPanel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u56FE\u4E66\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(0, 241, 975, 55);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_7 = new JLabel("\u56FE\u4E66\u5E8F\u53F7");
		label_7.setBounds(21, 25, 54, 15);
		panel_2.add(label_7);
		
		tfBkID = new JTextField();
		tfBkID.setBounds(81, 19, 172, 21);
		panel_2.add(tfBkID);
		tfBkID.setColumns(10);
		
		JLabel label_8 = new JLabel("\u56FE\u4E66\u540D\u79F0");
		label_8.setBounds(417, 23, 54, 15);
		panel_2.add(label_8);
		
		tfBkName = new JTextField();
		tfBkName.setColumns(10);
		tfBkName.setBounds(476, 17, 172, 21);
		panel_2.add(tfBkName);
		
		JButton btnBkSearchByID = new JButton("\u67E5\u8BE2");
		btnBkSearchByID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int BkID = Integer.valueOf(tfBkID.getText().trim());
				Book[] hits = new Book[1];
				hits[0] = bookBll.getBook(BkID);
				updateBookResultTable(hits);
			}

		});
		btnBkSearchByID.setBounds(267, 17, 63, 23);
		panel_2.add(btnBkSearchByID);
		
		JButton btnSearchBkByName = new JButton("\u67E5\u8BE2");
		btnSearchBkByName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String BkName = tfBkName.getText().trim();
				Book[] hits = bookBll.retrieveBooksStringByName(BkName);
				updateBookResultTable(hits);
			}
		});
		btnSearchBkByName.setBounds(658, 17, 63, 23);
		panel_2.add(btnSearchBkByName);
		
		bookSearchResultPanel =new JScrollPane((Component) null);
		bookSearchResultPanel.setBounds(0, 301, 975, 192);
		
		bookTableModel = new CustomizedTableModel<Book>(
				bookBll.getDisplayColumnNames(),
				bookBll.getMethodNames());
		
		bookSearchResultTable = new JTable(bookTableModel);
		bookSearchResultTable.setBounds(87, 74, 1, 1);
		bookSearchResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bookSearchResultPanel.setViewportView(bookSearchResultTable);
		add(bookSearchResultPanel);
	
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 496, 975, 39);
		add(panel_4);
		
		JButton btnBorrow = new JButton("\u501F\u9605");
		btnBorrow.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int selectedRow = bookSearchResultTable.getSelectedRow();
				
				if(tfRdID.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "请先查询借书证!");
				}else if(selectedRow <0 ){
					JOptionPane.showMessageDialog(null, "请先选择一本书籍!");
				}else {
					Book book = (Book) ((CustomizedTableModel<Book>) bookSearchResultTable.getModel()).getObjectAt(selectedRow);
					int bkID = book.getBkID();
					int rdID = Integer.valueOf(tfRdID.getText().trim());
					String borrowID = String.valueOf(rdID)+String.valueOf(bkID);
					
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
					String ldDateOut = format.format(cal.getTime());
					int day = Integer.valueOf(CanLendDay);
					cal.add(Calendar.FRIDAY, day);
					String ldDateRetPlan = format.format(cal.getTime());
					Borrow borrow = new Borrow();
					borrow.setBorrowID(borrowID);
					borrow.setRdID(rdID);
					borrow.setBkID(bkID);
					borrow.setLdContinueTimes(0);
					borrow.setLdDateOut(ldDateOut);
					borrow.setLdDateRetPlan(ldDateRetPlan);
					BorrowDAL dal = new BorrowDAL();
					try {
						dal.add(borrow);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "借阅成功!");
				}
			}
		});
		panel_4.add(btnBorrow);
		
		JButton btnReturn = new JButton("\u8FD8\u4E66");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = borrowSearchResultTable.getSelectedRow();
				if(selectedRow <0 ){
					JOptionPane.showMessageDialog(null, "请先选择一个记录!");
					return;
				}else{
					Borrow concle = ((CustomizedTableModel<Borrow>) borrowSearchResultTable.getModel())
							.getObjectAt(selectedRow);
					BorrowDAL dal = new BorrowDAL();
					try {
						dal.delete(concle);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				int BkID = Integer.valueOf(tfBkID.getText().trim());
				Book[] hits = new Book[1];
				hits[0] = bookBll.getBook(BkID);
				updateBookResultTable(hits);
				JOptionPane.showMessageDialog(null, "还书成功!");
			}
		});
		panel_4.add(btnReturn);
		
		JButton btnBorrowMore = new JButton("\u7EED\u501F");
		btnBorrowMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = borrowSearchResultTable.getSelectedRow();
				if(selectedRow <0 ){
					JOptionPane.showMessageDialog(null, "请先选择一个记录!");
					return;
				}else{
					Borrow concle = ((CustomizedTableModel<Borrow>) borrowSearchResultTable.getModel())
							.getObjectAt(selectedRow);
					int temp = concle.getLdContinueTimes();
					temp++;
					concle.setLdContinueTimes(temp);
					BorrowDAL dal = new BorrowDAL();
					try {
						dal.update(concle);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(null, "续借成功!");
			}
		});
		panel_4.add(btnBorrowMore);
	}
	
	private void updateBookResultTable() {
		// TODO Auto-generated method stub
		Book[] books = bookBll.retrieveBooksString();
		bookTableModel.setRecords(books);
		// 更新表格
		bookTableModel.fireTableDataChanged();
	}
	private void updateBookResultTable(Book[] books) {
		// TODO Auto-generated method stub
		if (books == null) {
			JOptionPane.showMessageDialog(null, "没有找到符合要求的记录");
			bookTableModel.setRecords(null);
			bookTableModel.fireTableDataChanged();
			return;
		}
		
		bookTableModel.setRecords(books);
		// 更新表格
		bookTableModel.fireTableDataChanged();
	}
	
	private void updateBorrowResultTable(Borrow[] borrows) {
		// TODO Auto-generated method stub
		if (borrows == null) {
			borrowTableModel.setRecords(null);
			borrowTableModel.fireTableDataChanged();
			return;
		}
		
		borrowTableModel.setRecords(borrows);
		// 更新表格
		borrowTableModel.fireTableDataChanged();
	}
	public static void main(String[] args) {
		JFrame jf = new JFrame("Test");
		jf.setSize(1000, 600);
		BorrowPanel rp = new BorrowPanel();
		jf.getContentPane().add(rp);
		jf.setVisible(true);
	}
}
