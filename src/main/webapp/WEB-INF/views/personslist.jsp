<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users List</title>
<script>
		window.contextRoot = '${contextRoot}'
			window.userRole = 'USER';

	
		</script>
	
	<sec:authorize access="hasRole('ADMIN')">
		<script type="text/javascript">
			window.userRole = 'ADMIN';
			</script>
		</sec:authorize>

<link href="<c:url value='/static/css/bootstrap.css'/>" rel="stylesheet"></link>
<%-- <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link> --%>
<link href="<c:url value='/static/css/dataTables.bootstrap.css'/>"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/dataTables.bootstrap4.css'/>"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/dataTables.jqueryui.css'/>"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/dataTables.semanticui.css'/>"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/jquery.dataTables.css'/>"
	rel="stylesheet"></link>

<link href="<c:url value='/static/css/responsive.bootstrap.min.css'/>"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/responsive.dataTables.min.css'/>"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/responsive.foundation.min.css'/>"
	rel="stylesheet"></link>
<link
	href="<c:url value='/static/css/responsive.responsive.jqueryui.min.css'/>"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/responsive.semanticui.min.css'/>"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/buttons.bootstrap.min.css'/>"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/buttons.bootstrap4.min.css'/>"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/buttons.dataTables.min.css'/>"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/buttons.foundation.min.css'/>"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/buttons.jqueryui.min.css'/>"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/buttons.semanticui.min.css'/>"
	rel="stylesheet"></link>


<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>

</head>

<body>

	<div class="container-fluid">
		<div class="table-wrapper">
			<div class="table-title">
				<%@include file="authheader.jsp"%>
				<span class="lead">List of Participants</span>
				<sec:authorize access="hasRole('ADMIN')">
		  		&nbsp;|&nbsp;<span class="lead"><a
						href="${contextRoot}/list">List of Users</a></span>
				</sec:authorize>
			</div>

			<div class="card-body">
			<sec:authorize access="hasRole('ADMIN')">
				<div class="well">
					<a class="btn btn-primary btn-xs"
						href="<c:url value='/newperson' />">Add New</a>
				</div>
			</sec:authorize>
				<table class="table table-hover table-bordered" id="voter"
					width="100%">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Company</th>
							<th>Designation</th>
							<th>Contact</th>
							<th>Age</th>
							<th>Gender</th>
							<th>Status</th>
							<th>Business Line</th>
							<th>Attended</th>
							<th>Code</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
				</table>
			</div>


			
			<sec:authorize access="hasRole('ADMIN')">
				<div class="well">

					<button type="button" class="btn btn-warning btn-xs"
						data-toggle="modal" data-target="#modalGenerateParticipants">Generate</button>
				</div>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ADMIN')">
				<div class="well">

					<button type="button" class="btn btn-warning btn-xs"
						data-toggle="modal" data-target="#modalPDFParticipants">PDF</button>
				</div>
			</sec:authorize>

		</div>

	</div>



	
	
	<div id="modalGenerateParticipants" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<form:form target="_blank" class="form-horizontal" action="${contextRoot}/generate-participantsform"
				method="GET">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Registration Form</h4>
					</div>
					<div class="modal-body">

						<div class="form-group">
							<label class="control-label col-md-4">No.</label>
							<div class="col-md-8 validate">
				
								<input type="number" name="generateNo" id="generateNo" class="form-control input-sm" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="Submit" class="btn btn-primary">GENERATE</button>
					</div>
				</div>

			</form:form>

		</div>
	</div>
	
	<div id="modalPDFParticipants" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<form:form target="_blank" class="form-horizontal" action="${contextRoot}/pdf-participantsform"
				method="GET">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">PDF - Registration Form</h4>
					</div>
					<div class="modal-body">

						<div class="form-group">
							<label class="control-label col-md-4">Rage (ex. 1 - 500)</label>
							<div class="col-md-8 validate">
				
								<input type="number" name="generateStartNo" id="generateStartNo" class="form-control input-sm" min="0"  />
       							-
       							<input type="number" name="generateEndNo" id="generateEndNo"  class="form-control input-sm" min="2"  />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="Submit" class="btn btn-primary">PDF</button>
					</div>
				</div>

			</form:form>

		</div>
	</div>
	
	





	<script src="<c:url value='/static/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/static/js/jquery.dataTables.js'/>"></script>
	<script src="<c:url value='/static/js/dataTables.bootstrap.js'/>"></script>
	<script src="<c:url value='/static/js/bootstrap.min.js'/>"></script>

	<script src="<c:url value='/static/js/dataTables.buttons.min.js'/>"></script>
	<script src="<c:url value='/static/js/buttons.print.min.js'/>"></script>

	<script src="<c:url value='/static/js/dataTables.bootstrap4.js'/>"></script>
	<script src="<c:url value='/static/js/dataTables.foundation.js'/>"></script>
	<script src="<c:url value='/static/js/dataTables.jqueryui.js'/>"></script>

	<script src="<c:url value='/static/js/dataTables.responsive.js'/>"></script>
	<script src="<c:url value='/static/js/responsive.bootstrap.min.js'/>"></script>
	<script src="<c:url value='/static/js/responsive.bootstrap4.min.js'/>"></script>
	<script src="<c:url value='/static/js/responsive.foundation.min.js'/>"></script>
	<script src="<c:url value='/static/js/responsive.jqueryui.min.js'/>"></script>
	<script src="<c:url value='/static/js/responsive.semanticui.min.js'/>"></script>

	<script src="<c:url value='/static/js/buttons.bootstrap.min.js'/>"></script>
	<script src="<c:url value='/static/js/buttons.colVis.min.js'/>"></script>
	<script src="<c:url value='/static/js/buttons.foundation.min.js'/>"></script>
	<script src="<c:url value='/static/js/buttons.html5.min.js'/>"></script>
	<script src="<c:url value='/static/js/buttons.jqueryui.min.js'/>"></script>


	<script src="<c:url value='/static/js/app.js'/>"></script>
</body>
</html>