package seven.libraryms.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import seven.libraryms.bll.BookAdmin;
import seven.libraryms.bll.ReaderAdmin;
import seven.libraryms.dal.BookDAL;
import seven.libraryms.gui.commons.CustomizedTableModel;
import seven.libraryms.gui.commons.ImageFilter;
import seven.libraryms.model.Book;

/**
 * 读者面板
 * 
 * @author Seven
 * @version 1.1
 */
public class SearchBookPanel extends JPanel {
	private JTextField tfBkID;
	private JTextField tfBkCode;
	private JTextField tfBkPress;
	private JTextField tfBkDatePress;
	private JTextField tfBkISBN;
	private JTextField tfBkPages;
	private JTextField tfBkPrice;
	private JTextField tfBkDateIn;
	private JTable searchResultTable;
	private JPanel readerInfoPanel;
	private JLabel lblBkCover;
	private JButton btnLoadPictureFile;
	private JPanel editCtlPanel;
	private JButton btnSubmitUpdate;
	private JButton btnDeteleBook;
	private JScrollPane searchResultPanel;
	private JTextField tfBkAuthor;
	private JTextField tfBkCatalog;
	private JTextField tfBkLanguage;
	private JTextField tfBkStatus;
	private JLabel label_1;
	private JTextField tfBkName;
	private JEditorPane epBkBrief;
	private JButton button;
	private CustomizedTableModel<Book> tableModel;
	private JPanel panel;
	private JPanel panel_1;
	private JButton btnSimpleQuery;
	private JButton btnNewButton_2;
	private JLabel label_2;
	private JComboBox cbSimpleQuery;
	private JTextField tfSimpleQuery;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	private ReaderAdmin readerBll = new ReaderAdmin();
	private BookAdmin bookBll = new BookAdmin();

	/**
	 * update ResultTable to JTable
	 * 
	 * @param readers
	 */
	private void updateResultTable(Book[] books) {
		if (books == null) {
			JOptionPane.showMessageDialog(null, "没有找到符合要求的记录");
			tableModel.setRecords(null);
			tableModel.fireTableDataChanged();
			return;
		}

		tableModel.setRecords(books);
		// 更新表格
		tableModel.fireTableDataChanged();

	}

	private void updateResultTable() {
		// TODO Auto-generated method stub

		Book[] books = bookBll.retrieveBooksString();
		// tableModel.setRecords(hits);
		tableModel.setRecords(books);
		// 更新表格
		tableModel.fireTableDataChanged();

	}

	private void setBookToText(Book book) {
		tfBkID.setText(String.valueOf(book.getBkID()));
		tfBkCode.setText(book.getBkCode());
		tfBkName.setText(book.getBkName());
		tfBkPress.setText(String.valueOf(book.getBkPress()));
		tfBkDatePress.setText(book.getBkDatePress());
		tfBkISBN.setText(String.valueOf(book.getBkISBN()));
		tfBkPages.setText(String.valueOf(book.getBkPages()));
		tfBkPrice.setText(String.valueOf(book.getBkPrice()));
		tfBkDateIn.setText(String.valueOf(book.getBkDateIn()));
		tfBkAuthor.setText(String.valueOf(book.getBkAuthor()));
		tfBkCatalog.setText(String.valueOf(book.getBkCatalog()));
		tfBkLanguage.setText(String.valueOf(book.getBkLanguage()));
		tfBkStatus.setText(String.valueOf(book.getBkStatus()));
		epBkBrief.setText(String.valueOf(book.getBkBrief()));
		lblBkCover.setIcon(new ImageIcon(book.getBkCover()));
	}

	private void setBookToTextNull() {
		tfBkID.setText("");
		tfBkCode.setText("");
		tfBkName.setText("");
		tfBkPress.setText("");
		tfBkDatePress.setText("");
		tfBkISBN.setText("");
		tfBkPages.setText("");
		tfBkPrice.setText("");
		tfBkDateIn.setText("");
		tfBkAuthor.setText("");
		tfBkCatalog.setText("");
		tfBkLanguage.setText("");
		tfBkStatus.setText("");
		epBkBrief.setText("");
		lblBkCover.setIcon(null);
	}

	private Book getBookFromText() {
		Book book = new Book();
		book.setBkCode(tfBkCode.getText().trim());
		book.setBkName(tfBkName.getText().trim());
		book.setBkAuthor(tfBkAuthor.getText().trim());
		book.setBkPress(tfBkPress.getText().trim());
		book.setBkDatePress(tfBkDatePress.getText().trim());
		book.setBkISBN(tfBkISBN.getText().trim());
		book.setBkCatalog(tfBkCatalog.getText().trim());
		book.setBkDateIn(tfBkDateIn.getText().trim());
		book.setBkBrief(epBkBrief.getText().trim());
		book.setBkStatus(tfBkStatus.getText().trim());
		if (!tfBkID.getText().trim().equals("")) {
			book.setBkID(Integer.valueOf(tfBkID.getText().trim()));
		}
		if (!tfBkLanguage.getText().trim().equals("")) {
			book.setBkLanguage(Integer.valueOf(tfBkLanguage.getText().trim()));
		}
		if (!tfBkPages.getText().trim().equals("")) {
			book.setBkPages(Integer.valueOf(tfBkPages.getText().trim()));
		}
		if (!tfBkPrice.getText().trim().equals("")) {
			book.setBkPrice(Float.valueOf(tfBkPrice.getText().trim()));
		}
		if (lblBkCover.getIcon() != null) {
			Image image = ((ImageIcon) lblBkCover.getIcon()).getImage();
			book.setBkCover(image);
		}
		return book;
	}

	public SearchBookPanel() throws SQLException {
		setSize(new Dimension(975, 535));
		setLayout(null);

		readerInfoPanel = new JPanel();
		readerInfoPanel.setLayout(null);
		readerInfoPanel.setBorder(
				new TitledBorder(null, "\u8BFB\u8005\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		readerInfoPanel.setBounds(663, 10, 302, 451);
		add(readerInfoPanel);

		btnLoadPictureFile = new JButton("\u56FE\u7247\u6587\u4EF6");
		btnLoadPictureFile.setBounds(187, 175, 93, 23);
		btnLoadPictureFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new ImageFilter());
				// 实现文件选取功能并添加选择图片文件类型的过滤器ImageFilter
				int returnVal = fc.showOpenDialog(SearchBookPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						BufferedImage img = ImageIO.read(file);
						Image dimg = img.getScaledInstance(lblBkCover.getWidth(), lblBkCover.getHeight(),
								Image.SCALE_SMOOTH);
						ImageIcon icon = new ImageIcon(dimg);
						lblBkCover.setIcon(icon);
					} catch (IOException ea) {
						ea.printStackTrace();
					}
				}
			}
		});
		readerInfoPanel.add(btnLoadPictureFile);

		lblBkCover = new JLabel("");
		lblBkCover.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblBkCover.setBounds(177, 18, 106, 147);
		readerInfoPanel.add(lblBkCover);

		JLabel label_4 = new JLabel("\u56FE\u4E66\u5E8F\u53F7");
		label_4.setBounds(10, 21, 72, 15);
		readerInfoPanel.add(label_4);

		JLabel label_5 = new JLabel("\u56FE\u4E66\u7F16\u53F7");
		label_5.setBounds(10, 52, 84, 15);
		readerInfoPanel.add(label_5);

		JLabel label_6 = new JLabel("\u56FE\u4E66\u4E66\u540D");
		label_6.setBounds(10, 83, 84, 15);
		readerInfoPanel.add(label_6);

		JLabel label_7 = new JLabel("\u56FE\u4E66\u4F5C\u8005 ");
		label_7.setBounds(10, 114, 106, 15);
		readerInfoPanel.add(label_7);

		JLabel label_8 = new JLabel("\u51FA\u7248\u793E ");
		label_8.setBounds(10, 145, 72, 15);
		readerInfoPanel.add(label_8);

		JLabel label_9 = new JLabel("\u51FA\u7248\u65E5\u671F ");
		label_9.setBounds(10, 176, 72, 15);
		readerInfoPanel.add(label_9);

		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(10, 207, 72, 15);
		readerInfoPanel.add(lblIsbn);

		JLabel label_11 = new JLabel("\u5206\u7C7B\u540D");
		label_11.setBounds(10, 238, 72, 15);
		readerInfoPanel.add(label_11);

		JLabel label_12 = new JLabel("\u56FE\u4E66\u8BED\u8A00");
		label_12.setBounds(10, 269, 84, 15);
		readerInfoPanel.add(label_12);

		JLabel label_13 = new JLabel("\u56FE\u4E66\u9875\u6570");
		label_13.setBounds(10, 300, 72, 15);
		readerInfoPanel.add(label_13);

		JLabel label_14 = new JLabel("\u56FE\u4E66\u4EF7\u683C");
		label_14.setBounds(10, 331, 72, 15);
		readerInfoPanel.add(label_14);

		JLabel label_15 = new JLabel("\u5165\u9986\u65E5\u671F");
		label_15.setBounds(10, 362, 72, 15);
		readerInfoPanel.add(label_15);

		JLabel label = new JLabel("\u56FE\u4E66\u72B6\u6001");
		label.setBounds(10, 393, 72, 15);
		readerInfoPanel.add(label);

		label_1 = new JLabel("\u56FE\u4E66\u7B80\u4ECB :");
		label_1.setBounds(177, 213, 72, 15);
		readerInfoPanel.add(label_1);

		tfBkID = new JTextField();
		tfBkID.setColumns(10);
		tfBkID.setBounds(75, 18, 92, 18);
		readerInfoPanel.add(tfBkID);

		tfBkCode = new JTextField();
		tfBkCode.setColumns(10);
		tfBkCode.setBounds(75, 49, 92, 18);
		readerInfoPanel.add(tfBkCode);

		tfBkPress = new JTextField();
		tfBkPress.setColumns(10);
		tfBkPress.setBounds(75, 142, 92, 18);
		readerInfoPanel.add(tfBkPress);

		tfBkDatePress = new JTextField();
		tfBkDatePress.setColumns(10);
		tfBkDatePress.setBounds(75, 173, 92, 18);
		readerInfoPanel.add(tfBkDatePress);

		tfBkISBN = new JTextField();
		tfBkISBN.setColumns(10);
		tfBkISBN.setBounds(75, 204, 92, 18);
		readerInfoPanel.add(tfBkISBN);

		tfBkPages = new JTextField();
		tfBkPages.setColumns(10);
		tfBkPages.setBounds(75, 297, 92, 18);
		readerInfoPanel.add(tfBkPages);

		tfBkPrice = new JTextField();
		tfBkPrice.setColumns(10);
		tfBkPrice.setBounds(75, 328, 92, 18);
		readerInfoPanel.add(tfBkPrice);

		tfBkDateIn = new JTextField();
		tfBkDateIn.setColumns(10);
		tfBkDateIn.setBounds(74, 359, 93, 18);
		readerInfoPanel.add(tfBkDateIn);

		tfBkAuthor = new JTextField();
		tfBkAuthor.setColumns(10);
		tfBkAuthor.setBounds(75, 111, 92, 18);
		readerInfoPanel.add(tfBkAuthor);

		tfBkCatalog = new JTextField();
		tfBkCatalog.setColumns(10);
		tfBkCatalog.setBounds(75, 235, 92, 18);
		readerInfoPanel.add(tfBkCatalog);

		tfBkLanguage = new JTextField();
		tfBkLanguage.setColumns(10);
		tfBkLanguage.setBounds(75, 266, 92, 18);
		readerInfoPanel.add(tfBkLanguage);

		tfBkStatus = new JTextField();
		tfBkStatus.setColumns(10);
		tfBkStatus.setBounds(74, 390, 93, 18);
		readerInfoPanel.add(tfBkStatus);

		tfBkName = new JTextField();
		tfBkName.setColumns(10);
		tfBkName.setBounds(75, 80, 92, 18);
		readerInfoPanel.add(tfBkName);

		epBkBrief = new JEditorPane();
		epBkBrief.setBounds(177, 238, 117, 172);
		readerInfoPanel.add(epBkBrief);

		button = new JButton("\u6E05\u7A7A\u4FE1\u606F");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setBookToTextNull();
			}
		});
		button.setBounds(116, 418, 133, 23);
		readerInfoPanel.add(button);

		editCtlPanel = new JPanel();
		editCtlPanel.setBounds(663, 471, 287, 41);
		add(editCtlPanel);
		btnSubmitUpdate = new JButton("\u53D8\u66F4\u56FE\u4E66");
		btnSubmitUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book Book = getBookFromText();
				bookBll.updateBook(Book);
				JOptionPane.showMessageDialog(null, "更改成功!");
				updateResultTable();
			}
		});
		editCtlPanel.add(btnSubmitUpdate);

		btnDeteleBook = new JButton("\u5220\u9664\u56FE\u4E66");
		btnDeteleBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = searchResultTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "请先选中一条记录!");
					return;
				} else {
					Book concle = ((CustomizedTableModel<Book>) searchResultTable.getModel()).getObjectAt(selectedRow);
					BookDAL dal = new BookDAL();
					try {
						dal.delete(concle);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "删除成功!");
					updateResultTable();
				}
			}
		});
		editCtlPanel.add(btnDeteleBook);

		searchResultPanel = new JScrollPane((Component) null);
		searchResultPanel.setBorder(
				new TitledBorder(null, "\u67E5\u8BE2\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		searchResultPanel.setBounds(10, 132, 643, 380);

		tableModel = new CustomizedTableModel<Book>(bookBll.getDisplayColumnNames(), bookBll.getMethodNames());

		searchResultTable = new JTable(tableModel);
		searchResultTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = searchResultTable.getSelectedRow();
				if (selectedRow > 0) {
					setBookToText(((CustomizedTableModel<Book>) searchResultTable.getModel()).getObjectAt(selectedRow));
				}
			}
		});
		searchResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		searchResultPanel.setViewportView(searchResultTable);
		add(searchResultPanel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 643, 113);
		add(tabbedPane);

		panel = new JPanel();
		tabbedPane.addTab("简单查询", null, panel, null);
		panel.setLayout(null);

		btnSimpleQuery = new JButton("\u67E5\u8BE2");
		btnSimpleQuery.setBounds(535, 30, 93, 23);
		panel.add(btnSimpleQuery);

		label_2 = new JLabel("\u68C0\u7D22\u5B57\u6BB5");
		label_2.setBounds(10, 30, 54, 23);
		panel.add(label_2);

		cbSimpleQuery = new JComboBox(bookBll.getDisplayColumnNames());
		cbSimpleQuery.setBounds(85, 30, 147, 23);
		panel.add(cbSimpleQuery);

		tfSimpleQuery = new JTextField();
		tfSimpleQuery.setBounds(242, 30, 283, 23);
		panel.add(tfSimpleQuery);
		tfSimpleQuery.setColumns(10);

		panel_1 = new JPanel();
		tabbedPane.addTab("高级查询", null, panel_1, null);
		panel_1.setLayout(null);

		btnNewButton_2 = new JButton("\u67E5\u8BE2");
		btnNewButton_2.setBounds(535, 33, 93, 23);
		panel_1.add(btnNewButton_2);

		JLabel label_3 = new JLabel("\u56FE\u4E66\u540D\u79F0");
		label_3.setBounds(10, 22, 68, 15);
		panel_1.add(label_3);

		textField_1 = new JTextField();
		textField_1.setBounds(65, 19, 93, 21);
		panel_1.add(textField_1);
		textField_1.setColumns(10);

		JLabel label_10 = new JLabel("\u51FA\u7248\u793E\u540D");
		label_10.setBounds(10, 56, 68, 15);
		panel_1.add(label_10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(65, 56, 93, 21);
		panel_1.add(textField_2);

		JLabel label_16 = new JLabel("\u56FE\u4E66\u4F5C\u8005");
		label_16.setBounds(181, 22, 68, 15);
		panel_1.add(label_16);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(243, 19, 93, 21);
		panel_1.add(textField_3);

		JLabel label_17 = new JLabel("\u5206\u7C7B\u53F7");
		label_17.setBounds(181, 56, 68, 15);
		panel_1.add(label_17);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(243, 56, 93, 21);
		panel_1.add(textField_4);

		JLabel label_18 = new JLabel("\u56FE\u4E66\u63CF\u8FF0");
		label_18.setBounds(347, 22, 68, 15);
		panel_1.add(label_18);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(409, 19, 93, 21);
		panel_1.add(textField_5);

		JLabel label_19 = new JLabel("\u51FA\u7248\u5E74");
		label_19.setBounds(347, 56, 68, 15);
		panel_1.add(label_19);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(409, 53, 93, 21);
		panel_1.add(textField_6);
		updateResultTable();
	}
	public static void main(String[] args) throws SQLException {
		// 用于便捷测试
		JFrame jf = new JFrame("Test");
		jf.setSize(1000, 600);
		SearchBookPanel rp = new SearchBookPanel();
		jf.getContentPane().add(rp);
		jf.setVisible(true);
	}
}
