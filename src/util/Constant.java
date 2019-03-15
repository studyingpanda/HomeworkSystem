/**
 * util包：工具包
 * Constant类：保存常量信息的类
 */
package util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
public class Constant {
	//roleMenu用于保存角色及对应的菜单信息
	public static HashMap<String,String[][]> RoleMenu=new HashMap<String,String[][]>();
	//pageSize用于保存不同实体列表页面显示实体个数信息（每页多少个）
	public static HashMap<String,Integer> PageSize=new HashMap<String,Integer>();
	//JobScore用于保存不同的job_score状态
	public static HashMap<String,String> JobScore=new HashMap<String,String>();
	//使用static代码块对roleMenu进行初始化
	static{
		//注意，二位数组中的每一组表示一个菜单的信息，又通过map建立了角色名和菜单直接的对应关系
		RoleMenu.put("校长", new String[][]{
				{"人员管理","view","User"},//由具体的地址，变为抽象的参数
				{"课程查看","view","Course"}
		});
		RoleMenu.put("教师", new String[][]{
				{"课程管理","view","Course"},
				{"批阅作业","view","Job"}
		});
		RoleMenu.put("学生", new String[][]{
				{"做作业","view","Job"},
				{"选课","view","Course"}
		});
		//初始化页面列表个数
		PageSize.put("Course", 5);
		PageSize.put("Job", 5);
		PageSize.put("Lesson", 5);
		PageSize.put("Role", 5);
		PageSize.put("User", 5);
		PageSize.put("Work", 5);
		//-2作业未提交 -1已提交未阅 0优秀 1良好 2合格 3不合格
		JobScore.put("-2", "作业未提交");
		JobScore.put("-1", "已提交未阅");
		JobScore.put("0", "优秀");
		JobScore.put("1", "良好");
		JobScore.put("2", "合格");
		JobScore.put("3", "不合格");
	}	
	public static String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String dateValue = sdf.format(now);
		return dateValue;
	}
}