<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
����� Ű�� �� ${encodedKey } �Դϴ�. <br>
����� ���ڵ� �ּҴ� �� ${url } �Դϴ�. <br><br>

<img alt="qrcode" class="js-qrcode qrcode" src="${url }" width="250" height="250">

 
<form action="<%=request.getContextPath() %>/OtpResultServlet" method="get">
    code : <input type="text" name="user_code"><br><br>
     
    <input type="hidden" name="encodedKey" value="${encodedKey }" readonly="readonly"><br><br>
    <input type="submit" value="����!">
     
</form>
</body>
</html>