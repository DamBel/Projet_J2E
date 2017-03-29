var init = function(){
   //Page loaded

   $(function(){
		console.log("1- J'exécute la fonction parallax()");
        $('.parallax').parallax();
        console.log("1- Normalement c'est fait...");
	});

   $(function(){
		console.log("2- Je prépare les fonctions onClick()");
        var imgPath = "./images/warning.png";
        var nomYoutuber = "";

        $(".youtuber").click(function() {
            imgPath = $(this).attr("imgPath");
            nomYoutuber = $(this).attr("youtuberName");
        });

        $(".youtuber, #modal-launcher, #modal-background, #modal-close").click(function() {
            $("#modal-content, #modal-background").toggleClass("active");
            $("#modal-content .modal_youtuber_avatar").attr('src', imgPath);
            $("#modal-content .nom").text(nomYoutuber);
            //$("#modal-content img").attr('name', 'lol');
            $(".presentation, .youtuber-section, .techno-section, .parallax-container").toggleClass("blur");
        });
        console.log("2- Normalement c'est fait...");
	});

        

	$(function(){
		console.log("3- J'exécute la fonction matchHeight()");
        $('.youtuber-section .card-content').matchHeight();
        console.log("3- Normalement c'est fait...");
	});

};

angular.module("Taverne", [])
.controller("TaverneCtrl", function($scope, $location, $http) {

	$http({
	   method: 'GET',
	   url: 'api/get.php'
	}).then(function (response) {
	   // code to execute in case of success
	   $scope.videastes = response.data;
	   init();
	}, function (response) {
	   // code to execute in case of error
	   $scope.videastes = null;
	});

	$http({
	   method: 'GET',
	   url: 'api/tools.php'
	}).then(function (response) {
	   // code to execute in case of success
	   $scope.tools = response.data;
	}, function (response) {
	   // code to execute in case of error
	   $scope.tools = null;
	});

	$scope.count = 0;
	$scope.ow_pc_profile = null;
	$scope.ow_pc_rank = null;
	$scope.ow_pc_rank_cote = null;
	$scope.ow_pc_rank_grade = null;
	$scope.grade1 = "invisible";


    $scope.myFunc = function(id_videaste) {
    	$scope.ow_pc_profile = null;
    	$scope.ow_pc_rank = null;
    	$scope.ow_pc_rank_grade = " ";
    	$scope.ow_pc_rank_cote = null;
        $scope.count++;
        console.log($scope.count);
        console.log("id_videaste = " + id_videaste);

        console.log("name_videaste = " + $scope.videastes[id_videaste].name);
        console.log("Rank image : " + $scope.ow_pc_rank_grade);

        
        if ($scope.videastes[id_videaste].OW_PC != ""){
        	console.log("Il existe un lien.");
        	$http({
			   method: 'GET',
			   url: $scope.videastes[id_videaste].OW_PC
			}).then(function (response) {
			   // code to execute in case of success
			   console.log("Success.");
			   $scope.ow_pc_profile = response.data;
			   if($scope.ow_pc_profile.eu.stats.competitive != null){
			   		console.log($scope.videastes[id_videaste].name + " est au rang : " + $scope.ow_pc_profile.eu.stats.competitive.overall_stats.tier);
			   		$scope.ow_pc_rank_cote = $scope.ow_pc_profile.eu.stats.competitive.overall_stats.comprank;
			   		$scope.ow_pc_rank = $scope.ow_pc_profile.eu.stats.competitive.overall_stats.tier;
			   		console.log("Rank sur Overwatch = " + $scope.ow_pc_rank);
			   		console.log("Cote sur Overwatch = " + $scope.ow_pc_rank_cote);
			   		$scope.ow_pc_rank_grade = "./images/overwatch/" + $scope.ow_pc_rank + ".png";
			   		$scope.grade1 = "visible";
			   }
			   else{
			   		console.log($scope.videastes[id_videaste].name + " joue sur Overwatch sur PC mais n'a pas de rang.");
			   		$scope.ow_pc_rank = "Aucun classement";
			   		$scope.ow_pc_rank_grade = " ";
			   		$scope.grade1 = "invisible";
			   		$scope.ow_pc_rank_cote = null;
			   		console.log("Rank sur Overwatch = " + $scope.ow_pc_rank);
			   		console.log("Cote sur Overwatch = " + $scope.ow_pc_rank_cote);
			   }
			}, function (response) {
			   // code to execute in case of error
			   $scope.ow_pc_rank = "Erreur";
			   $scope.grade1 = "invisible";
			   $scope.ow_pc_rank_cote = null;
			   console.log("Rank sur Overwatch = " + $scope.ow_pc_rank);
			   console.log("Cote sur Overwatch = " + $scope.ow_pc_rank_cote);
			});
        }
        else {
        	$scope.ow_pc_rank = "Aucun classement";
        	$scope.ow_pc_rank_cote = null;
        	$scope.grade1 = "invisible";
        	console.log("Rank sur Overwatch = " + $scope.ow_pc_rank);
        	console.log("Cote sur Overwatch = " + $scope.ow_pc_rank_cote);
        }

		console.log("Lien du profil sur Overwatch sur PC = " + $scope.videastes[id_videaste].OW_PC);
		console.log("Rank image fin : " + $scope.ow_pc_rank_grade);
		console.log("Cote sur Overwatch = " + $scope.ow_pc_rank_cote);

    };

});





