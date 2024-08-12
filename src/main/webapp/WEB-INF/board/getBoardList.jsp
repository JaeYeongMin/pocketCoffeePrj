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
  <meta name="viewport" content="width=device-width,initial-scale=0.45">

</head>

<style>
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

.container {
  padding: 15px 30px;
  position: relative;
  background: inherit;
  width: 50%;
}

.container.left {
  left: 0;
}

.container.right {
  left: 50%;
}

.container::after {
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

.container.right::after {
  left: -8px;
}

.container::before {
  content: "";
  position: absolute;
  width: 50px;
  height: 2px;
  top: calc(50% - 1px);
  right: 8px;
  background: #006e51;
  z-index: 1;
}

.container.right::before {
  left: 8px;
}

.container .date {
  position: absolute;
  display: inline-block;
  top: calc(50% - 8px);
  text-align: center;
  font-size: 14px;
  font-weight: bold;
  color: #006e51;
  text-transform: uppercase;
  letter-spacing: 1px;
  z-index: 1;
}

.container.left .date {
  right: -75px;
}

.container.right .date {
  left: -75px;
}

.container .icon {
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

.container.left .icon {
  right: 56px;
}

.container.right .icon {
  left: 56px;
}

.container .content {
  padding: 30px 90px 30px 30px;
  background: #f6d155;
  position: relative;
  border-radius: 0 500px 500px 0;
}

.container.right .content {
  padding: 30px 30px 30px 90px;
  border-radius: 500px 0 0 500px;
}

.container .content h2 {
  margin: 0 0 10px 0;
  font-size: 18px;
  font-weight: normal;
  color: #006e51;
}

.container .content p {
  margin: 0;
  font-size: 16px;
  line-height: 22px;
  color: #000000;
}

@media (max-width: 767.98px) {
  .timeline::after {
    left: 90px;
  }

  .container {
    width: 100%;
    padding-left: 120px;
    padding-right: 30px;
  }

  .container.right {
    left: 0%;
  }

  .container.left::after,
  .container.right::after {
    left: 82px;
  }

  .container.left::before,
  .container.right::before {
    left: 100px;
    border-color: transparent #006e51 transparent transparent;
  }

  .container.left .date,
  .container.right .date {
    right: auto;
    left: 15px;
  }

  .container.left .icon,
  .container.right .icon {
    right: auto;
    left: 146px;
  }

  .container.left .content,
  .container.right .content {
    padding: 30px 30px 30px 90px;
    border-radius: 500px 0 0 500px;
  }
}
</style>
<body>
<div class="timeline">



<c:forEach items="${boardList}" var="list" varStatus="stat">
			
	<c:choose>
		<c:when test="${(stat.index+1) mod 2 != 0}">
			<div class="container left">
			  <div class="date">${list.USER_ID}</div>
			  <i class="icon fa fa-home"></i>
			  <div class="content">
			    <h2>${list.CONT_TITLE}</h2>
			    <p>${list.CONT_TEXT}</p>
			  </div>
			</div>
		</c:when>
		
		<c:otherwise>
			<div class="container right">
			  <div class="date">${list.USER_ID}</div>
			  <i class="icon fa fa-gift"></i>
			  <div class="content">
			    <h2>${list.CONT_TITLE}</h2>
			    <p>${list.CONT_TEXT}</p>
			  </div>
			</div>	
		</c:otherwise>
	
	</c:choose>



	
	
	
	
</c:forEach>











</div>
</body>
</html>

<script>


//Jquery Start
$(document).ready(function(){
	
	$("#container").click(function(){
		onClikcPop();
	});


});// Jquery END



function onClikcPop(){
	
	alert('test');
	
}


</script>