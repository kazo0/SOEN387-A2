package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import soen387.jdbcUtil.JdbcUtilViaSSH;
import soen387.jdbcUtil.SSHjdbcSession;

public class TestJDBCSelectStatement {

	public static void main(String[] args) throws SQLException {

		String id = "5";

		String sql = "select * from users where id > " + id + " or true";

		SSHjdbcSession ssHsession = JdbcUtilViaSSH.getConnection();

		// Get sql connection from sshSession
		Connection connection = ssHsession.getConnection();

		Statement statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery(sql);

		JdbcUtilViaSSH.printRs(resultSet);

		JdbcUtilViaSSH.close(resultSet, statement, ssHsession);

	}

}
