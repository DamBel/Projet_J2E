var init = function(){

    $(function(){
        console.log("1- J'exécute la fonction parallax()");
        $('.parallax').parallax();
        console.log("1- Normalement c'est fait...");

        $("#responsive_headline").fitText(1.2, { minFontSize: '10px', maxFontSize: '40px' });

        $('.datepicker').pickadate({
            selectMonths: true, // Creates a dropdown to control month
            selectYears: 50, // Creates a dropdown of 50 years to control year
            format: 'yyyy-mm-dd',
            max: true
          });

        twemoji.size = '36x36';
        twemoji.parse(document.body);

        //$('.tooltipped').tooltip({delay: 50});

        $('select').material_select();

        //$('#twemoji-picker').twemojiPicker();

    $('textarea#textarea1').characterCounter();

        // Initialize collapse button
          $(".button-collapse").sideNav({draggable: true});
          // Initialize collapsible (uncomment the line below if you use the dropdown variation)
          //$('.collapsible').collapsible();

    });

};

/*
var initTooltip = function(){
    $(function(){
        $('.tooltipped').tooltip({delay: 50});
    });
};

var disableTooltip = function(){
    $(function(){
        // This will remove the tooltip functionality for the buttons on this page
        $('.tooltipped').tooltip('remove');
    });
};
*/

var initYoutubers = function(){

    init();

    $(function(){
        console.log("3- J'exécute la fonction matchHeight()");
        $('.youtuber-section .card-content').matchHeight();
        console.log("3- Normalement c'est fait...");
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
            if (!$(".content").hasClass("blur") && !$("#sidenav").hasClass("sidenav-active")){
                $("#modal-content, #modal-background").toggleClass("active");
                $("#modal-content .modal_youtuber_avatar").attr('src', imgPath);
                $("#modal-content .nom").text(nomYoutuber);
                //$("#modal-content img").attr('name', 'lol');
                $(".youtuber-section, .parallax-container").toggleClass("blur");
            }
        });
        console.log("2- Normalement c'est fait...");
    });


};

var initGames = function(){

    init();

    $(function(){
        console.log("3- J'exécute la fonction matchHeight()");
        $('.games-section .card-content').matchHeight();
        console.log("3- Normalement c'est fait...");
    });

};

var ouvrir = function(){
    $('.button-collapse').sideNav();
}

var app = angular.module("TaverneApp", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "partials/home.htm",
        controller : "homeCtrl"
    })
    .when("/home", {
        templateUrl : "partials/home.htm",
        controller : "homeCtrl"
    })
    .when("/videastes", {
        templateUrl : "partials/youtubers.htm",
        controller : "ytCtrl"
    })
    .when("/signup", {
        templateUrl : "partials/signup.htm",
        controller : "signupCtrl"
    })
    .when("/updates", {
        templateUrl : "partials/updates.htm",
        controller : "updatesCtrl"
    })
    .when("/feed", {
        resolve : {
            "check": function($location, $rootScope){
                if ($rootScope.user == null || $rootScope.loggedIn != true){
                    console.log($rootScope.user);
                    //console.log("rootScope user " + $rootScope.user.pseudo);
                    console.log("rootScope logged in = " + $rootScope.loggedIn);
                    $location.path('/home');
                }
                else{
                    console.log("rootScope user " + $rootScope.user.pseudo);
                    console.log("rootScope logged in = " + $rootScope.loggedIn);
                    console.log("je vais vers le feed");
                }
            }
        },
        templateUrl : "partials/feed.htm",
        controller : "feedCtrl"

    })
    .when("/games", {
        resolve : {
            "check": function($location, $rootScope){
                if ($rootScope.user == null || $rootScope.loggedIn != true){
                    console.log($rootScope.user);
                    //console.log("rootScope user " + $rootScope.user.pseudo);
                    console.log("rootScope logged in = " + $rootScope.loggedIn);
                    $location.path('/home');
                }
                else{
                    console.log("rootScope user " + $rootScope.user.pseudo);
                    console.log("rootScope logged in = " + $rootScope.loggedIn);
                    console.log("je vais vers le feed");
                }
            }
        },
        templateUrl : "partials/games.htm",
        controller : "gamesCtrl"

    })
    .when("/subs", {
        resolve : {
            "check": function($location, $rootScope){
                if ($rootScope.user == null || $rootScope.loggedIn != true){
                    $location.path('/home');
                }
                else{
                }
            }
        },
        templateUrl : "partials/subs.htm",
        controller : "subsCtrl"

    })
    .when("/login", {
        templateUrl : "partials/login.htm",
        controller : "loginCtrl"
    })
    .otherwise({
        redirectTo: '/'
    });

});

app.run(function($rootScope, $location, $window) {
    $rootScope.disconnect = function() {
        $rootScope.user = null;
        $rootScope.loggedIn = false;
        $location.path('/home');
    };

    $rootScope.redirectTo = function (url) {
        $window.open(url);
    };

    $rootScope.ouvrir = function () {
        ouvrir();
    };
});

app.controller("homeCtrl", function($scope, $location, $http, $rootScope, $window) {

    init();

    $scope.loggedIn = $rootScope.loggedIn;
    $scope.user = $rootScope.user;

    console.log($scope.loggedIn);
    console.log($scope.user);

    $http({
       method: 'GET',
       url: '../control?action=tools'
    }).then(function (response) {
       // code to execute in case of success
       console.log(response.data);
       $scope.tools = response.data;

    }, function (response) {
       // code to execute in case of error
       $scope.tools = null;
    });

    $http({
       method: 'GET',
       url: '../control?action=new_users'
    }).then(function (response) {
       // code to execute in case of success
       console.log(response.data);
       $scope.new_users = response.data;
    }, function (response) {
       // code to execute in case of error
       $scope.new_users = null;
    });




});

app.controller("updatesCtrl", function($scope, $location, $http, $rootScope, $window) {

    init();
    //initTooltip();

    $http({
       method: 'GET',
       url: 'js/updates.json'
    }).then(function (response) {
       // code to execute in case of success
       console.log(response.data);
       $scope.updates = response.data;
    }, function (response) {
       // code to execute in case of error
       $scope.updates = null;
    });


});

app.controller("loginCtrl", function($scope, $location, $http, $timeout, $rootScope) {

    init();

    $scope.hint = " ";
    $scope.hint_status = " ";

    $scope.connecter = function () {

        $scope.hint = " ";
        $scope.hint_status = " ";
        $rootScope.loggedIn = false;
        $rootScope.user = null;

        if($scope.email == null || $scope.password == null){
            $scope.hint = "Veuillez renseigner l'email et le mot de passe.";
            $scope.hint_status = "error";
            console.log("rootScope user " + $rootScope.user.pseudo);
            console.log("rootScope logged in = " + $rootScope.loggedIn);
        }
        else {
            var verification = $http({
                method: "post",
                url: 'api/taverne.php?action=login',
                data: {
                    email: $scope.email,
                    password: $scope.password
                },
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
            });

            /* Check whether the HTTP Request is successful or not. */
            verification.success(function (data) {

                //console.log(data);

                if (data.length != 0 && data != ""){
                    //console.log(JSON.stringify(data));
                    $scope.hint = "Vous êtes connecté.";
                    $scope.hint_status = "check_circle";

                    $rootScope.user = data[0];
                    $rootScope.loggedIn = true;
                    console.log($rootScope.user.pseudo);
                    console.log(data[0].pseudo);



                    $timeout(function () {
                        $location.path('/feed');
                    }, 2000);
                }
                else {
                    console.log(JSON.stringify(data));
                    $scope.hint = "L'email ou le mot de passe est incorrect.";
                    $scope.hint_status = "error";
                    $rootScope.loggedIn = false;
                    $rootScope.user = null;
                }
            });

        }



    };



});

app.controller("signupCtrl", function($scope, $location, $http, $timeout, $rootScope) {

    init();

    $scope.hint = " ";
    $scope.hint_status = " ";
    $scope.gender = "M";

    $scope.inscrire = function () {

        $scope.hint = " ";
        $scope.hint_status = " ";
        $scope.birthdate = document.getElementById("birthdate").value;
        $scope.flag = document.getElementById("select").value;

        if($scope.email == null || $scope.pseudo == null){
            $scope.hint = "Veuillez renseigner le pseudo et l'email.";
            $scope.hint_status = "error";
        }
        else {
            var verification = $http({
                method: "post",
                url: 'api/taverne.php?action=signup_check',
                data: {
                    email: $scope.email
                },
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
            });

            /* Check whether the HTTP Request is successful or not. */
            verification.success(function (data) {

                console.log("Gender = " + $scope.gender);
                console.log("Birthdate = " + $scope.birthdate);

                if (data.length != 0){
                    $scope.hint = "L'email est déjà utilisé.";
                    $scope.hint_status = "error";
                }
                else {

                    if ($scope.birthdate == null || $scope.birthdate == ''){
                        $scope.hint = "Veuillez saisir une date de naissance.";
                        $scope.hint_status = "error";
                    }
                    else {

                        if ($scope.password != $scope.password2){
                            $scope.hint = "Les mots de passe saisis ne sont pas identiques.";
                            $scope.hint_status = "error";
                        }
                        else {

                            if ($scope.password == null){
                                $scope.hint = "Veuillez saisir un mot de passe.";
                                $scope.hint_status = "error";
                            }
                            else {

                                //console.log("Pays = " + $scope.flag);

                                var enregistrement = $http({
                                    method: "post",
                                    url: 'api/taverne.php?action=signup_user',
                                    data: {
                                        email: $scope.email,
                                        pseudo: $scope.pseudo,
                                        password: $scope.password,
                                        gender: $scope.gender,
                                        birthdate: $scope.birthdate,
                                        flag: $scope.flag
                                    },
                                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                                });

                                enregistrement.success(function (data) {
                                    $scope.hint = data;
                                    $scope.hint_status = "check_circle";
                                    $timeout($location.path('/home'), 5000);
                                });
                            }

                        }


                    }

                }

            });

        }



    };


});

app.controller("subsCtrl", function($scope, $location, $http, $rootScope, $timeout) {

    $scope.hint = " ";
    $scope.hint_status = " ";
    $scope.results = null;

    init();

    $scope.form1 = false;
    console.log("Form1 = " + $scope.form1);

    var subs_list = $http({
       method: 'post',
       url: 'api/taverne.php?action=subscriptions',
       data: {
            follower_id: $rootScope.user.user_id
        },
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    });

    subs_list.success(function (data) {
        $scope.subscriptions = data;
        console.log($scope.subscriptions);
    });



    $scope.displayForm1 = function displayForm1(){
        console.log("Form1 = " + $scope.form1);
        if ($scope.form1 == false){
            $scope.form1 = true;
            //$scope.form1class = "animated zoomIn";
            console.log("Form1 = " + $scope.form1);
        }
        else {
            //$scope.form1class = "animated zoomOut";
            $timeout(function () {
                $scope.form1 = false;
                $scope.results = null;
                console.log("Form1 = " + $scope.form1);
            }, 500);
        }
            
    };

    $scope.follow_user = function follow_user(followed_id) {

        console.log("Fonction follow_uwer activée.");

        console.log("ID du follower" + $rootScope.user.user_id);
        console.log("ID de la personne suivie" + followed_id);

        var insertFollower = $http({
            method: "post",
            url: 'api/taverne.php?action=follow_user',
            data: {
                follower_id: $rootScope.user.user_id,
                followed_id: followed_id
            },
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        });

        insertFollower.success(function (data) {
            console.log(data);
        });
    };

    $scope.pushInJSON = function (user_id, pseudo, imgPath, flag) {

        $scope.subscriptions.push({
            "user_id": user_id,
            "pseudo": pseudo,
            "imgPath": imgPath,
            "flag": flag
        });
    };

    $scope.deleteFromJSON = function (user_id) {
        $scope.subscriptions = $scope.subscriptions.filter(function(item){ return item.user_id != user_id; });
    };

    $scope.toggleFollow = function (index) {
        if ($scope.results[index].follow == false){
            $scope.results[index].follow = true;
        }
        else if ($scope.results[index].follow == true){
            $scope.results[index].follow = false;
        }
    };

    $scope.unfollow_user = function unfollow_user(followed_id) {

        var unfollow = $http({
            method: "post",
            url: 'api/taverne.php?action=unfollow_user',
            data: {
                follower_id: $rootScope.user.user_id,
                followed_id: followed_id
            },
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        });

        unfollow.success(function (data) {

            for (var i = 0; i < $scope.results.length; i++){
                if ($scope.results[i].user_id == followed_id){
                    $scope.results[i].follow = false;
                }
            }
            
            console.log(data);

        });
    };

    $scope.rechercher = function () {

        if ($scope.pseudo_search == undefined || $scope.pseudo_search == ''){
            $scope.hint = "Votre nouveau statut est vide.";
            $scope.hint_status = "error";
        }
        else {

            var recherche = $http({
                method: "post",
                url: 'api/taverne.php?action=recherche_user',
                data: {
                    user_id: $rootScope.user.user_id,
                    pseudo_search: $scope.pseudo_search
                },
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
            });

            recherche.success(function (data) {
                $scope.results = data;
                console.log($scope.results);
            });


            

        }

    };


});

app.controller("feedCtrl", function($scope, $location, $http, $rootScope, $timeout) {

    init();

    $scope.hint = " ";
    $scope.hint_status = " ";

    $scope.form1 = false;
    console.log("Form1 = " + $scope.form1);

    $scope.posts = null;

    $scope.displayForm1 = function displayForm1(){
        console.log("Form1 = " + $scope.form1);
        if ($scope.form1 == false){
            $scope.form1 = true;
            $scope.form1class = "animated zoomIn";
            console.log("Form1 = " + $scope.form1);
        }
        else {
            $scope.form1class = "animated zoomOut";
            $timeout(function () {
                $scope.form1 = false;
                console.log("Form1 = " + $scope.form1);
            }, 500);
        }
            
    };

    $scope.logOut = function logOut(){
        $rootScope.user = null;
        $rootScope.loggedIn = false;
        $location.path('/home');
    };

    var feed_posts = $http({
        method: "post",
        url: 'api/taverne.php?action=feed_posts',
        data: {
            follower_id: $rootScope.user.user_id,
        },
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    });

    feed_posts.success(function (data) {
        $scope.posts = data;
        console.log($scope.posts);
    });

    $scope.publier = function () {

        $scope.hint = " ";
        $scope.hint_status = " ";

        $scope.publication_time = new Date().toISOString().slice(0, 19).replace('T', ' ');

        console.log($scope.publication_time);
        console.log($rootScope.user.user_id);
        console.log($scope.post_content);
        //console.log(document.getElementById('twemoji-picker').value);

        if ($scope.post_content == undefined || $scope.post_content == ''){
            $scope.hint = "Votre nouveau statut est vide.";
            $scope.hint_status = "error";
        }
        else {

            var envoiStatut = $http({
                method: "post",
                url: 'api/taverne.php?action=new_feed_post',
                data: {
                    user_id: $rootScope.user.user_id,
                    //content: document.getElementById('twemoji-picker').value,
                    content: $scope.post_content,
                    publication_time: $scope.publication_time
                },
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
            });

            envoiStatut.success(function (data) {
                $scope.hint = data;
                $scope.hint_status = "check_circle";
                $scope.post_content = '';

                $timeout(function () {
                    $scope.hint = " ";
                    $scope.hint_status = " ";
                    $scope.displayForm1();
                }, 1000); 

                var feed_posts = $http({
                    method: "post",
                    url: 'api/taverne.php?action=feed_posts',
                    data: {
                        follower_id: $rootScope.user.user_id,
                    },
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                });

                feed_posts.success(function (data) {
                    $scope.posts = data;
                    init();
                    console.log($scope.posts);
                });
                //$timeout($location.path('/home'), 5000);
            });

        }

    };

    //twemoji.parse('&#x1F3B9;');

});

app.controller("gamesCtrl", function($scope, $location, $http, $rootScope) {
    //console.log("rootScope user " + $rootScope.user.pseudo);
    //console.log("rootScope logged in = " + $rootScope.loggedIn);

    $http({
       method: 'GET',
       url: 'api/taverne.php?action=all_games'
    }).then(function (response) {
       // code to execute in case of success
       $scope.games = response.data;
       initGames();
    }, function (response) {
       // code to execute in case of error
       $scope.games = null;
    });

});

app.controller("ytCtrl", function($scope, $location, $http, $rootScope) {

    $scope.loggedIn = $rootScope.loggedIn;
    $scope.user = $rootScope.user;

    console.log($scope.loggedIn);

    $http({
       method: 'GET',
       url: 'api/get.php'
    }).then(function (response) {
       // code to execute in case of success
       $scope.videastes = response.data;
       initYoutubers();
    }, function (response) {
       // code to execute in case of error
       $scope.videastes = null;
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



        if ($scope.videastes[id_videaste].OW_PC != ""){

            $http({
               method: 'GET',
               url: $scope.videastes[id_videaste].OW_PC
            }).then(function (response) {
               // code to execute in case of success
               console.log("Success.");
               $scope.ow_pc_profile = response.data;
               if($scope.ow_pc_profile.eu.stats.competitive != null){
                    if($scope.ow_pc_profile.eu.stats.competitive.overall_stats.comprank != null){
                        $scope.ow_pc_rank_cote = $scope.ow_pc_profile.eu.stats.competitive.overall_stats.comprank;
                        $scope.ow_pc_rank = $scope.ow_pc_profile.eu.stats.competitive.overall_stats.tier;

                        $scope.ow_pc_rank_grade = "./images/overwatch/" + $scope.ow_pc_rank + ".png";
                        $scope.grade1 = "visible";
                    }
                    else{
                        $scope.ow_pc_rank = "Aucun classement";
                        $scope.ow_pc_rank_grade = " ";
                        $scope.grade1 = "invisible";
                        $scope.ow_pc_rank_cote = "";
                    }
               }
               else{
                    $scope.ow_pc_rank = "Aucun classement";
                    $scope.ow_pc_rank_grade = " ";
                    $scope.grade1 = "invisible";
                    $scope.ow_pc_rank_cote = "";

               }
            }, function (response) {
               // code to execute in case of error
               $scope.ow_pc_rank = "Erreur";
               $scope.grade1 = "invisible";
               $scope.ow_pc_rank_cote = "";

            });
        }
        else {
            $scope.ow_pc_rank = "Aucun classement";
            $scope.ow_pc_rank_cote = "";
            $scope.grade1 = "invisible";
        }

    };

});
