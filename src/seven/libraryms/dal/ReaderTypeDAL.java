package seven.libraryms.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import seven.libraryms.model.AbstractModel;
import seven.libraryms.model.Reader;
import seven.libraryms.model.ReaderType;

/**
 * ReaderTypeDAL类中实现AbstractDAL类中的抽象方法，
 * 	添加对表TB_ReaderType记录的插入add()、删除delete()、修改update()等方法。
 * @author Seven
 *	@Data 2016-12-12
 * @version 1.00	
 *
 */
public class ReaderTypeDAL extends AbstractDAL {
	
	private String[] dispColNames = new String[]{
			"读者类别",
			"读者类别名称",
			"可借书数量",
			"可借书天数",
			"可续借的次数",
			"罚款率（元/天）",
			"证书有效期（年)",
	};
	
	/** 指定字段在Reader实体类中的获取方法*/
	private String[] methodNames = new String[]{
			"getRdType",
			"getRdTypeName",
			"getCanLendQty",
			"getCanLendDay",
			"getCanContinueTimes",
			"getPunishRate",
			"getDateValid"
	};
	/**
	 * 通过读者类别名称查询读者类别
	 * @return int 读者类别
	 * 	-1表示查询错误
	 *  -2表示未找到对应读者类型
	 * @throws Exception
	 */
	public int getRdType(String RdTypeName) throws Exception {
		ResultSet rs = SQLHelper
				.getResultSet("select  rdType from TB_ReaderType where rdTypeName = "
																	+ "'"+RdTypeName+"'");
		if(rs != null){
			int rdType = -2;
			while(rs.next()){
			 rdType = Integer.valueOf(rs.getString("rdType"));
			}
			rs.close();
			return rdType;
		}
		return -1;
	}
	/**
	 * @param ResultSet 类型
	 * @return ReaderType
	 * @throws SQLException
	 * @brief  ResultSet 类型 转变为 ReaderType类型
	 * @test pass
	 */
	private ReaderType initReaderType(ResultSet rs)throws SQLException{
		ReaderType rt = new ReaderType();
		rt.setRdType(rs.getInt("rdType"));
		rt.setRdTypeName(rs.getString("rdTypeName"));
		rt.setCanLendQty(rs.getInt("CanlendQty"));
		rt.setCanLendDay(rs.getInt("CanLendDay"));
		rt.setCanContinueTimes(rs.getInt("CanContinueTimes"));
		rt.setPunishRate(rs.getFloat("PunishRate"));
		rt.setDateValid(rs.getInt("DateValid"));
		return rt;
	}
	@Override
	public int add(AbstractModel object) throws Exception {
		if(object instanceof ReaderType ==false){
			throw new Exception("Can only  handle ReaderType!");
		}
		//只能插入ReaderType类 的数据
		ReaderType rt = (ReaderType) object;
		//将参数转换为ReaderType类型
		
		String sql = "insert into TB_ReaderType ("
				+ "rdType,"
				+ "rdTypeName,"
				+ "CanLendQty,"
				+ "CanLendDay,"
				+ "CanContinueTimes,"
				+ "PunishRate,"
				+ "DateValid)"
				+ " VALUES(?,?,?,?,?,?,?)";
		//SQL插入语句
		
		Object[] params = new Object[7];
		params[0] = rt.getRdType();
		params[1] = rt.getRdTypeName();
		params[2] = rt.getCanLendQty();
		params[3] = rt.getCanLendDay();
		params[4] = rt.getCanContinueTimes();
		params[5] = rt.getPunishRate();
		params[6] = rt.getDateValid();
		//分割连串的插入数据
		
		return SQLHelper.ExecSql(sql,params);
		
	}

	
	@Override
	public int delete(AbstractModel object) throws Exception {
		if(object instanceof ReaderType ==false){
			throw new Exception("Can only handle ReaderType!");			
		}
		ReaderType rt = (ReaderType)object;
		
		String sql="delete from TB_ReaderType where rdType = ?";
		
		Object[] params  = new Object[]	{rt.getRdType()};
		int rows = SQLHelper.ExecSql(sql,params);
		return rows;
	}
	
	@Override
	public int update(AbstractModel object) throws Exception {
		if(object instanceof ReaderType ==false ){
			throw new Exception("Can only handle ReaderType!");
		}
		ReaderType rt = (ReaderType)object;
		String sql = "update TB_ReaderType set "
				+ "rdTypeName=?,"
				+ "CanlendQty=?,"
				+ "CanLendDay=?,"
				+ "CanContinueTimes=?,"
				+ "PunishRate=?,"
				+ "DateValid=?"
				+ " where rdType =?";
		Object[] params = new Object[]{ 
							rt.getRdTypeName(),
							rt.getCanLendQty(),
							rt.getCanLendDay(),
							rt.getCanContinueTimes(),
							rt.getPunishRate(),
							rt.getDateValid(),
							rt.getRdType()
							};
		return SQLHelper.ExecSql(sql, params);
	}
	
	@Override
	public AbstractModel getObjectByID(int rdType) throws SQLException {
		ReaderType rt = null;
		ResultSet rs = SQLHelper
				.getResultSet("select"
								+ " rdType,"
								+ "rdTypeName,"
								+ "CanLendQty,"
								+ "CanLendDay,"
								+ "CanContinueTimes,"
								+ "PunishRate,"
								+ "DateValid "
								+ " from TB_ReaderType"
								+ " where rdType="
								+ rdType);
		if(rs != null){
			if(rs.next()){
				rt=initReaderType(rs);
			}
			rs.close();
		}
		return rt;
	}

	@Override
	public AbstractModel[] getAllObjects() throws Exception {
		ArrayList<ReaderType> objects = new ArrayList<ReaderType>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_ReaderType");
		if(rs != null){
			while(rs.next()){
				ReaderType rt  = initReaderType(rs);
				objects.add(rt);
			}
			rs.close();
		}
		ReaderType[] types = new ReaderType[objects.size()];
		objects.toArray(types);
		return types;
				
	}
	
	@Override
	public String[] getAllObjectsName() throws Exception {
		ReaderType[] readerType = (ReaderType[]) getAllObjects();
		if(readerType != null){
			int length = readerType.length;
			String[] allReaderTypeName = new String[length];
			for(int i = 0 ; i <length ; i++){
				allReaderTypeName[i] = readerType[i].getRdTypeName();
			}
			return allReaderTypeName;
		}
		return null;
	}
	
	@Override
	public String[] getPrettyColumnNames() {
		// TODO Auto-generated method stub
		return dispColNames;
	}

	@Override
	public String[] getMethodNames() {
		// TODO Auto-generated method stub
		return methodNames;
	}

}
