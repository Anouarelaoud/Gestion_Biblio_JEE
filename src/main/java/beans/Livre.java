package beans;

import java.time.LocalDate;

public class Livre {
    private int id;
    private int num;
    private String titre;
    private LocalDate date;
    private int stock;

    public Livre() { }

    public Livre(int id, int num, String titre, LocalDate date, int stock) {
    	this.id = id;
    	this.num = num;
    	this.titre = titre;
        this.date = date;
        this.stock = stock;
    }
    
    public Livre(int num, String titre, LocalDate date, int stock) {
    	this.num = num;
    	this.titre = titre;
        this.date = date;
        this.stock = stock;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitre() { return titre; }
    public int getNum() { return num; }
    public LocalDate getDate() { return date; }
    public int getStock() { return stock; }
    public void setTitre(String titre) { this.titre = titre; }
    public void setNum(int num) { this.num = num; }
    public void setStock(int stock) { this.stock = stock; }
    public void setDate(LocalDate date) { this.date = date; }
    
    public void incrementStock() { this.stock++; };
    public void decrementStock() { this.stock--; };    
    
}
