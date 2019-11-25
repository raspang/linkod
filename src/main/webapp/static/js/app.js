$(function() {

	// code for jquery dataTable
	var $table = $('#voter'); 
	// execute the below code only where we have this table
	if ($table.length) {

		var jsonUrl = '';
		jsonUrl = window.contextRoot + '/json/data/listparticipants';

		/* alert(jsonUrl); */
		$table
				.DataTable({
					responsive: true,
					lengthMenu : [ [ 10, 20 ], [ '10 Records', '20 Records' ] ],
					pageLength : 10,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
						{
							data : 'id'
						},
						{
							data : 'code',
							mRender : function(data, type, row) {
								var str = '';

								if (data != null){
									str += "<code>"+data+"</code>";
								}

								return str;
						}
						},
						{
							data : 'id',
							bSortable : false,
							mRender : function(data, type, row) {

								var str = '';
								if (userRole === 'ADMIN') {
									str += '<a href="'
											+ window.contextRoot
											+ '/mark-participant-'
											+ data
											+ '"><span style="color:green" class="glyphicon glyphicon-thumbs-up"></span></a>';
								}
								return str;
							}
						},
						{
							data : 'attendsStr'
						},
							{
								data : 'completeName'
							},
							{
								data : 'company'
					
							},
							{
								data : 'designation'
							},
						/*	{
								data : 'contact'
							},
							{
								data : 'age',
								bSortable : false,
								mRender : function(data, type, row) {
									if (data == 0)
										return "";
									else
										return data;
									

								}
							},
							{
								data : 'gender'
							},
							{
								data : 'status'
							},*/
							{
								data : 'business'
							},
				
	

							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									if (userRole === 'ADMIN') {
										str += '<a href="'
												+ window.contextRoot
												+ '/edit-person-'
												+ data
												+ '" class="btn btn-success custom-width"  ><span style="color: #ffffff;" class="glyphicon glyphicon-pencil"></span></a>&nbsp;';
										str += '&nbsp;<a href="'
												+ window.contextRoot
												+ '/delete-person-'
												+ data
												+ '" onclick="return confirm('
												+ '\'Are you sure you want to delete this participant?\''
												+ ');" class="btn btn-danger custom-width" ><span style="color: #ffffff;" class="glyphicon glyphicon-remove"></span></a>';
									}
									return str;
								}
							} ]
				});
	}
	

	// code for jquery dataTable
	var $table2 = $('#codes');

	// execute the below code only where we have this table
	if ($table2.length) {

		var jsonUrl = '';
		jsonUrl = window.contextRoot + '/json/data/listcodes';

		/* alert(jsonUrl); */
		$table2
				.DataTable({

				
					order : [ [ 1, "asc" ] ],
					lengthMenu : [ [ 10, 20 ], [ '10 Records', '20 Records' ] ],
					pageLength : 10,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
		
							{
								data : 'barangay.name'
							},
							{
								data : 'code'
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									if (!row.status)
										str += '<a href="'
												+ window.contextRoot
												+ '/update-code?codeid='
												+ data
												+ '"><p  style="width:10%; padding-bottom:0; margin-bottom:0; border-bottom:0"><span style="color:green" class="glyphicon glyphicon-edit"></span></p></a>';
									else
										str += '<span style="color:black" class="glyphicon glyphicon-ok"></span>'
									return str;
								}
							}]
				});
	}

})