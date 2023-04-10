/**
tuxTrouveLettre -> si c'est pas la bonne lettre
 */
package game;

import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;

public class JeuDevineLeMotOrdre extends Jeu{
    private int nbLettresrestantes;
    private ArrayList<Letter> lettresRestantes;
    private ArrayList<Letter> lettresTrouvees;
    private ArrayList<Letter> wrongLetters;
    private int tempsLimite = 30;
    Chronometre chrono = new Chronometre(tempsLimite * 1000);

    /**
     * Constructeur sans paramètre de la classe JeuDevineMotOrdre.Il initialise
 les listes de mots
     *
     * @throws javax.xml.parsers.ParserConfigurationException
     */
    public JeuDevineLeMotOrdre() throws ParserConfigurationException, Exception{
        super();        
        lettresRestantes = new ArrayList<Letter>();
        lettresTrouvees = new ArrayList<Letter>();
    }

    /**
     * Demarre une partie en affichant le mot à trouver pendant 3s
     *
     * @param partie
     */
    @Override
    protected void démarrePartie(Partie partie){   
        //affichage du mot pendant quelques secondes
        affichageDelai(partie.getMot(), 3000);
               
        //instanciation du mot à deviner et le tux dans l'env
        ajoutEnvJeu(partie.getMot());                
        
        chrono.start();
                        
    }
    
    /**
     * Applique les règles du jeu à une partie
     *
     * @param partie
     */
    @Override
    protected void appliqueRegles(Partie partie){
        boolean finished=false;
        //System.out.println("Temps écoulé: " + chrono.getTime() + " ****** Temps restantes " + chrono.remainsTime());
        
        if(chrono.remainsTime()==true){//si la limite de temps est finie
            finished = true;
            System.out.println("\nFIN de la partie \nPas de chance!!!");
        }     
        if(nbLettresrestantes == 0 ){
            System.out.println("\nFIN de la partie \nBravo! C'est gagné!!! ");
            finished=true;
        }
        
        if (tuxTrouveLettre()) {
            nbLettresrestantes--;
        }
    }

    /**
     * Termine le jeu à la fin du temps imparti ou lorsque tux a trouvé toutes
     * les lettres
     *
     * @param partie
     */
    @Override
    protected void terminePartie(Partie partie){
        chrono.stop();    
        //set le temps et le pourcentage du jeu                
        partie.setTemps(chrono.getSeconds()); 
        partie.setTrouve(nbLettresrestantes);
        
        System.out.println("Temps ecoulé : " + partie.getTemps() + "s/" + tempsLimite
                + "\nPourcentage des lettres trouvé : " + partie.getTrouve() + "%");
        
    }
    
    //a chaque lettre trouver -> on enleve la lettre de la liste et de l'env
    //si mauvaise lettre on l'ajoute dans la liste de wrongLetters
    /**
     * Permet de savoir si tux a touché une lettre, et si c'est la bonne
     * l'enlever de l'environnement
     * @return boolean
     */
    private boolean tuxTrouveLettre(){
        for (Letter l : lettresRestantes) {
            if( collision(l)==true ){ 
                if(l == lettresRestantes.get(0)) {//regarde si la lettre trouvée est bien le suivant de la liste de mots restants à trouver
                    lettresRestantes.remove(l);
                    lettresTrouvees.add(l);
                    env.removeObject(l);
                    System.out.print(l.getLetter());
                    return true;
                }
                else{
                    wrongLetters.add(l);
                }
            }
        }            
        return false;
    }
    
    
    /**
     * Disperse les lettres du mot à trouver dans l'environnement
     *
     * @param mot
     */
    private void ajoutEnvJeu(String mot) {
        char caract[];
        caract = decouppeMot(mot);

        for (char c : caract) {
            Letter var = new Letter(c, randomDouble(0 + 3.0 , room.getWidth() - 3.0 ), randomDouble(0 + 3.0, room.getDepth() -3.0 ) );
            lettres.add(var);
            lettresRestantes.add(var);    
            env.addObject(var);
        }
        nbLettresrestantes = getNbLettresRestantes();
        
         //comporte les lettres qui sont mal choisi/désodre
        wrongLetters = new ArrayList<Letter>();
    }
    
    /**
     * Affiche le mot à trouver pendant le nombre de millisecondes secondes
     * passé en paramètre
     *
     * @param mot
     * @param temps
     */
    private void affichageDelai(String mot, int temps){
        //affichage du mot à deviner pendant "temps" secondes
        long max = temps; // 60000 = 1mn
        long tmax = System.currentTimeMillis() + max;

        ArrayList<Letter> lettresAffichees = new ArrayList<Letter>();
        char caracts[] = decouppeMot(mot);
        double i = 0.0;
        for (char c : caracts) {
            Letter var = new Letter(c, 6 + i, room.getDepth());
            env.addObject(var);
            lettresAffichees.add(var);
            i += 7.0;
        }
        env.advanceOneFrame();

        while (System.currentTimeMillis() < tmax) {
        }
        
        for (Letter letter : lettresAffichees) {
            env.removeObject(letter);
        }
    }
    
    private int getNbLettresRestantes(){
        return lettresRestantes.size();
    }
    
    private int getTemps(){
        return 0;
    }
    
    
    
   
}

