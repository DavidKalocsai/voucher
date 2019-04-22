<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="fragments/header.jsp" />
<body>
	<div class="container">
		<h1>Error Page</h1>
		<div class="alert alert-danger alert-dismissible" id="msg-alert"
			role="alert">
			<span>${msg}</span>
		</div>
	</div>
	<jsp:include page="fragments/footer.jsp" />
</body>
</html>