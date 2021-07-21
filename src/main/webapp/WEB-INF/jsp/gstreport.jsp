<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<title>GST REPORT PAGE</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js" /></script>
<script type="text/javascript">
	$(function() {
		$("#startDate").datepicker({
			dateFormat : 'dd-mm-yy',
			changeYear : true,
			changeMonth : true,
			onSelect : function(date) {
				var dt2 = $('#endDate');
				var startDate = $(this).datepicker('getDate');
				var minDate = $(this).datepicker('getDate');
				console.log(minDate);
				dt2.datepicker('setDate', minDate );
				startDate.setDate(startDate.getDate() + 30);
				//sets dt2 maxDate to the last day of 30 days window
				dt2.datepicker('option', 'minDate', startDate);
			}
		});

		$("#endDate").datepicker({
			dateFormat : 'dd-mm-yy',
			changeYear : true,
			changeMonth : true,
		});
	});
</script>
</head>
<body>
	<div id="page">

		<h1 class="h2 centerh2">GST Report</h1>

		<form:form method="post"
			action="${pageContext.request.contextPath}/gst/gstreport.html"
			modelAttribute="gstSearchcommand" cssClass="form-horizontal"
			role="form" id="gstSearchcommand">

			<div class="form-group">
				<div class="col-md-4 control-label col-xs-12">
					<label class="form-lbl mandatory_lbl"> GST Slab </label>
				</div>

				<div class="col-md-4 control-label col-xs-12 custom-select-box cf">
					<form:select path="slabId" class="form-control required">
						<option value="">Please Select</option>
						<form:options items="${gstslabmap}" />

					</form:select>

				</div>

			</div>
			<div class="form-group">
				<div class="col-md-4 control-label col-xs-12">
					<label for="exampleInputName2">Start Date</label>
					</div>
					<div class="col-md-4 control-label col-xs-12">
					<form:input path="startDate" type="text" class="form-control"
						readonly="true" />
				</div>
			</div>


			<div class="form-group">
				<div class="col-md-4 control-label col-xs-12">

					<label for="exampleInputEmail2">End Date</label>
				</div>
				<div class="col-md-4 control-label col-xs-12">
					<form:input path="endDate" type="text" class="form-control"
						readonly="true" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-12 col-xs-offset-2 col-xs-12">
					<button type="submit" class="btn btn-primary">Search GST Data</button>
				</div>
			</div>

		</form:form>
		
		<div class="table-responsive">
			<table class="table ">
			
				<tr>
					<th>#</th>
					<th>Date of Purchase</th>
					<th>Invoice No</th>
					<th>Customer Name</th>
					<th>Product Name</th>
					<th>HSN Code</th>
					<th>Item Name</th>
					<th>No of Item</th>
					<th>Unit Cost</th>
					<th>Discount</th>
					<th>GST Perc</th>
					<th>Total GST Amount</th>
					<th>Total Payble Amount</th>
					
					
				
				</tr>
			
				<c:forEach items="${listDto}" var="item" varStatus="count">

					<tr>
						<td>${count.index+1}</td>
						<td>${item.invoice_date}</td>
						<td>PT${item.item_transaction_id}</td>
						<td>${item.customerName}</td>
						<td>${item.product_name}</td>
						<td>${item.hsnCode}</td>
						<td>${item.item_name}</td>
						<td>${item.sell_unit}</td>
						<td>${item.unit_cost}</td>
						<td>${item.discountOffer}</td>
						<td>${item.gstPerc}%</td>
						<td>${item.gstAmount}</td>
						<td>${item.finalCost}</td>
					
						
					
					</tr>

				</c:forEach>

			</table>


	</div>

	</div>
</body>