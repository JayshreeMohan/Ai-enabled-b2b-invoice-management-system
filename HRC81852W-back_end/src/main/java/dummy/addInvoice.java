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
 * Servlet implementation class addInvoice
 */
@WebServlet("/addInvoice")
public class addInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addInvoice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String business_code = request.getParameter("business_code");
		String cust_number = request.getParameter("cust_number");
		String clear_date  = request.getParameter("clear_date");
		String buisness_year = request.getParameter("buisness_year");
		String document_id = request.getParameter("document_id");
		String posting_date  = request.getParameter("posting_date");
		String document_create_date = request.getParameter("document_create_date");
		String due_date = request.getParameter("due_date");
		String invoice_currency  = request.getParameter("invoice_currency");
		String document_type = request.getParameter("document_type");
		String posting_id  = request.getParameter("posting_id");
		String total_open_amount = request.getParameter("total_open_amount");
		String baseline_create_date = request.getParameter("baseline_create_date");
		String customer_payment_terms  = request.getParameter("customer_payment_terms");
		String invoice_id  = request.getParameter("invoice_id");
		
		
		String query = "INSERT INTO winter_internship (business_code, cust_number, clear_date, buisness_year, doc_id, posting_date, document_create_date, due_in_date, invoice_currency, document_type, posting_id, total_open_amount, baseline_create_date, cust_payment_terms, invoice_id) "
				+ "VALUES ('"+ business_code +"', "+ cust_number + ", '" + clear_date + "', " + buisness_year + ", '" +
				document_id + "', '" + posting_date + "', '" + document_create_date + "', '" + due_date + "', '" +
				invoice_currency + "', '"+	document_type + "', " + posting_id + ", " + total_open_amount +", '"+ 
				baseline_create_date+"', '" + customer_payment_terms + "', " + invoice_id +" )";
		System.out.println(query);
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/grey_goose?zeroDateTimeBehavior=convertToNull","root","Anjali5489md@");
			PreparedStatement ps = connection.prepareStatement(query);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
