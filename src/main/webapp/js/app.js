$(function() {
    var __students = [];
    var __groups = [];

    var __editedStudent = undefined;
    var __selectedStudent = undefined;

	var __editedGroup = undefined;
    var __selectedGroup = undefined;

	var refreshStudents = function() {
		$.ajax({
			url: '/student',
			method: 'GET',
		}).done(function(students) {
			__students = students;
			var table = $('#students');
			table.find('tr').remove();

			students.forEach(function(s) {
				var row = $('<tr>');
				row.append($('<td>').text(s.id));
				row.append($('<td>').text(s.lastName));
				row.append($('<td>').text(s.firstName));
				row.append($('<td>').text(s.patronymic));
				row.append($('<td>').text(s.group));
				row.append($('<td>').text(s.grant));

				row.data('id', s.id);
				table.append(row);
			});
		});
    };

	var refreshGroups = function() {
		$.ajax({
			url: '/group',
			method: 'GET',
		}).done(function(groups) {
			__groups = groups;
			var table = $('#groups');
			table.find('tr').remove();

			var select = $('#studentGroup');
			select.find('option').remove();

			groups.forEach(function(g) {
				var row = $('<tr>');
				row.append($('<td>').text(g.id));
				row.append($('<td>').text(g.name));
				row.append($('<td>').text(g.grant));

				row.data('id', g.id);
				table.append(row);

				var option = $('<option>');
				option.text(g.name);
				option.attr('value', g.id);
				select.append(option);
			});
		});
	}

	var fillStudentForm = function(s) {
		$('#studentFieldError').addClass('hidden');
		$('#studentServerError').addClass('hidden');
		if (!s) { s = {}; }

		$('#lastName').val(s.lastName);
		$('#firstName').val(s.firstName);
		$('#patronymic').val(s.patronymic);
		$('#studentGroup').val(s.groupId);
		$('#grant').val(s.grant);

		__editedStudent = s;
	};

	$('#students').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');

			__selectedStudent = undefined;
		} else {
			$('#students').find('tr.selected').removeClass('selected');
			$(this).addClass('selected');

			var selectedId = $(this).data('id');
			__selectedStudent = $.extend({}, $.grep(__students, function(s) { return s.id == selectedId })[0]);
		}
	});

	$('#groups').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');

			__selectedGroup = undefined;
		} else {
			$('#groups').find('tr.selected').removeClass('selected');
			$(this).addClass('selected');

			var selectedId = $(this).data('id');
			__selectedGroup = $.extend({}, $.grep(__groups, function(g) { return g.id == selectedId })[0]);
		}
	});

	$('#newStudentBtn').on('click', function() {
		fillStudentForm();
	});

	$('#editStudentBtn').on('click', function(e) {
		if (!__selectedStudent) {
			return false;
		}
		fillStudentForm(__selectedStudent);
	});

	$('#deleteStudentBtn').on('click', function(e) {
		if (!__selectedStudent) {
			return false;
		}

		$.ajax({
			url: '/student?id=' + __selectedStudent.id,
			method: 'DELETE',
		}).done(function(res) {
			refreshStudents();
			refreshGroups();
		});
	});

	$('#saveStudentBtn').on('click', function() {
		$('#studentFieldError').addClass('hidden');
		$('#studentServerError').addClass('hidden');

		__editedStudent.lastName = $('#lastName').val();
		__editedStudent.firstName = $('#firstName').val();
		__editedStudent.patronymic = $('#patronymic').val();
		__editedStudent.groupId = $('#studentGroup').val();
		__editedStudent.grant = $('#grant').val();

		if (!__editedStudent.lastName ||
			!__editedStudent.firstName ||
            !__editedStudent.patronymic ||
            !__editedStudent.groupId ||
            !__editedStudent.grant ||
			isNaN(__editedStudent.grant)
		) {
			$('#studentFieldError').removeClass('hidden');
			return false;
		}

 		$.ajax({
			url: '/student',
			method: 'PUT',
			contentType: 'application/json',
			data: JSON.stringify(__editedStudent)
		}).done(function(res) {
			refreshStudents();
			refreshGroups();

			$('#studentModal').modal('toggle');
		}).fail(function() {
        	$('#studentServerError').removeClass('hidden');
        });
	});


    refreshStudents();
    refreshGroups();
});