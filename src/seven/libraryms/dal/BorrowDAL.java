package seven.libraryms.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import seven.libraryms.model.AbstractModel;
import seven.libraryms.model.Borrow;
import seven.libraryms.model.Reader;

/**
 *  Copyright (C), 2016-2020, Seven FileName: BookDAL.java
 *  
 * 添加表TB_Borrow的插入add()、删除delete()、修改update()等3个方法；
 * 不必实现getObjectByID()方法，该表的查询一般是通过书号或读者号（
 * BorrowDAL类也需要提供Borrow实体类信息到列表数据记录的转换，
 * 即getPrettyColumnNames()和getMethodNames()方法。
 * @author Seven
 * @Data 2016-12-12
 * @version 1.00
 */

public class BorrowDAL extends AbstractDAL {
	
	/**用于指定各字段在JTable标题栏中的显示文本*/
	private String[] dispColNames = new String[]{
			"图书序号",
			"续借次数",
			"借书日期",
			"应还日期",
			"超期天数",
			"超期金额",
			"罚款金额",
			
	};
	
	/** 指定字段在Reader实体类中的获取方法*/
	private String[] methodNames = new String[]{
			"getBkID",
			"getLdContinueTimes",
			"getLdDateOut",
			"getLdDateRetPlan",
			"getLdOverDay",
			"getLdOverMoney",
			"getLdPunishMoney"
	};
	@Override
	public int add(AbstractModel object) throws Exception {
		if(object instanceof Borrow ==false){
			throw new Exception("Can only handle Borrow!");
		}
		//只能插入Borrow类 的数据
		
		Borrow bw = (Borrow) object;
		//将参数转换为Borrow类型
		
		String sql = "insert into TB_Borrow ("
										+ "BorrowID,"
										+ "rdID,"
										+ "bkID,"
										+ "ldContinueTimes,"
										+ "ldDateOut,"
										+ "ldDateRetPlan,"
										+ "ldDateRetAct,"
										+ "ldOverDay,"
										+ "ldOverMoney,"
										+ "ldPunishMoney,"
										+ "lsHasReturn,"
										+ "OperatorLend,"
										+ "OperatorRet)"
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		//SQL插入语句
		
		Object[] params = new Object[13];
		params[0] = bw.getBorrowID();
		params[1] = bw.getRdID();
		params[2] = bw.getBkID();
		params[3] = bw.getLdContinueTimes();
		params[4] = bw.getLdDateOut();
		params[5] = bw.getLdDateRetPlan();
		params[6] = bw.getLdDateRetAct();
		params[7] = bw.getLdOverDay();
		params[8] = bw.getLdOverMoney();
		params[9] = bw.getLdPunishMoney();
		params[10] = bw.isLsHasReturn();
		params[11] = bw.getOperatorLend();
		params[12] = bw.getOperatorRet();
		//得到每个需要插入的数据
		
		return SQLHelper.ExecSql(sql,params);
		
	}

	@Override
	public int delete(AbstractModel object) throws Exception {
		if(object instanceof Borrow ==false){
			throw new Exception("Can only handle Borrow!");			
		}
		Borrow bw = (Borrow)object;
		String sql="delete from TB_Borrow where BorrowID = ? and rdID = ?";
		
		Object[] params  = new Object[]	{bw.getBorrowID(),bw.getRdID()};
		int rows = SQLHelper.ExecSql(sql,params);
		return rows;
	}

	@Override
	public int update(AbstractModel object) throws Exception {
		if(object instanceof Borrow ==false){
			throw new Exception("Only can handle Borrow!");
		}
		Borrow bw = (Borrow)object;
		
		String sql = "update TB_Borrow set "
				+ "bkID =?,"
				+ "ldContinueTimes=?,"
				+ "ldDateOut=?,"
				+ "ldDateRetPlan=?,"
				+ "ldDateRetAct=?,"
				+ "ldOverDay=?,"
				+ "ldOverMoney=?,"
				+ "ldPunishMoney=?,"
				+ "lsHasReturn=?,"
				+ "OperatorLend=?,"
				+ "OperatorRet=?"
				+ " where BorrowID=? and"
				+ " rdId = ?";
	
		Object[] params = new Object[]
			{
					bw.getBkID(),
					bw.getLdContinueTimes(),
					bw.getLdDateOut(),
					bw.getLdDateRetPlan(),
					bw.getLdDateRetAct(),
					bw.getLdOverDay(),
					bw.getLdOverMoney(),
					bw.getLdPunishMoney(),
					bw.isLsHasReturn(),
					bw.getOperatorLend(),
					bw.getOperatorRet(),
					bw.getBorrowID(),
					bw.getRdID()
			};
		return SQLHelper.ExecSql(sql, params);
	}
	
	

	@Override
	public AbstractModel[] getAllObjects() throws Exception {
		ArrayList<Borrow> objects = new ArrayList<Borrow>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Borrow");
		if(rs != null){
			while(rs.next()){
				Borrow bw  = initBorrow(rs);
				objects.add(bw);
			}
			rs.close();
		}
		Borrow[] types = new Borrow[objects.size()];
		objects.toArray(types);
		
		return types;
	}
	

	/**
	 * 初始化借阅信息对象
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Borrow initBorrow(ResultSet rs)throws SQLException{
		Borrow bw = new Borrow();
		bw.setBorrowID(rs.getString("BorrowID"));
		bw.setRdID(rs.getInt("rdID"));
		bw.setBkID(rs.getInt("bkID"));
		bw.setLdContinueTimes(rs.getInt("ldContinueTimes"));
		bw.setLdDateOut(rs.getString("ldDateOut"));
		bw.setLdDateRetPlan(rs.getString("ldDateRetPlan"));
		bw.setLdDateRetAct(rs.getString("ldDateRetAct"));
		bw.setLdOverDay(rs.getInt("ldOverDay"));
		bw.setLdOverMoney(rs.getFloat("ldOverMoney"));
		bw.setLdPunishMoney(rs.getFloat("ldPunishMoney"));
		bw.setIsHasReturn(rs.getBoolean("lsHasReturn"));
		bw.setOperatorLend(rs.getString("OperatorLend"));
		bw.setOperatorRet(rs.getString("OperatorRet"));
		
		return bw;
	}
	
	@Override
	public String[] getPrettyColumnNames() {
		// TODO Auto-generated method stub
		return this.dispColNames;
	}

	@Override
	public String[] getMethodNames() {
		// TODO Auto-generated method stub
		return this.methodNames;
	}

	@Override
	public AbstractModel getObjectByID(int borrowID) throws SQLException {
		Borrow re = null;
		ResultSet rs = SQLHelper
				.getResultSet("select"
							+ "BorrowID,"
							+ "rdID,"
							+ "bkID,"
							+ "ldContinueTimes,"
							+ "ldDateOut,"
							+ "ldDateRetPlan,"
							+ "ldDateRetAct,"
							+ "ldOverDay,"
							+ "ldOverMoney,"
							+ "ldPunishMoney,"
							+ "lsHasReturn,"
							+ "OperatorLend,"
							+ "OperatorRet"
							+ " from TB_Borrow"
							+ " where BorrowID="
									+borrowID);
		
		if(rs != null){
			if(rs.next()){
				re=initBorrow(rs);
			}
			rs.close();
		}
		return re;
	}

	@Override
	public String[] getAllObjectsName() throws Exception {
		// TODO Auto-generated method stub
		return null;
		//未实现
	}

	public Borrow[] getBorrowsByString() throws SQLException {
		// TODO Auto-generated method stub
		BorrowDAL dal = new BorrowDAL();
		Borrow[] borrow =  dal.getBorrowsBy();
		return borrow;
	}

	private Borrow[] getBorrowsBy() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Borrow> borrows = new ArrayList<Borrow>();
		String sql = "select * from TB_Borrow";

		ResultSet rs = SQLHelper.getResultSet(sql);
		if(rs != null){
			while(rs.next()){
				Borrow borrow = initBorrow(rs);
				borrows.add(borrow);
			}
			rs.close();
		}
		if(borrows.size()>0){
			Borrow[] array = new Borrow[borrows.size()];
			borrows.toArray(array);
			return array;
		}
		return null;
	}
	
	public Borrow[] getBorrowsByStringByRdID(int rdID) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Borrow> borrows = new ArrayList<Borrow>();
		String sql = "select * from TB_Borrow where RdID = ?";
		
		Object[] params = new Object[]{ rdID };
		ResultSet rs = SQLHelper.getResultSet(sql,params);
		if(rs != null){
			while(rs.next()){
				Borrow borrow = initBorrow(rs);
				borrows.add(borrow);
			}
			rs.close();
		}
		if(borrows.size()>0){
			Borrow[] array = new Borrow[borrows.size()];
			borrows.toArray(array);
			return array;
		}
		return null;
	}
	
}
