package database;
import java.sql.*;//导入数据库相关类库
import java.util.ArrayList;
import java.util.Date;
import exception.MyException;
/**
 * MysqlHandler MySQL数据库管理类，使用数据库连接池
 * @author 猫哥
 * @date 2016.1.9
 * @modify 2016.2.6 MysqlHandler-MySQLHandler,use MyException
 */
public class MySQLHandler{   
	//依然是那熟悉的三个必备参数
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
    //建立数据库连接，此处仅用于提供原始连接，供放入池中使用
    public Connection buildConnection() {        
    	 String driver = "com.mysql.jdbc.Driver";
         String url = "jdbc:mysql://localhost:3306/homework?useUnicode=true&characterEncoding=utf-8";//数据库连接字符串
         String user = "root";
         String password = "Pass1234";
         try{   
        	Class.forName(driver);//加载驱动程序
        	conn=DriverManager.getConnection(url,user,password);
         }
         catch(Exception ex){
        	//暂时不处理（未throw），加上日志模块后记在日志里
        	new MyException(new Date(),ex.getMessage(),"数据库连接建立异常");
         }
        return conn;
    }
    //操作1，“增删改查”中，增、删、改都是执行sql语句，无需返回ResultSet结果集，所以设置为一个方法
    public int execute(String sql) throws MyException{
        try {
        	if(conn==null)//未分配
        		conn=MySQLPool.getConnecton();//用时从池中取，很爽快
        	stmt=conn.createStatement();
            int affectedCount = stmt.executeUpdate(sql);//此处真正执行stmt定义的操作
            return affectedCount;//这个是收到影响的行数
        }
        catch (Exception ex) {
    		throw new MyException(new Date(),ex.getMessage(),"数据库连接错误");
        }
    }
    //操作2，如果是查询，需返回结果集
    public ResultSet query(String sql)throws Exception{
    	try{
    		if(conn==null)//未分配
    			conn=MySQLPool.getConnecton();//用时从池中取，很爽快
	        stmt=conn.createStatement();
	        rs = stmt.executeQuery(sql);//执行pstmt中定义的查询
	        return rs;//将结果集返回 
	    }
    	 catch (Exception ex) {
     		throw new MyException(new Date(),ex.getMessage(),"数据库连接错误");
         }
    }
    //操作3，释放资源
    public void sayGoodbye(){
        if(rs!=null){//关闭结果集，这个不关闭也浪费
            try {
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(stmt!=null){//关闭Statement，不要浪费
            try {
            	stmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    	//此处注意，conn在池中管理，不用关闭，用完回归连接池继续使用
        MySQLPool.release(conn);
    }
   
    public Boolean doTransaction(ArrayList<String> sqlList) throws MyException{
    	try{
    		if(conn==null)//未分配
    			conn=MySQLPool.getConnecton();//用时从池中取，很爽快
	        //开始事务(不让mysql自动提交了，程序员做主何时一起提交或者还是回退)
    		conn.setAutoCommit(false);
    		for(String sql:sqlList){
    			stmt=conn.createStatement();
    			stmt.executeUpdate(sql);
    		}
    		//此处提交事务，如果中间所有执行语句没错，则全部一起执行，如果有错跳到catch
    		conn.commit();
    		return true;
	    }
    	catch (Exception ex) {
    		try {
    			//如果有错误，则回归到所有sql未执行状态
				conn.rollback();
			} catch (SQLException sqlEx) {
				new MyException(new Date(),sqlEx.getMessage(),"事务回滚错误");
			}  
    		throw new MyException(new Date(),ex.getMessage(),"数据库执行错误");
        }finally{
        	MySQLPool.release(conn);//用完归还
        }
    }
}
