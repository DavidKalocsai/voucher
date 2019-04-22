<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Euro Cup 2016 Voucher</title>
<link href="/resources/core/css/bootstrap.min.css" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/resources/core/js/bootstrap.min.js"></script>
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/hun/form" var="urlHungary" />
<spring:url value="/ger/form" var="urlGermany" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">Home</a> <a
				class="navbar-brand" href="${urlGermany}">Germany</a> <a
				class="navbar-brand" href="${urlHungary}">Hungary</a>
		</div>
	</div>
</nav>