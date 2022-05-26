<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>




<a href="MainController"> <img src="imgs/FunnierSmall.png" alt= "logo" style="height:80px; margin-bottom:20px"></a><br>


<form action="LoginController" method="POST">
    <label class="w3-text-red" for="mail"> Mail:</label><br>
  	<input class="w3-input w3-border w3-light-grey" type="email" id="mail" name="mail" placeholder="Mail" value="${login.mail}" required pattern="^.{8,50}$"><br>
    <label class="w3-text-red" for="pass"> Password: </label><br>
  	<input class="w3-input w3-border w3-light-grey" type="password" id="pass" name="pass" placeholder="Password" value="${login.pass}" required pattern="^[A-Za-z\d@$!%?&_+\-*\/#]{8,50}$"><br>
    <input class="w3-btn w3-red" type="submit" name="sumbit" value="Login">
    <label class="w3-text-red" style="padding-left:10px"> Don't have an account? You can register </label>
    <a class="w3-text-red menu" id="RegisterController" href=#><b>here</b></a> <br>


</form>

<div id="errors">
	<ul>
	<c:if test = "${login.error[0]}">
		<li> Invalid credentials format </li>
	</c:if>
	<c:if test = "${login.error[1]}">
		<li> Invalid credentials </li>
	</c:if>
	</ul>
</div>

<script>
var errors = function() {
	for(e of "${login.error}") {
		if (e == true)
			return true;
	}
	return false;
}

if (!errors()) {
	document.getElementById("errors").style.display = 'none';
} else {
	document.getElementById("errors").style.display = 'block';
}
</script>
