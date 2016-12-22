package seven.libraryms.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import seven.libraryms.bll.ReaderAdmin;
import seven.libraryms.model.Reader;

/**
 * ��¼����,�������
 * @author Seven
 * 	@Data 216-12-12
 * @version 1.1
 */
public class Login extends JFrame {
	
	public JPasswordField pwdField;
	public JTextField tfUserName;
	public JLabel labelLoginInfo;
	public JButton btnLogin;
	public JButton btnClose;
		
	/** ��½����*/
	private int loginTimes = 0;
	/** ��½�û���Ϣ����������������.static:�ڴ���رպ���Ҫ����ڲ��ɼ� */
	public static Reader reader = null;
	
	private ReaderAdmin readerBLL = new ReaderAdmin();
	
	public Login() {
		setSize(new Dimension(350, 250));
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("\u957F\u6C5F\u5927\u5B66\u56FE\u4E66\u9986\u7BA1\u7406\u4FE1\u606F\u7CFB\u7EDF");
		getContentPane().setLayout(null);
		
		btnLogin = new JButton("\u767B\u9646");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				++loginTimes;
				String errorMsg= "";
				int rdID=-1;
				try{
					//����û���ID
					rdID = Integer.valueOf(tfUserName.getText().trim());
				}catch(NumberFormatException e){
					//����������Ϣ
					errorMsg = "�û�����Ч";
					tfUserName.requestFocus();
					}
				if(rdID != -1){
					//ȡ�õ�¼�û�
					reader = readerBLL.getReader(rdID);
					if(reader == null){
						errorMsg = "�û�����Ч";
						tfUserName.requestFocus();
					}else if(reader.getReaderPassword().equals(
							new String(pwdField.getPassword()).trim())){
						try {
							//��֤�ɹ�,��������
							loadMainGUI();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						errorMsg = "��������";
						pwdField.requestFocus();
					}
				}
				if(errorMsg.length() >0)
					labelLoginInfo.setText(errorMsg);
			}
		});
		btnLogin.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnLogin.setBounds(70, 133, 93, 23);
		getContentPane().add(btnLogin);
		
		btnClose = new JButton("\u9000\u51FA");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();//�رյ�ǰ����
			}
		});
		btnClose.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnClose.setBounds(184, 133, 93, 23);
		getContentPane().add(btnClose);
		
		labelLoginInfo = new JLabel("");
		labelLoginInfo.setForeground(Color.RED);
		labelLoginInfo.setBounds(239, 192, 93, 28);
		getContentPane().add(labelLoginInfo);
		
		pwdField = new JPasswordField();
		pwdField.setBounds(175, 92, 98, 21);
		getContentPane().add(pwdField);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(175, 54, 98, 21);
		getContentPane().add(tfUserName);
		tfUserName.setColumns(10);
		
		JLabel labelUserName = new JLabel("\u7528\u6237\u7F16\u53F7 \uFF1A");
		labelUserName.setBounds(86, 58, 75, 15);
		getContentPane().add(labelUserName);
		
		JLabel labelPassword = new JLabel("\u7528\u6237\u5BC6\u7801 \uFF1A");
		labelPassword.setBounds(86, 93, 75, 15);
		getContentPane().add(labelPassword);
	}
	
	protected void loadMainGUI() throws SQLException {
		this.dispose();
		Main mainGUI = new Main();
		mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGUI.setLocationRelativeTo(null);
		mainGUI.setVisible(true);
	}
	public static void main(String[] args) {
		Login login = new Login();
		login.start();
	}
	public void start(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
