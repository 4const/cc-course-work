$(function() {
    var __students = [];
    var __groups = [];

    var __editedStudent = undefined;
    var __selectedStudent = undefined;

	var __editedGroup = undefined;
    var __selectedGroup = undefined;

    var onLogin = function(user) {
    	$('#main').removeClass('hidden');
		$('#loginModal').modal('hide');

		$('#username').text(user.name);

		$('#userrole').text(user.admin ? 'Администратор' : 'Пользователь');
		if (user.admin) {
            $('#studentBtnPanel').removeClass('hidden');
            $('#groupBtnPanel').removeClass('hidden');
		} else {
            $('#studentBtnPanel').addClass('hidden');
            $('#groupBtnPanel').addClass('hidden');
		}

		refreshStudents();
		refreshGroups();
    };

    var onLogout = function() {
		$('#main').addClass('hidden');
		$('#loginModal').modal('show');

		$('#students').find('tr').remove();
		$('#groups').find('tr').remove();
	};

	var checkLogin = function() {
		$.ajax({
			url: '/user',
			method: 'GET',
		}).done(function(user) {
			if (user) {
				onLogin(user);
			} else {
				onLogout();
			}
		});
	};

	$('#loginBtn').on('click', function() {
		$('#loginFieldError').addClass('hidden');
		$('#loginServerError').addClass('hidden');

		var login = $('#login').val();
		var password = $('#password').val();

		var fd = new FormData();
		fd.append('username', login);
		fd.append('password', password);

		$.ajax({
			url: '/login',
			method: 'POST',
			contentType: false,
			processData: false,
			data: fd
		}).done(function(user) {
			if (user.status === "rejected")
			{
				$('#loginFieldError').removeClass('hidden');
				return;
			}
			$('#login').val('');
			 $('#password').val('');
            onLogin(user);
		}).fail(function(e) {
			$('#loginServerError').removeClass('hidden');
		});
	});

    	$('#logoutBtn').on('click', function() {
    		onLogout();

    		$.ajax({
    			url: 'logout',
    			method: 'POST',
    			contentType: false,
    			processData: false
    		});
    	});

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

	var fillGroupForm = function(g) {
		$('#groupFieldError').addClass('hidden');
		$('#groupServerError').addClass('hidden');
		if (!g) { g = {}; }

		$('#groupName').val(g.name);

		__editedGroup = g;
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

	$('#newGroupBtn').on('click', function() {
		fillGroupForm();
	});

	$('#editGroupBtn').on('click', function(e) {
		if (!__selectedGroup) {
			return false;
		}
		fillGroupForm(__selectedGroup);
	});

	$('#deleteGroupBtn').on('click', function(e) {
		if (!__selectedGroup) {
			return false;
		}

		$.ajax({
			url: '/group?id=' + __selectedGroup.id,
			method: 'DELETE',
		}).done(function(res) {
			if (res) {
				refreshGroups();
			} else {
				$('#errorModal').modal('toggle');
			}
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

	$('#saveGroupBtn').on('click', function() {
		$('#groupFieldError').addClass('hidden');
		$('#groupServerError').addClass('hidden');

		__editedGroup.name = $('#groupName').val();

		if (!__editedGroup.name) {
			$('#groupFieldError').removeClass('hidden');
			return false;
		}

		$.ajax({
			url: '/group',
			method: 'PUT',
			contentType: 'application/json',
			data: JSON.stringify(__editedGroup)
		}).done(function(res) {
			refreshStudents();
			refreshGroups();

			$('#groupModal').modal('toggle');
		}).fail(function() {
			$('#groupServerError').removeClass('hidden');
		});
	});

	checkLogin();
});