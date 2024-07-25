<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>CodePen - Sign Up/Sign In Responsive form</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <meta name="viewport" content="width=device-width,initial-scale=1">
</head>
<style type="text/css">
    

@import url("https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600;700&display=swap");
*, ::before, ::after {
  box-sizing: border-box;
}

body {
  margin: 0;
  padding: 0;
  font-family: "Open Sans", sans-serif;
  font-size: 0.938rem;
  color: #23004d;
}

h1 {
  margin: 0;
}

a {
  text-decoration: none;
}

img {
  max-width: 100%;
  height: auto;
  display: block;
}

.login {
  display: grid;
  grid-template-columns: 100%;
  height: 100vh;
  margin-left: 1.5rem;
  margin-right: 1.5rem;
}
.login__content {
  display: grid;
}
.login__img {
  justify-self: center;
}
.login__img img {
  width: 310px;
  margin-top: 1.5rem;
}
.login__forms {
  position: relative;
  height: 368px;
}
.login__register, .login__create {
  position: absolute;
  bottom: 1rem;
  width: 100%;
  background-color: #f2f2f2;
  padding: 2rem 1rem;
  border-radius: 1rem;
  text-align: center;
  box-shadow: 0 8px 20px rgba(35, 0, 77, 0.2);
  animation-duration: 0.4s;
  animation-name: animateLogin;
}
.login__title {
  font-size: 1.5rem;
  margin-bottom: 2rem;
}
.login__box {
  display: grid;
  grid-template-columns: max-content 1fr;
  column-gap: 0.5rem;
  padding: 1.125rem 1rem;
  background-color: #fff;
  margin-top: 1rem;
  border-radius: 0.5rem;
}
.login__icon {
  font-size: 1.5rem;
  color: #4AD395;
}
.login__input {
  border: none;
  outline: none;
  font-size: 0.938rem;
  font-weight: 700;
  color: #23004d;
  width: 100%;
}
.login__input::placeholder {
  font-size: 0.938rem;
  font-family: "Open Sans", sans-serif;
  color: #a49eac;
}
.login__forgot {
  display: block;
  width: max-content;
  margin-left: auto;
  margin-top: 0.5rem;
  font-size: 0.813rem;
  font-weight: 600;
  color: #a49eac;
}
.login__button {
  display: block;
  padding: 1rem;
  margin: 2rem 0;
  background-color: #4AD395;
  color: #fff;
  font-weight: 600;
  text-align: center;
  border-radius: 0.5rem;
  transition: 0.3s;
}
.login__button:hover {
  background-color: #65bf97;
}
.login__account, .login__signin, .login__signup {
  font-weight: 600;
  font-size: 0.813rem;
}
.login__account--account, .login__signin--account, .login__signup--account {
  color: #23004d;
}
.login__account--signin, .login__account--signup, .login__signin--signin, .login__signin--signup, .login__signup--signin, .login__signup--signup {
  color: #4AD395;
  cursor: pointer;
}
.login__social {
  margin-top: 2rem;
}
.login__social--icon {
  font-size: 1.5rem;
  color: #23004d;
  margin: 0 1rem;
}

.block {
  display: block;
}

.none {
  display: none;
}

@keyframes animateLogin {
  0% {
    transform: scale(1, 1);
  }
  50% {
    transform: scale(1.1, 1.1);
  }
  100% {
    transform: scale(1, 1);
  }
}
@media screen and (min-width: 576px) {
  .login__forms {
    width: 348px;
    justify-self: center;
  }
}
@media screen and (min-width: 1024px) {
  .login {
    height: 100vh;
    overflow: hidden;
  }
  .login__content {
    grid-template-columns: repeat(2, max-content);
    justify-content: center;
    align-items: center;
    margin-left: 10rem;
  }
  .login__img {
    display: flex;
    width: 600px;
    height: 588px;
    background-color: #fff;
    border-radius: 1rem;
    padding-left: 1rem;
  }
  .login__img img {
    width: 80%;
    margin-top: 0;
  }
  .login__register, .login__create {
    left: -11rem;
  }
  .login__register {
    bottom: -2rem;
  }
  .login__create {
    bottom: -5.5rem;
  }
}
  
</style>


<body>
<!-- partial:index.partial.html -->
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>


  <div class="login">
    <div class="login__content">
      <div class="login__img">
        <img src="https://image.freepik.com/free-vector/code-typing-concept-illustration_114360-3581.jpg" alt="user login">
      </div>
      <div class="login__forms">
        <!-- login form -->
        <form action="" class="login__register" id="frmsc">
          <h1 class="login__title">Sign In</h1>
          <div class="login__box">
            <i class='bx bx-user login__icon'></i>
            <input type="text" placeholder="Username" class="login__input" id ="id_loginID" name="USER_ID">
          </div>
          <div class="login__box">
            <i class='bx bx-lock login__icon'></i>
            <input type="text" placeholder="Password" class="login__input" id ="id_loginPW" name ="USER_PW">
          </div>


          <!-- <a href="#" class="login__forgot">Forgot Password? </a> -->
          
          <a href="#" class="login__button" id="id_signIn">Sign In</a>
<!--           
          <div>
            <span class="login__account login__account--account">Don't Have an Account?</span>
            <span class="login__signin login__signin--signup" id="sign-up">Sign Up</span>
          </div>
 -->
        </form>
        
        <!--create account form -->
        <!-- 
        <form action="" class="login__create none" id="frm">
          <h1 class="login__title">Create Account</h1>
          <div class="login__box">
            <i class='bx bx-user login__icon'></i>
            <input type="text" placeholder="Username" class="login__input">
          </div>
          
          <div class="login__box">
            <i class='bx bx-at login__icon'></i>
            <input type="text" placeholder="Email" class="login__input">
          </div>
          
          <div class="login__box">
            <i class='bx bx-lock login__icon'></i>
            <input type="text" placeholder="Password" class="login__input">
          </div>
          
          <a href="#" class="login__button">Sign Up</a>
          
          <div>
            <span class="login__account login__account--account">Already have an Account?</span>
            <span class="login__signup login__signup--signup" id="sign-in">Login</span>
          </div>
          
          <div class="login__social">
             <a href="#" class="login__social--icon"><i class='bx bxl-facebook'></i></a>
             <a href="#" class="login__social--icon"><i class='bx bxl-twitter'></i></a>
             <a href="#" class="login__social--icon"><i class='bx bxl-google'></i></a>
             <a href="#" class="login__social--icon"><i class='bx bxl-github'></i></a>
          </div>
        </form>
        
         -->
        
      </div>
    </div>
   </div>
<!-- partial -->


</body>
</html>

<script type="text/javascript">
let settingId = "jesus";
let settingPw = "1234";

// Jquery Start
$(document).ready(function(){

    $("#id_signIn").click(function(){
        onClickLogin();
    });

    $("#id_loginPW").on("keyup",function(e){
        var keyC = e.keyCode ? e.keyCode : e.charCode;        
        if(keyC==13) {
            onClickLogin();
        }
    });

});// Jquery END



function onClickLogin() {
	
	var loginID = $("#id_loginID").val();
	var loginPW = $("#id_loginPW").val();
      
  	
  	$.ajax({
  	    url: "/user/chkUserInfo.do",
  	    type: "POST",
  	    dataType:"json",
  	    
  	    data: $("#frmsc").serialize() , 
  	    success: function(data) {
  	    	
  	    	
	  	  	if(loginID == data.USER_ID && loginPW == data.USER_PW){
	  		  alert('로그인 성공!');
	  		  
	  		  $("#frmsc").attr("target", "_self").attr("action", "/user/Dologin.do").submit();
	  		  
	  		  
	  		} else{
	  		  alert('아이디 혹은 패스워드를 확인해주세요.');
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


/*
const signup = document.getElementById("sign-up");
signin  = document.getElementById("sign-in");
loginin = document.getElementById("login-in");
loginup = document.getElementById("login-up");

signup.addEventListener("click", () => {

    alert('test!');
    loginin.classList.remove("block");
    loginup.classList.remove("none");

    loginin.classList.add("none");
    loginup.classList.add("block");
})

signin.addEventListener("click", () => {

    alert('test22222');
    loginin.classList.remove("none");
    loginup.classList.remove("block");

    loginin.classList.add("block");
    loginup.classList.add("none");
})
*/
</script>