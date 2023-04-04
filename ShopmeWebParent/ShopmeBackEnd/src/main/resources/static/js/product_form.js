dropdownBrands = $("#brand");
dropdownCategories = $("#category");

	$(document).ready(function(){
		
//selects shortdesc/fulldesc element and calls richText method		
		$("#shortDescription").richText();
		$("#fullDescription").richText();
		
		
		dropdownBrands.change(function(){
			dropdownCategories.empty();
			getCategories();
		});
		getCategories();
		
			//shows image thumbnail when uploading user photo
	//first checks so photo is not larger than 100kb
	$("#extraImage1").change(function(){
	if(!checkFileSize(this)){
		return;
	}	
	showExtraImageThumbnail(this);
	});
	});
	
	function showExtraImageThumbnail(fileInput){
		var file = fileInput.files[0];
		var reader = new FileReader();
		reader.onload = function(e){
			$("#extraThumbnail1").attr("src", e.target.result);
		};
		reader.readAsDataURL(file);
	}
	
	function getCategories(){
		brandId = dropdownBrands.val();
		url = brandModuleURL + "/" + brandId + "/categories";
	
	$.get(url, function(responseJson){
		//alert(responseJson);
		$.each(responseJson), function(index, category){
			$("<option>").val(category.id).text(category.name).appendTo(dropdownCategories);
		});
	});
	
	}

	
	function checkUnique(form){
		productId = $("#id").val();
		productName = $("#name").val();
		
		csrfValue = $("input[name'_csrf']").val();
		
		url = "[[@{/products/check_unique}]]";
		
		params = {id: productId, name: productName, _csrf: csrfValue};
		
		$.post(url, params, function(response){
			if (response == "OK"){
				form.submit();
			} else if (response == "Duplicate"){
				showWarningModal("There is another product having the name " + productName);
			} else {
				showErrorModal("Unknown reposnse from server");
			}
		}).fail(function(){
			showErrorModal("Could not connect to the server")
		});
		return false;
	}
	