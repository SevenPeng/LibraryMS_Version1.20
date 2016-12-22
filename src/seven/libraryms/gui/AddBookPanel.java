package seven.libraryms.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import seven.libraryms.dal.BookDAL;
import seven.libraryms.gui.commons.ImageFilter;
import seven.libraryms.model.Book;

/**
 * 读者面板
 * 
 * @author Seven
 * @version 2.0
 * @Data 2016-12-21
 */
public class AddBookPanel extends JPanel {
	private JTextField tfBkCode;
	private JTextField tfBkPress;
	private JTextField tfBkDatePress;
	private JTextField tfBkISBN;
	private JTextField tfBkPages;
	private JTextField tfBkPrice;
	private JTextField tfBkDateIn;
	private JPanel readerInfoPanel;
	private JPanel editCtlPanel;
	private JButton btnAddBook;
	private JButton btnReset;
	private JTextField tfBkAuthor;
	private JTextField tfBkCatalog;
	private JTextField tfBkName;
	private JEditorPane epBkBrief;
	private JLabel lblBkCover;
	private JComboBox cbBkStatus;
	private JComboBox cbBKLanguage;
	
	/**
	 * 获得当前日期
	 * @return String 日期  YYYY-MM-DD
	 */
	private String getNowDate(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateNow = format.format(cal.getTime());
		return dateNow;
	}

	/**
	 * 初始化所有输入框
	 */
	private void setNullToText() {
		tfBkCode.setText("");
		tfBkName.setText("");
		tfBkPress.setText("");
		tfBkDatePress.setText("");
		tfBkISBN.setText("");
		tfBkPages.setText("");
		tfBkPrice.setText("");
		tfBkDateIn.setText(getNowDate());
		tfBkAuthor.setText("");
		tfBkCatalog.setText("");
		cbBKLanguage.setSelectedIndex(0);
		epBkBrief.setText("");
		lblBkCover.setIcon(null);
	}

	/** 从输入框中新建一个Book对象
	 * @return Book对象
	 */
	private Book getBookFromText() {
		Book book = new Book();
		book.setBkCode(tfBkCode.getText().trim());
		book.setBkName(tfBkName.getText().trim());
		book.setBkAuthor(tfBkAuthor.getText().trim());
		book.setBkPress(tfBkPress.getText().trim());
		book.setBkDatePress(tfBkDatePress.getText().trim());
		book.setBkISBN(tfBkISBN.getText().trim());
		book.setBkCatalog(tfBkCatalog.getText().trim());
		book.setBkLanguage(cbBKLanguage.getSelectedIndex());
		if (!tfBkPages.getText().trim().equals("")) {
			book.setBkPages(Integer.valueOf(tfBkPages.getText().trim()));
		}
		if (!tfBkPrice.getText().trim().equals("")) {
			book.setBkPrice(Integer.valueOf(tfBkPrice.getText().trim()));
		}
		book.setBkDateIn(tfBkDateIn.getText().trim());
		book.setBkBrief(epBkBrief.getText().trim());
		book.setBkStatus((String) cbBkStatus.getSelectedItem());
		if (lblBkCover.getIcon() != null) {
			Image image = ((ImageIcon) lblBkCover.getIcon()).getImage();
			book.setBkCover(image);
		}
		return book;

	}

	public AddBookPanel() throws SQLException {
		setSize(new Dimension(975, 535));
		setLayout(null);

		readerInfoPanel = new JPanel();
		readerInfoPanel.setLayout(null);
		readerInfoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"\u56FE\u4E66\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		readerInfoPanel.setBounds(10, 10, 345, 464);
		add(readerInfoPanel);

		JLabel label_5 = new JLabel("\u56FE\u4E66\u7F16\u53F7");
		label_5.setBounds(22, 21, 84, 15);
		readerInfoPanel.add(label_5);

		JLabel label_6 = new JLabel("\u56FE\u4E66\u4E66\u540D");
		label_6.setBounds(22, 57, 84, 15);
		readerInfoPanel.add(label_6);

		JLabel label_7 = new JLabel("\u56FE\u4E66\u4F5C\u8005 ");
		label_7.setBounds(22, 93, 106, 15);
		readerInfoPanel.add(label_7);

		JLabel label_8 = new JLabel("\u51FA\u7248\u793E ");
		label_8.setBounds(22, 129, 72, 15);
		readerInfoPanel.add(label_8);

		JLabel label_9 = new JLabel("\u51FA\u7248\u65E5\u671F ");
		label_9.setBounds(22, 165, 72, 15);
		readerInfoPanel.add(label_9);

		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(22, 201, 72, 15);
		readerInfoPanel.add(lblIsbn);

		JLabel label_11 = new JLabel("\u5206\u7C7B\u53F7");
		label_11.setBounds(22, 237, 72, 15);
		readerInfoPanel.add(label_11);

		JLabel label_12 = new JLabel("\u56FE\u4E66\u8BED\u8A00");
		label_12.setBounds(22, 273, 84, 15);
		readerInfoPanel.add(label_12);

		JLabel label_13 = new JLabel("\u56FE\u4E66\u9875\u6570");
		label_13.setBounds(22, 309, 72, 15);
		readerInfoPanel.add(label_13);

		JLabel label_14 = new JLabel("\u56FE\u4E66\u4EF7\u683C");
		label_14.setBounds(22, 345, 72, 15);
		readerInfoPanel.add(label_14);

		JLabel label_15 = new JLabel("\u5165\u9986\u65E5\u671F");
		label_15.setBounds(22, 381, 72, 15);
		readerInfoPanel.add(label_15);

		JLabel label = new JLabel("\u56FE\u4E66\u72B6\u6001");
		label.setBounds(22, 417, 72, 15);
		readerInfoPanel.add(label);

		tfBkCode = new JTextField();
		tfBkCode.setColumns(10);
		tfBkCode.setBounds(87, 18, 236, 18);
		readerInfoPanel.add(tfBkCode);

		tfBkPress = new JTextField();
		tfBkPress.setColumns(10);
		tfBkPress.setBounds(87, 126, 236, 18);
		readerInfoPanel.add(tfBkPress);

		tfBkDatePress = new JTextField();
		tfBkDatePress.setColumns(10);
		tfBkDatePress.setBounds(87, 162, 236, 18);
		readerInfoPanel.add(tfBkDatePress);

		tfBkISBN = new JTextField();
		tfBkISBN.setColumns(10);
		tfBkISBN.setBounds(87, 198, 236, 18);
		readerInfoPanel.add(tfBkISBN);

		tfBkPages = new JTextField();
		tfBkPages.setColumns(10);
		tfBkPages.setBounds(87, 309, 236, 18);
		readerInfoPanel.add(tfBkPages);

		tfBkPrice = new JTextField();
		tfBkPrice.setColumns(10);
		tfBkPrice.setBounds(87, 345, 236, 18);
		readerInfoPanel.add(tfBkPrice);

		tfBkDateIn = new JTextField(getNowDate());
		tfBkDateIn.setColumns(10);
		tfBkDateIn.setBounds(86, 381, 237, 18);
		readerInfoPanel.add(tfBkDateIn);

		tfBkAuthor = new JTextField();
		tfBkAuthor.setColumns(10);
		tfBkAuthor.setBounds(87, 90, 236, 18);
		readerInfoPanel.add(tfBkAuthor);

		tfBkCatalog = new JTextField();
		tfBkCatalog.setColumns(10);
		tfBkCatalog.setBounds(87, 234, 236, 18);
		readerInfoPanel.add(tfBkCatalog);

		tfBkName = new JTextField();
		tfBkName.setColumns(10);
		tfBkName.setBounds(87, 54, 236, 18);
		readerInfoPanel.add(tfBkName);

		cbBkStatus = new JComboBox();
		cbBkStatus.setEnabled(false);
		cbBkStatus.setModel(new DefaultComboBoxModel(
				new String[] { "\u5728\u9986", "\u501F\u51FA", "\u9057\u5931", "\u53D8\u5356", "\u9500\u6BC1" }));
		cbBkStatus.setBounds(87, 417, 236, 21);
		readerInfoPanel.add(cbBkStatus);

		cbBKLanguage = new JComboBox();
		cbBKLanguage.setModel(new DefaultComboBoxModel(new String[] { "     0    -    \u4E2D\u6587",
				"     1    -    \u82F1\u6587", "     2    -    \u65E5\u6587", "     3    -    \u4FC4\u6587",
				"     4    -    \u5FB7\u6587", "     5    -    \u6CD5\u6587" }));
		cbBKLanguage.setBounds(87, 270, 236, 21);
		readerInfoPanel.add(cbBKLanguage);

		JPanel briefPanel = new JPanel();
		briefPanel.setLayout(null);
		briefPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u56FE\u4E66\u7B80\u4ECB",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		briefPanel.setBounds(355, 10, 298, 464);
		add(briefPanel);

		epBkBrief = new JEditorPane();
		epBkBrief.setFont(new Font("宋体", Font.PLAIN, 14));
		epBkBrief.setBounds(10, 22, 285, 432);
		briefPanel.add(epBkBrief);

		JPanel picturePanel = new JPanel();
		picturePanel.setLayout(null);
		picturePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5C01\u9762",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		picturePanel.setBounds(659, 10, 306, 464);
		add(picturePanel);

		lblBkCover = new JLabel("");
		lblBkCover.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblBkCover.setBounds(10, 22, 286, 399);
		picturePanel.add(lblBkCover);

		JButton btnLoadPictureFile = new JButton("\u56FE\u7247\u6587\u4EF6");
		btnLoadPictureFile.setBounds(111, 431, 93, 23);
		btnLoadPictureFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new ImageFilter());
				// 实现文件选取功能并添加选择图片文件类型的过滤器ImageFilter
				int returnVal = fc.showOpenDialog(AddBookPanel.this);
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
		picturePanel.add(btnLoadPictureFile);

		editCtlPanel = new JPanel();
		editCtlPanel.setBounds(0, 484, 950, 41);
		add(editCtlPanel);

		btnAddBook = new JButton("\u65B0\u589E\u56FE\u4E66");
		btnAddBook.setBounds(477, 5, 94, 23);
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfBkCode.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入图书编号");
				} else {
					Book book = getBookFromText();
					BookDAL dal = new BookDAL();
					try {
						dal.add(book);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "新增成功!");
				}
			}
		});

		btnReset = new JButton("\u91CD\u65B0\u8F93\u5165");
		btnReset.setBounds(340, 5, 94, 23);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setNullToText();
			}
		});
		editCtlPanel.setLayout(null);
		editCtlPanel.add(btnReset);
		editCtlPanel.add(btnAddBook);
	}

	public static void main(String[] args) throws SQLException {
		// 用于便捷测试
		JFrame jf = new JFrame("Test");
		jf.setSize(1000, 600);
		AddBookPanel rp = new AddBookPanel();
		jf.getContentPane().add(rp);
		jf.setVisible(true);
	}
}
