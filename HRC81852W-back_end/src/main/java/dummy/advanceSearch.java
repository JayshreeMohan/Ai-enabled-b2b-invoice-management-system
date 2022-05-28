package dummy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class advanceSearch
 */
@WebServlet("/advanceSearch")
public class advanceSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public advanceSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<Object, Object> Response = new HashMap<>();
		ArrayList<InvoiceData> invoiceData = new ArrayList<>();
//		int page = Integer.parseInt(request.getParameter("page")) - 1;
		String document_id = request.getParameter("document_id");
		String invoice_id = request.getParameter("invoice_id");
		String customer_number = request.getParameter("customer_number");
		String business_year = request.getParameter("business_year");
		int numberOfQueriesPerPage = 10;
		int offset = 1 * numberOfQueriesPerPage;
		String query = "SELECT * FROM winter_internship WHERE doc_id LIKE '%"+document_id+"%' AND invoice_id LIKE '%"+invoice_id+"%' AND cust_number LIKE '%"+customer_number+"%' AND buisness_year LIKE '%"+business_year+"%' ORDER BY 'sl_no' LIMIT 0,10;";
		System.out.println(query);
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/grey_goose?zeroDateTimeBehavior=convertToNull","root","Anjali5489md@");
			PreparedStatement ps = connection.prepareStatement(query);
//			ps.setInt(1, offset);
//			ps.setInt(2, numberOfQueriesPerPage);
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
