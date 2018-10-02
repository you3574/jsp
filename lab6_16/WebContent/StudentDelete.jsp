<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.net.*, lab6_16.*" %>
<%
String s1 = request.getParameter("id");
int id = Integer.parseInt(s1);
String pg = request.getParameter("pg");
String srchText = request.getParameter("srchText");
if (srchText == null) srchText = "";
String srchTextEncoded = URLEncoder.encode(srchText, "UTF-8");

StudentDAO.delete(id);
response.sendRedirect("StudentList.jsp?pg=" + pg + "&srchText=" + srchTextEncoded);
%>
