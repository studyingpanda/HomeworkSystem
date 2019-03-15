/**
 * util�������߰�
 * Constant�ࣺ���泣����Ϣ����
 */
package util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
public class Constant {
	//roleMenu���ڱ����ɫ����Ӧ�Ĳ˵���Ϣ
	public static HashMap<String,String[][]> RoleMenu=new HashMap<String,String[][]>();
	//pageSize���ڱ��治ͬʵ���б�ҳ����ʾʵ�������Ϣ��ÿҳ���ٸ���
	public static HashMap<String,Integer> PageSize=new HashMap<String,Integer>();
	//JobScore���ڱ��治ͬ��job_score״̬
	public static HashMap<String,String> JobScore=new HashMap<String,String>();
	//ʹ��static������roleMenu���г�ʼ��
	static{
		//ע�⣬��λ�����е�ÿһ���ʾһ���˵�����Ϣ����ͨ��map�����˽�ɫ���Ͳ˵�ֱ�ӵĶ�Ӧ��ϵ
		RoleMenu.put("У��", new String[][]{
				{"��Ա����","view","User"},//�ɾ���ĵ�ַ����Ϊ����Ĳ���
				{"�γ̲鿴","view","Course"}
		});
		RoleMenu.put("��ʦ", new String[][]{
				{"�γ̹���","view","Course"},
				{"������ҵ","view","Job"}
		});
		RoleMenu.put("ѧ��", new String[][]{
				{"����ҵ","view","Job"},
				{"ѡ��","view","Course"}
		});
		//��ʼ��ҳ���б����
		PageSize.put("Course", 5);
		PageSize.put("Job", 5);
		PageSize.put("Lesson", 5);
		PageSize.put("Role", 5);
		PageSize.put("User", 5);
		PageSize.put("Work", 5);
		//-2��ҵδ�ύ -1���ύδ�� 0���� 1���� 2�ϸ� 3���ϸ�
		JobScore.put("-2", "��ҵδ�ύ");
		JobScore.put("-1", "���ύδ��");
		JobScore.put("0", "����");
		JobScore.put("1", "����");
		JobScore.put("2", "�ϸ�");
		JobScore.put("3", "���ϸ�");
	}	
	public static String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String dateValue = sdf.format(now);
		return dateValue;
	}
}