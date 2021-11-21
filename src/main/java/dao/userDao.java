package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.user;
import dataBase.DataBase;

public class userDao {

	public int signUpUsr(user usr) throws ClassNotFoundException {
		String INSERT_USERS_SQL = "INSERT INTO users"
				+ "  (nom, prenom, username, password) VALUES "
				+ " (?, ?, ?, ?);";

		int result = 0;
		System.out.println("avant conn");
		try (Connection connection = DataBase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			System.out.println("apres");
			preparedStatement.setString(1, usr.getNom());
			preparedStatement.setString(2, usr.getPrenom());
			preparedStatement.setString(3, usr.getUsername());
			preparedStatement.setString(4, usr.getPassword());
			
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			DataBase.printSQLException(e);
		}
		return result;
	}

}
