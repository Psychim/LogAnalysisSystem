<%--
  Created by IntelliJ IDEA.
  User: swz
  Date: 2017/9/11
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/activity.css">
    <link rel="stylesheet" href="css/toolbar.css">
    <script src="js/toolbar.js"></script>
	<%--<script src="jquery/jquery-3.2.1.min.js"></script>--%>
    <%--<script>--%>
            <%--$("#form").submit(function showData() {--%>
                <%--var date = $("#date").value;--%>
                <%--alert(0);--%>
                <%--$.ajax({--%>
                    <%--url: "CityRateDataDateServlet.do",--%>
                    <%--data: {date: date},--%>
                    <%--dataType: "json",--%>
                    <%--success: function (data) {--%>
                        <%--alert(data.toString());--%>
                    <%--}--%>
                <%--})--%>
            <%--})--%>
  <%--</script>--%>
    <script>
        function check() {
            var date = document.getElementById("date");
            if (date.value == "") {
                alert("The date cannot be empty！")
                return false;
            }
            else {
                document.getElementById("form").submit();
            }
        }
    </script>
    <%--<script>--%>
        <%--window.alert = alert;--%>
        <%--function alert() {--%>
            <%--var a = document.createElement("div");--%>
            <%--var p = document.createElement("p");--%>
            <%--var btn = document.createElement("div");--%>
            <%--var textNode = document.createElement("The date cannot be empty！");--%>
            <%--var btnText = document.createElement("confirm");--%>
            <%--css (--%>
              <%--a, {--%>
                  <%--"position":"fixed",--%>
                    <%--"top":"20%",--%>
                    <%--"width":"30%",--%>
                    <%--"margin":"15% auto",--%>
                    <%--"background-color":"#2e3141",--%>
                    <%--"font-size":"20px",--%>
                    <%--"color":"#FFF",--%>
                    <%--"text-align":"center"--%>
            <%--}--%>
            <%--);--%>
            <%--css (--%>
                <%--btn, {--%>
                    <%--"background":""--%>
                <%--}--%>
            <%--);--%>
            <%--p.appendChild(textNode);--%>
            <%--btn.appendChild(btnText);--%>
            <%--a.appendChild(p);--%>
            <%--a.appendChild(btn);--%>
            <%--document.getElementsByTagName("Body")[0].appendChild(a);--%>
            <%--btn.onclick = function {--%>
                <%--a.parentNode.removeChild(a);--%>
            <%--}--%>
        <%--}--%>
        <%--function css(targetObj, cssObj) {--%>
            <%--var str = targetObj.getAttribute("style")?targetObj.getAttribute("style"):"";--%>
            <%--for (var i in cssObj) {--%>
                <%--str += i + ":" + cssObj[i] +";";--%>
            <%--}--%>
            <%--targetObj.style.cssText = str;--%>
        <%--}--%>
    <%--</script>--%>
    <script>
        window.alert = function(str)
        {
            var shield = document.createElement("DIV");
            shield.id = "shield";
            shield.style.position = "absolute";
            shield.style.left = "0px";
            shield.style.top = "0px";
            shield.style.width = "20%";
            shield.style.height = document.body.scrollHeight+"px";
            shield.style.textAlign = "center";
            //背景透明 IE有效
            shield.style.filter = "alpha(opacity=0.5)";
            var alertFram = document.createElement("DIV");
            alertFram.id="alertFram";
            alertFram.style.position = "fixed";
            alertFram.style.left = "35%";
            alertFram.style.top = "40%";
            //alertFram.style.marginLeft = "-225px";
            //alertFram.style.marginTop = "-75px";
            alertFram.style.width = "30%";
            alertFram.style.height = "150px";
            alertFram.style.background = "#2e3141";
            alertFram.style.textAlign = "center";
            alertFram.style.lineHeight = "150px";
            alertFram.style.borderRadius = "30px";
            alertFram.style.opacity = ".9";
            strHtml = "<ul style=\"list-style:none;margin:0px;padding:0px;width:100%;\">\n";
            strHtml += " <li style=\"text-align:center;color:#FFF;font-size:20px;height:60px;line-height:60px;margin-top:20px\">"+str+"</li>\n";
            strHtml += " <li style=\"text-align:center;font-weight:bold;height:25px;line-height:25px;margin-top:10px;\">" +
                "<input type=\"button\" value=\"OK\" style=\"border:0;border-radius: 20px;width: 30%;height: 30px;font-size:18px;\" onclick=\"doOk()\" />" +
                "</li>\n";
            strHtml += "</ul>\n";
            alertFram.innerHTML = strHtml;
            document.body.appendChild(alertFram);
            document.body.appendChild(shield);
            var ad = setInterval("doAlpha()",5);
            this.doOk = function(){
                alertFram.style.display = "none";
                shield.style.display = "none";
            }
            alertFram.focus();
            document.body.onselectstart = function(){return false;};
        }
    </script>
</head>

<body>
<div id="background" >
<header>
    <nav>
        <ul>
            <li class="logo"><a href="index.jsp">Home page</a></li>

            <li><a href="javascript:void(0)" id="menu" onclick="showMenu()">Menu</a></li>
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
            <li><a href="predict.jsp">Traffic forecast</a></li>
        </ul>
    </div>
</div>

<div id="querydiv">
    <div>
        <h1>Activity analysis system</h1>
        <hr>
        <p>
            Please input the date you want to query.
        </p>
        <form id="form" action="CityRateDataDateServlet.do" method="post">
            <input type="text" name="date" value="" id="date">
            <input type="button" value="query" id="querybutton" onclick="check()">
        </form>
    </div>
</div>
</div>
</body>
</html>
