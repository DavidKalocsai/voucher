$(document).ready(function() {
	$("#msg-alert").delay(1000).fadeOut(500, function() {
		$(this).remove();
	});
});