package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Etudiant;
import dataBase.DataBase;

public class etudiantDao {

	private static final String INSERT_ETUDIANTS_SQL = "INSERT INTO etudiant" + "  (cin, nom, prenom, filiere,  emprunt) VALUES " + " (?, ?, ?, ?, ?);";
	private static final String SELECT_ETUDIANT_BY_ID = "select id, CIN, nom, prenom, filiere, emprunt from etudiant where id =?";
	private static final String SELECT_ALL_ETUDIANTS = "select * from etudiant";
	private static final String DELETE_ETUDIANT_BY_ID = "delete from etudiant where id = ?;";
	private static final String UPDATE_ETUDIANT = "update etudiant set cin = ?, nom= ?, prenom =?, filiere =?, emprunt = ? where id = ?;";

	public etudiantDao() {
	}

	public void insertEtudiant(Etudiant etudiant) throws SQLException {
		System.out.println(INSERT_ETUDIANTS_SQL);
		try (Connection connection = DataBase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ETUDIANTS_SQL)) {
			preparedStatement.setString(1, etudiant.getCin());
			preparedStatement.setString(2, etudiant.getNom());
			preparedStatement.setString(3, etudiant.getPrenom());
			preparedStatement.setString(4, etudiant.getFiliere());
			preparedStatement.setInt(5, 0);
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			DataBase.printSQLException(exception);
		}
	}

	public Etudiant selectEtudiant(int etudiantId) {
		Etudiant etudiant = null;
		try (Connection connection = DataBase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ETUDIANT_BY_ID);) {
			preparedStatement.setInt(1, etudiantId);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String cin = rs.getString("cin");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String filiere = rs.getString("filiere");
				int emprunt = rs.getInt("emprunt");
				etudiant = new Etudiant(id, cin, nom, prenom, filiere, emprunt);
			}
		} catch (SQLException exception) {
			DataBase.printSQLException(exception);
		}
		return etudiant;
	}

	public List<Etudiant> selectAllEtudiants() {

		List<Etudiant> etudiant = new ArrayList<>();

		try (Connection connection = DataBase.getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ETUDIANTS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String cin = rs.getString("cin");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String filiere = rs.getString("filiere");
				int emprunt = rs.getInt("emprunt");
				etudiant.add(new Etudiant(id, cin, nom, prenom, filiere, emprunt));
			}
		} catch (SQLException exception) {
			DataBase.printSQLException(exception);
		}
		return etudiant;
	}

	public boolean deleteEtudiant(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = DataBase.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ETUDIANT_BY_ID);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateEtudiant(Etudiant etudiant) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = DataBase.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ETUDIANT);) {
			statement.setString(1, etudiant.getCin());
			statement.setString(2, etudiant.getNom());
			statement.setString(3, etudiant.getPrenom());
			statement.setString(4, etudiant.getFiliere());
			statement.setInt(5, etudiant.getEmprunt());
			statement.setInt(6, etudiant.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
}
