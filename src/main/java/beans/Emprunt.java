package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import dataBase.DataBase;

public class Emprunt {
    private int id;
    private int etudiantId;
    private int livreId;
    private Boolean remis;
    private static final String SELECT_ETUDIANT_BY_ID = "select id, CIN, nom, prenom, filiere, emprunt from etudiant where id =?";
    private static final String SELECT_LIVRE_BY_ID = "select id, num, titre, date, stock from livre where id =?";
    
    public Emprunt() { }

    public Emprunt(int id, int etudiantId, int livreId, Boolean remis) {
    	this.id = id;
        this.etudiantId = etudiantId;
        this.livreId = livreId;
        this.remis = remis;
    }
    
    public Emprunt(int etudiantId, int livreId, Boolean remis) {
        this.etudiantId = etudiantId;
        this.livreId = livreId;
        this.remis = remis;
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
    public int getEtudiantId() { return etudiantId; }
    public void setEtudiantId(int etudiantId) { this.etudiantId = etudiantId; }
    public int getLivreId() { return livreId; }
    public void setLivreId(int livreId) { this.livreId = livreId; }
    public Boolean getRemis() { return remis; }
    public void setRemis(Boolean remis) { this.remis = remis; }
    
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
//				LocalDate date = rs.getDate("date");
				int stock = rs.getInt("stock");
				livre = new Livre(id, num, titre, LocalDate.now(), stock);
			}
		} catch (SQLException exception) {
			DataBase.printSQLException(exception);
		}
		return livre;
	}
}
