package seven.libraryms.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * 主界面
 * 
 * @author Seven
 * 	@Data 216-12-12
 * @version 1.1
 */
public class Main extends JFrame {
	private final String homePanelName = "Home";
	private final String readerPanelName = "Reader";
	private final String addBookPanelName = "AddBook";
	private final String searchBookPanelName = "SearchBook";
	private final String borrowPanelName = "Borrow";
	private final String userPanelName = "User";
	private final String readerTypePanelName = "ReaderType";
	
	private JPanel cards;
	private HomePanel homePanel;
	private AddBookPanel addBookPanel;
	private SearchBookPanel searchBookPanel;
	private BorrowPanel borrowPanel;
	private UserPanel userPanel;
	private ReaderPanel readerPanel;
	private ReaderTypePanel readerTypePanel;
	// 以上通过菜单打开的面板

	private JMenuBar menuBar_1;
	private JMenu MN_ReaderMgt;
	private JMenuItem MI_NewReader;
	private JMenuItem MI_ReaderTypeMgt;
	private JMenu MN_BookMgt;
	private JMenuItem MI_NewBook;
	private JMenu MN_BorrowMgt;
	private JMenuItem MI_Borror;
	private JMenu MN_UserMgt;
	private JMenuItem MI_UpdatePassword;
	
	
	public static JPanel searchResultPanel;
	public static JPanel functionCtrlPanel;
	public static JPanel editCtrlPanel;
	public static JPanel readerInfoPanel;
	public static JPanel searchPanel;
	private JMenuItem MI_SearchBook;

	public Main() throws SQLException {
		getContentPane().setForeground(Color.CYAN);
		setSize(new Dimension(1000, 600));
		setBackground(UIManager.getColor("ProgressBar.background"));
		setTitle("\u957F\u6C5F\u5927\u5B66\u56FE\u4E66\u9986\u7BA1\u7406\u4FE1\u606F\u7CFB\u7EDF");
		getContentPane().setLayout(null);

		menuBar_1 = new JMenuBar();
		menuBar_1.setBackground(Color.GRAY);
		menuBar_1.setBounds(0, 0, 984, 21);
		getContentPane().add(menuBar_1);

		MN_ReaderMgt = new JMenu("\u8BFB\u8005\u7BA1\u7406");
		MN_ReaderMgt.setBackground(new Color(176, 224, 230));
		menuBar_1.add(MN_ReaderMgt);

		MI_NewReader = new JMenuItem("\u501F\u4E66\u8BC1\u7BA1\u7406");
		MI_NewReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout) (cards.getLayout());
				c1.show(cards, readerPanelName);
			}
		});
		MN_ReaderMgt.add(MI_NewReader);

		MI_ReaderTypeMgt = new JMenuItem("\u8BFB\u8005\u7C7B\u578B\u7BA1\u7406");
		MI_ReaderTypeMgt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout) (cards.getLayout());
				c1.show(cards, readerTypePanelName);

			}
		});
		MN_ReaderMgt.add(MI_ReaderTypeMgt);

		MN_BookMgt = new JMenu("\u56FE\u4E66\u7BA1\u7406");
		menuBar_1.add(MN_BookMgt);

		MI_NewBook = new JMenuItem("\u65B0\u4E66\u5165\u5E93");
		MI_NewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout) (cards.getLayout());
				c1.show(cards, addBookPanelName);
			}
		});
		MN_BookMgt.add(MI_NewBook);
		
		MI_SearchBook = new JMenuItem("\u7EF4\u62A4\u4E66\u7C4D");
		MI_SearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout) (cards.getLayout());
				c1.show(cards, searchBookPanelName);
			}
		});
		MN_BookMgt.add(MI_SearchBook);

		MN_BorrowMgt = new JMenu("\u501F\u9605\u7BA1\u7406");
		menuBar_1.add(MN_BorrowMgt);

		MI_Borror = new JMenuItem("\u501F\u9605\u7BA1\u7406");
		MI_Borror.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout) (cards.getLayout());
				c1.show(cards, borrowPanelName);
			}
		});
		MN_BorrowMgt.add(MI_Borror);

//		MN_UserMgt = new JMenu("\u7528\u6237\u7BA1\u7406");
//		menuBar_1.add(MN_UserMgt);
//
//		MI_UpdatePassword = new JMenuItem("\u7528\u6237\u7BA1\u7406");
//		MN_UserMgt.add(MI_UpdatePassword);

		initMenu();
		initCardPanel();

	}


	/**
	 * 跟据读者类型 设置选项菜单的可用性
	 */
	private void initMenu() {
		MN_ReaderMgt.setEnabled(Login.reader.isReaderAdmin());
		MN_BookMgt.setEnabled(Login.reader.isBookAdmin());
		MN_BorrowMgt.setEnabled(Login.reader.isBorrowAdmin());
		MN_UserMgt.setEnabled(Login.reader.isSysAdmin());

	}

	/**
	 * 添加各个卡片面板
	 * @throws SQLException
	 */
	private void initCardPanel() throws SQLException {
		homePanel = new HomePanel();
		homePanel.setVisible(true);
		
		readerPanel = new ReaderPanel();
		readerPanel.setVisible(false);

		addBookPanel = new AddBookPanel();
		addBookPanel.setVisible(false);
		
		searchBookPanel = new SearchBookPanel();
		searchBookPanel.setVisible(false);

		borrowPanel = new BorrowPanel();
		borrowPanel.setVisible(false);

		userPanel = new UserPanel();
		userPanel.setVisible(false);

		readerTypePanel = new ReaderTypePanel();
		readerPanel.setVisible(false);
		
		//加入的主卡片
		cards = new JPanel(new CardLayout());
		cards.setLocation(5, 22);
		cards.setSize(975, 535);
		
		//添加卡片面板
		cards.add(homePanel, homePanelName);
		cards.add(readerPanel, readerPanelName);
		cards.add(addBookPanel, addBookPanelName);
		cards.add(searchBookPanel, searchBookPanelName);
		cards.add(borrowPanel, borrowPanelName);
		cards.add(userPanel, userPanelName);
		cards.add(readerTypePanel, readerTypePanelName);
		getContentPane().add(cards);

	}
}
