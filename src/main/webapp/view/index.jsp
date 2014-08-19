<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/common/taglib.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page" />

<!DOCTYPE HTML>
<html>
<head>
<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<center><h1>This is my JSP page.</h1></center>
	<form method="post" action="${ctx}/user/query" id="userform">
		<table width="700" border="1" cellspacing="0" cellpadding="4" bordercolor="#33CCFF" align="center">
			<tr><td colspan="3" align="center">Query user information</td></tr>
			<tr><td>name : </td><td><input type="text" name="name" length="100%" value="${user.name}"/></td><td><input type="submit" value="query" /></td>
			</tr>
			<tr><td>result : </td><td colspan="2">${userinfo}</td></tr>
		</table>
	</form>

	<br>
	<form method="post" action="${ctx}/user/uploadPhoto?id=${user.id}&name=${user.name}" enctype="multipart/form-data">
		<table width="700" border="1" cellspacing="0" cellpadding="4" bordercolor="#33CCFF" align="center">
			<tr>
				<td width="60%" height="54" align="center">一、请点取下面的“浏览”按键选择您要上传的图片<br> (图片尺寸：<b><font color=red>200</font> </b> X <b><font
						color=red>150</font> </b>象素，<font color=red><b>30K</b> </font>字节以内):<br> <br> <input type="file" name="userfile"> <br>
				</td>
				<td width="40%" rowspan="2" align="center" valign="middle"><img border="0" width="200" height="150"
					src="${ctx}/user/viewPhoto?id=${user.id}">
				</td>
			</tr>
			<tr>
				<td width="60%" align="center">二、选择好您要上传的图片后，请按下面的确认键开<br>始上传... <br> <input type="submit" name="submit" value="确 认">
				</td>
			</tr>
			<tr>
				<td width="60%" align="center"><br> 三、对已上传照片不满意的用户，可以重新上传新照<br> 片覆盖原照，具体操作与首次上传一致。 <br> <br>
				</td>
				<td align="center" width="40%"><a href="javascript:window.close()"><b>关闭</b> </a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
