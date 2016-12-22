package seven.libraryms.bll;

import java.sql.SQLException;

import seven.libraryms.dal.BookDAL;
import seven.libraryms.dal.ReaderTypeDAL;
import seven.libraryms.model.Book;
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
public class BookAdmin extends LibraryBLL{
	
	
	public BookAdmin(){
		dal = new BookDAL();
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
	
	public Book[] retrieveBooksString() {
		// TODO Auto-generated method stub
		try {
			return (Book[]) dal.getAllObjects();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		try {
			dal.update(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Book[] retrieveBooksStringByName(String bkName) {
		// TODO Auto-generated method stub
		try {
			return ((BookDAL) dal).getBooksByName(bkName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ������Ӧ��Book�������
	 * @param bkID BookID
	 * @return Book��Ķ�Ӧ����
	 */
	public Book getBook(int bkID) {
		try {
			return (Book) dal.getObjectByID(bkID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
