package seven.libraryms.bll;

import seven.libraryms.dal.AbstractDAL;

/**
 * Copyright (C), 2016-2020, Seven FileName: LibraryBLL.java
 * 
 * Library Business Logic Layer ҵ�����߼��������
 * 
 * @author Seven
 * @Data 2016-12-12
 * @version 1.00
 */
public abstract class LibraryBLL{

	/**����������ݴ�����*/
	protected AbstractDAL dal;
	
	/**
	 * ��JTable��ʼ����ͷʱ���ã�����ʼ�����ṹ
	 * @return ���ݿ��е�����
	 */
	public String[] getDisplayColumnNames(){
		return dal.getPrettyColumnNames();
	}
	
	/**
	 *��ȡָ��JTable����������Ҫ���õ�Readerʵ���෽�����ơ�
	 *@return ʵ���෽������
	 */
	public String[] getMethodNames(){
		return dal. getMethodNames();
	}

}
