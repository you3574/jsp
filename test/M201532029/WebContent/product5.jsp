<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, M201532029.*" %>
<%

request.setCharacterEncoding("UTF-8");
String r1 = request.getParameter("radio1");

int radio1 = (r1 == null) ? 0 : Integer.parseInt(r1);
int radio2 = (r1 == null) ? 1 : Integer.parseInt(r1);
int radio3 = (r1 == null) ? 2 : Integer.parseInt(r1);
int radio4 = (r1 == null) ? 3 : Integer.parseInt(r1);

List<category> list;
if (radio1 == 0) list = productDAO4.findAll();
else if(radio1 == 1) list=productDAO4.findBycategoryId(radio2);
else if(radio1 == 2) list=productDAO4.findBycategoryId(radio3);
else list=productDAO4.findBycategoryId(radio4);

String all_checked = "0".equals(r1) ? "checked" : "";
String d_checked = "1".equals(r1) ? "checked" : "";
String al_checked = "2".equals(r1) ? "checked" : "";
String s_checked = "3".equals(r1) ? "checked" : "";


%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
      thead th { background-color: #eee; }
      table.table { width: 700px; margin-top: 10px; }
  </style>
</head>
<body>

<div class="container">

<h1>제품목록</h1>

<form class="form-inline" method="get">
  <div class="form-group">
    <label>제품유형</label>
    <div class="radio">
      <label><input type="radio" name="radio1" value="0" checked <%=all_checked %> /> 전체</label>
    </div>
      <div class="radio">
      <label><input type="radio" name="radio1" value="1" <%= d_checked %> /> 음료수</label>
    </div>
    <div class="radio">
      <label><input type="radio" name="radio1" value="2" <%= al_checked %> /> 주류</label>
    </div> 
     <div class="radio">
      <label><input type="radio" name="radio1" value="3" <%= s_checked %> /> 과자</label>
    </div>     
</div>
  <button type="submit" class="btn btn-primary">조회</button>
</form>

<table class="table table-bordered table-condensed">
    <thead>
        <tr>
            <th>id</th>
            <th>제품명</th>
            <th>카테고리</th>
            <th>가격</th>
            <th>수량</th>
        </tr>
    </thead>
    <tbody>
        <% for (category category : list) { %>
            <tr>
                <td><%= category.getId() %></td>
                <td><%= category.getTitle() %></td>
                <td><%= category.getName() %></td>
                <td><%= category.getUnitCost() %></td>
                <td><%= category.getQuantity() %></td>
                
            </tr>
        <% } %>
    </tbody>


</div>
</body>
</html>


</body>
</html>