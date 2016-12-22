package seven.libraryms.bll;

import java.sql.SQLException;

import seven.libraryms.dal.ReaderDAL;
import seven.libraryms.dal.ReaderTypeDAL;
import seven.libraryms.model.Reader;
import seven.libraryms.model.ReaderType;

/**
 * Copyright (C), 2016-2020, Seven FileName: DepartmentTypeAdmin.java
 * 
 * �������͹��������ڲ�ѯ���Ӧ�Ķ��߶���
 * 
 * @author Seven
 * @Data 2016-12-12
 * @version 1.00
 *
 */
public class ReaderTypeAdmin extends LibraryBLL{
	
	
	public ReaderTypeAdmin(){
		dal = new ReaderTypeDAL();
	}
	
	/**
	 * ������ʾReaderPanel�е�deptTypeComboBox
	 * @return �ַ����Ͷ������͵�����
	 */
	public String[] getReaderTypesName(){
		try{
			return(String[]) dal.getAllObjectsName();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public ReaderType[] retrieveReaderTypes() {
		// TODO Auto-generated method stub
	
		try {
			return (ReaderType[]) ((ReaderTypeDAL) dal).getAllObjects();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateReaderType(ReaderType readerType) {
		// TODO Auto-generated method stub
		try {
			dal.update(readerType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ReaderType getReaderType(int rdType) {
		// TODO Auto-generated method stub
		try {
			return (ReaderType) dal.getObjectByID(rdType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
