/**
 * 
 */

$(document).ready(function() {
	$(".ui-menu-child").css({
		'width' : ($("body").width() + 'px')
	});
});

$(window).bind('resize', function(e) {
	if (window.RT)
		clearTimeout(window.RT);
	window.RT = setTimeout(function() {
		this.location.reload(false); /* false to get page from cache */
	}, 100);
});