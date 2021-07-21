$().ready(function() {
	
	$('#checkNoDIv').hide();

	changeProduct();
	changeProductItemPurchase();
	addToTabel();
	changeItemPurchase();
	customerSearch();
	paymnetMode();

});

function paymnetMode() {
	

		$("#paymentMode").change(function() {
			$('#checkNoDIv').hide();
			var key = $(this).val();
			console.log(key);

			if(key=='CHEQUE')
				$('#checkNoDIv').show();
		})
	
}

function customerSearch() {

	$("#customerSerach")
			.on(
					"click",
					function() {

						$('#vname').val('');
						$('#vaddress').val('');
						$('#vmobileno').val('');
						$('#vdues').val('');
						$('#vupdatedate').val('');
						$('#vemail').val('');
						$('.nocustomer').text("");
						var name = $('#csearchname').val();
						var mobileno = $('#csearchmobileno').val();
						// console.log(mobileno + name);
						$
								.ajax({
									type : 'GET',
									url : ctx
											+ "/customer/getCustomer.html?mobileno="
											+ mobileno + "&name=" + name,

									success : function(data) {
										console.log(data);

										if (data.includes("empty")) {
											$('.nocustomer').text(
													"No Data Provide.");
										}

										if (data.includes("Error ")) {
											$('.nocustomer').text(
													"No Customer Found.");
										}

										if (!data.includes("empty")
												&& !data.includes("Error ")) {

											var datamap = $.parseJSON(data);
											// console.log(datamap.customer_id);
											$('#vname').val(datamap.name);
											$('#customerName').val(datamap.name);
											$('#cid').val(datamap.customer_id);
											
											
											$('#vaddress').val(datamap.address);
											$('#vmobileno').val(
													datamap.mobileNo);
											$('#vdues').val(datamap.dues);
											if (datamap.updatedate) {
												$('#vupdatedate').val(
														datamap.date);
											} else {
												$('#vupdatedate').val(
														datamap.updatedate);
											}
											$('#vemail').val(datamap.email);
										}
									}
								});

					});
}

function changeProduct() {

	$("#product")
			.change(
					function() {

						var key = $(this).val();
						console.log(key);
						$
								.ajax({
									type : 'GET',
									url : ctx
											+ "/sell/getProductItemData.html?key="
											+ key,

									success : function(data) {
										$("#itemtabel tr:gt(0)").remove();
										if (data) {
											var datamap = $.parseJSON(data);
											var row
											$
													.each(
															datamap,
															function(key, value) {
																row += '<tr><td>'
																		+ value.id
																		+ '</td><td id="pn'
																		+ value.id
																		+ '">'
																		+ value.product_name
																		+ '</td><td id="in'
																		+ value.id
																		+ '">'
																		+ value.item_name
																		+ '</td><td id="au'
																		+ value.id
																		+ '">'
																		+ value.stock_avalble
																		+ '</td><td><input type ="text" id="row'
																		+ value.id
																		+ '"onkeypress="if ( isNaN( String.fromCharCode(event.keyCode) )) return false;"/></td>'
																		
																		+ '<td id="gst'
																		+ value.id
																		+ '">'
																		+ value.gstRate+" %"
																		+ '</td>'
																		
																		+ '<td id="up'
																		+ value.id
																		+ '">'
																		+ value.unit_price
																		+ '</td>'
																		+ '<td><a href = "javascript:void(0)"  data-button ='
																		+ value.id
																		+ ' product-id ='
																		+ value.product_id
																		+ ' item-id ='
																		+ value.id
																		+ ' class="btn btn-primary" >ADD</a>'
																'</td></tr>'
															});
											// alert(row)
											$("#itemtabel tr:first").after(row);

										}
									}
								});

					});
}
function addToTabel() {
	/*
	 * $( ".row" ).click(function() { alert( '' ); });
	 */
	var count = 0;
	$("#itemtabel")
			.on(
					"click",
					"a",
					function() {
						var id = $(this).attr('data-button');
						var au = $("#au" + id).text();

						var ru = Number($("#row" + id).val());
						var product_id = $(this).attr('product-id');
						var item_id = $(this).attr('item-id');
						var product_name = $("#pn" + id).text() + '';
						var item_name = $("#in" + id).text() + '';
						var unit_price = $("#up" + id).text();
						var gst_perce = $("#gst" + id).text();
						

						console.log(gst_perce);

						if (ru <= au && ru > 0) {
							//alert('valid');
							var row = '<tr><td>'
									+ count
									+ '</td>'

									+ '<td><input type="text" readonly="true" class="reoo"   name="itemDto['
									+ count
									+ '].product_name" value= "'
									+ product_name
									+ '" /> </td>'
									+ '<td><input type="text" readonly="true" class="reoo"  name="itemDto['
									+ count
									+ '].item_name" value= "'
									+ item_name
									+ '" /> </td>'
									+ '<td><input type="text" readonly="true" class="reoo"  name="itemDto['
									+ count + '].total_unit" value= ' + ru
									+ ' /></td>'
									+ '<td><input type="text" name="itemDto['
									+ count + '].unit_cost" readonly="true" class="reoo" value= '
									+ unit_price + ' /></td>'
									+ '<td><input type="text" name="itemDto['
									+ count + '].offer_price" value= 0 onkeypress="if ( isNaN( String.fromCharCode(event.keyCode) )) return false;" /></td>'
									+ '<td><input type="text" name="itemDto['
									+ count + '].gstPerc" readonly="true" class="reoo"  value= ' + 
									gst_perce + ' /></td>'
									+ '<td><input type="text" name="itemDto['
									+ count + '].total_price" readonly="true" class="reoo"  value= ' + ru
									* unit_price + ' /></td>'
									+ '<input type="hidden" name="itemDto['
									+ count + '].product_id" value= '
									+ product_id + ' /> '
									+ '<input type="hidden" name="itemDto['
									+ count + '].id" value= ' + item_id
									+ ' /> '

									+ '</tr>'
							$("#selectedItem tr:first").after(row);
							count++;
						}
						if (ru > au) {
							alert('Please Enter Valid unit');
						}
					});
}

function changeProductItemPurchase() {
	$("#product_ID")
			.change(
					function() {
						$("#itemID").find('option').remove();
						$('#itemID').append(
								$("<option value=''></option>").attr("value",
										"").text("Please Select"));
						var key = $(this).val();
						$
								.ajax({
									type : 'GET',
									url : ctx
											+ "/sell/getProductItemData.html?key="
											+ key,

									success : function(data) {
										// alert(data);
										$("#itemID").find('option').remove();
										$('#itemID').append(
												$("<option></option>").attr(
														"value", "").text(
														"Please Select"));
										if (data) {
											var datamap = $.parseJSON(data);
											// $("#prouductSubtype ").remove();
											$
													.each(
															datamap,
															function(key, value) {
																// console.log(key
																// + ": " +
																// value);
																$('#itemID')
																		.append(
																				$(
																						"<option></option>")
																						.attr(
																								"value",
																								value.id)
																						.text(
																								value.item_name));
															});

										}
									}
								});

					});
}

function changeItemPurchase() {
	$("#itemID").change(function() {

		var key = $(this).val();
		$.ajax({
			type : 'GET',
			url : ctx + "/purchase/getItemData.html?key=" + key,

			success : function(data) {
				// alert(data);
				// console.log(data)
				if (data) {
					var datamap = $.parseJSON(data);
					console.log(datamap);
					console.log(datamap.item_name);
					// $("#prouductSubtype ").remove();
					$('#item-Name').val(datamap.item_name);
					// $('#noof-Unit').val(datamap.stock_avalble);
					// $('#unit-Price').val(datamap.unit_price);

				}
			}
		});

	});
}