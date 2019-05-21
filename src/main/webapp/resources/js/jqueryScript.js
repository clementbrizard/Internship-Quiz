$(document).ready(function() {
	
	// DO GET
	$.ajax({
		type : "GET",
		url : "api/user/all",
		success: function(result){
			$.each(result, function(i, user){
				
				var userRow = '<tr>' +
									'<td>' + user.id + '</td>' +
									'<td>' + user.username + '</td>' +
									'<td>' + user.firstName + '</td>' +
									'<td>' + user.secondName.toUpperCase() + '</td>' +
									'<td>' + user.mail + '</td>' +
									'<td>' + user.admin + '</td>' +
									'<td>' + user.company + '</td>' +
									'<td>' + user.phone + '</td>' +
									'<td>' + user.creationDate + '</td>' +
									'<td>' + user.valid + '</td>' +
								  '</tr>';
				
				$('#userTable tbody').append(userRow);
				
	        });
			
			$( "#userTable tbody tr:odd" ).addClass("info");
			$( "#userTable tbody tr:even" ).addClass("success");
		},
		error : function(e) {
			alert("ERROR WITH THIS TABLE: ", e);
			console.log("ERROR: ", e);
		}
	});
	
	// do Filter on View
	$("#inputFilter").on("keyup", function() {
	    var inputValue = $(this).val().toLowerCase();
	    $("#userTable tr").filter(function() {
	    	$(this).toggle($(this).text().toLowerCase().indexOf(inputValue) > -1)
	    });
	});
})