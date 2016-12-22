package seven.libraryms.model;

/**
 * @author Seven
 *	@Data 2016-12-12
 * @version 1.00
 */
public class Borrow extends AbstractModel {
	
	/**Numeric(12,0)	借书顺序号【主键】*/
	private String BorrowID; 
	/** Int	读者序号【外键TB_Reader】*/
	private int rdID;
	/** Int	图书序号【外键TB_Book】*/
	private int bkID;
	/** Int	续借次数（第一次借时，记为0） */
	private int ldContinueTimes;
	/** DateTime	借书日期 */
	private String ldDateOut;
	/** DateTime	应还日期*/
	private String ldDateRetPlan;
	/**DateTime	实际还书日期*/
	private String ldDateRetAct;
	/**Int	超期天数*/
	private int ldOverDay;
	/** Money	超期金额（应罚款金额）*/
	private float ldOverMoney;
	/** Money	罚款金额*/
	private float ldPunishMoney;
	/** Bit	是否已经还书，缺省为0-未还*/
	private boolean lsHasReturn;
	/** Nvarchar(20)	借书操作员*/
	private String OperatorLend;
	/** Nvarchar(20)	还书操作员*/
	private String OperatorRet;
	
	public Borrow(){}
	public Borrow(Borrow bo){
		setBorrowID(bo.getBorrowID());
		setRdID(bo.getRdID());
		setBkID(bo.getBkID());
		setLdContinueTimes(bo.getLdContinueTimes());
		setLdDateOut(bo.getLdDateOut());
		setLdDateRetPlan(bo.getLdDateRetPlan());
		setLdDateRetAct(bo.getLdDateRetAct());
		setLdOverDay(bo.getLdOverDay());
		setLdOverMoney(bo.getLdOverMoney());
		setLdPunishMoney(bo.getLdPunishMoney());
		setIsHasReturn(bo.isLsHasReturn());
		setOperatorLend(bo.getOperatorLend());
		setOperatorRet(bo.getOperatorRet());
	}
	public Borrow(String borrowID, int rdID, int bkID, int ldContinueTimes, String ldDateOut, String ldDateRetPlan,
			String ldDateRetAct, int ldOverDay, float ldOverMoney, float ldPunishMoney, boolean lsHasReturn,
			String operatorLend, String operatorRet) {
		super();
		BorrowID = borrowID;
		this.rdID = rdID;
		this.bkID = bkID;
		this.ldContinueTimes = ldContinueTimes;
		this.ldDateOut = ldDateOut;
		this.ldDateRetPlan = ldDateRetPlan;
		this.ldDateRetAct = ldDateRetAct;
		this.ldOverDay = ldOverDay;
		this.ldOverMoney = ldOverMoney;
		this.ldPunishMoney = ldPunishMoney;
		this.lsHasReturn = lsHasReturn;
		OperatorLend = operatorLend;
		OperatorRet = operatorRet;
	}
	
	public String getBorrowID() {
		return BorrowID;
	}
	public void setBorrowID(String borrowID) {
		BorrowID = borrowID;
	}
	public int getRdID() {
		return rdID;
	}
	public void setRdID(int rdID) {
		this.rdID = rdID;
	}
	public int getBkID() {
		return bkID;
	}
	public void setBkID(int bkID) {
		this.bkID = bkID;
	}
	public int getLdContinueTimes() {
		return ldContinueTimes;
	}
	public void setLdContinueTimes(int ldContinueTimes) {
		this.ldContinueTimes = ldContinueTimes;
	}
	public String getLdDateOut() {
		return ldDateOut;
	}
	public void setLdDateOut(String ldDateOut) {
		this.ldDateOut = ldDateOut;
	}
	public String getLdDateRetPlan() {
		return ldDateRetPlan;
	}
	public void setLdDateRetPlan(String ldDateRetPlan) {
		this.ldDateRetPlan = ldDateRetPlan;
	}
	public String getLdDateRetAct() {
		return ldDateRetAct;
	}
	public void setLdDateRetAct(String ldDateRetAct) {
		this.ldDateRetAct = ldDateRetAct;
	}
	public int getLdOverDay() {
		return ldOverDay;
	}
	public void setLdOverDay(int ldOverDay) {
		this.ldOverDay = ldOverDay;
	}
	public float getLdOverMoney() {
		return ldOverMoney;
	}
	public void setLdOverMoney(float ldOverMoney) {
		this.ldOverMoney = ldOverMoney;
	}
	public float getLdPunishMoney() {
		return ldPunishMoney;
	}
	public void setLdPunishMoney(float ldPunishMoney) {
		this.ldPunishMoney = ldPunishMoney;
	}
	public boolean isLsHasReturn() {
		return lsHasReturn;
	}
	public void setIsHasReturn(boolean lsHasReturn) {
		this.lsHasReturn = lsHasReturn;
	}
	public String getOperatorLend() {
		return OperatorLend;
	}
	public void setOperatorLend(String operatorLend) {
		OperatorLend = operatorLend;
	}
	public String getOperatorRet() {
		return OperatorRet;
	}
	public void setOperatorRet(String operatorRet) {
		OperatorRet = operatorRet;
	}
}
