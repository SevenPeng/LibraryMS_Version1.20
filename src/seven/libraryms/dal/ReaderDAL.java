package seven.libraryms.dal;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.MissingFormatArgumentException;

import seven.libraryms.model.AbstractModel;
import seven.libraryms.model.Reader;
import seven.libraryms.model.ReaderType;

/**
 * �������ݱ�����ࣨ�塢ɾ���ġ��顢�洢���̵ȣ�
 * ��ReaderDAL������ӶԱ�TB_Reader��¼�Ĳ���add()��ɾ��delete()���޸�update()��
 * 	���ݹؼ��ֲ�ѯReaderʵ�������getObjectByID()������ʵ������ʾ�ֶμ����ȡ������getPrettyColumnNames()
 * 	��getMethodNames()�ȷ�������������ѯ��������ÿ��������UI�������ʵ�ֹ�����ȥ���ֺ����
 * @author 	Seven
 * 	@Data 2016-12-12
 * @version 1.00
 */
public class ReaderDAL extends AbstractDAL {
	
	/**����ָ�����ֶ���JTable�������е���ʾ�ı�*/
	private String[] dispColNames = new String[]{
			"ID",
			"����",
			"�Ա�",
			"����",
			"��λ",
			"�绰",
			"email",
			"״̬",
			"�ѽ��鼮",
			"ע������",
	};
	
	/** ָ���ֶ���Readerʵ�����еĻ�ȡ����*/
	private String[] methodNames = new String[]{
			"getRdID",
			"getRdName",
			"getRdSex",
			"getRdType",
			"getRdDept",
			"getRdPhone",
			"getRdEmail",
			"getRdStatus",
			"getRdBorrowQty",
			"getRdDateReg"
	};
	
	
	/**
	 * ��ѯ���ж��ߵĵ�λ
	 * @return String[] ���ж��ߵĵ�λ
	 * @throws Exception
	 */
	public String[] getAllRdDept() throws Exception {
		ArrayList<String> objects = new ArrayList<String>();
		ResultSet rs = SQLHelper.getResultSet("select distinct rdDept from TB_Reader");
		if(rs != null){
			while(rs.next()){
				objects.add(rs.getString("rdDept"));
			}
			rs.close();
		}
		String[] types = new String[objects.size()];
		objects.toArray(types);
		return types;
	}
	/**
	 * ���ղ�������ҪѰ�ҵĶ���
	 * @param rdType String  ��������
	 * @param deptType String ��λ
	 * @param userName String ��������
	 * @return ҪѰ�ҵĶ���
	 * @throws SQLException
	 */
	public Reader[] getRequestedReaders(int rdType, String deptType,
			String userName) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Reader> readers = new ArrayList<Reader>();
		//SQL���
		String sql = "select * from TB_Reader where rdType like ?  and"
				+ " rdDept = ? and rdName like ?";
		//��Ҫ�Ĳ��� ,ʹ��"%"����ģ����ѯ
		Object[] params = new Object[]{rdType+'%',
				deptType,"%"+userName+"%"};

		ResultSet rs = SQLHelper.getResultSet(sql,params);
		if(rs != null){
			while(rs.next()){
				Reader reader = initReader(rs);
				readers.add(reader);
			}
			rs.close();
		}
		if(readers.size()>0){
			Reader[] array = new Reader[readers.size()];
			readers.toArray(array);
			return array;
		}
		return null;
	}	
	public static void main(String[] args) throws SQLException {
		ReaderDAL dal =new ReaderDAL();
		Reader[] r = dal.getRequestedReaders(0, "�ƿ�Ժ", "��");
		for(Reader temp : r){
			System.out.println(temp.getRdName());
		}
	}
	
	/**
	 * @param ResultSet ����
	 * @return Reader
	 * @throws SQLException
	 * @brief  ResultSet ���� ת��Ϊ Reader����
	 * 
	 */
	private Reader initReader(ResultSet rs)throws SQLException{
		Reader re = new Reader();
		re.setRdID(rs.getInt("rdID"));
		re.setRdName(rs.getString("rdName"));
		re.setRdSex(rs.getString( "rdSex"));
		re.setRdType(rs.getShort( "rdType"));
		re.setRdDept(rs.getString("rdDept"));
		re.setRdPhone(rs.getString("rdPhone"));
		re.setRdEmail(rs.getString("rdEmail"));
		re.setRdDateReg(rs.getString("rdDateReg"));
		re.setRdPhoto(rs.getBytes("rdPhoto"));
		re.setRdStatus(rs.getString("rdStatus"));
		re.setRdBorrowQty(rs.getInt("rdBorrowQty"));
		re.setRdPwd(rs.getString("rdPwd"));
		re.setRdAdminRoles(rs.getInt("rdAdminRoles"));
		return re;
	}

	@Override
	public int add(AbstractModel object) throws Exception {
		if(object instanceof Reader ==false){
			throw new Exception("Can only handle Reader!");
		}
		//ֻ�ܲ���Reader�� ������
		
		Reader re = (Reader) object;
		//������ת��ΪReader����
		
		String sql = "insert into TB_Reader ("
				+ "rdID,"
				+ "rdName,"
				+ "rdSex,"
				+ "rdType,"
				+ "rdDept,"
				+ "rdPhone,"
				+ "rdEmail,"
				+ "rdDateReg,"
				+ "rdPhoto,"
				+ "rdStatus,"
				+ "rdBorrowQty,"
				+ "rdPwd,"
				+ "rdAdminRoles)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		//SQL�������
		
		Object[] params = new Object[13];
		params[0] = re.getRdID();
		params[1] = re.getRdName();
		params[2] = re.getRdSex();
		params[3] = re.getRdType();
		params[4] = re.getRdDept();
		params[5] = re.getRdPhone();
		params[6] = re.getRdEmail();
		params[7] = re.getRdDateReg();
		params[8] = re.getRdPhotoByte();
		params[9] = re.getRdStatus();
		params[10] = re.getRdBorrowQty();
		params[11] = re.getRdPwd();
		params[12] = re.getRdAdminRoles();
		
		return SQLHelper.ExecSql(sql,params);
		
	}

	@Override
	public int delete(AbstractModel object) throws Exception {
		if(object instanceof Reader ==false){
			throw new Exception("Can only handle Reader!");			
		}
		Reader re = (Reader)object;
		String sql="delete from TB_Reader where rdID = ?";
		
		Object[] params  = new Object[]	{re.getRdID()};
		int rows = SQLHelper.ExecSql(sql,params);
		return rows;
	}

	@Override
	public int update(AbstractModel object) throws Exception {
		if(object instanceof Reader ==false){
			throw new Exception("You only can handle Reader!");
		}
		Reader re = (Reader)object;
		
		String sql = "update TB_Reader set "
				+ "rdID=?,"
				+ "rdName=?,"
				+ "rdSex=?,"
				+ "rdType=?,"
				+ "rdDept=?,"
				+ "rdPhone=?,"
				+ "rdEmail=?,"
				+ "rdDateReg=?,"
				+ "rdPhoto=?,"
				+ "rdStatus=?,"
				+ "rdBorrowQty=?,"
				+ "rdPwd=?,"
				+ "rdAdminRoles=?"
				+ " where rdID=?";
		Object[] params = new Object[]
			{
				re.getRdID(),
				re.getRdName(),
				re.getRdSex(),
				re.getRdType(),
				re.getRdDept(),
				re.getRdPhone(),
				re.getRdEmail(),
				re.getRdDateReg(),
				re.getRdPhotoByte(),
				re.getRdStatus(),
				re.getRdBorrowQty(),
				re.getRdPwd(),
				re.getRdAdminRoles(),
				re.getRdID()
			};
		return SQLHelper.ExecSql(sql, params);
	}
	
	@Override
	public AbstractModel getObjectByID(int rdID) throws SQLException {
		Reader re = null;
		ResultSet rs = SQLHelper
				.getResultSet("select"
								+ " rdID,"
								+ "rdName,"
								+ "rdSex,"
								+ "rdType,"
								+ "rdDept,"
								+ "rdPhone,"
								+ "rdEmail,"
								+ "rdDateReg,"
								+ "rdPhoto,"
								+ "rdStatus,"
								+ "rdBorrowQty,"
								+ "rdPwd,"
								+ "rdAdminRoles"
								+ " from TB_Reader"
								+ " where rdID="
											+rdID);
		if(rs != null){
			if(rs.next()){
				re=initReader(rs);
			}
			rs.close();
		}
		return re;
	}

	@Override
	public AbstractModel[] getAllObjects() throws Exception {
		ArrayList<Reader> objects = new ArrayList<Reader>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Reader");
		if(rs != null){
			while(rs.next()){
				Reader re  = initReader(rs);
				objects.add(re);
			}
			rs.close();
		}
		Reader[] types = new Reader[objects.size()];
		objects.toArray(types);
		return types;
				
	}
	
	@Override
	public String[] getPrettyColumnNames() {
		return dispColNames;
	}

	@Override
	public String[] getMethodNames() {
		return methodNames;
	}
	public String[] getDepartmentTypes(){
		return null;
	}

	@Override
	public String[] getAllObjectsName() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
