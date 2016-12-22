package seven.libraryms.model;

/**
 * @author Seven
 * 读者类型实体类
 */
public class ReaderType extends AbstractModel{


	/**
	 * 读者类别【主键】  
	 * 专科生：0
	 * 本科生：1
	 * 硕士研究生：2
	 * 博士研究生：3
	 * 教师：4
	 */
	private int rdType;
	/**
	 * 读者类别名称【唯一、非空】
	 * 类别：专科生，本科生，硕士研究生，博士研究生，教师
	 */
	private String rdTypeName;
	/**
	 * 可借书数量
	 * 教师最多借书12本
	 * 学生最多借书8本
	 */
	private int canLendQty;
	/**
	 * 可借书天数
	 * 学生借书期限最长为30天
	 * 教师借书期限最长为60天
	 */
	private int canLendDay;
	/**
	 * 可续借的次数，
	 * 学生可以续借1次
	 * 教师可以续借2次
	 */
	private int canContinueTimes;
	/**
	 * 罚款率（元/天）
	 */
	private float punishRate;
	/**
	 * 证书有效期（年）【非空，0表示永久有效】
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
	 * 读者类别【主键】  
	 * 专科生：0
	 * 本科生：1
	 * 硕士研究生：2
	 * 博士研究生：3
	 * 教师：4
	 */
	public int getRdType() {
		return rdType;
	}

	/**
	 * @param rdType
	 * 读者类别【主键】  
	 * 专科生：0
	 * 本科生：1
	 * 硕士研究生：2
	 * 博士研究生：3
	 * 教师：4
	 * 
	 */
	public void setRdType(int rdType) {
		this.rdType = rdType;
	}

	/**
	 * @return
	 * 读者类别名称【唯一、非空】
	 * 类别：专科生，本科生，硕士研究生，博士研究生，教师
	 */
	public String getRdTypeName() {
		return rdTypeName;
	}

	/**
	 * @param rdTypeName
	 * 读者类别名称【唯一、非空】
	 * 类别：专科生，本科生，硕士研究生，博士研究生，教师
	 * 
	 */
	public void setRdTypeName(String rdTypeName) {
		this.rdTypeName = rdTypeName;
	}

	/**
	 * @return/
	 * 可借书数量
	 * 教师最多借书12本
	 * 学生最多借书8本
	 */
	public int getCanLendQty() {
		return canLendQty;
	}

	/**
	 * @param canLendQty
	 * 可借书数量
	 * 教师最多借书12本
	 * 学生最多借书8本
	 */
	public void setCanLendQty(int canLendQty) {
		this.canLendQty = canLendQty;
	}

	/**
	 * @return
	 * 可借书天数
	 * 学生借书期限最长为30天
	 * 教师借书期限最长为60天
	 */
	public int getCanLendDay() {
		return canLendDay;
	}

	/**
	 * @param canLendDay
	 * 
	 * 可借书天数
	 * 学生借书期限最长为30天
	 * 教师借书期限最长为60天
	 */
	public void setCanLendDay(int canLendDay) {
		this.canLendDay = canLendDay;
	}

	/**
	 * @return
	 * 可续借的次数，
	 * 学生可以续借1次
	 * 教师可以续借2次
	 */
	public int getCanContinueTimes() {
		return canContinueTimes;
	}

	/**
	 * @param canContinueTimes
	 * 可续借的次数，
	 * 学生可以续借1次
	 * 教师可以续借2次
	 */
	public void setCanContinueTimes(int canContinueTimes) {
		this.canContinueTimes = canContinueTimes;
	}

	/**
	 * @return
	 * 罚款率（元/天）
	 */
	public float getPunishRate() {
		return punishRate;
	}

	/**
	 * @param punishRate
	 * 罚款率（元/天）
	 */
	public void setPunishRate(float punishRate) {
		this.punishRate = punishRate;
	}

	/**
	 * @return
	 * 证书有效期（年）【非空，0表示永久有效】
	 */
	public int getDateValid() {
		return dateValid;
	}

	/**
	 * @param dateValid
	 * 证书有效期（年）【非空，0表示永久有效】
	 */
	public void setDateValid(int dateValid) {
		this.dateValid = dateValid;
	}
	
}
