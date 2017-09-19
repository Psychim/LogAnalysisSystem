<%--
  Created by IntelliJ IDEA.
  User: Mingyu
  Date: 2017/9/19
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/phone.css">
    <link rel="stylesheet" href="css/toolbar.css">
    <script src="js/toolbar.js"></script>
</head>
<body>
<div id="background">
    <header>
        <nav>
            <ul>
                <li class="logo"><a href="index.jsp">Home page</a></li>

                <li><a href="javascript:void(0)" id="menu" onclick="showMenu()">Menu</a></li>
                <li><a href="">Contact</a></li>
                <li><a href="">Top</a></li>
            </ul>
        </nav>
    </header>

    <div id="newMenu">
        <div>
            <h2>Menu</h2>
            <hr>
            <ul>
                <li><a href="activity.jsp">Activity analysis</a></li>
                <li><a href="phone.jsp">Phone analysis</a></li>
                <li><a href="timeline.jsp">Timeline analysis</a></li>
                <li><a href="personas.jsp">Personas analysis</a></li>
                <li><a href="website.jsp">Hot20 websites</a></li>
            </ul>
        </div>
    </div>

    <div id="querydiv">
        <div>
            <h1>Phone analysis system</h1>
            <hr>
            <p>
                Please input the range of date you want to query.
            </p>
            <form id="form" action="ByteSeriesServlet.do" method="post">
                <scan style="font-size:20px;color:#FFF;margin-left:10px;">End:</scan>
                <input type="text" name="name"  id="name">
                <input type="submit" value="query" id="querybutton">
            </form>
        </div>
    </div>
</div>
</body>
</html>