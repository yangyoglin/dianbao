<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

 <style>
        /* 这里给P标签设置点样式
           为了显眼一些
        */
        #result{
            background-color: skyblue;
            font-size: 30px;
            color: white;
        }
    </style>
</head>
<body>
<p id="result">用于展示结果</p>
<!-- 按钮用来请求后台的数据 -->
<button id="request">点我请求后台的数据</button>

<script>
    //点击按钮后向后台发起请求
    document.getElementById("request").onclick = function () {
        var getRequest = new XMLHttpRequest();//创建请求对象
        getRequest.open("POST", "http://192.168.227.2:8080/dianbaoWeb/imagesinfo/selectByList", true);//链接服务器
        getRequest.setRequestHeader("Content-Type","application/json");
        getRequest.send();//发送请求
        getRequest.onreadystatechange = function () {
            if (getRequest.readyState === 4) {//请求已完成，且响应已就绪
                if (getRequest.status === 200) {//请求成功
                    var resultStr = getRequest.responseText;//获取结果
                    var resultObj = JSON.parse(resultStr);//解析数据
                    //将返回的结果展示到P标签中
                    document.getElementById("result").innerHTML =
                        "名称 : " + resultObj.name + "</br>" +
                        "年龄 : " + resultObj.age + "</br>" +
                        "性别 : " + resultObj.sex;
                }
                else {
                    alert("错误码 : " + getRequest.status);
                }
            }
        }
    }
</script>

</body>
</html>