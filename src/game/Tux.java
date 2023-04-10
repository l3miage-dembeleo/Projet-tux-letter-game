
package game;

/**
 *
 * @author diagneam
 */
import env3d.Env;
import env3d.advanced.EnvNode;
import org.lwjgl.input.Keyboard;

public class Tux extends EnvNode {

    private Env env;
    private Room room;

    /**
     * Constructeur du personnage Tux
     *
     * @param env
     * @param room
     */
    public Tux(Env env, Room room) {
        //Dans le constructeur
        this.env = env;// initialisation de l'attribut env
        this.room = room;// initialisation de l'attribut room
        setScale(4.0);
        setX(this.room.getWidth() / 2);// positionnement au milieu de la largeur de la room
        setY(getScale() * 1.1); // positionnement en hauteur basé sur la taille de Tux
        setZ(this.room.getDepth() / 2); // positionnement au milieu de la profondeur de la room
        setTexture("models/tux/tux.png");
        setModel("models/tux/tux.obj");
    }

    public void déplace() {    //permet le deplacement de tux dans l'environnement de jeu
        
        if (env.getKeyDown(Keyboard.KEY_Z) || env.getKeyDown(Keyboard.KEY_UP)) { // Fleche 'haut' ou Z
            // Haut
            this.setRotateY(180);
            if(testeRoomCollision(this.getX() , this.getZ() - 1.0)){
                this.setZ(this.getZ() - 1.0);
            }
        }
        if (env.getKeyDown(Keyboard.KEY_S) || env.getKeyDown(Keyboard.KEY_DOWN)) { // Fleche 'bas' ou S
           // Bas
            this.setRotateY(360);
            if(testeRoomCollision(this.getX() , this.getZ() + 1.0)){
                this.setZ(this.getZ() + 1.0);
            }
        }
        if (env.getKeyDown(Keyboard.KEY_Q) || env.getKeyDown(Keyboard.KEY_LEFT)) { // Fleche 'gauche' ou Q
            // Gauche
            this.setRotateY(-90);
            if(testeRoomCollision(this.getX() - 1.0 , this.getZ())){
                this.setX(this.getX() - 1.0);
            }
        }
        if (env.getKeyDown(Keyboard.KEY_D) || env.getKeyDown(Keyboard.KEY_RIGHT)) { // Fleche 'droite' ou D
            // Droite
            this.setRotateY(90);
            if(testeRoomCollision(this.getX() + 1.0 , this.getZ())){
                this.setX(this.getX() + 1.0);
            }
        }
    }
    
    //gestion de la collision de Tux avec les bordures dre l'environnement
        private Boolean testeRoomCollision ( double x, double z){
        return (( (this.room.getDepth()- getScale()) > z) && ( 0.0 + getScale()  < z) ) && ( (this.room.getWidth() - getScale())> x) && (0.0 + getScale() < x);
    }  
}
