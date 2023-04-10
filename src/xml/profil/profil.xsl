<?xml version="1.0" encoding="UTF-8"?>
<!--Aminata-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:prof="http://myGame/tux"
                version="1.0">
    <xsl:output method="html"/>
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>profil.xsl</title>
            </head>
            <body>
                <table style="width:100%">
                    <tr>
                        <td>
                            <h1> 
                                <xsl:value-of select="/prof:profil/nom"/> 
                            </h1>
                        </td>                        
                        <xsl:variable name="photo" select="//prof:avatar"/><!--definition d'une variable photo -->
                        <td> 
                            <img  src = "$photo" width="100" height="100" /><!--affichage de la variable photo -->
                        </td>    
                    </tr>
                    <tr>
                        <td>
                            <h3> Née le : <xsl:value-of select="//prof:anniversaire"/> </h3><!--affichage des données de l'anniversaire -->
                        </td>
                    </tr>
                </table>
                <br/>
                <br/>
                <table style="width:100%" border="1"><!--création d'un tableau contenant les Parties,les dates ,le temps mis pour chaque partie et le mot à deviné-->
                    <tr>
                        <th>Partie</th>
                        <th>Date</th> 
                        <th>Temps</th>
                        <th>Mot</th>
                    </tr>
                    <xsl:apply-templates select="//prof:partie"/><!--application de la template -->
                </table>
                
            </body>   
        </html>
    </xsl:template>

    <xsl:template match="prof:partie">  <!--template qui parcours le fichier xml et  renvoie l'information relatif pour chaque partie dans le tableau -->      
        <tr>
            <td> 
                <xsl:value-of select="position()"/> <!-- suivant le parcours du fichier (nombre d'atribut partie rencontré),on pourra deduire la partie correspondante -->
            </td>     
            <td> 
                <xsl:value-of select="@date"/>
            </td>    
            <td> 
                <xsl:value-of select="prof:temps/text()"/>
            </td>    
            <td> 
                <xsl:value-of select="prof:mot/text()"/>
            </td>   
        </tr> 
    </xsl:template>
</xsl:stylesheet>
