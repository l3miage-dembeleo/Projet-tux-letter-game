package game;

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

    private static class Mots {
        String name = "";
    }

    public static void main(String[] args) throws Exception {

        // Initialise une chaîne de caractère avec le chemin du fichier XML.
        
        String filename = "src/game/dico.xml";

        // Crée un objet de type File avec le chemin du fichier XML.
        File xmlFile = new File(filename);

        
        List<Mots> motsList = loadPersonDataFromXml(xmlFile);

        // Affiche le résultat dans la sortie standard (System.out).
        printPersonList(System.out, motsList);
    }


    public static List<Mots> loadPersonDataFromXml(File file) throws Exception {

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
        NodeList personNodeList = doc.getElementsByTagName("ns1:mot");


        // Pour chaque élément XML de la liste
        for (int i = 0; i < personNodeList.getLength(); i = i + 1) {
            Node node = personNodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                // Récupère l'élément
                Element motElement = (Element)node;

                // Crée un nouvel objet de type Mots
                Mots mot = new Mots();
                mot.name = motElement.getTextContent();
                // Ajoute le mot à la liste
                motsList.add(mot);

            }
        }
        // Renvoie la référence à l’objet motnList
        return motsList;
    }


    private static void printPersonList(PrintStream out, List<Mots> motsList) {

        for (int i = 0; i < motsList.size(); i = i + 1) {
            Mots mot = motsList.get(i);
            printPerson(out, mot);
        }
    }


    private static void printPerson(PrintStream out, Mots mot) {

        out.printf(" %s\r\n",
                mot.name);
    }
}
