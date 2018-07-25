
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
public class DataContext {
	private Connection connection;
	private Statement statement;

	public DataContext()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PizzaShop", "root", "");
			this.statement = this.connection.createStatement();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}

	public Vector<Vector<String>> getOderList()
	{
		try
		{
			String sql = "SELECT * FROM pizz";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<Vector<String>> orderList = new Vector<Vector<String>>();
			while(rs.next())
			{
				Vector<String> cont = new Vector<String>();
				cont.add(rs.getString("OrderId"));
				cont.add(rs.getString("PizzaType"));
				cont.add(rs.getString("Quantity"));

				orderList.add(cont);
			}
			return orderList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	public boolean Save(Order contact)
	{
		try
		{
			String sql = "INSERT INTO pizz VALUES (null,'" + contact.getPizzaType() +  "', '" + contact.getQuantity() +    "')";
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

	public boolean AddEmpInfo(Employee cate)
	{
		try
		{
			String sql = "INSERT INTO employees VALUES (null,'" + cate.getName() +  "', '" + cate.getPhone()+ "')";
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

	public Vector<Vector<String>> getLoginInfo(String username, String password)
	{
		try
		{
			String sql = ("SELECT * FROM login WHERE username LIKE '" + username + "' AND password LIKE '" + password + "'");;
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<Vector<String>> loginList = new Vector<Vector<String>>();
			while(rs.next())
			{
				Vector<String> log = new Vector<String>();
				log.add(rs.getString("username"));
				log.add(rs.getString("password"));
				loginList.add(log);
			}
			return loginList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	public Vector<Vector<String>> getEmpListAsString()
	{
		try
		{
			String sql = "SELECT * FROM employees";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<Vector<String>> categoryList = new Vector<Vector<String>>();
			while(rs.next())
			{
				Vector<String> cont = new Vector<String>();
				cont.add(rs.getString("EmpId"));
				cont.add(rs.getString("EmpName"));
				cont.add(rs.getString("EmpPhone"));
				categoryList.add(cont);
			}
			return categoryList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}


	public boolean EmpUpdate(Employee emp,String Nname, String NPhone)
	{
		try
		{
			String sql = "UPDATE employees SET EmpName = '" + Nname + "', EmpPhone = '" + NPhone + "' WHERE EmpName LIKE '" + emp.getName() + "' AND EmpPhone LIKE '" + emp.getPhone() + "'";
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

	public boolean EmpDelete(String empId, String empName, String empPhone)
	{
		try
		{
			String sql =  "DELETE FROM employees where EmpId like '" + empId + "' AND EmpName like '" + empName + "' AND EmpPhone LIKE '" + empPhone + "'";
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

	public Vector<String> getEmpId() {
		try
		{
			String sql = "SELECT EmpId FROM employees";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<String> categoryList = new Vector<String>();
			while(rs.next())
			{
				categoryList.add(rs.getString("EmpId"));
			}
			return categoryList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
    }

	public boolean OrderUpdate(Order cont,String uPizzaType,String uQuantity)
	{
		try
		{
			String sql = ("UPDATE pizz SET PizzaType = '" + uPizzaType + "', Quantity = '" + uQuantity + "' where PizzaType ='" + cont.getPizzaType() + "'AND Quantity='" + cont.getQuantity() +"'");
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

	public boolean OrderDelete(String uOrderId,String uPizzaType,String uQuantity)
	{
		try
		{
			String sql =  "DELETE FROM pizz where OrderId Like '" + uOrderId + "' AND PizzaType LIKE '" + uPizzaType + "' AND Quantity LIKE '" + uQuantity + "'";
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
}

