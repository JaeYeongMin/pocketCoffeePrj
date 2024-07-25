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
	.btn{
		border: 1px solid black;
		display:inline-block;
		padding: 3px; cursor: pointer; 
	}

</style>
<body>
<form id="frmsc" name="frmsc" method="post">
	<h1><a href="/">하나님나라로 Login!</a></h1>
	<hr>
		
		<div id="id_result">???????????????</div>
		
		<input type="text" name="LOGIN_YN" id="LOGIN_YN" value="${detail.LOGIN_YN}">
			
	<hr>
	<br>
	<div id="onClickBtn" class="btn" >시작!</div>
	
	<div id="id_alertTxt"></div>
	
	
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

	$("#id_alertTxt").html("");
	$("#id_alertTxt").html("시작되었습니다: " + i);
	
	$.ajax({
	    url: "/user/chkUserInfo.do",
	    type: "POST",
	    dataType:"json",
	    data: $("#frmsc").serialize() , 
	    success: function(data) {
	    	$("#LOGIN_YN").val(data.LOGIN_YN);
	    	
	    	
	    	if(data.LOGIN_YN == "Y"){
	    		$("#id_result").html("로그인 완료!");
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
/* function doArduino() {
    let port;
    let writer;
    
    // 사용자가 장치를 선택하도록 요청
    // port = await navigator.serial.requestPort();
    // 연결 설정
    await port.open({ baudRate: 9600 });

    const encoder = new TextEncoderStream();
    writer = encoder.writable.getWriter();
    encoder.readable.pipeTo(port.writable);

    console.log('Connected to Arduino');
    
    if (writer) {
        await writer.write(10 + '\n');
        console.log('Speed set to', 10);
    }

} */


</script>