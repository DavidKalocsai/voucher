<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="fragments/header.jsp" />
<script src="/resources/core/js/voucher-validator.js"></script>
<body>
	<div class="container">
		<jsp:include page="fragments/message.jsp" />

		<c:set var = "territoryLowerCase" value = "${fn:toLowerCase(voucher.territory)}" />
		<form:form class="form-horizontal" method="post"
			modelAttribute="voucher" action="/${territoryLowerCase}/status">

			<div class="form-group" id ="form-group-email">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<form:input path="email" type="text" class="form-control"
						id="email" placeholder="Email" onkeyup="validate()" />
					<small class="text-danger" id="email-validation"><strong>Please enter valid email!</strong></small>
				</div>
			</div>
			<div class="form-group" id ="form-group-voucher">
				<label class="col-sm-2 control-label">Voucher</label>
				<div class="col-sm-10">
					<form:input path="code" type="text" class="form-control" id="code"
						placeholder="Voucher Code" onkeyup="validate()" maxlength="10" />
					<small class="text-danger" id="voucher-validation"><strong>Please enter valid voucher code!</strong></small>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" id="redeem-button" class="btn-lg pull-right">Redeem</button>
				</div>
			</div>
		</form:form>
	</div>
</body>
<jsp:include page="fragments/footer.jsp" />
</html>