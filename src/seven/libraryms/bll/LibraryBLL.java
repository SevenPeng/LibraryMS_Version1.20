package seven.libraryms.bll;

import seven.libraryms.dal.AbstractDAL;

/**
 * Copyright (C), 2016-2020, Seven FileName: LibraryBLL.java
 * 
 * Library Business Logic Layer 业务罗逻辑层抽象类
 * 
 * @author Seven
 * @Data 2016-12-12
 * @version 1.00
 */
public abstract class LibraryBLL{

	/**引入抽象数据处理类*/
	protected AbstractDAL dal;
	
	/**
	 * 供JTable初始化表头时调用，即初始化表格结构
	 * @return 数据库中的列名
	 */
	public String[] getDisplayColumnNames(){
		return dal.getPrettyColumnNames();
	}
	
	/**
	 *获取指定JTable列数据所需要调用的Reader实体类方法名称。
	 *@return 实体类方法名称
	 */
	public String[] getMethodNames(){
		return dal. getMethodNames();
	}

}
