<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML>
<div id="tabs-4">
				<!-- Supplier Detail -->
				<form:form method="post"
					action="${pageContext.request.contextPath}/customer/addsupplier.html"
					modelAttribute="purchaseCommandForm" cssClass="form-horizontal"
					role="form" id="purchasesupplierform">



					<h3 class=".h2 centerh2">Add Supplier To System</h3>

					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Supplier Name</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="supplierName" type="text" class="form-control" />

						</div>
					</div>


					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Supplier LicenceNo</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="supplierLicenceNo" type="text"
								class="form-control" />

						</div>
					</div>



					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Supplier Mobile No</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="supplierMobileNo" type="text"
								class="form-control" />

						</div>
					</div>


					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Supplier Addres</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="supplierAddres" type="text"
								class="form-control" />

						</div>
					</div>



					<div class="form-group">
						<div class="col-md-12 col-xs-offset-2 col-xs-12">

							<input type="submit" class="btn btn-primary" value="Add Supplier" />

						</div>
					</div>

				</form:form>

			</div>
			
			<div id="tabs-5">


				<!-- customer Detail -->
				<form:form method="post"
					action="${pageContext.request.contextPath}/customer/addcustomer.html"
					modelAttribute="customerCommandForm" cssClass="form-horizontal"
					role="form" id="customerCommandForm">



					<h3 class=".h2 centerh2">Add Customer To System</h3>

					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Customer Name</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="name" type="text" class="form-control" />

						</div>
					</div>


					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Customer Address</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="address" type="text" class="form-control" />

						</div>
					</div>



					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Customer Mobile No</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="mobile_no" type="text" class="form-control" />

						</div>
					</div>


					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Customer Email</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="email" type="text" class="form-control" />

						</div>
					</div>



					<div class="form-group">
						<div class="col-md-12 col-xs-offset-2 col-xs-12">

							<input type="submit" class="btn btn-primary"
								value="Add Customer " />

						</div>
					</div>

				</form:form>

			</div>
			
			