package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import beans.Emprunt;
import beans.Etudiant;
import beans.Livre;
import dataBase.DataBase;

public class empruntDao {

	private static final String INSERT_EMPRUNTS_SQL = "INSERT INTO emprunt" + "  (etudiantID, livreId, remis) VALUES " + " (?, ?, ?);";
	private static final String SELECT_EMPRUNT_BY_ID = "select id, etudiantID, livreId, remis from emprunt where id =?";
	private static final String SELECT_ALL_EMPRUNTS = "select * from emprunt";
	private static final String DELETE_EMPRUNT_BY_ID = "delete from emprunt where id = ?;";
	private static final String UPDATE_EMPRUNT = "update emprunt set etudiantId = ?, livreId = ?, remis = ? where id = ?;";
	private static final String SELECT_ALL_ETUDIANTS = "select * from etudiant";
	private static final String SELECT_ALL_LIVRES = "select * from livre";
	private static final String SELECT_ETUDIANT_BY_ID = "select * from etudiant where id =?";
	private static final String SELECT_LIVRE_BY_ID = "select * from livre where id =?";
	private static final String UPDATE_ETUDIANT = "update etudiant set emprunt = ? where id = ?;";
	private static final String UPDATE_LIVRE = "update livre set stock = ? where id = ?;";
	private static final String SELECT_ALL_EMPRUNTS_BY_ETUDIANT_ID = "select * from emprunt where etudiantId = ?";
	
	public empruntDao() {
	}

	public void insertEmprunt(Emprunt emprunt) throws SQLException {
//		System.out.println(INSERT_EMPRUNTS_SQL);
		try (Connection connection = DataBase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPRUNTS_SQL)) {
			preparedStatement.setInt(1, emprunt.getEtudiantId());
			preparedStatement.setInt(2, emprunt.getLivreId());
			preparedStatement.setBoolean(3, false);
//			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			DataBase.printSQLException(exception);
		}
	}

	public Emprunt selectEmprunt(int empruntId) {
		Emprunt emprunt = null;
		try (Connection connection = DataBase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPRUNT_BY_ID);) {
			preparedStatement.setInt(1, empruntId);
//			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int etudiantId = rs.getInt("etudiantId");
				int livreId = rs.getInt("livreId");
				Boolean remis = rs.getBoolean("remis");
				emprunt = new Emprunt(id, etudiantId, livreId, remis);
			}
		} catch (SQLException exception) {
			DataBase.printSQLException(exception);
		}
		return emprunt;
	}

	public List<Emprunt> selectAllEmprunts() {

		List<Emprunt> emprunt = new ArrayList<>();

		try (Connection connection = DataBase.getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPRUNTS);) {
//			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int etudiantId = rs.getInt("etudiantId");
				int livreId = rs.getInt("livreId");
				Boolean remis = rs.getBoolean("remis");
				emprunt.add(new Emprunt(id, etudiantId, livreId, remis));
			}
		} catch (SQLException exception) {
			DataBase.printSQLException(exception);
		}
		return emprunt;
	}

	public boolean deleteEmprunt(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = DataBase.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_EMPRUNT_BY_ID);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateEmprunt(Emprunt emprunt) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = DataBase.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_EMPRUNT);) {
			statement.setInt(1, emprunt.getEtudiantId());
			statement.setInt(2, emprunt.getLivreId());
			statement.setBoolean(3, emprunt.getRemis());
			statement.setInt(4, emprunt.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public List<Etudiant> selectAllEtudiants() {

		List<Etudiant> etudiant = new ArrayList<>();

		try (Connection connection = DataBase.getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ETUDIANTS);) {
//			System.out.println(preparedStatement);
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
	
	public List<Livre> selectAllLivres() {

		List<Livre> livre = new ArrayList<>();

		try (Connection connection = DataBase.getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIVRES);) {
//			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int num = rs.getInt("num");
				String titre = rs.getString("titre");
//				LocalDate date = rs.getObject("date");
				int stock = rs.getInt("stock");
				livre.add(new Livre(id, num, titre, LocalDate.now(), stock));
			}
		} catch (SQLException exception) {
			DataBase.printSQLException(exception);
		}
		return livre;
	}
	
	public Etudiant selectEtudiantById(int etudiantId) {
		Etudiant etudiant = null;
		try (Connection connection = DataBase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ETUDIANT_BY_ID);) {
			preparedStatement.setInt(1, etudiantId);
//			System.out.println(preparedStatement);
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
	
	public boolean updateEtudiant(int etudiantId, int cas) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = DataBase.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ETUDIANT);) {
			if (cas == 1)
				statement.setInt(1, selectEtudiantById(etudiantId).getEmprunt() + 1);
			else
				statement.setInt(1, selectEtudiantById(etudiantId).getEmprunt() - 1);
			statement.setInt(2, etudiantId);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public Livre selectLivreById(int livreId) {
		Livre livre = null;
		try (Connection connection = DataBase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIVRE_BY_ID);) {
			preparedStatement.setLong(1, livreId);
//			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int num = rs.getInt("num");
				String titre = rs.getString("titre");
//				LocalDate date = rs.getDate("date");
				int stock = rs.getInt("stock");
				livre = new Livre(id, num, titre, LocalDate.now(), stock);
			}
		} catch (SQLException exception) {
			DataBase.printSQLException(exception);
		}
		return livre;
	}
	
	public boolean updateLivre(int livreId, int cas) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = DataBase.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_LIVRE);) {
			if (cas == 1)
				statement.setInt(1, selectLivreById(livreId).getStock() - 1);
			else
				statement.setInt(1, selectLivreById(livreId).getStock() + 1);
			statement.setInt(2, livreId);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public List<Emprunt> selectAllEmpruntsByEtudiantId(int etId) {

		List<Emprunt> emprunt = new ArrayList<>();

		try (Connection connection = DataBase.getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPRUNTS_BY_ETUDIANT_ID);) {
			preparedStatement.setLong(1, etId);
//			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int etudiantId = rs.getInt("etudiantId");
				int livreId = rs.getInt("livreId");
				Boolean remis = rs.getBoolean("remis");
				emprunt.add(new Emprunt(id, etudiantId, livreId, remis));
			}
		} catch (SQLException exception) {
			DataBase.printSQLException(exception);
		}
		return emprunt;
	}
	
	public Livre selectLivre(int livreId) {
		Livre livre = null;
		try (Connection connection = DataBase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIVRE_BY_ID);) {
			preparedStatement.setLong(1, livreId);
//			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int num = rs.getInt("num");
				String titre = rs.getString("titre");
//				LocalDate date = rs.getDate("date");
				int stock = rs.getInt("stock");
				livre = new Livre(id, num, titre, LocalDate.now(), stock);
			}
		} catch (SQLException exception) {
			DataBase.printSQLException(exception);
		}
		return livre;
	}
	
	public List<Livre> selectAllLivresByFromEmprunts(List<Emprunt> emprunts) {

		List<Livre> livres = new ArrayList<>();
		Livre livre;

		System.out.println("emprunts" + emprunts.size());
		for( Emprunt emprunt: emprunts) {
			livre = selectLivre(emprunt.getLivreId());
			livres.add(livre);
		}
		return livres;
	}
	
	public Boolean checkEmprunt(int etudiantId, int livreId) {
		
		List<Emprunt> emprunts = selectAllEmpruntsByEtudiantId(etudiantId);
		List<Livre> livres = selectAllLivresByFromEmprunts(emprunts);
		Livre livre = selectLivreById(livreId);
		System.out.println("livre" + livres.size());
		for (Livre l : livres) {
			if (l.getId() == livre.getId())
				return false;
		}
		return true;
	}
}
