<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<title>Sell Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">

function myFunction() {
    window.print();
}
</script>



</head>
<body>



	<div id="page">


		<h1 class="h2 centerh2">INVOICE</h1>
		<h4 style="text-align: center;" ><spring:eval expression="@environment.getProperty('product')" /></h4>
		<c:url var="reportDate1"
							value="${reportDate}" />
		<h5 id="reportdate">Date-${reportDate1}</h5>

		<div class="tradersdata">
		
		<p>
		<spring:eval expression="@environment.getProperty('tradersName')" /> <br>
		GSTN NO:-<spring:eval expression="@environment.getProperty('gstnNo')" /> <br>
		<spring:eval expression="@environment.getProperty('address')" /> <br>
		<spring:eval expression="@environment.getProperty('state')" /><spring:eval expression="@environment.getProperty('dist')" /> <br>
		<spring:eval expression="@environment.getProperty('mobileno')" /><br>
		</p>
	
		
		</div>


		<!-- invoice Detail -->

		<div class="customerdata">
		<h4>Customer Detail</h4>
		Customer Name:-${reportdata.customerName} <br>
		Delevery Adress :-${reportdata.address} <br>
		Mobile No:-${reportdata.mobileNo} <br>
		
		</div>
		<div class="ivoicedata">
		
		<table id="info" class="table ">
			<tr>
							<td class="meta-head">Invoice #</td>
							<td class="meta-data">PT${reportdata.transactionid}</td>
			</tr>
			
			<tr>
							<td class="meta-head">Transaction Date</td>
							<td class="meta-data">${reportdata.transactionDate}</td>
			</tr>
			
			<tr>
							<td class="meta-head">Total Amount </td>
							<td class="meta-data">${reportdata.paybleamount}</td>
			</tr>
			
			<tr>
							<td class="meta-head">Payment Mode</td>
							<td class="meta-data">${reportdata.paymentMode}</td>
			</tr>
			
		</table>
		
		</div>
			
			<div>
				<table class="table ">
					<tr>
						<th>#</th>
						<th>Product Name</th>
						<th>HSN Code</th>
						<th>Item Name</th>
						<th>Unit</th>
						<th>Price per Unit</th>
						<th>Discount</th>
						<th>CGST%</th>
						<th>SGST%</th>
						<th>CGST Amount</th>
						<th>SGST Amount</th>
						
						<th>Total</th>
						<th>Total GST Amount</th>
						<th>Payble Amount</th>
					</tr>

					<c:forEach items="${reportdata.transactionItemDTO}" var="item" varStatus="count">
						
						<tr>
							<td>${count.index+1}</td>
							<td>${item.productName}</td>
							<td>${item.hsnCode}</td>
							<td>${item.itemName}</td>
							<td>${item.sellUnit}</td>
							<td>${item.unitcost}</td>
							<td>${item.discount}</td>
							<td>${item.cgstPerc}</td>
							<td>${item.sgstPerc}</td>
							<td>${item.cgstAmt}</td>
							<td>${item.sgstAmt}</td>
							
							<td>${item.totalamount}</td>
							<td>${item.totalGstAmt}</td>
							<td>${item.paybleAmount}</td>
						</tr>

					</c:forEach>
					
					<tr class="bottomboreder">
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							
					
					</tr>
					
					
					
					<tr>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="leftboreder">Total Discount</td>
							<td>${reportdata.totaldiscount}</td>
					
					</tr>
					
					<tr>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="leftboreder">Total Amount</td>
							<td>${reportdata.totalamount}</td>
					
					</tr>
					
					<tr class="bottomboreder">
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="leftboreder meta-head">Total GST Amount</td>
							<td >${reportdata.gstAmount}</td>
					
					</tr>
					
					
					<tr class="bottomboreder">
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="noborder"></td>
							<td class="leftboreder meta-head">Total Payble Amount</td>
							<td >${reportdata.paybleamount}</td>
					
					</tr>

				</table>

				<a class="btn btn-primary" target="_blanck"
				
				href="./report/bill.pdf?billId=${reportdata.transactionid}">Print Receipt</a>
			</div>
		


	</div>
	<!-- /.container -->

	

</body>
</html>