<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML>
<div id="tabs-6">

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
								<button type="button" id="customerSerach"
									class="btn btn-primary">Customer Search</button>

							</div>
						</div>

						<p class="error nocustomer"></p>

					</form:form>
				</div>


				<form:form method="post" action=""
					modelAttribute="customerCommandForm" cssClass="form-horizontal"
					role="form" id="customerCommandForm">



					<h3 class=".h2 centerh2">Serach Customer In System</h3>






					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Customer Name</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="name" id="vname" type="text"
								class="form-control" readonly="true" />

						</div>
					</div>


					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Customer Address</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="address" id="vaddress" type="text"
								class="form-control" readonly="true" />

						</div>
					</div>



					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Customer Mobile No</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="mobile_no" id="vmobileno" type="text"
								class="form-control" readonly="true" />

						</div>
					</div>

					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Customer Dues</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="dues" type="text" id="vdues"
								class="form-control" readonly="true" />

						</div>
					</div>

					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Last Transaction
								Date</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="updatedate" type="text" id="vupdatedate"
								class="form-control" readonly="true" />

						</div>
					</div>


					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Customer Email</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="email" type="text" class="form-control"
								id="vemail" readonly="true" />

						</div>
					</div>





				</form:form>

			</div>