<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta preço</title>
    </head>
    <body>
        <h1>Preencha os campos com as informações adequadas:</h1>
        <form action="/manipular" method="post">
            Nome: <input type="text" name="nome"/><br/>
            Login: <input type="text" name="login"/><br/>
            Email: <input type="text" name="email"/><br/>
            Afiliação: <input type="text" name="afiliacao"/><br/>
            <input type="submit" value="Salvar"/>
        </form>
    </body>
</html>
