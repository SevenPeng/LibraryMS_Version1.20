package seven.libraryms.model;

/**
 * @author Seven
 * ��������ʵ����
 */
public class ReaderType extends AbstractModel{


	/**
	 * �������������  
	 * ר������0
	 * ��������1
	 * ˶ʿ�о�����2
	 * ��ʿ�о�����3
	 * ��ʦ��4
	 */
	private int rdType;
	/**
	 * ����������ơ�Ψһ���ǿա�
	 * ���ר��������������˶ʿ�о�������ʿ�о�������ʦ
	 */
	private String rdTypeName;
	/**
	 * �ɽ�������
	 * ��ʦ������12��
	 * ѧ��������8��
	 */
	private int canLendQty;
	/**
	 * �ɽ�������
	 * ѧ�����������Ϊ30��
	 * ��ʦ���������Ϊ60��
	 */
	private int canLendDay;
	/**
	 * ������Ĵ�����
	 * ѧ����������1��
	 * ��ʦ��������2��
	 */
	private int canContinueTimes;
	/**
	 * �����ʣ�Ԫ/�죩
	 */
	private float punishRate;
	/**
	 * ֤����Ч�ڣ��꣩���ǿգ�0��ʾ������Ч��
	 */
	private int dateValid;
	
	public ReaderType(){}
	
	public ReaderType(int rdType, String rdTypeName, int canLendQty, int canLendDay, int canContinueTimes,
			float punishRate, int dateValid) {
		super();
		this.rdType = rdType;
		this.rdTypeName = rdTypeName;
		this.canLendQty = canLendQty;
		this.canLendDay = canLendDay;
		this.canContinueTimes = canContinueTimes;
		this.punishRate = punishRate;
		this.dateValid = dateValid;
	}
	
	public ReaderType(ReaderType rt){
		setRdType(rt.getRdType());
		setRdTypeName(rt.getRdTypeName());
		setCanLendQty(rt.getCanLendQty());
		setCanLendDay(rt.getCanLendDay());
		setCanContinueTimes(rt.getCanContinueTimes());
		setPunishRate(rt.getPunishRate());
		setDateValid(rt.getDateValid());
	}
	
	
	
	public ReaderType(Integer rdType) {
		// TODO Auto-generated constructor stub
		this.rdType = rdType;
		
	}

	/**
	 * @return
	 * �������������  
	 * ר������0
	 * ��������1
	 * ˶ʿ�о�����2
	 * ��ʿ�о�����3
	 * ��ʦ��4
	 */
	public int getRdType() {
		return rdType;
	}

	/**
	 * @param rdType
	 * �������������  
	 * ר������0
	 * ��������1
	 * ˶ʿ�о�����2
	 * ��ʿ�о�����3
	 * ��ʦ��4
	 * 
	 */
	public void setRdType(int rdType) {
		this.rdType = rdType;
	}

	/**
	 * @return
	 * ����������ơ�Ψһ���ǿա�
	 * ���ר��������������˶ʿ�о�������ʿ�о�������ʦ
	 */
	public String getRdTypeName() {
		return rdTypeName;
	}

	/**
	 * @param rdTypeName
	 * ����������ơ�Ψһ���ǿա�
	 * ���ר��������������˶ʿ�о�������ʿ�о�������ʦ
	 * 
	 */
	public void setRdTypeName(String rdTypeName) {
		this.rdTypeName = rdTypeName;
	}

	/**
	 * @return/
	 * �ɽ�������
	 * ��ʦ������12��
	 * ѧ��������8��
	 */
	public int getCanLendQty() {
		return canLendQty;
	}

	/**
	 * @param canLendQty
	 * �ɽ�������
	 * ��ʦ������12��
	 * ѧ��������8��
	 */
	public void setCanLendQty(int canLendQty) {
		this.canLendQty = canLendQty;
	}

	/**
	 * @return
	 * �ɽ�������
	 * ѧ�����������Ϊ30��
	 * ��ʦ���������Ϊ60��
	 */
	public int getCanLendDay() {
		return canLendDay;
	}

	/**
	 * @param canLendDay
	 * 
	 * �ɽ�������
	 * ѧ�����������Ϊ30��
	 * ��ʦ���������Ϊ60��
	 */
	public void setCanLendDay(int canLendDay) {
		this.canLendDay = canLendDay;
	}

	/**
	 * @return
	 * ������Ĵ�����
	 * ѧ����������1��
	 * ��ʦ��������2��
	 */
	public int getCanContinueTimes() {
		return canContinueTimes;
	}

	/**
	 * @param canContinueTimes
	 * ������Ĵ�����
	 * ѧ����������1��
	 * ��ʦ��������2��
	 */
	public void setCanContinueTimes(int canContinueTimes) {
		this.canContinueTimes = canContinueTimes;
	}

	/**
	 * @return
	 * �����ʣ�Ԫ/�죩
	 */
	public float getPunishRate() {
		return punishRate;
	}

	/**
	 * @param punishRate
	 * �����ʣ�Ԫ/�죩
	 */
	public void setPunishRate(float punishRate) {
		this.punishRate = punishRate;
	}

	/**
	 * @return
	 * ֤����Ч�ڣ��꣩���ǿգ�0��ʾ������Ч��
	 */
	public int getDateValid() {
		return dateValid;
	}

	/**
	 * @param dateValid
	 * ֤����Ч�ڣ��꣩���ǿգ�0��ʾ������Ч��
	 */
	public void setDateValid(int dateValid) {
		this.dateValid = dateValid;
	}
	
}
