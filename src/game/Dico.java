package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import test.Parsage_Dico;



/**
 *
 * @author diagneam
 */
public class Dico {
    /**
      definition des attributs de dico 
     */

    private ArrayList<String> listeNiveau1;
    private ArrayList<String> listeNiveau2;
    private ArrayList<String> listeNiveau3;
    private ArrayList<String> listeNiveau4;
    private ArrayList<String> listeNiveau5;
    private String cheminFichierDico;

    /**
     * Constructeur de partie qui prends en paramètre le chemin du fichier et qui se charge d'instancier les
     * attributs de classe. 
     */
    public Dico(String cheminFichier) {
        this.cheminFichierDico = cheminFichier;
        listeNiveau1 = new ArrayList<String>();
        listeNiveau2 = new ArrayList<String>();
        listeNiveau3 = new ArrayList<String>();
        listeNiveau4 = new ArrayList<String>();
        listeNiveau5 = new ArrayList<String>();

    }
    /**
     methodes qui se charge de parcourir la liste de mot suivant le niveau correspondant entré en paramètre 
     * .Il verifira au préalable si le niveau existe bel et bien et renverra un mot aleatoire de cette liste
     */
    public String getMotDepuisListeNiveau(int niveau) {
        switch (vérifieNiveau(niveau)) {
            case 1:
                return getMotDepuisListe(this.listeNiveau1);

            case 2:
                return getMotDepuisListe(this.listeNiveau2);
            case 3:
                return getMotDepuisListe(this.listeNiveau3);
            case 4:
                return getMotDepuisListe(this.listeNiveau4);
            case 5:
                return getMotDepuisListe(this.listeNiveau5);
            default:
        }
        return null;
    }
/**
     methodes qui un mot dans la liste de niveau correspondanate 
     * .Il verifira au préalable si le niveau existe bel et bien
     */
    public void ajouteMotADico(int niveau, String mot) {
        int level = vérifieNiveau(niveau);
        switch (level) {
            case 1:
                listeNiveau1.add(mot);
                break;
            case 2:
                listeNiveau2.add(mot);
                break;
            case 3:
                listeNiveau3.add(mot);
                break;
            case 4:
                listeNiveau4.add(mot);
                break;
            case 5:
                listeNiveau5.add(mot);
                break;
            default:
        }
    }
    /**
    renvoie l'attribut cheminFichierDico
     */
    public String getCheminFichierDico() {
        return cheminFichierDico;
    }
    /**
    verifie si le niveau appartient à l'intervalle [1,5] sinon renvoie 1 par defaut
     */
    private int vérifieNiveau(int niveau) {
        if ((niveau >= 1) && (niveau <= 5)) {
            return niveau;
        } else {
            return 1; //valeur par défaut du niveau
        }
    }

    /**
   Parcours une arraylist et renvoie une valeur aleatoire de cette liste ou "ListeVide" si la liste est vide
     */
    public String getMotDepuisListe(ArrayList<String> list) {
        if (!list.isEmpty()) {
            // generation random numbers entre 0 et la taille de la list
            int rand = (int) (Math.random() * list.size() - 1) + 1;
            //System.out.println( rand + "-> "+ list.get(rand) );
            return list.get(rand);
        } //si liste vide return valeur par défaut
        else {
            return "ListeVide";
        }

    }

    /**
    renvoie l'attribut listeNiveau
     */
    public ArrayList<String> getList1() {
        return listeNiveau1;
    }

    /**
    parser qui parcours le filename(Ici dico.xml) et rempli les arraylistNiveau des mots ayant l'attribut niveau correspondante
     */
    public void lireDictionnaireDOM(String filename) throws SAXException, IOException, ParserConfigurationException, Exception {

        String s = filename;
        List<Parsage_Dico.Mots> motsList = Parsage_Dico.loadMotDataFromXml(new File(s));
        //ContactManager2.printPersonList(System.out, motsList);
        for (int i = 0; i < motsList.size(); i++) {
            if (motsList.get(i).getAttribute().equals("1")) {
                ajouteMotADico(1, motsList.get(i).getName());

            } else if (motsList.get(i).getAttribute().equals("2")) {
                ajouteMotADico(2, motsList.get(i).getName());
            } else if (motsList.get(i).getAttribute().equals("3")) {
                ajouteMotADico(3, motsList.get(i).getName());
            } else if (motsList.get(i).getAttribute().equals("4")) {
                ajouteMotADico(4, motsList.get(i).getName());
            } else if (motsList.get(i).getAttribute().equals("5")) {
                ajouteMotADico(5, motsList.get(i).getName());
            }
        }
    }

}
