<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<title>Stock Inventory</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">
$( function() {
	$( "#startDate" ).datepicker({ dateFormat: 'dd-mm-yy',  changeYear: true, changeMonth: true,
    	onSelect: function (date) {
            var dt2 = $('#endDate');
            var startDate = $(this).datepicker('getDate');
            var minDate = $(this).datepicker('getDate');
          
            dt2.datepicker('setDate', minDate);
            startDate.setDate(startDate.getDate() );
            //sets dt2 maxDate to the last day of 30 days window
            dt2.datepicker('option', 'minDate', startDate);
    	}  });
   
    
    
    
    
    $( "#endDate" ).datepicker({ dateFormat: 'dd-mm-yy',changeYear: true, changeMonth: true, });
    
   
    
   
  } );
  


</script>


</head>
<body>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/inventory.js" /></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js" /></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jsPdf.js" /></script>

	<div id="page">


		<h1 class="h2 centerh2">Stock Inventory</h1>
		
		

		<div>
		
		<form:form method="post" action="${pageContext.request.contextPath}/inventory/stock.html"
					modelAttribute="searchStockCommandForm" cssClass="form-horizontal"
					role="form" id = "stockForm">
					
					
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
							<label class="form-lbl mandatory_lbl">Avalaible Unit</label>
						</div>
						<div class="col-md-4 control-label col-xs-12">
							<form:input path="noofUnit" type="text" class="form-control" readonly="true"  />
						</div>
					</div>
					
					
					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Price of Unit</label>
						</div>
						<div class="col-md-4 control-label col-xs-12">
							<form:input path="unitPrice" type="text" class="form-control" readonly="true"  />
						</div>
					</div>
					
					
					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">

							<label class="form-lbl mandatory_lbl">History Type</label>
						</div>
						<div class="col-md-4 control-label col-xs-12 custom-select-box cf">
							<form:select path="reason" id="itemID"
								class="form-control required">
								<option value="ALL">ALL</option>
								<option value="UPDATE">UPDATE</option>
								<option value="SELL">SELL</option>

							</form:select>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Start Date</label>
						</div>
						<div class="col-md-4 control-label col-xs-12">
							<form:input path="startDate" type="text" class="form-control" readonly="true"  />
						</div>
					</div>
					
					
					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">End Date</label>
						</div>
						<div class="col-md-4 control-label col-xs-12">
							<form:input path="endDate" type="text" class="form-control" readonly="true" />
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-12 col-xs-offset-2 col-xs-12">

							<input type="submit" class="btn btn-primary" value="Serach Item History" />

						</div>
					</div>
					
					</form:form>
					
					
		
		</div>
		
	<div class="table-responsive">
			<table class="table ">
			
			<tr>
					<th>#</th>
					<th>Item Name</th>
					<th>Product Name </th>
					<th>No of Item</th>
					<th>Unit Cost</th>
					<th>Discount offer</th>
					<th>Total Discount offer</th>
					<th>Customer Name </th>
					<th>Supplier Name </th>
					<th>Transaction Type</th>
					<th>Date of Transaction</th>
					
					
					<th>Total Sell Amount</th>
					<th>Total Purchase Amount</th>
					
					
				</tr>
			
			<c:forEach items="${invoicedtoList}" var="item" varStatus="count">

					<tr>
						<td>${count.index+1}</td>
						<td>${item.itemName}</td>
						<td>${item.productName}</td>
						<td>${item.noofUnit}</td>
						<td>${item.unitPerchaseCost}</td>
						<td>${item.discountOfer}</td>
						<td>${item.totalDiscountOffer}</td>
						<td>${item.customerName}</td>
						<td>${item.supplierName}</td>
						<td>${item.updateReason}</td>
						<td>${item.addedDate}</td>
						
						
						<td>${item.totalSellCost}</td>
						<td>${item.totalPurchaseCost}</td>
						
					</tr>

				</c:forEach>
			
			</table>
			</div>	
			
			<div id="selectedDate"> <b>Date Range -   &nbsp; </b><b>${daterange}</b></div>
			<c:url var="pdfUrl"
							value="${download}" />	
		<c:if test="${not empty download}">
			<div id="pdfreport"><b><a href ="${pdfUrl}" class="btn btn-primary" target="_blank">Download Report</a></b></div>
		</c:if>
	</div>
</body>
</html>