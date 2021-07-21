<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Sell Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validation.js" /></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/mmtmain.js" /></script>
	

</head>
<body>



	<div id="page">

		<!-- customer Detail -->

		<div id="serachform">
			<form:form method="post" action=""
				modelAttribute="customerCommandForm" cssClass="form-inline"
				role="form" id="customerserachform">

				<div class="form-group">
					<label for="exampleInputName2">Customer Name</label>

					<form:input path="name" id="csearchname" type="text"
						class="form-control" />
				</div>
				<div class="form-group">
					<label for="exampleInputEmail2">Mobile No</label>

					<form:input path="mobile_no" id="csearchmobileno" type="text"
						class="form-control" />
				</div>

				<div class="form-group">
					<div class="col-md-12 col-xs-offset-2 col-xs-12">
						<button type="button" id="customerSerach" class="btn btn-primary">Customer
							Search</button>

					</div>
				</div>

				<p class="error nocustomer"></p>

			</form:form>
		</div>


		<form:form method="post"
			action="${pageContext.request.contextPath}/sell/sellitem.html"
			modelAttribute="transactionCommandForm" cssClass="form-horizontal"
			role="form" id="sellform">

			<h3 class=".h2 centerh2">Sales Transaction</h3>





			<!-- Product Detail -->
			<div class="form-group">
				<div class="col-md-4 control-label col-xs-12">
					<label class=" mandatory_lbl"> Product Name </label>
				</div>
				<div class="col-md-4 control-label col-xs-12 custom-select-box cf">
					<form:select path="product" class="form-control required">
						<option value="">Please Select</option>
						<form:options items="${productList}" />

					</form:select>
				</div>
			</div>

			<div style="margin-top: 70px;">
				<!-- Product Item Detail -->
				<table id="itemtabel" class="table table-striped">
					<tr>
						<th>#</th>
						<th>Product Name</th>
						<th>Item Name</th>
						<th>Avalible Unit</th>
						<th>Required Unit</th>
						<th>GST Percentage</th>
						<th>Unit Price</th>
						<th>Action</th>
					</tr>

				</table>

			</div>


			<!-- Item Detail -->



			<div>
				<table id="selectedItem" class="table table-striped">
					<tr>
						<th>#</th>
						<th>Product Name</th>
						<th>Item Name</th>
						<th>Unit</th>
						<th>Price per Unit</th>
						<th>Discount</th>
						<th>GST Percentage</th>
						<th>Total</th>
						
					</tr>

				</table>
			</div>
			
			<div class="form-group" >
					<div class="col-md-4 control-label col-xs-12">
						<label class="form-lbl mandatory_lbl">Customer Name</label>
					</div>
					<div class="col-md-4 control-label col-xs-12">
						<form:input path="customerName" type="text" class="form-control" readonly="true"  />
					</div>
				</div>

			<div class="form-group">


				<form:hidden path="customerId" id="cid" class="form-control" />
				<div class="col-md-4 control-label col-xs-12">
					<label for="exampleInputEmail2">Payment Mode</label>


				</div>
				
				

				<div class="col-md-4 control-label col-xs-12 custom-select-box cf">
					<form:select path="paymentMode" class="form-control required">
						<option value="">Please Select</option>
						<form:option value="CASH">CASH</form:option>
						<form:option value="CREDIT">CREDIT</form:option>
						<form:option value="CHEQUE">CHEQUE</form:option>

					</form:select>

				</div>
				</div>

				<div class="form-group"  id="checkNoDIv">
					<div class="col-md-4 control-label col-xs-12">
						<label class="form-lbl mandatory_lbl">Cheque No</label>
					</div>
					<div class="col-md-4 control-label col-xs-12">
						<form:input path="checkNo" type="text" class="form-control" />
					</div>
				</div>
			


			<div class="form-group">
				<div class="col-md-12 col-xs-offset-2 col-xs-12">
					<input type="submit" value="Sell" class="btn btn-primary" />

				</div>
			</div>




		</form:form>
	</div>

	<!-- /.container -->

	<!-- <script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->

</body>
</html>