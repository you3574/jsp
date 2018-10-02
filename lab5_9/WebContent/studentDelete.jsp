<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, lab5_9.*" %>
<%
String s1 = request.getParameter("id");
int id = Integer.parseInt(s1);
String pg = request.getParameter("pg");

studentDAO.delete(id);
response.sendRedirect("studentList1.jsp?pg=" + pg);
%>
