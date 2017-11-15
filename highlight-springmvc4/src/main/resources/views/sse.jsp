<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>SSE Demo</title>
</head>
<body>
<div id="msgFromPush"></div>
<!-- PDF中此处为asset/js/jquery.js -->
<script src="<c:url value="/assets/js/jquery.js" />" type="text/javascript"></script>
<script>
    //只有新式浏览器才支持(Chrome、FireFox)EventSource
    if (!!window.EventSource) {
        var source = new EventSource('push');
        var s = '';
        source.addEventListener('message', function (e) {
            s += e.data + "<br/>";
            $("#msgFromPush").html(s);
        });

        source.addEventListener('open', function (e) {
            console.log("连接打开。");
        }, false);

        source.addEventListener('error', function (e) {
            // PDF中使用e.readyState，控制台显示undefined。
            if (e.srcElement.readyState == EventSource.CLOSED) {
                console.log("连接关闭！");
            } else {
//               console.log(e);
                console.log(e.srcElement.readyState);
            }
        }, false);
    } else {
        console.log("你的浏览器不支持SSE。")
    }
</script>
</body>
</html>