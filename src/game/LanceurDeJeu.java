
package game;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;



/**
 *
 * @author diagneam
 */
public class LanceurDeJeu {
   public static void main(String args[]) throws IOException, ParserConfigurationException, SAXException, Exception {
        // Declare un Jeu
        //Jeu jeu;
        
        //jeu = new Jeu(){};
        //Execute le jeu
        
        //jeu.execute();
        
        JeuDevineLeMotOrdre game = new JeuDevineLeMotOrdre(); /**instanciation d'un jeuDevineLeMotOrdre nommé game*/
        game.execute();//execution de game
   
    } 
}
    

