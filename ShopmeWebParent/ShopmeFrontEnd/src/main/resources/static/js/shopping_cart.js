//Increasing or decreasing products when user clicks + / -

$(document).ready(function(){
	$(".linkMinus").on("click", function(evt){
		evt.preventDefault();
		decreaseQuantity($(this));

	});
	
		$(".linkPlus").on("click", function(evt){
	    evt.preventDefault();
	    increaseQuantity($(this));
	
	});
		$(".linkRemove").on("click", function(evt){
	    evt.preventDefault();
	    removeProduct($(this));
	
	});
});

function decreaseQuantity(link){
			productId = $link.attr("pid");
		quantityInput = $("#quantity" + productId);
		newQuantity = parseInt(quantityInput.val()) -1;
		
		if (newQuantity > 0){
			quantityInput.val(newQuantity);
			updateQuantity(productId, newQuantity);
		}else{
			showWarningModal('Minimum quantity is 1');
		}
}

function increaseQuantity(link){
	productId = $link.attr("pid");
		quantityInput = $("#quantity" + productId);
		newQuantity = parseInt(quantityInput.val()) +1;
		
		if (newQuantity <= 5){
			quantityInput.val(newQuantity);
				updateQuantity(productId, newQuantity);
		}else{
			showWarningModal('Maximum quantity is 5');
		}
		}
		
		
		function updateQuantity(productId, quantity){
				url = contextPath + "cart/update" + productId + "/" + quantity;
	//Making an ajax call to the restservice
    $.ajax({
	type: "POST",addToCarturl: url,addToCartbeforeSend: function(xhr){
		xhr.setRequestHeader(csrfHeaderName, csrfValue);
	}
    }).done(function(updatedSubtotal){
	updateSubtotal(updatedSubtotal, productId);
	updateTotal();
    }).fail(function(){
	showErrorModal("Error while updating product quantity.");
    });
		}
		
	//Updating amount when increasing / decreasing products	
		function updateSubtotal(updatedSubtotal, productId){
			formattedSubtotal = $.number(updatedSubtotal,2);
			$("#subtotal" + productId).text(formattedSubtotal);
		}
		
		function updateTotal(){
			total = 0.0;
			$(".subtotal").each(function(index, element){
			productCount ++;
				total += parseFloat(element.innerHTML.replaceAll(",", ""));
			});
			
			if(productCount < 0){
				showEmptyShoppingCart();
			} else{
			formattedTotal = $.number(total, 2);
			$("#total").text(formattedTotal);
			}
		}
		
		
			function showEmptyShoppingCart(){
			$("#sectionTotal").hide();
			$("#sectionEmptyCartMessage").removeClass("d-none");
			
		}
		
		
		function removeProduct(){
			url = link.attr("href");
			alert(url);
			
				//Making an ajax call to the restservice
           $.ajax({
	       type: "DELETE",addToCarturl: url,addToCartbeforeSend: function(xhr){
		   xhr.setRequestHeader(csrfHeaderName, csrfValue);
	       }
           }).done(function(response){
            rowNumber = link.attr("rowNumber");
            removeProductHTML(rowNumber);
            updateTotal();
            updateCountNumbers();
            showModalDialog("Shopping Cart", response);
            }).fail(function(){
	        showErrorModal("Error while removing product.");
            });
		    }
		
		    function removeProductHTML(rowNumber){
			$("#row" + rowNumber).remove();
			$("#blankLine" + rowNumber).remove();
		    }
		    
		//Updating count of products in cart when customer add or removed    
		//products
		function updateCountNumbers(){
			$(".divCount").each(function(index, element){
				element.innerHTML = "" + (index + 1);
			});
		}
		
	