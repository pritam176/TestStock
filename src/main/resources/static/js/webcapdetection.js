function capture () {

	var localstream;
	var streaming = false,
	video = document.querySelector('#video'),
	canvas = document.querySelector('#canvas'),
	photo = document.querySelector('#photo'),
	startbutton = document.querySelector('#startbutton'),
	width = 320,
	height = 300

		navigator.getMedia = (navigator.getUserMedia ||
			navigator.webkitGetUserMedia ||
			navigator.mozGetUserMedia ||
			navigator.msGetUserMedia);

	navigator.getMedia({
		video: true,
		audio: false
	},
		function (stream) {
		if (navigator.mozGetUserMedia) {
			video.mozSrcObject = stream;
		} else {
			var vendorURL = window.URL || window.webkitURL;
			video.src = vendorURL.createObjectURL(stream);
		}
		localstream = stream;
		video.play();
	},
		function (err) {
		console.log("An error occured! " + err);
	});

	video.addEventListener('canplay', function (ev) {
		if (!streaming) {
			height = video.videoHeight / (video.videoWidth / width);
			video.setAttribute('width', width);
			video.setAttribute('height', height);
			canvas.setAttribute('width', width);
			canvas.setAttribute('height', height);
			streaming = true;
		}
	}, false);

};
