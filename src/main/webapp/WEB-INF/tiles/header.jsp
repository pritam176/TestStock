
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

	<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}" />
<style>
.middle{





}
body {margin:0;}
	.topnav {
  overflow: hidden;
  background-color: #337ab7;
}

.topnav a {
  float: left;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 20px;
}

.topnav a:hover {
  background-color: white;
  color: gold !important;
}

.topnav .icon {
  display: none;
}

@media screen and (max-width: 600px) {
  .topnav a:not(:first-child) {display: none;}
  .topnav a.icon {
    float: right;
    display: block;
  }
}

@media screen and (max-width: 600px) {
  .topnav.responsive {position: relative;}
  .topnav.responsive .icon {
    position: absolute;
    right: 0;
    top: 0;
  }
  .topnav.responsive a {
    float: none;
    display: block;
    text-align: left;
  }

}
</style>
<!-- Navigation -->
<div class="topnav" id="myTopnav">
<a class="navbar-brand page-scroll"
				href="${pageContext.request.contextPath}/mmt/home.html"> <%--                 <img class="logo" src="<c:url value="/resources/img/logos/header_logo.png" /> "> --%>
				<spring:eval expression="@environment.getProperty('tradersName')" />
			</a>
			<div style="float: right;">
  <sec:authorize access="isAuthenticated()">
  <c:set var="username"><sec:authentication property="principal.username" /></c:set>
 
  <spring:eval expression='@environment.getProperty("imagePath")' var="someList" />
		<c:url var="url" value="${baseURL}/${someList}${username }" />
  		
					<a class="page-scroll middle"
						href="${pageContext.request.contextPath}/customer/customerPage.html">
						<img class="img-rounded" src="${url}"  height="40" width="40"/></a>




					<a class="page-scroll middle"
						href="${pageContext.request.contextPath}/purchase/purchaseproduct.html">System
							Config</a>


					<a class="page-scroll middle"
						href="${pageContext.request.contextPath}/inventory/invoice.html">Invoice</a>
					
					<a class="page-scroll middle"
						href="${pageContext.request.contextPath}/inventory/stock.html">Stock
							Detail</a>
					<sec:authorize access="hasRole('ROLE_SUPER_USER')">		
					<a class="page-scroll middle"
						href="${pageContext.request.contextPath}/gst/gstreport.html">GST
							Report</a>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_USER')">
						<a class="page-scroll middle"
							href="${pageContext.request.contextPath}/sell/sellitem.html">Sales</a>
						
					</sec:authorize>

					<a class="page-scroll middle"
						href="${pageContext.request.contextPath}/mmt/logout.html">Logout</a>
					

				</sec:authorize>

				<sec:authorize access="!isAuthenticated()">

					<a class="page-scroll middle"
						href="${pageContext.request.contextPath}/mmt/home.html">Home </a>
					<a class="page-scroll"
						href="${pageContext.request.contextPath}/purchase/purchaseproduct.html">System
							Config</a>

					<a class="page-scroll middle"
						href="${pageContext.request.contextPath}/inventory/invoice.html">Invoice</a>
					
					<a class="page-scroll middle"
						href="${pageContext.request.contextPath}/inventory/stock.html">Stock
							Detail</a>
					<a class="page-scroll middle"
						href="${pageContext.request.contextPath}/mmt/login.html">Login</a>
				</sec:authorize>
  <a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>
  </div>
</div>
<script>
function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}
</script>
	<!-- /.container-fluid -->
