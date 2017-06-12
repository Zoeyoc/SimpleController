<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/view">
		<!-- TODO: Auto-generated template -->
		<html>
			<body>
				<xsl:attribute name = "title">
				<xsl:value-of select = "header/title"/>
				</xsl:attribute>
				<form>
					<xsl:attribute name = "name">
					<xsl:value-of select = "body/form/name"/>
					</xsl:attribute>
					
					<xsl:attribute name = "action">
					<xsl:value-of select = "body/form/action"/>
					</xsl:attribute>
					
					<xsl:attribute name="method">
					<xsl:value-of select="body/form/method"/>
					</xsl:attribute>
					
					<xsl:for-each select = "body/form/textView">
						
						<label>
						<xsl:value-of select = "label"/>
						</label>
						
						<input type = "text">
						<xsl:attribute name ="name">
						<xsl:value-of select = "name"/>
						</xsl:attribute>
						
						<xsl:attribute name ="value">
						<xsl:value-of select = "value"/>
						</xsl:attribute>
						</input>
						<br/>
					</xsl:for-each>
					
					<input type = "submit">
					<xsl:attribute name="name">
					<xsl:value-of select="body/form/buttonView/name"/>
					</xsl:attribute>
					<xsl:attribute name="label">
					<xsl:value-of select="body/form/buttonView/label"/>
					</xsl:attribute>
					</input>
					
				</form>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>