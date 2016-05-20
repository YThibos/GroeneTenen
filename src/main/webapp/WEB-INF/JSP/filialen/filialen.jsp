<%@ page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="v" uri="http://vdab.be/tags" %>

<!DOCTYPE HTML>
<html lang="nl">

<head>
	<v:head title="Filialen"/>
</head>

<body>
	<v:menu/>

	<h1>Filialen</h1>
	
	<c:forEach items='${filialen}' var='filiaal'>
		
		<h2>${filiaal.naam}</h2>
		<p>${filiaal.adres.straat} ${filiaal.adres.huisNr}<br>
		${filiaal.adres.postcode} ${filiaal.adres.gemeente.toUpperCase()}</p>
	</c:forEach>

</body>

</html>