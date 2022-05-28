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
 * Servlet implementation class editInvoice
 */
@WebServlet("/editInvoice")
public class editInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editInvoice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sl_no = Integer.parseInt(request.getParameter("sl_no"));
		String invoiceCurrency = request.getParameter("invoiceCurrency");
		String custPaymentTerms  = request.getParameter("cust_payment_terms");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/grey_goose?zeroDateTimeBehavior=convertToNull","root","Anjali5489md@");
			PreparedStatement ps = connection.prepareStatement("Update winter_internship set invoice_currency = '"+ invoiceCurrency +"' , cust_payment_terms = '" + custPaymentTerms + "' where sl_no = "+ sl_no);
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
