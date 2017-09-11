<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Welcome</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/toolbar.css">
    <script src="js/toolbar.js"></script>
</head>

<body>
<div id="background" >


    <header>
        <!-- 顶栏开始 -->
        <nav>
            <ul>
                <li class="logo"><a href="index.jsp">Home page</a></li>

                <li><a href="javascript:void(0)" id="menu" onclick="showMenu()">Menu</a></li>
                <li><a href="">Contact</a></li>
                <li><a href="">Top</a></li>
            </ul>
        </nav>
        <!-- 顶栏结束 -->

        <!-- 欢迎模块开始 -->
        <div id="banner">
            <div class="inner">
                <a class="a1">Welcome to Log Analyse System!</a>
                <hr>
                <a class="a2">
                    Welcome to Log Analse System!
                    The early bird catches worm.
                    God helps those who help themselves.
                    We can both of God and the devil.
                </a>
            </div>
        </div>
        <!-- 欢迎模块结束 -->

        <div id="newMenu">
            <div>
                <h2>Menu</h2>
                <hr>
                <ul>
                    <li><a href="activity.jsp">Activity analysis</a></li>
                    <li><a href="phone.jsp">Phone analysis</a></li>
                    <li><a href="timeline.jsp">Timeline analysis</a></li>
                    <li><a href="personas.jsp">Personas analysis</a></li>
                </ul>
            </div>
        </div>
    </header>

    <!-- 主页内容模块开始 -->
    <div id="content">
        <!-- 地区活跃度介绍模块开始 -->
        <div id="activity-div">
            <div class="wrapper1">
                <div>
                    <a href="activity.jsp" class="a1">Retional activity analysis</a>
                    <hr>
                    <a href="activity.jsp" class="a2">
                        Welcome to Log Analse System!
                        The early bird catches worm.
                        God helps those who help themselves.
                        We can both of God and the devil.
                    </a>
                </div>
            </div>
        </div>
        <!-- 地区活跃度介绍模块开始 -->
    </div>
    <!-- 主页内容模块结束 -->
</div>
</body>
</html>