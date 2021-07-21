<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<body>
	<div id="page" >

		<h1 class="h2 centerh2">Access denied</h1>
		
		<h4 style="text-align: center;">Sorry User,&nbsp; &nbsp;<sec:authentication
								property="principal.username" />&nbsp; &nbsp;You do not have
								 permission to view this page.</h4>
		
 
</div>
</body>