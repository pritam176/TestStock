<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<title>INVOICE REPORT PAGE</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<script type="text/javascript">
$( function() {
    $( "#startDate" ).datepicker({ dateFormat: 'dd-mm-yy',  changeYear: true, changeMonth: true,
    	onSelect: function (date) {
            var dt2 = $('#endDate');
            var startDate = $(this).datepicker('getDate');
            var minDate = $(this).datepicker('getDate');
          
            dt2.datepicker('setDate', minDate);
            startDate.setDate(startDate.getDate());
            //sets dt2 maxDate to the last day of 30 days window
            dt2.datepicker('option', 'minDate', startDate);
    	}  });
   
    
    
    
    
    $( "#endDate" ).datepicker({ dateFormat: 'dd-mm-yy',changeYear: true, changeMonth: true, });
  } );

</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js" /></script>
</head>
<body>



	<div id="page">


		<h1 class="h2 centerh2">INVOICE REPORT</h1>

<div id="serachform">
		<form:form method="post" action="${pageContext.request.contextPath}/inventory/invoiceSerach.html"
			modelAttribute="serachInvoiceCommand" cssClass="form-inline" id="serachInvoiceCommand"
			
					role="form">

			<div class="form-group">
				<label for="exampleInputName2">Start Date</label> 
				
				<form:input path="startDate" type="text" class="form-control" readonly="true" />
			</div>
			<div class="form-group">
				<label for="exampleInputEmail2">End Date</label>
				
				<form:input path="endDate" type="text" class="form-control" readonly="true" />
			</div>
			
			<div class="form-group">
						<div class="col-md-12 col-xs-offset-2 col-xs-12">
			<button type="submit" class="btn btn-primary">Search</button>
			
			</div>
			</div>

		</form:form>
</div>

<div id="invoiceform">
		<form:form method="post" action="${pageContext.request.contextPath}/inventory/invoiceSerachone.html"
			modelAttribute="serachInvoiceCommand" cssClass="form-inline"
			
					role="form" id="invoiceform1">

			<div class="form-group">
				<label for="exampleInputName2" class=" mandatory_lbl">Invoice No</label> 
				
				<form:input path="invoiceNo" type="text" class="form-control required" />
			</div>
			
			
			<div class="form-group">
						<div class="col-md-12 col-xs-offset-2 col-xs-12">
			<button type="submit" class="btn btn-primary">Invoice Search</button>
			
			</div>
			</div>

		</form:form>
</div>

		<div class="table-responsive">
			<table class="table ">
				<tr>
					<th>#</th>
					<th>Invoice No</th>
					<th>Customer Name</th>
					<th>No of Item</th>
					<th>Discount offer</th>
					<th>Total Amount</th>
					<th>Total GST Amount</th>
					<th>Total Payble Amount</th>
					<th>Mode Of Payment</th>
					<th>Date of Purchase</th>
					<th>View</th>
				</tr>

				<c:forEach items="${transactionList}" var="item" varStatus="count">

					<tr>
						<td>${count.index+1}</td>
						<td>PT${item.transactionid}</td>
						<td>${item.customerName}</td>
						<td>${item.noOfItem}</td>
						<td>${item.totaldiscount}</td>
						<td>${item.totalamount}</td>
						<td>${item.gstAmount}</td>
						<td>${item.paybleamount}</td>
						<td>${item.paymentMode}</td>
						<td>${item.transactionDate}</td>
						<c:url var="encUrl"
							value="/sell/getReportItemData.html?key=${item.transactionid}" />
						<td><a href="${encUrl}">Show</a></td>
					</tr>

				</c:forEach>

			</table>
			<c:url var="pdfUrl"
							value="${download}" />
							
							
		<c:if test="${not empty download}">
			<div id="pdfreport"><b><a href ="${pdfUrl}" class="btn btn-primary"  target="_blank">Download Report</a></b></div>
		</c:if>
							
			
			<div id="selectedDate"> <b>Date Range -   &nbsp; </b><b>${daterange}</b></div>


		</div>

	</div>
	<!-- /.container -->



</body>
</html>