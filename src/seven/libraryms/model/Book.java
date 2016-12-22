package seven.libraryms.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import seven.libraryms.dal.BookDAL;

/**
 * @author Seven
 * 	@Data 2016-12-12
 * @version 1.00
 *
 */
public class Book extends AbstractModel{
	/** ͼ����š���ʶ�У�������*/
	private int bkID;
	/** ͼ���Ż�����ţ�ǰ���е���ţ�*/
	private String bkCode;
	/**���� */
	private String bkName;
	/**����*/
	private String bkAuthor;
	/** ������*/
	private String bkPress;
	/** datetime	��������*/
	private String bkDatePress;
	/**Nvarchar (15)	ISBN���*/
	private String bkISBN;
	/** Nvarchar(30)	����ţ��磺TP316-21/123��*/
	private String bkCatalog;
	/**SmallInt	���ԣ�0-���ģ�1-Ӣ�ģ�2-���ģ�3-���ģ�4-���ģ�5-����*/
	private int bkLanguage;
	/** Int	ҳ��*/
	private int bkPages;
	/** Money	�۸�*/
	private float bkPrice;
	/** DateTime	�������*/
	private String bkDateIn;
	/** Text	���ݼ��*/
	private String bkBrief;
	/** image	ͼ�������Ƭ*/
	private byte[] bkCover;
	/** NChar(2)	ͼ��״̬���ڹݡ��������ʧ������������*/
	private String bkStatus;
	
	
	public Book(Book bo) throws FileNotFoundException{
		setBkID(bo.getBkID());
		setBkCode(bo.getBkCode());
		setBkName(bo.getBkName());
		setBkAuthor(bo.getBkAuthor());
		setBkPress(bo.getBkPress());
		setBkDatePress(bo.getBkDatePress());
		setBkISBN(bo.getBkISBN());
		setBkCatalog(bo.getBkCatalog());
		setBkLanguage(bo.getBkLanguage());
		setBkPages(bo.getBkPages());
		setBkPrice(bo.getBkPrice());
		setBkDateIn(bo.getBkDateIn());
		setBkBrief(bo.getBkBrief());
		setBkCover(bo.getBkCoverByte());
		setBkStatus(bo.getBkStatus());	
	}
	
	public Book(int bkID, String bkCode, String bkName, String bkAuthor, String bkPress, String bkDatePress,
			String bkISBN, String bkCatalog, int bkLanguage, int bkPages, float bkPrice, String bkDateIn, String bkBrief,
			byte[] bkCover, String bkStatus) {
		super();
		this.bkID = bkID;
		this.bkCode = bkCode;
		this.bkName = bkName;
		this.bkAuthor = bkAuthor;
		this.bkPress = bkPress;
		this.bkDatePress = bkDatePress;
		this.bkISBN = bkISBN;
		this.bkCatalog = bkCatalog;
		this.bkLanguage = bkLanguage;
		this.bkPages = bkPages;
		this.bkPrice = bkPrice;
		this.bkDateIn = bkDateIn;
		this.bkBrief = bkBrief;
		this.bkCover = bkCover;
		this.bkStatus = bkStatus;
	}
	public Book(){
	}
	public int getBkID() {
		return bkID;
	}
	public void setBkID(int bkID) {
		this.bkID = bkID;
	}
	public String getBkCode() {
		return bkCode;
	}
	public void setBkCode(String bkCode) {
		this.bkCode = bkCode;
	}
	public String getBkName() {
		return bkName;
	}
	public void setBkName(String bkName) {
		this.bkName = bkName;
	}
	public String getBkAuthor() {
		return bkAuthor;
	}
	public void setBkAuthor(String bkAuthor) {
		this.bkAuthor = bkAuthor;
	}
	public String getBkPress() {
		return bkPress;
	}
	public void setBkPress(String bkPress) {
		this.bkPress = bkPress;
	}
	public String getBkDatePress() {
		return bkDatePress;
	}
	public void setBkDatePress(String bkDatePress) {
		this.bkDatePress = bkDatePress;
	}
	public String getBkISBN() {
		return bkISBN;
	}
	public void setBkISBN(String bkISBN) {
		this.bkISBN = bkISBN;
	}
	public String getBkCatalog() {
		return bkCatalog;
	}
	public void setBkCatalog(String bkCatalog) {
		this.bkCatalog = bkCatalog;
	}
	public int getBkLanguage() {
		return bkLanguage;
	}
	public void setBkLanguage(int bkLanguage) {
		this.bkLanguage = bkLanguage;
	}
	public int getBkPages() {
		return bkPages;
	}
	public void setBkPages(int bkPages) {
		this.bkPages = bkPages;
	}
	public float getBkPrice() {
		return bkPrice;
	}
	public void setBkPrice(float bkPrice) {
		this.bkPrice = bkPrice;
	}
	public String getBkDateIn() {
		return bkDateIn;
	}
	public void setBkDateIn(String bkDateIn) {
		this.bkDateIn = bkDateIn;
	}
	public String getBkBrief() {
		return bkBrief;
	}
	public void setBkBrief(String bkBrief) {
		this.bkBrief = bkBrief;
	}
	
	public Image getBkCover() {
		if (this.bkCover == null) {
			return null;
		}
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		String filePath = "src/seven/libraryms/model/image";
		String fileName = this.getBkName();
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {// �ж��ļ�Ŀ¼�Ƿ����
				dir.mkdirs();
			}
			file = new File(filePath + "\\" + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(this.bkCover);
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
	public byte[] getBkCoverByte() {
		return this.bkCover;
	}
	public void setBkCover(byte[] bkCover) {
		this.bkCover = bkCover;
	}
	public void setBkCover(Image image) {
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
		this.bkCover = os.toByteArray();
	}
	public String getBkStatus() {
		return bkStatus;
	}
	public void setBkStatus(String bkStatus) {
		this.bkStatus = bkStatus;
	}
}
