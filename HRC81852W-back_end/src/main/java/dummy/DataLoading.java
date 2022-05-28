package dummy;

import java.sql.*;
import java.util.*;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class DataLoading
 */
@WebServlet("/DataLoading")
public class DataLoading extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataLoading() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<Object, Object> Response = new HashMap<>();
		ArrayList<InvoiceData> invoiceData = new ArrayList<>();
		int page = Integer.parseInt(request.getParameter("page")) - 1;
		int numberOfQueriesPerPage = 10;
		int offset = page * numberOfQueriesPerPage;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/grey_goose?zeroDateTimeBehavior=convertToNull","root","Anjali5489md@");
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM winter_internship Limit ?, ?");
			ps.setInt(1, offset);
			ps.setInt(2, numberOfQueriesPerPage);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				InvoiceData data = new InvoiceData(
						rs.getInt("sl_no"),
						rs.getString("business_code"),
						rs.getInt("cust_number"),
						rs.getDate("clear_date"),
						rs.getString("buisness_year"),
						rs.getString("doc_id"),
						rs.getDate("posting_date"),
						rs.getDate("document_create_date"),
						rs.getDate("due_in_date"),
						rs.getString("invoice_currency"),
						rs.getString("document_type"),
						rs.getInt("posting_id"),
						rs.getString("area_business"),
						rs.getDouble("total_open_amount"),
						rs.getDate("baseline_create_date"),
						rs.getString("cust_payment_terms"),
						rs.getInt("invoice_id"),
						rs.getInt("isOpen"),
						rs.getString("aging_bucket"),
						rs.getInt("is_deleted")
						);
				invoiceData.add(data);
			}
			Response.put("invoice", invoiceData);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(Response);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().append(jsonResponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
