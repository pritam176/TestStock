<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html>
<head>
<style type="text/css">
#logout {
	text-align: center;
	margin-top: 100px;
}
</style>
<title>Logout Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

</head>
<body>
	<div id="page">

		<h3 class=".h2 centerh2">Logout User -<sec:authentication property="principal.username" /></h3>
		
		<c:url var="logoutUrl" value="/logout" />
		<form:form action="${logoutUrl}" method="post"
			cssClass="form-horizontal" role="form" id="logout">
			<input type="submit" class="btn btn-primary" value="Log out" />
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form:form>
	</div>