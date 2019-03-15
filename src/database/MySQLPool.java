/**
 * database�����������ݿ��������İ�������MySQLPool��MySQLHandler��
 * MySQLPool�����ݿ����ӳ���
 * MySQLHandler:ʹ�����ݿ����ӳص�mysql���ݿ������
 */
package database;
import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import exception.MyException;
/**
 * MySQL���ݿ��Զ������ӳ�
 * @author è��
 * @date 2017.2.6�޸�
 */
public class MySQLPool {
    private static LinkedList<Connection> pool = new LinkedList<Connection>(); 
    private static int maxCount=1;//���������
    static{//��ʼ��
    	for(int i=0;i<maxCount;i++){
			MySQLHandler handler=new MySQLHandler();
            Connection connection = handler.buildConnection();
            pool.add(connection);
        }    
    }
    public static Connection getConnecton() throws MyException{
    	if(pool.size()==0)//��������
    	{
    		throw new MyException(new Date(),"���ݿ����ӳ���Դ��ȱ�������ӿɷ���","���ݿ����Ӵ���");
    	}
    	else{
    		return pool.remove(0);//ɾ����һ�����󲢷���
    	}
    }
    public static void release(Connection connection){//ʹ����Ĺ黹������
        pool.add(connection);
    }
}
