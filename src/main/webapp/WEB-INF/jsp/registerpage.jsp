<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>


<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>
<link rel="stylesheet" type="text/css" href="css/q1.min.css">
<link rel="stylesheet" type="text/css" href="css/q4.css">
<link rel="stylesheet" type="text/css" href="css/q3.css">
<link rel="stylesheet" type="text/css" href="css/q2.css">


<div class="form-body without-side">
    <div class="website-logo">
        <a href="index.html">
        </a>
    </div>
    <div class="row">
        <div class="img-holder">
            <div class="bg"></div>
            <div class="info-holder">
                <img src="images/graphic3.svg" alt="">
            </div>
        </div>
        <div class="form-holder">
            <div class="form-content">
                <div class="form-items">
                    <h3>Register new account</h3>
                    <form action="register" method="post">
                        <input class="form-control" type="text" name="firstName" placeholder="Name" required>
                        <input class="form-control" type="text" name="lastName" placeholder="Surname" required>
                        <c:out value="${errore}"></c:out>
                        <input class="form-control" type="email" name="email" placeholder="E-mail Address" required>
                        <c:out value="${errorp}"></c:out>
                        <input class="form-control" type="password" name="password" placeholder="Password" required>
                        <input class="form-control" type="password" name="repeatPassword" placeholder="Repeat password"
                               required>
                        <div class="form-button">
                            <button id="submit" type="submit" class="ibtn">Register</button>
                        </div>
                    </form>
                    <div class="other-links">
                        <div class="text">Or register with</div>
                        <a href="#"><i class="fab fa-facebook-f"></i>Facebook</a><a href="#"><i
                            class="fab fa-google"></i>Google</a><a href="#"><i
                            class="fab fa-linkedin-in"></i>Linkedin</a>
                    </div>
                    <div class="page-links">
                        <a href="login">Login to account</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/loginjs3.min.js"></script>
<script src="js/loginjs5.min.js"></script>
<script src="js/loginjs1.min.js"></script>
<script src="js/loginjs4.js"></script>
<jsp:include page="footer.jsp"></jsp:include>