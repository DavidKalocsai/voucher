$(document).ready(function() {
	setTimeout(ajaxPoll, 100);
});
		
function ajaxPoll() {
	$.ajax({
		type : "get",
		url : "http://localhost:8080/api/fetchResponse", 
		data: {	"responseId" : id},
		success : responseArrived
	});
};
		
function responseArrived(response) {
	console.log(response.status + response.message);
	$('#details').text('{' + response.status + " " + response.message + '}\n');
	if (response.status === 'NO') {
		setTimeout(ajaxPoll, 1000);
	}
};