import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class testdb {
	
	public static void main(String[] args){
		//声明Connection对象，这里引入sql的包
		Connection con; 
		//驱动程序名
		String driver = "com.mysql.jdbc.Driver";
		//URL指向要访问的数据库名
		//PS这里jdbc:mysql://localhost:3306/empDb是数据库名
		//？是隔断
		//useSSL = true 是因为MySql在高版本需要指明是否需要进行SSL连接
		String url = "jdbc:mysql://localhost:3306/empDb?useSSL=true";
		//MySQL配置时的用户名
		String user = "root";
		//MySQL配置时的密码，给自己提个醒，记住你的密码root123！！！
		String password = "root123";
		//遍历查询结果集
		try{
			//加载驱动程序
			Class.forName(driver);
			//1.getConnection()方法，连接MySQL数据库
			con = DriverManager.getConnection(url, user, password);
			if(!con.isClosed()){
				System.out.println("Succeeded connecting to the Database!");
			}
			//2 创建statement类对象来执行SQL语句
			Statement statement = con.createStatement();
			//要执行的SQL语句
			String sql = "select *from emp";
			//3 ResultSet类，用来存放获取的结果集
			ResultSet rs = statement.executeQuery(sql);
			System.out.println("--------------------");
			System.out.println("result is :");
			System.out.println("--------------------");
			System.out.println("name" + "\t" + "job");
			System.out.println("--------------------");
			
			String job = null;
			String id = null;
			while(rs.next()){
				//获取emp里的job数据
				job = rs.getString("job");
				//获取emp里的name数据
				id = rs.getString("ename");
				//输出结果
				System.out.println(id + "\t" + job);
			}
			rs.close();
			con.close();
		}catch(ClassNotFoundException e){
			//数据库驱动类异常处理
			System.out.println("Sorry,can't find the Driver!");
			e.printStackTrace();
		}catch(SQLException e){
			//数据库连接失败异常处理
			e.printStackTrace();
		}finally{
			System.out.println("数据库数据获取成功！");
		}
	}
}
