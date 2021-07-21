$().ready(function() {

	sendImage();
});

function sendImage() {

	$("#save").on("click", function() {
		var images = canvas.toDataURL("image/png");
		console.log(username);
		console.log(uploadUrl);
		
		saveImage(images, username,uploadUrl)
	});
}

function saveImage(file, imageId,uploadUrl) {
	var oMyForm = new FormData();
	oMyForm.append("file", makeblob(file));
	oMyForm.append("imageId", imageId);
	$.ajax({
		url : uploadUrl+"/mmtImageApi/rest/upload.html",

		type : "POST",
		data : oMyForm,
		enctype : 'multipart/form-data',
		contentType : false,
		processData : false,
		crossDomain : true
	}).done(function(response) {
		location.reload();
		console.log("face id from image -" + response);

	}).fail(function(error) {
		// Oops, an error :(
		alert('No Internet COnection ');
	});
}

// converting encoded file to binary file
makeblob = function(dataURL) {
	var BASE64_MARKER = ';base64,';
	if (dataURL.indexOf(BASE64_MARKER) == -1) {
		var parts = dataURL.split(',');
		var contentType = parts[0].split(':')[1];
		var raw = decodeURIComponent(parts[1]);
		return new Blob([ raw ], {
			type : contentType
		});
	}
	var parts = dataURL.split(BASE64_MARKER);
	var contentType = parts[0].split(':')[1];
	var raw = window.atob(parts[1]);
	var rawLength = raw.length;

	var uInt8Array = new Uint8Array(rawLength);

	for (var i = 0; i < rawLength; ++i) {
		uInt8Array[i] = raw.charCodeAt(i);
	}

	return new Blob([ uInt8Array ], {
		type : contentType
	});
}
