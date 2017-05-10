
function main() {

(function () {
    $(".navbar-toggle").on("click", function () {
				    $(this).toggleClass("active");
	});
/*
	// Hide .navbar first
	$(".navbar").hide();
	
	// Fade in .navbar
	$(function () {
		$(window).scroll(function () {
            // set distance user needs to scroll before we fadeIn navbar
			if ($(this).scrollTop() > 200) {
				$('.navbar').fadeIn();
			} else {
				$('.navbar').fadeOut();
			}
		});

	
	});
	*/

}());


}
main();