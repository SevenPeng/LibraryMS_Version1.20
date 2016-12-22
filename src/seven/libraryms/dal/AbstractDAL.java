package seven.libraryms.dal;

import java.sql.SQLException;

import seven.libraryms.model.AbstractModel;
import seven.libraryms.model.Borrow;

/**
 * ����������ͳһ���ݲ��������ķ���
 * �ṩ����ɾ�Ĳ�ķ���
 * 
 * @author Seven
 * 	@Data 2016-12-12
 * @version 1.00
 */
public abstract class AbstractDAL {
	
	/** ������ȡ�ñ��е����м�¼��
	 * 	�������ɶ������������б�͵�λ�����б�ʱ����
	 * @return AllObjects
	 * @throws Exception
	 * @author Seven 	
	 */
	public abstract AbstractModel[] getAllObjects() throws Exception;

	/** ͨ��������ñ��ж�Ӧ��������
	 * @param id ����������
	 * @return �Ǹ���:����ִ��; -1:ִ�д���; -2:���Ӵ���
	 * @throws SQLException
	 */
	public abstract AbstractModel getObjectByID(int id)throws SQLException;
	
	/** �����ݿ������Ӷ���
	 * @param object ���Ӷ���
	 * @return �Ǹ���:����ִ��; -1:ִ�д���; -2:���Ӵ���
	 * @throws Exception
	 */
	public abstract int add(AbstractModel object) throws Exception;
	
	/**�����ݿ���ɾ������
	 * @param object ɾ������
	 * @return �Ǹ���:����ִ��; -1:ִ�д���; -2:���Ӵ���
	 * @throws Exception
	 */
	public abstract int delete(AbstractModel object) throws Exception;
	
	/**�����ݿ��еĶ�����и���
	 * @param object
	 * @return �Ǹ���:����ִ��; -1:ִ�д���; -2:���Ӵ���
	 * @throws Exception
	 */
	public abstract int update(AbstractModel object) throws Exception;
	
	/** ������ȡ�ñ��е����м�¼�����ƣ�
	 * 	�������ɶ������������б�͵�λ�����б�ʱ����
	 * @return AllObjects
	 * @throws Exception
	 * @author Seven 	
	 */
	
	public abstract String[] getAllObjectsName() throws Exception;
	
	/**
	 * ��JTable��ʼ����ͷʱ���ã�����ʼ�����ṹ
	 * @return ���ݿ��е�����
	 * @brief getPrettyColumnNames��������ʵ��ʵ���ൽUI���JTable���ݵ�ת����
	 * 	�������б���ʽ��ʾ��ѯ�õ��Ķ��ߡ����ļ�¼��
	 */
	public abstract String[] getPrettyColumnNames();
	
	/**
	 *��ȡָ��JTable����������Ҫ���õ�Readerʵ���෽�����ơ�
	 *@return ʵ���෽������
	 */
	public abstract String[] getMethodNames();
	
}
