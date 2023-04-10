
package game;
/**
 *
 * @author diagneam
 */



import org.w3c.dom.Document;
import org.w3c.dom.*;

public class Partie {
    private String date;
    private String mot;
    private int niveau;
    private int trouvé; 
    private double temps = 0;
    
    /**
     * Constructeur de partie sans paramètre qui se charge d'initialiser les
     * attributs de classe. La date à la date du jour, mot à "" et niveau à 1
     */
    public Partie() {
        this.date = "";
        this.mot = "";
        this.niveau = 1;
    }

    //construction d'une nouvelle partie et initialise donc tous ses attributs
    /**
     * Constructeur de partie qui initialise les attributs de classes aux
     * valeurs passées en paramètres. Partie(date: String, mot: String, niveau:
     * int)
     *
     * @param date
     * @param mot
     * @param niveau
     */
    public Partie(String date, String mot, int niveau){
        this.date = date;
        this.mot = mot;
        this.niveau = niveau;
    }
    
    /*
    *Gestion des partie    
    */    
    
    public void setTrouve(int nbLettresRestantes) {
        this.trouvé = (this.mot.length() - nbLettresRestantes) * 100 / this.mot.length();
    }
   //renvoyer un pourcentage des lettres trouvées.
    public int getTrouve() {
        return trouvé;
    }
    
    public int getTemps() {
        return (int) temps;
    }
    
    public void setTemps(int temps) {
        this.temps = temps;
    }
    
    public int getNiveau(){
        return this.niveau;
    }
    
    public String toString(){
        return "Partie du " + date + " de niveau " + niveau;
    } 
    
    
    //supplementaire
    public String getMot(){
        return this.mot;   
    } 
    
    public String getDate() {
        return date;
    }

    

    public void setDate(String date) {
        this.date = date;
    }

}
