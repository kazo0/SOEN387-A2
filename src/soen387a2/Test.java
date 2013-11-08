package soen387a2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Game gm = GameIdentiyMap.getInstance().get(1);
		if (gm == null) {
			SSHjdbcSession ssHsession = JdbcUtilViaSSH.getConnection();
			Connection connection = ssHsession.getConnection();
			String query = "select * from Games where id = 1"; 
			ResultSet rs = DBAccess.ExecuteQuery(connection, query);
			JdbcUtilViaSSH.printRs(rs);
			int d = 5;
			int s  = d + 3;
			//gm = new Game(db param);
			//Add to Identity map
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
