<%@ page import="org.movie.dao.ReviewDAO" %><%--
  Created by IntelliJ IDEA.
  User: zzz
  Date: 2018-09-07
  Time: 오후 4:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int rno = Integer.parseInt(request.getParameter("rno"));
    String choice = request.getParameter("choice");

    System.out.println("-------------------------");
    System.out.println(rno);
    System.out.println(choice);

    new ReviewDAO().updateChoice(rno, choice);

%>
<script>

    parent.doA();

</script>
