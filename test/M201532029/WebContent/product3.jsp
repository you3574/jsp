<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, M201532029.*" %>
<%
String s = request.getParameter("id");
int id = (s == null) ? 0 : Integer.parseInt(s);
List<category> list;
if (id == 0) list = productDAO3.findAll();
else list = productDAO3.findBycategoryId(id);
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

<form class="form-inline">
  <div class="form-group">
    <label>학과</label>
    <select name="id" class="form-control">
      <option value="0" <%= id == 0 ? "selected" : "" %>>전체</option>
      <option value="1" <%= id == 1 ? "selected" : "" %>>음료수</option>
      <option value="2" <%= id == 2 ? "selected" : "" %>>주류</option>
      <option value="3" <%= id == 3 ? "selected" : "" %>>과자</option>
    </select>
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

