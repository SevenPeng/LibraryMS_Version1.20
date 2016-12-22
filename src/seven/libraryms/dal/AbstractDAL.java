package seven.libraryms.dal;

import java.sql.SQLException;

import seven.libraryms.model.AbstractModel;
import seven.libraryms.model.Borrow;

/**
 * 抽象类用于统一数据层的所有类的方法
 * 提供了增删改查的方法
 * 
 * @author Seven
 * 	@Data 2016-12-12
 * @version 1.00
 */
public abstract class AbstractDAL {
	
	/** 方法获取该表中的所有记录，
	 * 	这在生成读者类型下拉列表和单位下拉列表时有用
	 * @return AllObjects
	 * @throws Exception
	 * @author Seven 	
	 */
	public abstract AbstractModel[] getAllObjects() throws Exception;

	/** 通过主键获得表中对应的行数据
	 * @param id 表对象的主键
	 * @return 非负数:正常执行; -1:执行错误; -2:连接错误
	 * @throws SQLException
	 */
	public abstract AbstractModel getObjectByID(int id)throws SQLException;
	
	/** 向数据库中增加对象
	 * @param object 增加对象
	 * @return 非负数:正常执行; -1:执行错误; -2:连接错误
	 * @throws Exception
	 */
	public abstract int add(AbstractModel object) throws Exception;
	
	/**向数据库中删除对象
	 * @param object 删除对象
	 * @return 非负数:正常执行; -1:执行错误; -2:连接错误
	 * @throws Exception
	 */
	public abstract int delete(AbstractModel object) throws Exception;
	
	/**对数据库中的对象进行更新
	 * @param object
	 * @return 非负数:正常执行; -1:执行错误; -2:连接错误
	 * @throws Exception
	 */
	public abstract int update(AbstractModel object) throws Exception;
	
	/** 方法获取该表中的所有记录的名称，
	 * 	这在生成读者类型下拉列表和单位下拉列表时有用
	 * @return AllObjects
	 * @throws Exception
	 * @author Seven 	
	 */
	
	public abstract String[] getAllObjectsName() throws Exception;
	
	/**
	 * 供JTable初始化表头时调用，即初始化表格结构
	 * @return 数据库中的列名
	 * @brief getPrettyColumnNames方法用于实现实体类到UI组件JTable数据的转换，
	 * 	比如以列表形式显示查询得到的读者、借阅记录等
	 */
	public abstract String[] getPrettyColumnNames();
	
	/**
	 *获取指定JTable列数据所需要调用的Reader实体类方法名称。
	 *@return 实体类方法名称
	 */
	public abstract String[] getMethodNames();
	
}
