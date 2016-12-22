package seven.libraryms.model;

import java.lang.reflect.Method;

/**
 * 抽象实体类，用于统一抽象数据访问类的方法接口
 * @author Seven
 * 	@Data 216-12-12
 * @version 1.00
 *
 */
public abstract class AbstractModel {
	public Object getFieldValue(Class<?> objectClass,String methodName)
			throws Exception{
		Method[] allMethods = objectClass.getDeclaredMethods();
		for(Method m:allMethods){
			String mname = m.getName();
			if(mname.equals(methodName)){
				return m.invoke(this, null);
			}
		}
		return null;
	}
	public static void main(String[] args) throws Exception {
		//for test 
		Reader r = new Reader();
		String[] methodNames = new String[]{
				"getReaderID",
				"getReaderName",
				"getReaderSex",
				"getReaderType",
				"getReaderDeptType",
				"getReaderPhone",
				"getReaderEmail",
				"getReaderStatus",
				"getReaderBorrowQty",
				"getReaderDateReg"
		};
		r.setRdAdminRoles(0000);
		r.setRdBorrowQty(00000);
		r.setRdDateReg("2016-1-1");
		r.setRdDept("0000");
		r.setRdEmail("0000");
		r.setRdID(0000);
		r.setRdName("0000");
		r.setRdPhone("0000");
		byte[] a  = {0,0,0,0,0};
		//r.setRdPhoto(a);
		r.setRdPwd("0000");
		r.setRdSex("0000");
		r.setRdStatus("0000");
		r.setRdType(0000);
		System.out.println(r.getRdStatus());
		System.out.println(r.getFieldValue(r.getClass(), "getRdStatus"));
	}
	
}
