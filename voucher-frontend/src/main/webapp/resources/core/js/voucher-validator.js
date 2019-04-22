$(document).ready(function() {
	validate();
});

function validateEmail() {
	var pattern = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var email = document.getElementById('email').value;
	var validationResult = pattern.test(email);
	console.log('Email' + validationResult);
	document.getElementById('email-validation').style.display = validationResult ? 'none'
			: 'block';
	document.getElementById('form-group-email').className = validationResult ? 'form-group'
			: 'form-group has-error';
	return validationResult;
}

function validateVoucher() {
	var pattern = /^([A-Z0-9]){10}$/;
	var code = document.getElementById('code').value;

	var validationResult = pattern.test(code);
	console.log('code' + validationResult);
	document.getElementById('voucher-validation').style.display = validationResult ? 'none'
			: 'block';
	document.getElementById('form-group-voucher').className = validationResult ? 'form-group'
			: 'form-group has-error';
	return validationResult;
}

function disableSubmitButton() {
	document.getElementById('redeem-button').disabled = true;
	document.getElementById('redeem-button').className = 'btn-lg btn-secondary pull-right';
}

function enableSubmitButton() {
	document.getElementById('redeem-button').disabled = false;
	document.getElementById('redeem-button').className = 'btn-lg btn-primary  pull-right';
}

function validate() {
	if (validateEmail() && validateVoucher()) {
		enableSubmitButton();
		console.log('enable');
	} else {
		disableSubmitButton();
		console.log('disable');
	}
}