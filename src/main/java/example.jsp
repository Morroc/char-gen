<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title> Очень простая страница JSP </title>

    </head>

    <body>

    <h1> Очень простая страница JSP </h1>

<НЗ>Этот пример показывает, как работать с выражениями JSP</H3>

    Текущая дата: <%= new java.util.Date()%>

    <br>

    Значение параметра "param": <%= request.getParameter("param")%>

</body>

</html>