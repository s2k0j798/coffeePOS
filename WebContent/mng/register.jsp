<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="mngButton.js"></script>
</head>
<body>

	<style>
h1 {
	text-align: center;
}

#wrapper {
	border: 1px solid black;
}
</style>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="mngButton.js" charset="UTF-8"></script>
</head>
<body>
	<div id="wrapper">
		<h1>��� ���</h1>
		<table width="300" height="300" align="center">

			<tr>
				<td>���</td>
				<td><input id="eNum" name="id" type="text"></td>
			</tr>
			<tr>
				<td>��ȭ��ȣ</td>
				<td><input id="ePhone" name="tel" type="text"></td>
			</tr>
			<tr>
				<td>�̸�</td>
				<td><input id="eName" name="name" type="text"></td>
			</tr>
			<tr>
				<td>����</td>
				<td><input id="ePay" name="name" type="text"></td>
			</tr>
			<tr>
				<td rowspan="3" colspan="1" align="center" bgcolor="white"></td>
			</tr>
			<tr>
				<td align="center"><input type="submit" value="���"
					id="registerSubmit"> <input type="submit" value="���"></td>
			</tr>
		</table>
	</div></html>