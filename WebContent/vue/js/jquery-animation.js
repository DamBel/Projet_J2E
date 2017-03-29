// Angular version

(function($){
  $(function(){

    //$('.button-collapse').sideNav();
    $('.parallax').parallax();

  }); // end of document ready
})(jQuery); // end of jQuery name space

jQuery(document).ready(function(){

    $(function(){
        var imgPath = "./images/warning.png";
        var nomYoutuber = "";
        var nationaliteYoutuber = "";

        $(".youtuber").click(function() {
            imgPath = $(this).attr("imgPath");
            nomYoutuber = $(this).attr("youtuberName");
            nationaliteYoutuber = $(this).attr("nationalite");
            console.log( "imgPath = " + $(this).attr("youtuberName"));
            //console.log( "imgPath = " + $(this).find("p").text());
            console.log( "imgPath = " + $(this).attr("imgPath"));

        });

        $("#youtuber, #modal-launcher, #modal-background, #modal-close").click(function() {
            $("#modal-content, #modal-background").toggleClass("active");
            $("#modal-content img").attr('src', imgPath);
            $("#modal-content .nom").text(nomYoutuber);
            $("#modal-content .nationalite").html("Nationalité : " + '<i class="twa twa-' + nationaliteYoutuber + '-flag"></i>');
            //$("#modal-content img").attr('name', 'lol');
            $(".presentation, .youtuber-section, .techno-section, .parallax-container").toggleClass("blur");
        });

    });

});

function animationClick(element, animation){
    element = $(element);
    element.click(
        function() {
            element.addClass('animated ' + animation);        
            //wait for animation to finish before removing classes
            window.setTimeout( function(){
                element.removeClass('animated ' + animation);
            }, 2000);         
  
        });
}
