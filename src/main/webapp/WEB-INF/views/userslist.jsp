<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users List</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<script>
		window.contextRoot = '${contextRoot}'
		window.userRole = 'USER';
	</script>

	<sec:authorize access="hasRole('ADMIN')">
		<script type="text/javascript">
			window.userRole = 'ADMIN';
		</script>
	</sec:authorize>

	<div class="container">
		<%@include file="authheader.jsp"%>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of Users </span>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Firstname</th>
						<th>Lastname</th>
						<th>Email</th>
						<th>SSO ID</th>
						<sec:authorize access="hasRole('ADMIN')">
							<th></th>
						</sec:authorize>
						<sec:authorize access="hasRole('ADMIN')">
							<th></th>
						</sec:authorize>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.email}</td>
							<td>${user.ssoId}</td>
							<sec:authorize access="hasRole('ADMIN')">
								<td><a href="<c:url value='/edit-user-${user.ssoId}' />"
									class="btn btn-success custom-width">Edit</a></td>
							</sec:authorize>
							<sec:authorize access="hasRole('ADMIN')">
								<td><a href="<c:url value='/delete-user-${user.ssoId}' />"
									class="btn btn-danger custom-width">Delete</a></td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<sec:authorize access="hasRole('ADMIN')">
			<div class="well">
				<a href="<c:url value='/newuser' />">Add New User</a>
			</div>
		</sec:authorize>
	</div>

</body>
</html>