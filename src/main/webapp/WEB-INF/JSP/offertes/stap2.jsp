<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://vdab.be/tags"%>
<%@taglib prefix='sform' uri='http://www.springframework.org/tags/form'%>

<!DOCTYPE HTML>

<html lang="nl">

<head>
	<v:head title="Aanvraag offerte (stap 2)" />
</head>

<body>
	<v:menu />

	<h1>Aanvraag offerte</h1>

	<h2>Stap 2</h2>

	<sform:form commandName='offerte'>
		<sform:label path='oppervlakte'>Oppervlakte:
		<sform:errors path='oppervlakte'/></sform:label>
		
		<sform:input path='oppervlakte' autofocus='true' required='required' type='number' min='1'/>
		
		<c:forEach items='${offerte.gazontypes}' var='entry'>
			<div class='rij'>
				<sform:checkbox path='gazontypes[${entry.key}]' label="${entry.key.toString().toLowerCase()}"/>
			</div>
		</c:forEach>
		
		<sform:errors cssClass='fout'/>
		
		<input type='submit' value='Vorige stap' name='vorige' formnovalidate>
		<input type='submit' value='Bevestigen' name='bevestigen'>
	</sform:form>

</body>
</html>