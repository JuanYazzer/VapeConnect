<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Login</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
  </head>
  <body
    class="d-flex justify-content-center align-items-center vh-100 bg-light"
  >
    <div class="p-4 bg-white shadow rounded w-25">
      <h3 class="text-center mb-4">Login</h3>
      <form action="login" method="post">
        <input
          type="text"
          name="username"
          class="form-control mb-2"
          placeholder="Username"
          required
        />
        <input
          type="password"
          name="password"
          class="form-control mb-2"
          placeholder="Password"
          required
        />
        <button type="submit" class="btn btn-dark w-100">Login</button>
      </form>
      <p class="text-center mt-3">
        Belum Punya Akun? <a href="register.jsp">Daftar disini</a>
      </p>
    </div>
    <jsp:include page="Template/footer.jsp" />
  </body>
</html>
