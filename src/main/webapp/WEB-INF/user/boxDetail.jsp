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
	  background: url(../../resource/images/box/login_do.png) no-repeat center center fixed; 
	  -webkit-background-size: cover;
	  -moz-background-size: cover;
	  -o-background-size: cover;
	  background-size: cover;
	}

	.btn{
		border: 1px solid black;
		display:inline-block;
		padding: 3px; cursor: pointer;
		width: 50px;
		height: 50px;
	}
	
	.bgimg {
	    border: 0;
	    padding: 0; 
	    background-image: url('image.jpg');
	    min-height: 100%;
	    background-position: center;
	    background-size: cover;
	}

</style>
<body>
<form id="frmsc" name="frmsc" method="post">
<input type="hidden" name="LOGIN_YN" id="LOGIN_YN" value="${detail.LOGIN_YN}">
<!-- 


	<h1><a href="/">하나님나라로 Login!</a></h1>
	<hr>
		
		<img alt="" src="../../resource/images/box/resultIMG.jpg">
	<hr>
	<br>
	<div id="id_alertTxt"></div>
-->

<div id="onClickBtn" class="btn" style="color: white;"></div>
<div id="id_alertTxt" style="color: white;"></div>

</form>
</body>
</html>

<script>

$(document).ready(function(){
	var index = 0;
	var SCH_STATUS ="WAIT";
	
	// 클릭
	$("#onClickBtn").click(function(){
		
		if(SCH_STATUS !="WAIT"){
			alert('조회중입니다.');
			return false;
		}else{
			SCH_STATUS = "START";
		}
		
		
		let loginYn = $("#LOGIN_YN").val();
		
		setInterval(function(){
			loginYn = $("#LOGIN_YN").val();
			index++;
			
			if(loginYn =="N"){
				chkLoginYN(index);
			}

		}, 1000);

	});
	
});


function chkLoginYN(i){

	//$("#id_alertTxt").html("");
	// $("#id_alertTxt").html("시작되었습니다: " + i);
	
	$("#onClickBtn").css({
		"border" : "none"
		
	})
	
	console.log("시작되었습니다: " + i);
	
	$.ajax({
		url: "/user/chkUserInfo.do",
	    type: "POST",
	    dataType:"json",
	    data: $("#frmsc").serialize() , 
	    success: function(data) {
	    	$("#LOGIN_YN").val(data.LOGIN_YN);
	    	
	    	
	    	if(data.LOGIN_YN == "Y"){
	    		// $("#id_result").html("로그인 완료!");
	    		
				$("html").css({
				    "background":"url(../../resource/images/box/result_img.jpg) no-repeat center center fixed", 
				    /* "background-repeat" : "no-repeat", */ 
				    /* "background-position":"center center", */
				  	"-webkit-background-size": "cover",
				    "-moz-background-size": "cover",
				    "-o-background-size" : "cover",
				    "background-size": "cover"
				});
	    		
	    		doArduino();
	    	}
	    	
	    },beforeSend:function(){ 
	 	    },
	 	    complete:function(){ 
	 	    },
	       error : function(xhRequest, ErrorText, thrownError) {
	       	alert('데이터 수정중 오류가 발생했습니다.');
	       }
	});

}


// 아두이노 연동!
function doArduino() {
	
	$.ajax({
	    url: "/user/executeArduino.do",
	    type: "POST",
	    dataType:"json",
	    data: $("#frmsc").serialize() , 
	    success: function(data) {
	    	
	    	// $("#id_alertTxt").html("");
	    	// $("#id_alertTxt").html("박스 OPEN!!");
	    	
	    },beforeSend:function(){ 
	 	    },
	 	    complete:function(){ 
	 	    },
	       error : function(xhRequest, ErrorText, thrownError) {
	       	alert('연결에 실패했습니다');
	       }
	});

}




</script>