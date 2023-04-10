
package game;

/**
 *
 * @author diagneam
 */
import java.util.ArrayList;
import env3d.Env;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

public abstract class Jeu {

    // DECLARATION des attributs de  classe
    protected Env env;
    protected Room room;
    protected Profil profil;
    protected Tux tux;
    protected ArrayList<Letter> lettres;
    protected Dico dico;
    protected Chronometre chrono;
    //char c = 'o';
    //Letter lettre;
    //EnvText textChrono;
    
    //String mot="aminata";
    String mot;
    //mot = get
    
    

    public Jeu() throws IOException, ParserConfigurationException, Exception {

        // Crée un nouvel environnement
        env = new Env();

        // Instancie une Room
        room = new Room();

        //accès et lecture du fichier dico.xml
        dico = new Dico("");
        dico.lireDictionnaireDOM("src/xml/xml/dico.xml");
        

        // Règle la camera
        env.setCameraXYZ(50, 60, 175);
        env.setCameraPitch(-20);

        // Désactive les contrôles par défaut
        env.setDefaultControl(false);

        // Instancie un profil par défaut
        profil = new Profil();

        // Instanciation d'une lettre
        //lettre = new Letter(c, 20.0, 10.0);
        //instancie les lettres en Liste Letter
        lettres = new ArrayList<Letter>();

    }

    public void joue(Partie partie) {
        

        // TEMPORAIRE : on règle la room de l'environnement. Ceci sera à enlever lorsque vous ajouterez les menus.
        env.setRoom(room);
        //String mot = dico.getMotDepuisListeNiveau(2);
        mot = dico.getMotDepuisListeNiveau(5);
        for(int i = 0; i < mot.length(); i++){
            double x = (double) Math.random()*(room.getWidth());
            double y = (double) Math.random()* room.getDepth();
            lettres.add(new Letter(mot.charAt(i),x,y));
        }  
        //lettres = new ArrayList<Letter>('o');
        /*for (int i = 0; i < mot.length(); i++) {
            // lettre.add(mot.charAt(i));
            lettre = new Letter(mot.charAt((int) i), (i * 10 + 20.0), (room.getDepth() / 2));
            env.addObject(lettre);
            lettres.add(lettre);
        }
        */
        for(Letter lettre: lettres){
            env.addObject(lettre);
        }
        //env.addObject(lettres);

        // Instancie un Tux
        tux = new Tux(env, room);
        env.addObject(tux);

        // Ici, on peut initialiser des valeurs pour une nouvelle partie
        démarrePartie(partie);

        // Boucle de jeu
        Boolean finished;
        finished = false;
        while (!finished) {

            // Contrôles globaux du jeu (sortie, ...)
            //1 is for escape key
            if (env.getKey() == 1) {
                finished = true;
            }

            // Contrôles des déplacements de Tux (gauche, droite, ...)
            tux.déplace();  
            
            // Ici, on applique les regles
            appliqueRegles(partie);
            //textChrono.modify("Temps restant: " + (30 - chrono.getTime()) + "s");

            // Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
            env.advanceOneFrame();
        }

        // Ici on peut calculer des valeurs lorsque la partie est terminée
        terminePartie(partie);

    }

    public void execute() {

        // pour l'instant, nous nous contentons d'appeler la méthode joue comme cela
        // et nous créons une partie vide, juste pour que cela fonctionne
        
         
        joue(new Partie());
        // Détruit l'environnement et provoque la sortie du programme 
        env.exit();
    }

    protected void démarrePartie(Partie partie) {

    }

    protected void appliqueRegles(Partie partie) {

    }

    protected void terminePartie(Partie partie) {

    }
    protected double distance(Letter letter){
        return tux.distance(letter);
    }
    
    
    
    
     //découpe le mot string en tableau de char
    /**
     * Decoupe le mot en lettres
     *
     * @param mot
     * @return char[]
     */
    public char[] decouppeMot(String mot){
        char motdecoupé[];
        motdecoupé = new char[mot.length()];
        
        for (int i=0; i<mot.length(); i++){
            motdecoupé[i] = mot.charAt(i);
        }
        
        return motdecoupé;
    }
    //generer les coordonnees alétoirement
    /**
     * Genère un nombre aléatoire entre min et max
     *
     * @param min
     * @param max
     * @return double
     */
    public double randomDouble(double min, double max) {
        double range = max - min + 1.0 ;
        // generate random numbers 
        double rand = (double)(Math.random() * range) + min;
        
        if ( (rand != tux.getX() || rand != tux.getY()) && rand != room.getDepth() && rand != room.getWidth()) {
            return rand;
        } else
            rand = randomDouble(min, max);
        return rand;
    }
    
    
    
    /**
     * Retourne vrai si tux est en contact avec la lettre passée en paramètre
     *
     * @param letter
     * @return boolean
     */
     protected boolean collision(Letter letter){
        boolean res = false;
        if(distance(letter) < letter.getScale()+tux.getScale()){
            res = true;
        }
        
        return res;
    }

}
