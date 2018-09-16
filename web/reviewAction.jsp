<%@ page import="org.movie.domain.ReviewVO" %>
<%@ page import="org.movie.dao.ReviewDAO" %><%--
  Created by IntelliJ IDEA.
  User: zzz
  Date: 2018-09-07
  Time: 오후 5:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    request.setCharacterEncoding("UTF-8");
    int mnum = Integer.parseInt(request.getParameter("mnum"));
    int score = Integer.parseInt(request.getParameter("score"));
    String cmt = request.getParameter("cmt");

    ReviewVO vo = new ReviewVO();
    vo.setMnum(mnum);
    vo.setScore(score);
    vo.setCmt(cmt);

    System.out.println(vo);

    new ReviewDAO().addReview(vo);


%>
<script>

    parent.doA();

</script>