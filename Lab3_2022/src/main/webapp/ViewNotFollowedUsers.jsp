<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
	<h2>You may know...</h2>
</div>
<c:forEach var="u" items="${users}">       
<div id="${u.id}" class="w3-container w3-card w3-round w3-white w3-center w3-section" style="width:300px; height:300px">
	<p>Friend Suggestion</p>
    <img class="w3-circle" src="${u.profilePhoto}" style="max-width:120px" alt="Avatar"><br><br>
    <div>@${u.username}</div>
   	<button type="button" class="followUser w3-row w3-button w3-green w3-section" style="border-radius:8px"><i class="fa fa-user-plus"></i> &nbsp;Follow</button> 
</div>
</c:forEach>