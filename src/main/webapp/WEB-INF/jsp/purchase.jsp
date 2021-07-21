<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Purchase Product Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>

</head>
<body>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/mmtmain.js" /></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/validation.js" /></script>

	<div id="page">
	

		<div id="tabs">

			<ul>
			

				<sec:authorize access="hasAnyRole('ROLE_SUPER_USER')">
					<li><a href="#tabs-1">Add Product</a></li>
					<li><a href="#tabs-8">Add GST Rate</a></li>
					<li><a href="#tabs-9">Update GST Rate</a></li>
				</sec:authorize>
				<li><a href="#tabs-4">Add Supplier</a></li>
				<li><a href="#tabs-2">Add Item Detail</a></li>
				<li><a href="#tabs-3">Update Item Detail</a></li>
				<li><a href="#tabs-5">Add Customer Detail</a></li>
				<li><a href="#tabs-6">Serach Customer Detail</a></li>
				<li><a href="#tabs-7">Update Supplier Detail</a></li>
			</ul>


<jsp:include page="./include/superrole.jsp"></jsp:include>
<jsp:include page="./include/customer.jsp"></jsp:include>
<jsp:include page="./include/customersearch.jsp"></jsp:include>

			

			<div id="tabs-2">

				<form:form method="post"
					action="${pageContext.request.contextPath}/purchase/purchaseitem.html"
					modelAttribute="purchaseCommandForm" cssClass="form-horizontal"
					role="form" id="itemform">

					<h3 class=".h2 centerh2">Add Item Detail To System</h3>




					<!-- Item Detail -->

					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl"> Product Name </label>
						</div>

						<div class="col-md-4 control-label col-xs-12 custom-select-box cf">
							<form:select path="productID" class="form-control required">
								<option value="">Please Select</option>
								<form:options items="${productList}" />

							</form:select>

						</div>

					</div>

					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">

							<label class="form-lbl mandatory_lbl">Item Name</label>
						</div>
						<div class="col-md-4 control-label col-xs-12">
							<form:input path="itemName" type="text" class="form-control" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">No of Unit</label>
						</div>
						<div class="col-md-4 control-label col-xs-12">
							<form:input path="noofUnit" type="text" class="form-control" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Unit Price</label>
						</div>
						<div class="col-md-4 control-label col-xs-12">
							<form:input path="unitPrice" type="text" class="form-control" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">

							<label class="form-lbl mandatory_lbl">Discount Offer</label>
						</div>
						<div class="col-md-4 control-label col-xs-12">
							<form:input path="discountOfer" type="text" class="form-control" />
						</div>
					</div>


					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Supplier Id</label>
						</div>
						<div class="col-md-4 control-label col-xs-12 custom-select-box cf">


							<form:select path="supplierId" class="form-control required">
								<option value="">Please Select</option>
								<form:options items="${suppliermap}" />

							</form:select>

						</div>
					</div>


					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">

							<label class="form-lbl mandatory_lbl">Unit Purchase Price</label>
						</div>
						<div class="col-md-4 control-label col-xs-12">
							<form:input path="purchasePriceUnit" type="text"
								class="form-control" />
						</div>
					</div>


					<div class="form-group">
						<div class="col-md-12 col-xs-offset-2 col-xs-12">
							<input type="submit" class="btn btn-primary" value="Add Item" />
						</div>
					</div>

				</form:form>

			</div>

			<div id="tabs-3">

				<form:form method="post"
					action="${pageContext.request.contextPath}/purchase/updatepurchaseitem.html"
					modelAttribute="purchaseCommandForm" cssClass="form-horizontal"
					role="form" id="updateitemForm">

					<h3 class=".h2 centerh2">Update Item Detail</h3>




					<!-- Item Detail -->
					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label> Product Name </label>
						</div>
						<div class="col-md-4 control-label col-xs-12 custom-select-box cf">
							<form:select path="productID" id="product_ID"
								class="form-control required">
								<option value="">Please Select</option>
								<form:options items="${productList}" />

							</form:select>
						</div>
					</div>


					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">

							<label> Item Name </label>
						</div>
						<div class="col-md-4 control-label col-xs-12 custom-select-box cf">
							<form:select path="itemID" id="itemID"
								class="form-control required">
								<option value="">Please Select</option>
								<form:options items="${productItemList}" />

							</form:select>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">

							<label>Item Name</label>
						</div>
						<div class="col-md-4 control-label col-xs-12">
							<form:input path="itemName" type="text" id="item-Name"
								class="form-control" readonly="true" disabled="true" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label>No of Unit</label>
						</div>
						<div class="col-md-4 control-label col-xs-12">
							<form:input path="noofUnit" type="text" id="noof-Unit"
								class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label>Unit Price</label>
						</div>
						<div class="col-md-4 control-label col-xs-12">
							<form:input path="unitPrice" id="unit-Price" type="text"
								class="form-control" />
						</div>
					</div>



					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Supplier Id</label>
						</div>
						<div class="col-md-4 control-label col-xs-12 custom-select-box cf">


							<form:select path="supplierId" class="form-control required">
								<option value="">Please Select</option>
								<form:options items="${suppliermap}" />

							</form:select>

						</div>
					</div>

					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label>Unit Purchase Price</label>
						</div>
						<div class="col-md-4 control-label col-xs-12">
							<form:input path="purchasePriceUnit" type="text"
								class="form-control" />
						</div>
					</div>


					<div class="form-group">
						<div class="col-md-12 col-xs-offset-2 col-xs-12">
							<input type="submit" class="btn btn-primary" value="Update Item" />
						</div>
					</div>

				</form:form>
			</div>

			

			
			<div id="tabs-7">
				<h3 class=".h2 centerh2">Update Supplier Detail</h3>
			</div>

		</div>
	</div>