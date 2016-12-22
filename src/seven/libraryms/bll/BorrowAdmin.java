package seven.libraryms.bll;

import java.sql.SQLException;

import seven.libraryms.dal.BorrowDAL;
import seven.libraryms.model.Borrow;

/**
 * Copyright (C), 2016-2020, Seven FileName: DepartmentTypeAdmin.java
 * 
 * 读者类型管理类用于查询相对应的读者对象
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
	 * 用以显示ReaderPanel中的deptTypeComboBox
	 * @return 字符串型读者类型的名字
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
