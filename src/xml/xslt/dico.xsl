<?xml version="1.0" encoding="UTF-8"?>
<!--Aminata-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:ns1='http://univ-grenoble-alpes.fr/Dembele/Mot' version="1.0">
    <xsl:output method="html"/><!--la forme général du fichier de sortie sera de type html-->
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>dico.xsl</title>
            </head>
            <body>
                <table style="width:100%" border="1" ><!--création d'un tableau avec un bordure de 1-->
                    <tr bgcolor="#FFFF00"><!--coloriage du contenu en jaune-->
                        <th>Mot</th><!--tableau de mots-->
                    </tr> 
                    <xsl:apply-templates select="/ns1:dictionnaire/ns1:mot"><!-- On applique la template defini en dessous-->
                        <xsl:sort select="text()" order="ascending"/>  <!-- triage des mots par ordre alphabétique du fichier -->
                    </xsl:apply-templates>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="ns1:mot"><!--template qui selectionnera le text() contenu dans un mot donné-->
        <tr>
            <th>
                <ul>
                    <xsl:value-of select="text()"/> 
                </ul> 
            </th>
        </tr>
    </xsl:template>
</xsl:stylesheet>
