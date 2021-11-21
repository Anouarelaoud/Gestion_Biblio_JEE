package beans;

public class Etudiant {

	private int id;
    private String cin;
	private String nom;
    private String prenom;
    private String filiere;
    private int emprunt;

    public Etudiant() { }

    public Etudiant(int id, String cin, String nom, String prenom, String filiere, int emprunt) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.filiere = filiere;
        this.emprunt = emprunt;
    }
    
    public Etudiant(String cin, String nom, String prenom, String filiere, int emprunt) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.filiere = filiere;
        this.emprunt = emprunt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setCin(String cin) { this.cin = cin; }
    public void setFiliere(String filiere) { this.filiere = filiere; }
    public void setEmprunt(int emprunt) { this.emprunt = emprunt; }
    public int getEmprunt() { return emprunt; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getCin() { return cin; }
    public String getFiliere() { return filiere; }
    
    public void incrementEmprunt() { this.emprunt++; };
    public void decrementEmprunt() { this.emprunt--; };
}
