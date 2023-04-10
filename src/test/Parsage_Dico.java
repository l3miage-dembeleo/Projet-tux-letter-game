package test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Parsage_Dico {

    public static class Mots {
        private String name = "";
        private String attribute="";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }
        
    }

    public static void main(String[] args) throws Exception {

        // Initialise une chaîne de caractère avec le chemin du fichier XML.
       
        String filename = "src/game/dico.xml";

        // Crée un objet de type File avec le chemin du fichier XML.
        File xmlFile = new File(filename);

        // Charge les données des mots qui se trouve dans le fichier XML
        // dans un objet de type List de mot et affecte une référence à cet
        // objet à la variable motList.
        List<Mots> motsList = loadMotDataFromXml(xmlFile);

        // Affiche le résultat dans la sortie standard (System.out).
        printMotList(System.out, motsList);
    }


    public static List<Mots> loadMotDataFromXml(File file) throws Exception {

        // Crée un tableau dynamique.
        List<Mots> motsList= new ArrayList<Mots>();

        // Crée un objet Document qui représente les données du fichier XML
        // sous la forme d’une hiérarchie d’objets de type Node. Un objet de
        // type nœud peut représenter aussi bien un élément, qu’un nœud
        // de texte ou un attribut.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        doc.getDocumentElement().normalize();


        // Recherche tous les elements <mot>
        NodeList motNodeList = doc.getElementsByTagName("ns1:mot");


        // Pour chaque élément XML de la liste
        for (int i = 0; i < motNodeList.getLength(); i = i + 1) {
            Node node = motNodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                // Récupère l'élément
                Element motElement = (Element)node;

                // Crée un nouvel objet de type Mots
                Mots mot = new Mots();
                mot.name = motElement.getTextContent();
                mot.attribute = motElement.getAttribute("niveau");
                // Ajoute le mot à la liste
                motsList.add(mot);

            }
        }
        // Renvoie la référence à l’objet personList
        return motsList;
    }


    public static void printMotList(PrintStream out, List<Mots> motsList) {

        for (int i = 0; i < motsList.size(); i = i + 1) {
            Mots mot = motsList.get(i);
            printMot(out, mot);
        }
    }


    private static void printMot(PrintStream out, Mots mot) {

        out.printf(" %s\r\n",
                mot.name);
    }
}
