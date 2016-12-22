package seven.libraryms.bll;

import java.sql.SQLException;

import seven.libraryms.dal.ReaderDAL;
import seven.libraryms.dal.ReaderTypeDAL;
import seven.libraryms.model.Reader;

/**
 * Copyright (C), 2016-2020, Seven FileName: DepartmentTypeAdmin.java
 * 
 * ���߹��������ڲ�ѯ���Ӧ�Ķ��߶���
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
	 * �ṩ�� (String)rdTypeName ת��Ϊ (int) rdtype �Ĺ���
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
	 * ������Ӧ�Ķ��ߴ������
	 * @param rdID ����ID
	 * @return Reader��Ķ�Ӧ����
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
	 * ͨ����������,��λ������ȡ�����Ӧ�Ķ���.����ReaderPanel��ѯ����
	 * @param rdType ��������
	 * @param deptType ��λ����
	 * @param userName	��������
	 * @return ���߶���
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
	 * �Զ��߶����������
	 * @param reader ���߶���
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
