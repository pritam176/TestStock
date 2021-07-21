<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}" />

<style>
#profilePic{
display:none;
width: 60%;
float: right;
text-align: center;
}
#profileData{
float: left;
width: 40%;
}
#canvas{
display: none;
}#control{

margin-top: 50px;
}
.profilePicprev{

float: right;
margin: 0 100px;
}
.profilePicprev{
height: 320px !important;
width: 300px !important;
}
#save{
display: none;
}
</style>
<html>
<body>

	<div id="page">






		<h1 class="h2 centerh2">Admin Page</h1>
		<div id="profileData">
		<c:set var="username"><sec:authentication property="principal.username" /></c:set>
		<table class="table">
			<tr>
				<td><i>Welcome :-</i></td>
				<td>${username}</td>
			</tr>
			<tr>
				<td><i>Role :-</i></td>


				<td><sec:authentication property="principal.authorities" /></td>
			</tr>
			<tr>
				<td><i>Upload Photo </i></td>


				<td><button id="takepic"         class="btn btn-primary">Take Picture To Upload</button></td>
			</tr>


		</table>
		
		</div>
		<spring:eval expression='@environment.getProperty("imagePath")' var="context" />
		<c:url var="url" value="${baseURL}/${context}${username }" />
		
		<div class="profilePicprev"><img class="img-responsive" src="${url}" height="250" width="300" alt="Please Take Photo to Upload"/></div>
		<div id="profilePic">
			
			<video id="video" preload="none"></video>
			

			<canvas id="canvas" ></canvas>
			
			<div id="control">
				<button id="startbutton" class="btn btn-primary">Take Profile photo</button>
			<!-- <button id="closebutton">Close Button</button> -->
			<button id="start" class="btn btn-primary">Re-Take Profile photo</button>
			<button id="save" class="btn btn-primary">Save Profile photo</button>
		</div>
		
		</div>
		
		

	</div>
	<script id="jsbin-javascript">
	var username= "${username }";
	var uploadUrl= "${baseURL}"
		function takepicture() {
			var width = 320;
			var height = 300;

			canvas.width = width;
			canvas.height = height;
			canvas.getContext('2d').drawImage(video, 0, 0, width, height);
			// var data = canvas.toDataURL('image/png');
			vidOff();
			var images = canvas.toDataURL("image/png");
			
			$("#canvas").show();
			$("#save").show();
			$("#startbutton").hide();
			//document.getElementById("video").style.display = "none";
			//detectPersion(images);

		}

		function startVideo() {
			$("#canvas").hide();
			video = document.querySelector('#video');

			document.getElementById("video").style.display = "";
			video.play();

		}

		function vidOff() {
			//clearInterval(theDrawLoop);
			//ExtensionData.vidStatus = 'off';
			var video = document.querySelector('#video');
			video.pause();
			//video.height =0;
			//video.width =0;
			//video.src = "";
			//localstream.getTracks()[0].stop();
			document.getElementById("video").style.display = "none";
			console.log("Vid off");
		}

		//var b = setTimeout(timeFunction, 3000);

		function timeFunction() {
			// takepicture();
		}

		startbutton.addEventListener('click', function(ev) {
			takepicture();
			ev.preventDefault();
		}, false);

		start.addEventListener('click', function(ev) {
			startVideo();
			ev.preventDefault();
		}, false);

		/* closebutton.addEventListener('click', function(ev) {
			vidOff();
			ev.preventDefault();
		}, false); */
		
		
		$("#takepic")
		.on(
				"click",
				function() {
					capture();
					$(".profilePicprev").hide();
					$('#profilePic').show();
					
				});
		
		
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/webcapdetection.js" /></script>
		<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/imageCapture.js" /></script>
</body>
</html>