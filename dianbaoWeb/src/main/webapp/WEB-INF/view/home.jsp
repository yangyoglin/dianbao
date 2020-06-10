<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>上传测试页</title> 
</head> 
<body>
	<form action="file/uploadImg" method="post" enctype="multipart/form-data">
		<label>用户名：</label><input type="text" name="username"><br>
		<label>密码：</label><input type="password" name="password"><br>
		<label>上传头像：</label><input type="file" name="file"><br>
		<input type="submit">
	</form>
</body> 
</html>