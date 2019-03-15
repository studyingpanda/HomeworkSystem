package database;
import java.sql.*;//�������ݿ�������
import java.util.ArrayList;
import java.util.Date;
import exception.MyException;
/**
 * MysqlHandler MySQL���ݿ�����࣬ʹ�����ݿ����ӳ�
 * @author è��
 * @date 2016.1.9
 * @modify 2016.2.6 MysqlHandler-MySQLHandler,use MyException
 */
public class MySQLHandler{   
	//��Ȼ������Ϥ�������ر�����
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
    //�������ݿ����ӣ��˴��������ṩԭʼ���ӣ����������ʹ��
    public Connection buildConnection() {        
    	 String driver = "com.mysql.jdbc.Driver";
         String url = "jdbc:mysql://localhost:3306/homework?useUnicode=true&characterEncoding=utf-8";//���ݿ������ַ���
         String user = "root";
         String password = "Pass1234";
         try{   
        	Class.forName(driver);//������������
        	conn=DriverManager.getConnection(url,user,password);
         }
         catch(Exception ex){
        	//��ʱ������δthrow����������־ģ��������־��
        	new MyException(new Date(),ex.getMessage(),"���ݿ����ӽ����쳣");
         }
        return conn;
    }
    //����1������ɾ�Ĳ顱�У�����ɾ���Ķ���ִ��sql��䣬���践��ResultSet���������������Ϊһ������
    public int execute(String sql) throws MyException{
        try {
        	if(conn==null)//δ����
        		conn=MySQLPool.getConnecton();//��ʱ�ӳ���ȡ����ˬ��
        	stmt=conn.createStatement();
            int affectedCount = stmt.executeUpdate(sql);//�˴�����ִ��stmt����Ĳ���
            return affectedCount;//������յ�Ӱ�������
        }
        catch (Exception ex) {
    		throw new MyException(new Date(),ex.getMessage(),"���ݿ����Ӵ���");
        }
    }
    //����2������ǲ�ѯ���践�ؽ����
    public ResultSet query(String sql)throws Exception{
    	try{
    		if(conn==null)//δ����
    			conn=MySQLPool.getConnecton();//��ʱ�ӳ���ȡ����ˬ��
	        stmt=conn.createStatement();
	        rs = stmt.executeQuery(sql);//ִ��pstmt�ж���Ĳ�ѯ
	        return rs;//����������� 
	    }
    	 catch (Exception ex) {
     		throw new MyException(new Date(),ex.getMessage(),"���ݿ����Ӵ���");
         }
    }
    //����3���ͷ���Դ
    public void sayGoodbye(){
        if(rs!=null){//�رս������������ر�Ҳ�˷�
            try {
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(stmt!=null){//�ر�Statement����Ҫ�˷�
            try {
            	stmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    	//�˴�ע�⣬conn�ڳ��й������ùرգ�����ع����ӳؼ���ʹ��
        MySQLPool.release(conn);
    }
   
    public Boolean doTransaction(ArrayList<String> sqlList) throws MyException{
    	try{
    		if(conn==null)//δ����
    			conn=MySQLPool.getConnecton();//��ʱ�ӳ���ȡ����ˬ��
	        //��ʼ����(����mysql�Զ��ύ�ˣ�����Ա������ʱһ���ύ���߻��ǻ���)
    		conn.setAutoCommit(false);
    		for(String sql:sqlList){
    			stmt=conn.createStatement();
    			stmt.executeUpdate(sql);
    		}
    		//�˴��ύ��������м�����ִ�����û����ȫ��һ��ִ�У�����д�����catch
    		conn.commit();
    		return true;
	    }
    	catch (Exception ex) {
    		try {
    			//����д�����ع鵽����sqlδִ��״̬
				conn.rollback();
			} catch (SQLException sqlEx) {
				new MyException(new Date(),sqlEx.getMessage(),"����ع�����");
			}  
    		throw new MyException(new Date(),ex.getMessage(),"���ݿ�ִ�д���");
        }finally{
        	MySQLPool.release(conn);//����黹
        }
    }
}
