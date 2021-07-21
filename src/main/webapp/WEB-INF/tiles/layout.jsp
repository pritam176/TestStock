<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 

 	<link href="${pageContext.request.contextPath}/css/agency.css"  rel="stylesheet">
 	<link href="${pageContext.request.contextPath}/css/jqueryui.css"  rel="stylesheet">
 	<link href="${pageContext.request.contextPath}/css/bootstrap.css"  rel="stylesheet">
<!--  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->
 
 
 
</head>
<body>
	
<!-- <script   src="https://code.jquery.com/jquery-2.2.0.min.js"   integrity="sha256-ihAoc6M/JPfrIiIeayPE9xjin4UWjsx2mjW/rtmxLM4="   crossorigin="anonymous"></script> -->
 
 <script   src="${pageContext.request.contextPath}/js/jquery-2.2.0.min.js"  ></script>
 <script   src="${pageContext.request.contextPath}/js/bootstrap.min.js"  ></script>
 <script   src="${pageContext.request.contextPath}/js/jquery.easing.min.js"  ></script>
 <script   src="${pageContext.request.contextPath}/js/jqueryui.js"  ></script>
 <script   src="${pageContext.request.contextPath}/js/jquery.validate.js"  ></script>
 
 
<!--  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->
 <!-- <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script> -->
 
  <!-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
   
	<tiles:insertAttribute name="header" />
	<br />
	<br />
	<tiles:insertAttribute name="body" />
	<br />
	<br />
	<tiles:insertAttribute name="footer" />
	
	<script>
	var ctx = "${pageContext.request.contextPath}"
</script>
 
</body>
</html>