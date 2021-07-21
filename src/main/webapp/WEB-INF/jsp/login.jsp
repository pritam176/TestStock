<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>



	<div id="page" >

		<h1 class="h2 centerh2">Login TO SYstem</h1>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm' Class="form-horizontal"
			action="<c:url value='/mmt/logindo.html' />" method='POST'>

			<div class="form-group">
				<div class="col-md-4 control-label col-xs-12">
					<label class="form-lbl mandatory_lbl" for="exampleInputName2">User Name:</label>
				</div>
				<div class="col-md-4 control-label col-xs-12">
					<input type='text' name='username' value='' class="form-control">
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-4 control-label col-xs-12">
					<label class="form-lbl mandatory_lbl" for="exampleInputEmail2">Password :</label>
				</div>

				<div class="col-md-4 control-label col-xs-12">
					<input type='password' name='password' class="form-control" />
				</div>
			</div>
				<div class="form-group">
						<div class="col-md-12 col-xs-offset-2 col-xs-12">
					<input name="submit" type="submit" class="btn btn-primary"
						value="Login" style="margin-left: 400px;" />
				</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>
	</div>

</body>
</html>