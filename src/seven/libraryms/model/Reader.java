package seven.libraryms.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import seven.libraryms.dal.ReaderDAL;

/**
 * @author Seven
 * @Data 2016-12-12
 * @version 1.00
 */
public class Reader extends AbstractModel {

	/** Int ���߱��/����֤�š������� */
	private int rdID;
	/** nvarchar(20) �������� */
	private String rdName;
	/** nchar(1) �Ա���/Ů */
	private String rdSex;
	/** SmallInt ����������TB_ReaderType�����ǿա� */
	private int rdType;
	/** nvarchar (20) ��λ����/��λ���� */
	private String rdDept;
	/** nvarchar(25) �绰���� */
	private String rdPhone;
	/** nvarchar(25) �������� */
	private String rdEmail;
	/** datetime ���ߵǼ�����/��֤���� */
	private String rdDateReg;
	/** image ������Ƭ */
	private byte[] rdPhoto;
	/** nchar(2) ֤��״̬��3������Ч����ʧ��ע�� */
	private String rdStatus;
	/** c �ѽ�������(ȱʡֵ0) */
	private int rdBorrowQty;
	/** nvarchar (20) ��������(��ֵ123)���ɼ��ܴ洢 */
	private String rdPwd;
	/** SmallInt �����ɫ��0-���ߡ�1-����֤����2-ͼ�����4-���Ĺ���8-ϵͳ��������� */
	private int rdAdminRoles;

	public Reader() {
	}

	public Reader(Reader re) throws FileNotFoundException {
		setRdID(re.getRdID());
		setRdName(re.getRdName());
		setRdSex(re.getRdSex());
		setRdType(re.getRdType());
		setRdDept(re.getRdDept());
		setRdPhone(re.getRdPhone());
		setRdEmail(re.getRdEmail());
		setRdDateReg(re.getRdDateReg());
		setRdPhoto(re.getRdPhoto());
		setRdStatus(re.getRdStatus());
		setRdBorrowQty(re.getRdBorrowQty());
		setRdPwd(re.getRdPwd());
		setRdAdminRoles(re.getRdAdminRoles());
	}

	public Reader(int rdID) {
		this.rdID = rdID;
	}

	public Reader(int rdID, String rdName, String rdSex, int rdType, String rdDept, String rdPhone, String rdEmail,
			String rdDateReg, byte[] rdPhoto, String rdStatus, int rdBorrowQty, String rdPwd, int rdAdminRoles) {
		super();
		this.rdID = rdID;
		this.rdName = rdName;
		this.rdSex = rdSex;
		this.rdType = rdType;
		this.rdDept = rdDept;
		this.rdPhone = rdPhone;
		this.rdEmail = rdEmail;
		this.rdDateReg = rdDateReg;
		this.rdPhoto = rdPhoto;
		this.rdStatus = rdStatus;
		this.rdBorrowQty = rdBorrowQty;
		this.rdPwd = rdPwd;
		this.rdAdminRoles = rdAdminRoles;
	}

	public int getRdID() {
		return rdID;
	}

	public void setRdID(int rdID) {
		this.rdID = rdID;
	}

	public String getRdName() {
		return rdName;
	}

	public void setRdName(String rdName) {
		this.rdName = rdName;
	}

	public String getRdSex() {
		return rdSex;
	}

	public void setRdSex(String rdSex) {
		this.rdSex = rdSex;
	}

	public int getRdType() {
		return rdType;
	}

	public void setRdType(int rdType) {
		this.rdType = rdType;
	}

	public String getRdDept() {
		return rdDept;
	}

	public void setRdDept(String rdDept) {
		this.rdDept = rdDept;
	}

	public String getRdPhone() {
		return rdPhone;
	}

	public void setRdPhone(String string) {
		this.rdPhone = string;
	}

	public String getRdEmail() {
		return rdEmail;
	}

	public void setRdEmail(String rdEmail) {
		this.rdEmail = rdEmail;
	}

	public String getRdDateReg() {
		return rdDateReg;
	}

	public void setRdDateReg(String rdDateReg) {
		this.rdDateReg = rdDateReg;
	}

	/**
	 * Image���ͷ���ֱ������ͷ��
	 */
	public Image getRdPhoto() {
		if (this.rdPhoto == null) {
			return null;
		}
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		// ͷ�񱣴�·��
		String filePath = "src/seven/libraryms/model/image";
		// ͷ���ļ���
		String fileName = this.getRdName();
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {// �ж��ļ�Ŀ¼�Ƿ����
				dir.mkdirs();
			}
			file = new File(filePath + "\\" + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(this.rdPhoto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		Image image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	public void setRdPhoto(Image image) {
		BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2 = bi.createGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ImageIO.write(bi, "jpg", os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.rdPhoto = os.toByteArray();
	}
	public byte[] getRdPhotoByte() {
		return this.rdPhoto;
	}
	public void setRdPhoto(byte[] rdPhoto) {
		this.rdPhoto = rdPhoto;
	}

	public String getRdStatus() {
		return rdStatus;
	}

	public void setRdStatus(String rdStatus) {
		this.rdStatus = rdStatus;
	}

	public int getRdBorrowQty() {
		return rdBorrowQty;
	}

	public void setRdBorrowQty(int rdBorrowQty) {
		this.rdBorrowQty = rdBorrowQty;
	}

	public String getRdPwd() {
		return rdPwd;
	}

	public void setRdPwd(String rdPwd) {
		this.rdPwd = rdPwd;
	}

	public int getRdAdminRoles() {
		return rdAdminRoles;
	}

	public void setRdAdminRoles(int rdAdminRoles) {
		this.rdAdminRoles = rdAdminRoles;
	}

	public boolean isReaderAdmin() {
		return (this.rdAdminRoles & 1) > 0;
	}

	public boolean isBookAdmin() {
		return (this.rdAdminRoles & 2) > 0;
	}

	public boolean isBorrowAdmin() {
		return (this.rdAdminRoles & 4) > 0;
	}

	public boolean isSysAdmin() {
		return (this.rdAdminRoles & 8) > 0;
	}

	public String getReaderPassword() {
		// TODO Auto-generated method stub
		return getRdPwd();
	}

	public static void main(String[] args) {
		Reader r = new Reader();
		int a = 2014020;
		r.setRdID(a);
		byte[] buffer = null;
		try {
			File file = new File("C:/Users/Seven/Desktop/a.jpg");
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		r.setRdName("����");
		ReaderDAL rd = new ReaderDAL();
		try {
			rd.add(r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
