$().ready(function() {
	// validate the comment form when it is submitted

	invoiceFormValidate();
	stockFormValidate();
	sellformValidate();
	purchaseProductformValidate();
	purchaseSupplierformValidate();
	purchaseItemformValidate();
	updateItemformValidate();
	customerformValidate();
	invoiceSearchFormValidate();
	purchaseGSTformValidate();
	gstUpdateCommandFormValidate();
	gstSearchFormValidate();

});

function invoiceFormValidate() {
	$("#invoiceform1").validate({
		rules : {
			invoiceNo : "required"

		},
		messages : {
			invoiceNo : "Please enter Invoice No."

		}
	});
}

function gstSearchFormValidate() {
	$("#gstSearchcommand").validate({
		rules : {
			slabId : "required",
			startDate : "required",
			endDate : "required",

		},
		messages : {
			slabId : "Please Select Slab ",
			startDate : "Please Select report Start Date",
			endDate : "Please Select report End Date",

		}
	});
}

function invoiceSearchFormValidate() {
	$("#serachInvoiceCommand").validate({
		rules : {
			startDate : "required"

		},
		messages : {
			startDate : "Please Chose Start Date ."

		}
	});
}

function stockFormValidate() {
	$("#stockForm").validate({
		rules : {
			productID : "required",
			itemID : "required"

		},
		messages : {
			productID : "Please Select Product Name.",
			itemID : "Please Select Product Name Then Item Name."

		}
	});
}

function sellformValidate() {
	$("#sellform").validate({
		rules : {
			product : "required",
			paymentMode : "required",
			customerName : "required",
			checkNo : "required"

		},
		messages : {
			product : "Please Select Product Name.",
			paymentMode : "Please Select Payment Mode.",
			customerName : "No Customer Selected,Please Search ",
			checkNo : "Please Enter Cheque No."

		}
	});
}

function purchaseProductformValidate() {
	$("#purchaseproductform").validate({
		rules : {
			productName : {
				required : true,
				lettersonly : true,
				remote : {
					url : ctx + "/purchase/isAvalblebyProductName.html?",
					type : "get",
					data : {
						username : function() {
							// return $( "#name" ).val();
						}
					},
					complete : function(data) {
						console.log(data.responseText);

					}
				}
			},
			hsnCode : {
				required : true,
				number : true
			},
			gstSlabid : "required",

		},
		messages : {
			productName : {
				required : "Please Enter  Product Name.",
				lettersonly : "Please Enter  letters only.",
				remote : "Product Name Already Exist"
			},
			hsnCode : {
				required : "Please Enter  HSN Code.",
				number : "Please enter numbers Only"
			},
			gstSlabid : "Please Select GST Slab",

		}
	});
}

function purchaseGSTformValidate() {
	$("#gstCommandForm").validate({
		rules : {
			name : {
				required : true,
				lettersonly : true,
				remote : {
					url : ctx + "/gst/isAvalblebySlabName.html?",
					type : "get",
					data : {
						username : function() {
							// return $( "#name" ).val();
						}
					},
					complete : function(data) {
						console.log(data.responseText);

					}
				}
			},
			taxrate : {
				required : true,
				number : true,
				remote : {
					url : ctx + "/gst/isAvalblebyTaxRate.html?",
					type : "get",
					data : {
						username : function() {
							// return $( "#name" ).val();
						}
					},
					complete : function(data) {
						console.log(data.responseText);

					}
				}
			},

		},
		messages : {
			name : {
				required : "Please Enter  GST Slab Name.",
				lettersonly : "Please Enter  letters only.",
				remote : "GST Slab Name Already Exist"
			},
			taxrate : {
				required : "Please Enter TAX rate.",
				number : "Please enter numbers Only",
				remote : "Tax Rate Already Exist"
			},

		}
	});
}

function gstUpdateCommandFormValidate() {
	$("#gstUpdateCommandForm").validate({
		rules : {
			name : {
				required : true,
				lettersonly : true,
				remote : {
					url : ctx + "/gst/isAvalblebySlabName.html?",
					type : "get",
					data : {
						username : function() {
							// return $( "#name" ).val();
						}
					},
					complete : function(data) {
						console.log(data.responseText);

					}
				}
			},
			taxrate : {
				required : true,
				number : true,
				remote : {
					url : ctx + "/gst/isAvalblebyTaxRate.html?",
					type : "get",
					data : {
						username : function() {
							// return $( "#name" ).val();
						}
					},
					complete : function(data) {
						console.log(data.responseText);

					}
				}
			},
			slabId : "required",

		},
		messages : {
			name : {
				required : "Please Enter	GST Slab Name.",
				lettersonly : "Please Enter  letters only.",
				remote : "GST Slab Name Already Exist"
			},
			taxrate : {
				required : "Please Enter TAX rate.",
				number : "Please enter numbers Only",
				remote : "Tax Rate Already Exist"
			},
			slabId : "Please Select  GST Slab Name",

		}
	});
}

function purchaseSupplierformValidate() {
	$("#purchasesupplierform").validate({
		rules : {
			supplierName : {
				required : true,
				remote : {
					url : ctx + "/customer/isAvalbleBySupplierName.html?",
					type : "get",
					data : {
						username : function() {
							// return $( "#name" ).val();
						}
					},
					complete : function(data) {
						console.log(data.responseText);

					}
				}
			},
			supplierMobileNo : {
				required : true,
				number : true
			}

		},
		messages : {
			supplierName : {
				required : "Please Enter  Supplier Name .",
				remote : "Supplier Name Already Present"
			},
			supplierMobileNo : {
				required : "Please Enter  Supplier Mobile No.",
				number : "Please enter numeric value only"
			}

		}
	});
}

function purchaseItemformValidate() {
	$("#itemform").validate({
		rules : {
			productID : "required",

			itemName : {
				required : true,
				remote : {
					url : ctx + "/purchase/isAvalblebyItemName.html?",
					type : "get",
					data : {
						username : function() {
							// return $( "#name" ).val();
						}
					},
					complete : function(data) {
						console.log(data.responseText);

					}
				}
			},
			unitPrice : {
				required : true,
				number : true
			},
			noofUnit : {
				required : true,
				number : true
			},
			purchasePriceUnit : {
				required : true,
				number : true
			},
			supplierId : "required",

		},
		messages : {
			productID : "Please Select  Product  Name.",

			itemName : {
				required : "Please Select  Item Name .",
				remote : "Item Name Already Present"
			},
			unitPrice : {
				required : "Please Enter  Unit Price",
				number : "Please enter numbers Only"
			},
			noofUnit : {
				required : "Please Enter No of Unit",
				number : "Please enter numbers Only"
			},
			purchasePriceUnit : {
				required : "Please Enter Purchase Price",
				number : "Please enter numbers Only"
			},
			supplierId : "Please Select Supplier Name",

		}
	});
}

function updateItemformValidate() {
	$("#updateitemForm").validate({
		rules : {
			productID : "required",

			itemID : "required",
			unitPrice : {
				required : true,
				number : true
			},
			noofUnit : {
				required : true,
				number : true
			},
			purchasePriceUnit : {
				required : true,
				number : true
			},
			supplierId : "required",

		},
		messages : {
			productID : "Please Select  Product  Name.",

			itemID : "Please Select  Item Name .",
			unitPrice : {
				required : "Please Enter  Unit Price",
				number : "Please enter numbers Only"
			},
			noofUnit : {
				required : "Please Enter No of Unit",
				number : "Please enter numbers Only"
			},
			purchasePriceUnit : {
				required : "Please Enter Purchase Price",
				number : "Please enter numbers Only"
			},
			supplierId : "Please Select Supplier Name",

		}
	});
}

function customerformValidate() {
	$("#customerCommandForm").validate({
		rules : {
			name : {
				required : true,
				remote : {
					url : ctx + "/customer/isAvalblebyCustomerName.html?",
					type : "get",
					data : {
						username : function() {
							// return $( "#name" ).val();
						}
					},
					complete : function(data) {
						console.log(data.responseText);

					}
				}
			},

			address : "required",
			mobile_no : {
				required : true,
				number : true,
				minlength : 10,
				maxlength : 10,
			},
			email : {
				email : true
			}

		},
		messages : {
			name : {
				required : "Please Enter Customer  Name.",
				remote : "Customer Name Already Present"
			},

			address : "Please Enter Customer  Address .",
			mobile_no : {
				required : "Please Enter  Mobile No",
				number : "Please enter numbers Only",
				maxlength : "Please enter 10 digit Only",
				minlength : "Please enter 10 digit Only"
			},
			email : "Please Enter  valid email",

		}
	});
}
jQuery.validator.addMethod("lettersonly", function(value, element) {
	return this.optional(element) || /^[a-z]+$/i.test(value);
}, "Letters only please");
