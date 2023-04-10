<?xml version="1.0" encoding="UTF-8"?>
<!--Aminata-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:ns1='http://univ-grenoble-alpes.fr/Dembele/Mot' version="1.0">
    <xsl:output method="html"/><!--la forme général du fichier de sortie sera de type html-->
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/"><!-- On créer la template qui va donner la forme général du fichier html-->
        <html>
            <head>
                <title>dico.xsl</title>
            </head>
            <body>
                <table style="width:100%" border="1" ><!--création d'un tableau avec un bordure de 1-->
                    <tr bgcolor="#FFFF00"><!--coloriage du contenu en jaune-->
                        <th>Mot</th><!--definition de la colonne mot-->
                        <th>Niveau</th><!--definition de la colonne niveau-->
                    </tr> 
                    <xsl:apply-templates select="/ns1:dictionnaire/ns1:mot"><!-- On applique la template defini en dessous-->
                        <xsl:sort select="@niveau" order="ascending"/><!-- triage des niveaux par ordre de grandeur contenu dans le fichier xml-->
                        <xsl:sort select="text()" order="ascending"/> <!-- triage des mots par ordre alphabétique contenu dans le fichier xml en tenant en compte le triage par niveau --> 
                    </xsl:apply-templates>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="ns1:mot"><!--template qui selectionnera le text() contenu dans un mot donné et le niveau correspondant-->
        <tr>
            <th>
                <ul>
                    <xsl:value-of select="text()"/> 
                </ul> 
            </th>
            <th>
                <xsl:value-of select="@niveau"/>
            </th>
        </tr>
    </xsl:template>
</xsl:stylesheet>
