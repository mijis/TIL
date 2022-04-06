<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css" href="http://211.63.89.149/jsp_prj/common/css/main_20220321.css">

<!-- bootstrap CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

<style type="text/css">
td{border: 1px solid #333; height: 100px; width: 140px; background-color: #000000; padding-left: 10px; padding-right: 10px}
img{ text-align: center}
span{color:#ffffff}
</style>
<!-- jQuery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	
});//ready
</script>
</head>
<body>
<div>
<%
String[] names={"정인선","권용현","강현모","신유철","서희수"};
%>

<table>
	<tr>
		<!-- JSP가 익숙하지 못해서 깔끔한 코드가 나오지 못했다. 항상 변수를 올리는 걸 잊지말자 -->
		<% 
		String img="";
		for(int i=0; i<names.length; i++) {
		 	img = names[i].equals("권용현")? "images/img1.png":"images/img2.png"; %> 
			<td><img src="<%=img%>" style="height: 80px"/><br><span><%=names[i] %></span></td>
		<%} %> 
	</tr>
</table>
</div>
</body>
</html>