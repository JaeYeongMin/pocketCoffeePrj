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
    ID: <span id="span_id">${detail.USER_ID}</span> 
    <br><input type="text" id="USER_ID" name="USER_ID" placeholder="ID" value="${detail.USER_ID}"> <br><br><br>
    
    PW: <span id="span_pw">${detail.USER_PW}</span>
    <br><input type="text" id="USER_PW" name="USER_PW" placeholder="PW" value="${detail.USER_PW}"> <br><br><br>
	
	성공여부:
	<select id="LOGIN_YN" name="LOGIN_YN">
		<option value ="N" <c:if test="${detail.LOGIN_YN eq 'N'}"> selected</c:if>>대기</option>
		<option value ="Y" <c:if test="${detail.LOGIN_YN eq 'Y'}"> selected</c:if>>성공</option>
	</select>

  <hr>
  <br>
  <div id="btnUpdate" class="btn" >수정</div>
</form>
</body>
</html>

<script>
$(document).ready(function(){
	$("#btnUpdate").click(function(){
		updateUserInfo();
	});
});



function updateUserInfo(){

	var userId= $("#USER_ID").val();
	var userPw= $("#USER_PW").val();
	
    if(userId == "" || userId == null || userId == typeof "undefinded"){
    	alert('아이디를 입력해주세요.');
    	return;
    }
    
    if(userPw == "" || userPw == null || userPw == typeof "undefinded"){
    	alert('비밀번호를 입력해주세요.');
    	return;
    }
	
	$.ajax({
	    url: "/user/updateUserInfo.do",
	    type: "POST",
	    dataType:"json",
	    
	    data: $("#frmsc").serialize() , 
	    success: function(data) {
	    	alert("수정되었습니다.");
	    	 location.reload();
	    },beforeSend:function(){ 
  	    },
  	    complete:function(){ 
  	    },
        error : function(xhRequest, ErrorText, thrownError) {
        	alert('데이터 수정중 오류가 발생했습니다.');
        }
	});
}

</script>