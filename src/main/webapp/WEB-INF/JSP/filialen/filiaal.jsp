<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="v" uri="http://vdab.be/tags"%>

<!DOCTYPE HTML>
<html lang="nl">

<head>
	<v:head title="${filiaal.naam}" />
</head>

<body>

	<v:menu />
	
	<c:choose>
		<c:when test="${not empty filiaal}">
		
			<h1>${filiaal.naam}</h1>
		
			<dl><dt>Straat</dt><dd>${filiaal.adres.straat}</dd>
			<dt>Huisnr.</dt><dd>${filiaal.adres.huisNr}</dd>
			<dt>Postcode</dt><dd>${filiaal.adres.postcode}</dd>
			<dt>Gemeente</dt><dd>${filiaal.adres.gemeente}</dd>
			<dt>Type</dt><dd>${filiaal.hoofdFiliaal ? "Hoofdfiliaal" : "Bijfiliaal"}</dd>
			<dt>Waarde gebouw</dt><dd>&euro; <fmt:formatNumber value='${filiaal.waardeGebouw}'/></dd>
			
			<fmt:parseDate value="${filiaal.inGebruikName}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
			<dt>Ingebruikname</dt><dd><fmt:formatDate value='${parsedDate}' type="date"
					dateStyle="short"/></dd></dl>
		
		</c:when>
		<c:otherwise>
			<div class="fout">Filiaal niet gevonden</div>
		</c:otherwise>
	</c:choose>
	
</body>

</html>