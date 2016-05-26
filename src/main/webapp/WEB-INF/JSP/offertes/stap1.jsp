<%@ page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="v" uri="http://vdab.be/tags" %>
<%@taglib prefix='sform' uri='http://www.springframework.org/tags/form'%>

<!DOCTYPE HTML>
<html lang="nl">

<head>
	<v:head title="Aanvraag offerte (stap 1)"/>
</head>

<body>
	<v:menu/>

	<h1>Aanvraag offerte</h1>

	<h2>Stap 1</h2>

	<c:url value='/offertes' var='url'/>

	<sform:form action='${url}' commandName='offerte'>
		<sform:label path='voornaam'>Voornaam:<sform:errors path='voornaam'/></sform:label>
		<sform:input path='voornaam' autofocus='true' required='required'/>

		<sform:label path='familienaam'>Familienaam:
		<sform:errors path='familienaam'/></sform:label>
		<sform:input path='familienaam' required='required'/>
	
		<sform:label path='emailAdres'>E-mail adres:
		<sform:errors path='emailAdres'/></sform:label>
		<sform:input path='emailAdres' required='required' type='email'/>
		
		<div>Telefoonnummer(s):</div>
		<c:forEach items='${offerte.telefoonNrs}' varStatus='status'>
			<div class='rij'><sform:input path='telefoonNrs[${status.index}]' type='tel'/>
			<sform:errors path='telefoonNrs[${status.index}]' cssClass='fout'/></div>
		</c:forEach>
		
		<input type='submit' value='Nog een telefoonnummer' name='nogeennummer' formnovalidate>

		<input type='submit' value='Volgende stap' name='volgende'>
	</sform:form>

</body>
</html>