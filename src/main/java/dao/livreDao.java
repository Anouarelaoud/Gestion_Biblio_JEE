package dao;

import java.sql.Connection;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Livre;
import dataBase.DataBase;

public class livreDao {

	private static final String INSERT_LIVRES_SQL = "INSERT INTO livre" + "  (num, titre, date, stock) VALUES " + " (?, ?, ?, ?);";
	private static final String SELECT_LIVRE_BY_ID = "select id, num, titre, date, stock from livre where id =?";
	private static final String SELECT_ALL_LIVRES = "select * from livre";
	private static final String DELETE_LIVRE_BY_ID = "delete from livre where id = ?;";
	private static final String UPDATE_LIVRE = "update livre set num = ?, titre = ?, date = ?, stock = ? where id = ?;";

	public livreDao() {
	}

	public void insertLivre(Livre livre) throws SQLException {
		System.out.println(INSERT_LIVRES_SQL);
		try (Connection connection = DataBase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIVRES_SQL)) {
			preparedStatement.setInt(1, livre.getNum());
			preparedStatement.setString(2, livre.getTitre());
			preparedStatement.setObject(3, livre.getDate());
			preparedStatement.setInt(4, livre.getStock());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			DataBase.printSQLException(exception);
		}
	}

	public Livre selectLivre(int livreId) {
		Livre livre = null;
		try (Connection connection = DataBase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIVRE_BY_ID);) {
			preparedStatement.setLong(1, livreId);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int num = rs.getInt("num");
				String titre = rs.getString("titre");
				LocalDate date = rs.getDate("date").toLocalDate();
				int stock = rs.getInt("stock");
				livre = new Livre(id, num, titre, date, stock);
			}
		} catch (SQLException exception) {
			DataBase.printSQLException(exception);
		}
		return livre;
	}

	public List<Livre> selectAllLivres() {

		List<Livre> livre = new ArrayList<>();

		try (Connection connection = DataBase.getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIVRES);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int num = rs.getInt("num");
				String titre = rs.getString("titre");
				LocalDate date = rs.getDate("date").toLocalDate();
				int stock = rs.getInt("stock");
				livre.add(new Livre(id, num, titre, date, stock));
			}
		} catch (SQLException exception) {
			DataBase.printSQLException(exception);
		}
		return livre;
	}

	public boolean deleteLivre(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = DataBase.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_LIVRE_BY_ID);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateLivre(Livre livre) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = DataBase.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_LIVRE);) {
			statement.setInt(1, livre.getNum());
			statement.setString(2, livre.getTitre());
			statement.setObject(3, livre.getDate());
			statement.setInt(4, livre.getStock());
			statement.setInt(5, livre.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
}
