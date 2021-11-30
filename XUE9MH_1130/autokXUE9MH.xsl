<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" encoding="UTF-8" />
	<xsl:template match="/">
		<html>
            <body>
                <h2>Renszamok:</h2>
                    <xsl:apply-templates/>                    
            </body>
        </html>
	</xsl:template>
	
	<xsl:template match="autok/auto">
		<div><xsl:value-of select = "@rsz"/></div>        
        <br/>
	</xsl:template>
</xsl:stylesheet>