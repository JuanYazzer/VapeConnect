<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">
    <div class="p-4 bg-white shadow rounded w-25">
        <h3 class="text-center mb-4">Register</h3>
        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null) { %>
        <div class="alert alert-danger text-center"><%= error %></div>
        <% } %>

        <form action="register" method="post">
            <input type="text" name="username" class="form-control mb-2" placeholder="Username" required/>
            <input type="email" name="email" class="form-control mb-2" placeholder="Email" required/>
            <input type="password" name="password" class="form-control mb-2" placeholder="Password" required/>
            <input type="date" name="age" class="form-control mb-3" required/>
            <button type="submit" class="btn btn-dark w-100">Register</button>
        </form>
        <p class="text-center mt-3">Sudah punya akun? <a href="index.jsp">Login</a></p>
    </div>
</body>
</html>
