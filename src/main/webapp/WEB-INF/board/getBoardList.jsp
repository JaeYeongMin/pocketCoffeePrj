<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<!DOCTYPE">
<html>
<head>
  <meta charset="UTF-8">
  <title></title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>

<style>
input textarea{
	font-size :50px;
}
*,
*::before,
*::after {
  box-sizing: border-box;
}

body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
  background: #ffffff;
}

.timeline {
  position: relative;
  width: 100%;
  max-width: 1140px;
  margin: 0 auto;
  padding: 15px 0;
}

.timeline::after {
  content: "";
  position: absolute;
  width: 2px;
  background: #006e51;
  top: 0;
  bottom: 0;
  left: 50%;
  margin-left: -1px;
}

.containerTime {
  padding: 15px 30px;
  position: relative;
  background: inherit;
  width: 50%;
}

.containerTime.left {
  left: 0;
}

.containerTime.right {
  left: 50%;
}

.containerTime::after {
  content: "";
  position: absolute;
  width: 16px;
  height: 16px;
  top: calc(50% - 8px);
  right: -8px;
  background: #ffffff;
  border: 2px solid #006e51;
  border-radius: 16px;
  z-index: 1;
}

.containerTime.right::after {
  left: -8px;
}

.containerTime::before {
  content: "";
  position: absolute;
  width: 50px;
  height: 2px;
  top: calc(50% - 1px);
  right: 8px;
  background: #006e51;
  z-index: 1;
}

.containerTime.right::before {
  left: 8px;
}

.containerTime .date {
  position: absolute;
  display: inline-block;
  top: calc(50% - 25px);
  text-align: center;
  font-size: 30px;
  font-weight: bold;
  color: #006e51;
  text-transform: uppercase;
  letter-spacing: 1px;
  z-index: 1;
}

.containerTime.left .date {
  right: -100px;
}

.containerTime.right .date {
  left: -100px;
}

.containerTime .icon {
  position: absolute;
  display: inline-block;
  width: 40px;
  height: 40px;
  padding: 9px 0;
  top: calc(50% - 20px);
  background: #f6d155;
  border: 2px solid #006e51;
  border-radius: 40px;
  text-align: center;
  font-size: 18px;
  color: #006e51;
  z-index: 1;
}

.containerTime.left .icon {
  right: 56px;
}

.containerTime.right .icon {
  left: 56px;
}

.containerTime .content {
  padding: 30px 90px 30px 30px;
  background: #f6d155;
  position: relative;
  border-radius: 0 100px 100px 0;
}

.containerTime.right .content {
  padding: 30px 30px 30px 90px;
  border-radius: 100px 0 0 100px;
  text-align: right;
}

.containerTime .content h2 {
  margin: 0 0 10px 0;
  font-size: 35px;
  font-weight: normal;
  color: #006e51;
}

.containerTime .content p {
  margin: 0;
  font-size: 30px;
  line-height: 50px;
  color: #000000;
}

@media (max-width: 767.98px) {
  .timeline::after {
    left: 90px;
  }

  .containerTime {
    width: 100%;
    padding-left: 120px;
    padding-right: 30px;
  }

  .containerTime.right {
    left: 0%;
  }

  .containerTime.left::after,
  .containerTime.right::after {
    left: 82px;
  }

  .containerTime.left::before,
  .containerTime.right::before {
    left: 100px;
    border-color: transparent #006e51 transparent transparent;
  }

  .containerTime.left .date,
  .containerTime.right .date {
    right: auto;
    left: 15px;
  }

  .containerTime.left .icon,
  .containerTime.right .icon {
    right: auto;
    left: 146px;
  }

  .containerTime.left .content,
  .containerTime.right .content {
    padding: 30px 30px 30px 90px;
    border-radius: 100px 0 0 100px;
  }
}


</style>









<style>

* {
   box-sizing: border-box;
   outline: none;
   font-family: Arial;
}
.btn {
   background-color: #ddd;
   display: inline-block;
   padding: 20px 30px;
   color: #333;
   font: bold 16px Arial;
   text-decoration: none;
}
#modal-window {
   position: fixed;
   top: 0;
   left: 0;
   width: 100%;
   height: 100%;
   z-index: 9999;
   background-color: rgba(0, 0, 0, 0.8); /* shaded background color */
   visibility: hidden;
   display: table;
}
#modal-window.active {
   visibility: visible;
}
.modal-close {
   position: absolute;
   width: 100%;
   height: 100%;
   /* top: 0; */
   display: block;
   font-size: 40px;
   line-height: 30px;
   color: rgba(255, 255, 255, 0.5);
   text-align: right;
   padding: 20px;
   cursor: pointer;
   transition: 0.2s;
   -webkit-transition: 0.2s;
   transition-delay: 0.2s;
   -webkit-transition-delay: 0.2s;
   transform: translateY(-20px);
   -webkit-transform: translateY(-20px);
}
#modal-window.active .modal-close {
   transform: translateY(0px);
   -webkit-transform: translateY(0px);
}
.align-content {
   display: table-cell;
   vertical-align: middle;
}
#modal-window .content2 {
   position: relative;
   max-width: 720px; /*modal window max width */
   background-color: #fff;
   margin: 0 auto;
   padding: 10px 30px;
   height: 1000px;
   /* visual effect */
   opacity: 0;
   transition: 0.2s;
   -webkit-transition: 0.2s;
   transform: translateY(20px);
   -webkit-transform: translateY(20px);
}
#modal-window.active .content2 {
   opacity: 1;
   transform: translateY(0px);
   -webkit-transform: translateY(0px);
}



</style>
















<body>

<div class="timeline">



<c:forEach items="${boardList}" var="list" varStatus="stat">
	<c:set var="str1" value="${list.CONT_TEXT}"/>
	<c:set var="cont_text" value="${fn:replace(str1, '\\\n', '<br>')}" />

			
	<c:choose>
		<c:when test="${(stat.index+1) mod 2 != 0}">
		
			<div class="containerTime left" SEQ ="${list.USER_SEQ}"  TITLE ="${list.CONT_TITLE}"  TEXT ="${cont_text}">
			  <div class="date">${list.USER_ID}</div>
			  <i class="icon fa fa-home"></i>
			  <div class="content">
			    <h2>${list.CONT_TITLE}</h2>
			    <p>${cont_text }</p>
			  </div>
			</div>
		</c:when>
		
		<c:otherwise>
			<div class="containerTime right" SEQ ="${list.USER_SEQ}"  TITLE ="${list.CONT_TITLE}"  TEXT ="${cont_text}">
			  <div class="date">${list.USER_ID}</div>
			  <i class="icon fa fa-gift"></i>
			  <div class="content">
			    <h2>${list.CONT_TITLE}</h2>
			    <p>${cont_text }</p>
			  </div>
			</div>	
		</c:otherwise>
	
	</c:choose>



	
	
	
	
</c:forEach>











</div>

<!-- ========== MODALS ========== -->
<div id="modal-window">
	<span class="modal-close">&#10006;</span>
	<div class="align-content">
		<div class="content2">
		
			<div style="height: 900px;">
				<form id="hForm" name="hForm">
					<input type="hidden" name="USER_SEQ" id="USER_SEQ" value="">
					<input type="hidden" name="TEMP_CONT_TITLE" id="TEMP_CONT_TITLE">
					<input type="hidden" name="TEMP_CONT_TEXT" id="TEMP_CONT_TEXT">
			
					
					
					<div class="mb-3">
						<label for="CONT_TITLE" class="form-label" style="font-size: 50px;">제목</label>
						<p id="view1" style="font-size: 30px;"></p>
						<input class="form-control form-control-lg edit" type="hidden" style="font-size: 30px;" name="CONT_TITLE" id="CONT_TITLE">
					</div>
					</br>
					
					<div class="mb-3">
						<label for="CONT_TEXT" class="form-label" style="font-size: 50px;">내용</label>
						<p id="view2" style="font-size: 30px;"></p>
						
						<textarea class="form-control edit" rows="11" style="font-size: 30px; display: none" name="CONT_TEXT" id="CONT_TEXT"></textarea>
					</div>
	
				</form>
			
			</div>
			<div style=" text-align: center; position: relative; bottom: 10px; ">
			
				<a class="btn btn-primary" href="#" style="font-size: 30px;" id="btnEdit">편집하기</a>
				<a class="btn btn-primary" href="#" style="font-size: 30px; display:none;" id="btnSend">저장하기</a>
			<!-- <a class="btn btn-danger" href="#" role="button" style="font-size: 30px;" id="modal-close">닫기</a> -->
			
			</div>

		</div>
		

	</div>
</div>
<!-- ========== END MODALS ========== -->






</body>


</html>


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<script>


//Jquery Start
$(document).ready(function(){
	
	$(".containerTime").click(function(){
		onClikcPop($(this).attr("SEQ"), $(this).attr("TITLE") , $(this).attr("TEXT")  );
	});
	
	$('.modal-close').click(function() {
		// $(this).parent().parent().parent().parent().removeClass('active');
		$(this).parent().removeClass('active');
		onClickInit();
		
	});

	
    $('#btnEdit').on('click', function(event){
    	event.preventDefault();
    	$(this).css("display", "none");
    	
    	
    	$('#btnSend').css("display", "");
    	
    	$("#view1").css("display","none");
    	$("#view2").css("display","none");
    	

    	$("#CONT_TITLE").attr("type","text");
    	$("#CONT_TEXT").css("display","");
    	

    	$("#CONT_TITLE").val($("#TEMP_CONT_TITLE").val());
    	$("#CONT_TEXT").val($("#TEMP_CONT_TEXT").val().replaceAll(/\<br>/g, '\n'));
    	
    	
    });
	
    $('#btnSend').on('click', function(event){
    	event.preventDefault();
    	if(!confirm('저장하시겠습니까?')) return false;
    	
    	
      	$.ajax({
      	    url: "/board/updateBoard.do",
      	    type: "POST",
      	    dataType:"json",
      	    
      	    data: $("#hForm").serialize() , 
      	    success: function(data) {
      	    	
    	  	  	if(data.RETN_CODE =="200"){
    	  		  
    	  	  	 	location.reload();
    	  		  
    	  		} 
      	    },beforeSend:function(){ 
        	    },
        	    complete:function(){ 
        	    },
              error : function(xhRequest, ErrorText, thrownError) {
              	alert('데이터 수정중 오류가 발생했습니다.');
              }
      	});
		
    });
});// Jquery END



function onClikcPop(seq,title,text){

/* 	
	var loginYN = false;
	var inputString = prompt('비밀번호를 입력하세요');
	loginYN = true;
*/
	$("#USER_SEQ").val(seq);
	$("#view1").html(title);
	$("#view2").html(text);
	
	
	$("#TEMP_CONT_TEXT").val(text);
	$("#TEMP_CONT_TITLE").val(title);
		
	
	/* 
	$("#CONT_TEXT").val(text);

	$("#CONT_TITLE").val(title);
	*/
	
	

	
	$('#modal-window').addClass('active');
	
}


// 초기화 펑션
function onClickInit(){
	
	
	$("#btnEdit").css("display", "");
	
	
	$('#btnSend').css("display", "none");
	
	$("#view1").css("display","");
	$("#view2").css("display","");
	

	$("#CONT_TITLE").attr("type","hidden");
	$("#CONT_TEXT").css("display","none");
	

}

</script>

