package test;

import game.Dico;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * Aminata
 */
public class TestDico {

    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException, Exception {
        String s="src/game/dico.xml";
        Dico dictionnaire = new Dico("");
        try {
            dictionnaire.lireDictionnaireDOM(s);
            System.out.println("Niveau1 "+dictionnaire.getMotDepuisListeNiveau(1));
            System.out.println("Niveau2 "+dictionnaire.getMotDepuisListeNiveau(2));
            System.out.println("Niveau3 "+dictionnaire.getMotDepuisListeNiveau(3));
            System.out.println("Niveau4 "+dictionnaire.getMotDepuisListeNiveau(4));
            System.out.println("Niveau5 "+dictionnaire.getMotDepuisListeNiveau(5));
        } catch (SAXException ex) {
            Logger.getLogger(TestDico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestDico.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }

}
