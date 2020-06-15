<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


System.out.println(basePath);
%>
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>上传测试页</title> 
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script> --%>
<%-- <script type="text/javascript" src="<%=basePath %>js/jquery-1.9.1.min.js"></script> --%>
</head> 
<body>
	<form action="http://192.168.227.2:8080/dianbaoWeb/imagesinfo/uploadImg" method="post" enctype="multipart/form-data">
		<label>图片名称：</label><input type="text" name="imagesName"><br>
		<label>模块编号：</label><input type="text" name="imagesModuleCode"><br>
		<label>模块名称：</label><input type="text" name="imagesModuleName"><br>
		<label>图片排序：</label><input type="text" name="imagesSort"><br>
		<!-- <label>密码：</label><input type="password" name="password"><br> -->
		<label>上传图片：</label><input type="file" name="file" id="fileupload"><br>
		 <input type="submit"> 
	</form>

</body> 
</html>