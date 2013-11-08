package test;

import java.sql.Connection;
import java.sql.SQLException;

import soen387.jdbcUtil.JdbcUtilViaSSH;
import soen387.jdbcUtil.SSHjdbcSession;

public class JDBCViaSSHTest {

	public static void main(String[] args) throws SQLException {

		SSHjdbcSession ssHsession = JdbcUtilViaSSH.getConnection();

		// Get sql connection from sshSession
		Connection connection = ssHsession.getConnection();

		if (connection != null) {

			System.out.println(connection.getCatalog());

			System.out.println("Connection : true");
			JdbcUtilViaSSH.close(null, null, ssHsession);
		} else {
			System.out.println("Connection : false");

		}

	}

}
