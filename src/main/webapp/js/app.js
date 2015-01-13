$(function() {
	var refreshStudents = function() {
		$.ajax({
			url: '/student',
			method: 'GET',
			contextType: 'application/json',
		}).done(function(students) {
			var studentsTable = $('#students');
			studentsTable.find('tr').remove();

			students.forEach(function(s) {
				var row = $('<tr>');
				row.append($('<td>').text(s.id));
				row.append($('<td>').text(s.lastName));
				row.append($('<td>').text(s.firstName));
				row.append($('<td>').text(s.patronymic));
				row.append($('<td>').text(s.group));
				row.append($('<td>').text(s.grant));

				studentsTable.append(row);
			});
		});
    }



    refreshStudents();
});