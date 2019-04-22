<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="/resources/core/js/alertfadeout.js"></script>

<c:if test="${not empty msg}">
	<div class="alert alert-${css} alert-dismissible" id="msg-alert"
		role="alert">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<strong>${msg}</strong>
	</div>
</c:if>

