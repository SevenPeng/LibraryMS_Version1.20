package seven.libraryms.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import seven.libraryms.model.AbstractModel;
import seven.libraryms.model.Book;

/**
 * Copyright (C), 2016-2020, Seven FileName: BookDAL.java
 * 
 * 书籍管理类,提供了书籍的对象增删改查的方法
 * 
 * @author Seven
 * @Data 2016-12-12
 * @version 1.00
 * 
 */
public class BookDAL extends AbstractDAL {
	
	private String[] dispColNames = new String[]{
			"序号",
			"编号",
			"书名",
			"作者",
			"出版社",
			"出版日期",
			"ISBN",
			"分类号",
			"语言",
			"页数",
			"价格",
			"入馆日期",
			"状态",
			"简介"
	};
	
	/** 指定字段在Reader实体类中的获取方法*/
	private String[] methodNames = new String[]{
			"getBkID",
			"getBkCode",
			"getBkName",
			"getBkAuthor",
			"getBkPress",
			"getBkDatePress",
			"getBkISBN",
			"getBkCatalog",
			"getBkLanguage",
			"getBkPages",
			"getBkPrice",
			"getBkDateIn",
			"getBkStatus",
			"getBkBrief",
	};
	
	public Book[] getBooksByName(String bkName) throws SQLException {
		// TODO Auto-generated method stub
				ArrayList<Book> books = new ArrayList<Book>();
				
				String sql = "select * from TB_Book where bkName like ?";
				
				Object[] params = new Object[]{bkName};

				ResultSet rs = SQLHelper.getResultSet(sql,params);
				if(rs != null){
					while(rs.next()){
						Book book = initBook(rs);
						books.add(book);
					}
					rs.close();
				}
				if(books.size()>0){
					Book[] array = new Book[books.size()];
					books.toArray(array);
					return array;
				}
				return null;
	}
//通过Book对象 查找
//	public Book[] getLookingBooks(Book bk) throws SQLException {
//		// TODO Auto-generated method stub
//		ArrayList<Book> books = new ArrayList<Book>();
//
//		String sql = "select * from TB_Book where "
//										+ "bkID like ? and "
//										+ "bkCode like ? and "
//										+ "bkName like ? and "
//										+ "bkAuthor like ? and "
//										+ "bkPress like ? and "
//										+ "bkDatePress like ? and "
//										+ "bkISBN like ? and "
//										+ "bkCatalog like ? and "
//										+ "bkLanguage like ? and "
//										+ "bkPages like ? and "
//										+ "bkPrice like ? and "
//										+ "bkDateIn like ? and "
//										+ "bkBrief like ? and "
//										+ "bkStatus like ? ";
//										
//		Object[] params = new Object[]{bk.getBkID(),
//									"%"+bk.getBkCode()+"%",
//									"%"+bk.getBkName()+"%",
//									"%"+bk.getBkAuthor()+"%",
//									"%"+bk.getBkPress()+"%",
//									"%"+bk.getBkDatePress()+"%",
//									"%"+bk.getBkISBN()+"%",
//									"%"+bk.getBkCatalog()+"%",
//									"%"+bk.getBkLanguage()+"%",
//										bk.getBkPages(),
//										bk.getBkPrice(),
//									"%"+bk.getBkDateIn()+"%",
//									"%"+bk.getBkBrief()+"%",
//									"%"+bk.getBkStatus()+"%"
//								};
//
//		ResultSet rs = SQLHelper.getResultSet(sql, params);
//		if (rs != null) {
//			while (rs.next()) {
//				Book book = initBook(rs);
//				books.add(book);
//			}
//			rs.close();
//		}
//		if (books.size() > 0) {
//			Book[] array = new Book[books.size()];
//			books.toArray(array);
//			return array;
//		}
//		return null;
//	}
	
	
	/**
	 * 初始化书籍对象
	 * @param ResultSet 类型
	 * @return Book
	 * @throws SQLException
	 * @brief  ResultSet 类型 转变为 Book类型
	 */
	private Book initBook(ResultSet rs)throws SQLException{
		Book bk = new Book();
		bk.setBkID(rs.getInt("bkID"));
		bk.setBkCode(rs.getString("bkCode"));
		bk.setBkName(rs.getString("bkName"));
		bk.setBkAuthor(rs.getString("bkAuthor"));
		bk.setBkPress(rs.getString("bkPress"));
		bk.setBkDatePress(rs.getString("bkDatePress"));
		bk.setBkISBN(rs.getString("bkISBN"));
		bk.setBkCatalog(rs.getString("bkCatalog"));
		bk.setBkLanguage(rs.getInt("bkLanguage"));
		bk.setBkPages(rs.getInt("bkPages"));
		bk.setBkPrice(rs.getFloat("bkPrice"));
		bk.setBkDateIn(rs.getString("bkDateIn"));
		bk.setBkBrief(rs.getString("bkBrief"));
		bk.setBkCover(rs.getBytes("bkCover"));
		bk.setBkStatus(rs.getString("bkStatus"));
		return bk;
	}

	@Override
	public int add(AbstractModel object) throws Exception {
		if(object instanceof Book ==false){
			throw new Exception("Can only handle Book!");
		}
		//只能插入Book类 的数据
		
		Book bk = (Book) object;
		//将参数转换为Book类型
		
		String sql = "insert into TB_Book ("
										+ "bkCode,"
										+ "bkName,"
										+ "bkAuthor,"
										+ "bkPress,"
										+ "bkDatePress,"
										+ "bkISBN,"
										+ "bkCatalog,"
										+ "bkLanguage,"
										+ "bkPages,"
										+ "bkPrice,"
										+ "bkDateIn,"
										+ "bkBrief,"
										+ "bkCover,"
										+ "bkStatus)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		//SQL插入语句
		
		Object[] params = new Object[14];
		params[0] = bk.getBkCode();
		params[1] = bk.getBkName();
		params[2] = bk.getBkAuthor();
		params[3] = bk.getBkPress();
		params[4] = bk.getBkDatePress();
		params[5] = bk.getBkISBN();
		params[6] = bk.getBkCatalog();
		params[7] = bk.getBkLanguage();
		params[8] = bk.getBkPages();
		params[9] = bk.getBkPrice();
		params[10] = bk.getBkDateIn();
		params[11] = bk.getBkBrief();
		params[12] = bk.getBkCoverByte();
		params[13] = bk.getBkStatus();
		//得到每个需要插入的数据
		return SQLHelper.ExecSql(sql,params);
		
	}
	

	@Override
	public int delete(AbstractModel object) throws Exception {
		if(object instanceof Book ==false){
			throw new Exception("Can only handle Book!");			
		}
		Book bk = (Book)object;
		String sql="delete from TB_Book where bkID = ?";
		
		Object[] params  = new Object[]	{bk.getBkID()};
		int rows = SQLHelper.ExecSql(sql,params);
		return rows;
	}

	
	@Override
	public int update(AbstractModel object) throws Exception {
		if(object instanceof Book ==false){
			throw new Exception("You only can handle Book!");
		}
		Book bk = (Book)object;
		
		String sql = "update TB_Book set "
				+ "bkCode=?,"
				+ "bkName=?,"
				+ "bkAuthor=?,"
				+ "bkPress=?,"
				+ "bkDatePress=?,"
				+ "bkISBN=?,"
				+ "bkCatalog=?,"
				+ "bkLanguage=?,"
				+ "bkPages=?,"
				+ "bkPrice=?,"
				+ "bkDateIn=?,"
				+ "bkBrief=?,"
				+ "bkCover=?,"
				+ "bkStatus=?"
				+ " where bkID=?";

		Object[] params = new Object[]
			{
				bk.getBkCode(),
				bk.getBkName(),
				bk.getBkAuthor(),
				bk.getBkPress(),
				bk.getBkDatePress(),
				bk.getBkISBN(),
				bk.getBkCatalog(),
				bk.getBkLanguage(),
				bk.getBkPages(),
				bk.getBkPrice(),
				bk.getBkDateIn(),
				bk.getBkBrief(),
				bk.getBkCoverByte(),
				bk.getBkStatus(),
				bk.getBkID()
			};
		return SQLHelper.ExecSql(sql, params);
	}
	
	@Override
	public AbstractModel getObjectByID(int bkID) throws SQLException {
		Book bk = null;
		ResultSet rs = SQLHelper
				.getResultSet("select * from TB_Book where bkID = "+bkID);
		if(rs != null){
			if(rs.next()){
				bk=initBook(rs);
			}
			rs.close();
		}
		return bk;
	}

	@Override
	public AbstractModel[] getAllObjects() throws Exception {
		ArrayList<Book> objects = new ArrayList<Book>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Book");
		if(rs != null){
			while(rs.next()){
				Book bk  = initBook(rs);
				objects.add(bk);
			}
			rs.close();
		}
		Book[] types = new Book[objects.size()];
		objects.toArray(types);
		return types;
				
	}
	
	@Override
	public String[] getPrettyColumnNames() {
		// TODO Auto-generated method stub
		return this.dispColNames;
		//未实现
	}

	@Override
	public String[] getMethodNames() {
		// TODO Auto-generated method stub
		return this.methodNames;
		//未实现
	}

	@Override
		public String[] getAllObjectsName() throws Exception {
			// TODO Auto-generated method stub
		return null;
		//未实现
	}
	
}
