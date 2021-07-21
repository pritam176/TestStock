<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML>

<sec:authorize access="hasAnyRole('ROLE_SUPER_USER')">
			<div id="tabs-1">

				<!-- Purchase Detail -->
				<form:form method="post"
					action="${pageContext.request.contextPath}/purchase/purchaseproduct.html"
					modelAttribute="purchaseCommandForm" cssClass="form-horizontal"
					role="form" id="purchaseproductform">
					<div>


						<h3 class=".h2 centerh2">Add Product To System</h3>

					</div>

					<div class="form-group">

						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Purchase</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="productName" type="text" class="form-control" />

						</div>

					</div>
					
					<div class="form-group">

						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">HSN Code</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="hsnCode" type="text" class="form-control" />

						</div>

					</div>

					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl"> GST Slab </label>
						</div>

						<div class="col-md-4 control-label col-xs-12 custom-select-box cf">
							<form:select path="gstSlabid" class="form-control required">
								<option value="">Please Select</option>
								<form:options items="${gstslabmap}" />

							</form:select>

						</div>

					</div>



					<div class="form-group">
						<div class="col-md-12 col-xs-offset-2 col-xs-12">

							<input type="submit" class="btn btn-primary" value="Add Purchase" />

						</div>
					</div>

				</form:form>

			</div>

			<div id="tabs-8">
				<!-- Supplier Detail -->
				<form:form method="post"
					action="${pageContext.request.contextPath}/gst/addgstslab.html"
					modelAttribute="gstCommandForm" cssClass="form-horizontal"
					role="form" id="gstCommandForm">



					<h3 class=".h2 centerh2">Add GST Slab</h3>

					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">GST Name</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="name" type="text" class="form-control"
								 />

						</div>
					</div>


					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Tax Rate</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="taxrate" type="text" class="form-control" />

						</div>
					</div>







					<div class="form-group">
						<div class="col-md-12 col-xs-offset-2 col-xs-12">

							<button type="submit" class="btn btn-primary" id="gstsubmit" value="" >Add GST Slab</button>
							<p id="success" style="display: none;">Data Saved</p>
						</div>
					</div>

				</form:form>

			</div>

			<div id="tabs-9">
				<!-- Supplier Detail -->
				<form:form method="post"
					action="${pageContext.request.contextPath}/gst/updategstslab.html"
					modelAttribute="gstCommandForm" cssClass="form-horizontal"
					role="form" id="gstUpdateCommandForm">



					<h3 class=".h2 centerh2">Update GST Slab</h3>

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
							<label class="form-lbl mandatory_lbl">GST Name</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="name" type="text" class="form-control"
								 />

						</div>
					</div>


					<div class="form-group">
						<div class="col-md-4 control-label col-xs-12">
							<label class="form-lbl mandatory_lbl">Tax Rate</label>
						</div>

						<div class="col-md-4 control-label col-xs-12">
							<form:input path="taxrate" type="text" class="form-control" />

						</div>
					</div>



					<div class="form-group">
						<div class="col-md-12 col-xs-offset-2 col-xs-12">

							<input type="submit" class="btn btn-primary" value="Update GST" />

						</div>
					</div>

				</form:form>

			</div>

</sec:authorize>