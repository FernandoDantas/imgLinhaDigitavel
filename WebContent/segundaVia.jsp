<%@page import="domain.DebitoDomain"%>
<%@page contentType="text/html" import="java.util.Date, java.text.*" 
pageEncoding="ISO-8859-1"%>

<%
  
  DebitoDomain debito = new DebitoDomain();


%>

<h1>Img aqui em baixo</h1>

<br>
<br>

 <img height=10 src="saida/<%=debito.getDbtLinhaDigitavel()%>" width=87%>

