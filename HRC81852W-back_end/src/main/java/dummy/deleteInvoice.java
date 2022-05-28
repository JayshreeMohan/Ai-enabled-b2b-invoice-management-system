package dummy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleteInvoice
 */
@WebServlet("/deleteInvoice")
public class deleteInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteInvoice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sl_noArray = request.getParameter("sl_no");
		String curr = sl_noArray.substring(1,sl_noArray.length()-1);
		String arr[] = curr.split(",");
		int res[] = new int[arr.length];
		for(int i = 0 ; i < arr.length; i++)
		    res[i] = Integer.parseInt(arr[i].substring(1, arr[i].length()-1));
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < res.length; i++)
		{
			sb.append(res[i]);
			if(i != res.length-1)
				sb.append(" , ");
			
		}
		String toAdd = sb.toString();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/grey_goose?zeroDateTimeBehavior=convertToNull","root","Anjali5489md@");
			PreparedStatement ps = connection.prepareStatement("DELETE FROM winter_internship WHERE sl_no IN ( " + toAdd + " )");
			int rs = ps.executeUpdate();
			response.getWriter().print(rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		response.setHeader("Access-Control-Allow-Origin", "*");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sl_noArray = request.getParameter("sl_no");
		String curr = sl_noArray.substring(1,sl_noArray.length()-1);
		String arr[] = curr.split(",");
		int res[] = new int[arr.length];
		for(int i = 0 ; i < arr.length; i++)
		    res[i] = Integer.parseInt(arr[i].substring(1, arr[i].length()-1));
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < res.length; i++)
		{
			sb.append(res[i]);
			if(i != res.length-1)
				sb.append(" , ");
			
		}
		String toAdd = sb.toString();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/grey_goose?zeroDateTimeBehavior=convertToNull","root","Tushar!11");
			PreparedStatement ps = connection.prepareStatement("DELETE FROM winter_internship WHERE sl_no IN ( " + toAdd + " )");
			int rs = ps.executeUpdate();
			response.getWriter().print(rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
	}

}
