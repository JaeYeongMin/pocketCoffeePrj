<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<!DOCTYPE">
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
</head>

<style>
	html { 
	  background: url(../../resource/images/box/result_img.jpg) no-repeat center center fixed; 
	  -webkit-background-size: cover;
	  -moz-background-size: cover;
	  -o-background-size: cover;
	  background-size: cover;
	}
</style>
<body>

<audio id="audio2" controls preload="none" style="display:none" >
    <source src="../../resource/sounds/box/login.mp3">
</audio>

</body>
</html>

<script>

$(document).ready(function(){
	palyAudio2();
	
});


function palyAudio2(){
	var ie_player = document.getElementById("audio2");
	ie_player.play();
}


function palyAudio(){
	
	var audio = new Audio("../../resource/sounds/box/login.mp3");
	// load
	audio.load();

	// 볼륨 설정
	audio.volume = 1;

	// 실행
	audio.play();
	
}

</script>