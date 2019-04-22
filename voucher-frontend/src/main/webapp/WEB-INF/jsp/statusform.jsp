<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Spring MVC Form Handling</title>
</head>
<body>
	<jsp:include page="fragments/header.jsp" />
	<script>var id = ${voucher.id}</script>
	<script src="/resources/core/js/async-polling.js"></script>
	

	<div class="container">
		<jsp:include page="fragments/message.jsp" />


		<h3>Voucher submitted</h3>
		<div class="col-sm-10"></div>
		<div class="col-sm-10">
			<label class="col-sm-2 control-label">Request Id</label> <label
				class="col-sm-2 control-label">${voucher.id}</label>
		</div>
		<div class="col-sm-10">
			<label class="col-sm-2 control-label">Email</label> <label
				class="col-sm-2 control-label">${voucher.email}</label>
		</div>
		<div class="col-sm-10">
			<label class="col-sm-2 control-label">Voucher</label> <label
				class="col-sm-2 control-label">${voucher.code}</label>
		</div>
		<div class="col-sm-10">
			<label class="col-sm-2 control-label">Details:</label> <label
				class="col-sm-10 control-label" id="details"></label>
		</div>

	</div>
	<jsp:include page="fragments/footer.jsp" />
</body>
</html>