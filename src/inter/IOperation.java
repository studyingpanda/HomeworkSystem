package inter;
import java.util.List;
//�淶������ִ�ж����Ӧ���ݿ�������������
public interface IOperation {
	public int selectCount();//��ȡ����
	public List selectAll();//ѡȡ������������
	public List selectPage(int offset,int size);//��ҳ��ʾ����һ������Ϊ��ʼλ�ã���id���򣩣��ڶ�������Ϊÿҳ��ʾ����
	public Object selectById(int id);//��id��ȡһ����¼
	public int add(Object obj);//���һ������
	public int deleteById(int id);//��idɾ��һ����¼
	public int update(Object obj);//��obj�������Ϣ�޸�һ����¼(��obj��id�����Ҫ�޸ĵļ�¼)
}