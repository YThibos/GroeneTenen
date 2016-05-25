<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="v" uri="http://vdab.be/tags"%>

<!DOCTYPE HTML>
<html lang="nl">

<head>
	<v:head title="Filialen per postcode" />
</head>

<body>

	<v:menu />

	<h1>Filialen per postcode</h1>

	<c:url value="/filialen" var="url" />
	
	<sform:form action="${url}" commandName="postcodeReeks" method="get">
		<sform:label path="vanpostcode">Van:<sform:errors path="vanpostcode"/></sform:label>	
		<sform:input path="vanpostcode" autofocus="autofocus" type="number" required="required" min="1000" max="9999"/>	
		<sform:label path="totpostcode">Tot:<sform:errors path="totpostcode"/></sform:label>	
		<sform:input path="totpostcode" type="number" required="required" min="1000" max="9999"/>
		<input type="submit" value="Zoeken">	
		<sform:errors cssClass="fout" />
	</sform:form>
	
	<c:forEach items="${filialen}" var="filiaal">
		<spring:url var="url" value="/filialen/{id}">
			<spring:param name="id" value="${filiaal.id}"/>
		</spring:url>
		<h2><a href="${url}">${filiaal.naam}</a></h2>
		<p>${filiaal.adres.straat} ${filiaal.adres.huisNr}<br>
		${filiaal.adres.postcode} ${filiaal.adres.gemeente}</p>
	</c:forEach>

</body>
</html>