/**
 * database包：保存数据库操作基类的包，包含MySQLPool和MySQLHandler类
 * MySQLPool：数据库连接池类
 * MySQLHandler:使用数据库连接池的mysql数据库操作类
 */
package database;
import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import exception.MyException;
/**
 * MySQL数据库自定义连接池
 * @author 猫哥
 * @date 2017.2.6修改
 */
public class MySQLPool {
    private static LinkedList<Connection> pool = new LinkedList<Connection>(); 
    private static int maxCount=1;//最大连接数
    static{//初始化
    	for(int i=0;i<maxCount;i++){
			MySQLHandler handler=new MySQLHandler();
            Connection connection = handler.buildConnection();
            pool.add(connection);
        }    
    }
    public static Connection getConnecton() throws MyException{
    	if(pool.size()==0)//分配完了
    	{
    		throw new MyException(new Date(),"数据库连接池资源短缺，无连接可分配","数据库连接错误");
    	}
    	else{
    		return pool.remove(0);//删除第一个对象并返回
    	}
    }
    public static void release(Connection connection){//使用完的归还给池子
        pool.add(connection);
    }
}
