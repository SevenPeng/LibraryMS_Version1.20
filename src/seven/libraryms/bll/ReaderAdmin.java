package seven.libraryms.bll;

import java.sql.SQLException;

import seven.libraryms.dal.ReaderDAL;
import seven.libraryms.dal.ReaderTypeDAL;
import seven.libraryms.model.Reader;

/**
 * Copyright (C), 2016-2020, Seven FileName: DepartmentTypeAdmin.java
 * 
 * 读者管理类用于查询相对应的读者对象
 * 
 * @author Seven
 * @Data 2016-12-12
 * @version 1.00
 *
 */
public class ReaderAdmin extends LibraryBLL {
	
	public ReaderAdmin() {
		dal = new ReaderDAL();
	}
	
	/**
	 * 提供从 (String)rdTypeName 转化为 (int) rdtype 的功能
	 * @param RdTypeName
	 * @return 
	 */
	public int getReaderTypeCode(String RdTypeName){
		ReaderTypeDAL rtdal = new ReaderTypeDAL();
			try {
				return   rtdal.getRdType(RdTypeName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return -1;
	}
	/**
	 * 获得相对应的读者处理对象
	 * @param rdID 读者ID
	 * @return Reader类的对应对象
	 */
	public Reader getReader(int rdID) {
		try {
			return (Reader) dal.getObjectByID(rdID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过读者类型,单位和姓名取回相对应的读者.用于ReaderPanel查询功能
	 * @param rdType 读者类型
	 * @param deptType 单位类型
	 * @param userName	读者姓名
	 * @return 读者对象
	 */
	public Reader[] retrieveReadersString(String rdType, String deptType, String userName) {
		// TODO Auto-generated method stub
		ReaderTypeDAL readerTypeDal = new ReaderTypeDAL();
		try {
			int temp;
			temp = readerTypeDal.getRdType(rdType);
			return ((ReaderDAL) dal).getRequestedReaders(temp, deptType, userName);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	/**
	 * 对读者对象更新属性
	 * @param reader 读者对象
	 */
	public void updateReader(Reader reader) {
		// TODO Auto-generated method stub
		try {
			dal.update(reader);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] getAllReaderDepartment() {
		// TODO Auto-generated method stub
		try {
			return ((ReaderDAL)dal).getAllRdDept();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
