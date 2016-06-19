<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">  
function reloadVerifyCode(){  
    document.getElementById('verifyCodeImage').setAttribute('src', '${pageContext.request.contextPath}/account/verifycodeimage?t='+new Date().getTime());  
}  
</script>
<style type="text/css">
*{
	margin: 0;
}

.container{
	padding: 30px;
}

input{
	margin-top: 3px;
	-webkit-box-sizing: border-box;
	height: 30px;
}

input,img{vertical-align:middle;}

#verifyCodeImage{
-webkit-box-sizing: border-box;
	height: 30px;
	border: 1px solid gray;
	margin-top: 3px;
}

.btn{
	width: 100px;
	margin-top: 15px;
	margin-left: 20px;
	letter-spacing: 18px;
	text-indent: 20px;

}

</style>
</head>
<body>
<div class="container">
	<div style="color:red; font-size:22px;">${message_login}</div>  
	  
	<form action="${pageContext.request.contextPath }/account/login" method="POST">  
	    姓名：<input type="text" name="username"/><br/>  
	    密码：<input type="text" name="password"/><br/>  
	    验证：<input type="text" name="verifyCode" />  
	       <img id="verifyCodeImage" onclick="reloadVerifyCode()" src="<%=request.getContextPath()%>/account/verifycodeimage"/><br/>  
	    <input type="submit" value="登录" class="btn"/>  
	    <input type="reset" value="重置" class="btn"/>  
	</form>
</div> 
</body>
</html>