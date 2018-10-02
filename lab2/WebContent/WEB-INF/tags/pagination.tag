<%@ tag description="pagination" pageEncoding="UTF-8"%>
<%@ tag import="java.util.ArrayList" %>
<%@ attribute name="recordCount" type="java.lang.Integer" required="true" %>
<%@ attribute name="pageSize" type="java.lang.Integer" required="true" %>
<%@ attribute name="queryStringName" type="java.lang.String" required="true" %>
<%! 
private class Page { 
    int page; 
    String label;
    
    Page(int page, String label) {
        this.page = page;
        this.label = label;
    }
} 
%>

<%
int recordCount = (Integer)jspContext.getAttribute("recordCount");
int pageSize = (Integer)jspContext.getAttribute("pageSize");
String name = (String)jspContext.getAttribute("queryStringName");

int currentPage = 1;
if (request.getParameter(name) != null)
    currentPage = Integer.parseInt(request.getParameter(name));

int pageCount = recordCount / pageSize;
if (pageCount * pageSize < recordCount) ++pageCount;

String queryString = request.getQueryString();
if (queryString == null)
    queryString = name + "=%d";
else if (queryString.matches(".*" + name + "=[0-10]+.*"))
    queryString = queryString.replaceAll(name + "=[0-10]+", name + "=%d");   //pg=숫자 넘기는 것.
else
    queryString = queryString + "&" + name + "=%d";
String url = request.getRequestURI() + "?" + queryString;

if (currentPage > pageCount) currentPage = pageCount;
int base = ((currentPage - 1) / 10) * 10;

ArrayList<Page> pages = new ArrayList<Page>();
if (base > 0) pages.add(new Page(base, "&lt;")); //xml특수문자
for (int i = 1; i <= 10; ++i) {
    int n = base + i;
    if (n > pageCount) break;
    pages.add(new Page(n, String.valueOf(n)));
} //보여지는 페이지 버튼갯수
int n = base + 11;
if (n <= pageCount)
    pages.add(new Page(n, "&gt;"));
%>
<ul class="pagination">
  <% for (Page p : pages) { %>
    <li class='<%= p.page == currentPage ? "active" : "" %>'>
        <a href='<%= String.format(url, p.page) %>'><%= p.label %></a>
    </li>    
  <% } %>
</ul>
