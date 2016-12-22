package seven.libraryms.bll;

import java.sql.SQLException;

import seven.libraryms.dal.BorrowDAL;
import seven.libraryms.model.Borrow;

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
public class BorrowAdmin extends LibraryBLL{
	
	
	public BorrowAdmin(){
		dal = new BorrowDAL();
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

	public Borrow[] retrieveBorrowsString() {
		// TODO Auto-generated method stub
		try {
			return ((BorrowDAL) dal).getBorrowsByString();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateBorrow(Borrow borrow) {
		// TODO Auto-generated method stub
		try {
			dal.update(borrow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Borrow[] retrieveBorrowsStringByRdID(int rdID) {
		// TODO Auto-generated method stub
		try {
			return  ((BorrowDAL) dal).getBorrowsByStringByRdID(rdID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
