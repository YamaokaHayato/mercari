'use strict';

$(function() {
	$('#largeCategory').change(function() {
		console.log("wwwwwwwww")
		let hostUrl = "http://localhost:8080/mercari/add/mediumCategory";
		let id = $("#largeCategory").val();
		$("#mediumCategory > option").remove();
		$.ajax({
			url: hostUrl,
			type: "POST",
			dataType: "json",
			data: {
				id: id,
			},
			async: true,
		}).done(function(mediumCategoryList) {
			for (let i = 0; i < mediumCategoryList.length; i++) {
				$("#mediumCategory").append($("<option>").html(mediumCategoryList[i].name).val(mediumCategoryList[i].id));
			}
		}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
			alert("エラーが発生しました");
			console.log("XMLHttpRequest :" + XMLHttpRequest.status);
			console.log("textStatus :" + textStatus);
			console.log("errorThrown :" + errorThrown.message);
		});
	});
	$("#mediumCategory").change(function() {
		let hostUrl = "http://localhost:8080/mercari/add/smallCategory";
		let id = $("#mediumCategory").val();
		$("#smallCategory > option").remove();
		$.ajax({
			url: hostUrl,
			type: "POST",
			dataType: "json",
			data: {
				id: id,
			},
			async: true,
		}).done(function(smallCategoryList) {
			for (let i = 0; i < smallCategoryList.length; i++) {
				$("#smallCategory").append($("<option>").html(smallCategoryList[i].name).val(smallCategoryList[i].id));
			}
		}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
			alert("エラーが発生しました");
			console.log("XMLHttpRequest :" + XMLHttpRequest.status);
			console.log("textStatus :" + textStatus);
			console.log("errorTHrown :" + errorThrown.message);
		});
	});
});