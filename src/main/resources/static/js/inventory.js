$().ready(function() {

	
	//changeProduct();
	changeProductItemPurchase();
	//addToTabel();
	changeItemPurchase();

});


function changeProductItemPurchase(){
	$("#productID").change(
			function() {
				$("#itemID").find('option').remove();
				$('#itemID').append(
						$("<option></option>").attr(
								"value", "").text(
								"Please Select"));
				var key = $(this).val();
				$.ajax({
					type : 'GET',
					url : ctx + "/sell/getProductItemData.html?key=" + key,

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
							$.each(datamap, function(key, value) {
								//console.log(key + ": " + value);
								$('#itemID').append(
										$("<option></option>").attr(
												"value", value.id).text(
														value.item_name));
							});

						}
					}
				});

			});
	}

function changeItemPurchase(){
	$("#itemID").change(
			function() {
				
				var key = $(this).val();
				$.ajax({
					type : 'GET',
					url : ctx + "/purchase/getItemData.html?key=" + key,

					success : function(data) {
						// alert(data);
						//console.log(data)
						if (data) {
							var datamap = $.parseJSON(data);
							console.log(datamap);
							console.log(datamap.item_name);
							// $("#prouductSubtype ").remove();
							
							$('#noofUnit').val(datamap.stock_avalble);
							$('#unitPrice').val(datamap.unit_price);
							

						}
					}
				});

			});
	}