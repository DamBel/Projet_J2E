<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="row">
  <div ng-include="'partials/menu-fixed.htm'"></div>


  <div class="content" style="margin-top:-20px;">

        <div ng-include="'partials/navigation.htm'"></div>
        <script src="js/ui-materialize.js"></script>

        <div class="parallax-container">
            <div class="parallax"><img src="images/giphy2.gif"></img></div>
            <div class="banniere">
            </div>
            <div class="welcome" style="width: 100%;/* max-width: 1000px; */position: relative;height: 100%;/* margin: auto; */display: flex;">
                <div class="container" style="/* margin-left: 0px; *//* margin-right: 0px; */margin: auto;width: 100%;text-align: center;">
                    <p style="max-width: 100%;min-width: 300px; padding-left: 10px; padding-right: 10px;">Bienvenue sur le réseau social de la Taverne&nbsp;!</p>
                    <a href="#login" class="waves-effect waves-light btn-large button" style="background-color: darkslateblue;min-width: 300px;max-width: 100%;width: 40%;" ng-hide="loggedIn != false && loggedIn != undefined">Connexion</a>
                    <a href="#signup" class="waves-effect waves-light btn-large button" style="background-color: crimson;min-width: 300px;max-width: 100%;width: 40%;" ng-hide="loggedIn != false && loggedIn != undefined">Inscription</a>

                </div>
                
            </div>
        </div>
        <div class="presentation animated fadeIn">

            <div class="row">
                <div class="col s12">

                    <h1 class="simple" style="width: 100%; pre-wrap:;">PR&Eacute;SENTATION</h1>

                    <div class="card">
                        <div class="card-content">
                            <span class="card-title">Bienvenue dans la Taverne des Jeux&#8209;Vidéo&nbsp;!</span>
                            <p>La Taverne des Jeux&#8209;Vidéo (LTDJV) est une chaîne Youtube communautaire, elle propose un contenu très hétérogène, car chaque membre de la taverne a un profil et une approche différente, c'est le lieu idéal pour trouver de nouveaux talents&nbsp;!</p>
                            
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <div class="techno-section animated fadeIn">

                    <div class="col s12">
                        <h1>TECHNOLOGIES</h1>
                    </div>

                    <div class="row">

                        <div ng-hide="tool == null" ng-repeat="tool in tools">

                            <div class="col s6 m3 l2">
                                <div class="techno-card valign center">
                                    
                                    <a ng-click="redirectTo(tool.url)" url="{{tool.url}}" data-tooltip="{{tool.name}}"><img ng-src="{{tool.imgPath}}"></a>
                                    
                                </div>
                            </div>

                        </div>

                    </div>

                    <div class="preloader-wrapper small active" ng-hide="tools != null" style="margin-left: 10px;">
                        <div class="spinner-layer spinner-green-only">
                            <div class="circle-clipper left">
                                <div class="circle"></div>
                            </div><div class="gap-patch">
                                    <div class="circle"></div>
                            </div><div class="circle-clipper right">
                            <div class="circle"></div>
                            </div>
                        </div>
                    </div>
        </div>

        <div class="presentation animated fadeIn">

            <div class="row">
                <div class="col s12">

                    <h1 class="simple" style="width: 100%;">NOUVEAUX&nbsp;INSCRITS</h1>
                    <div class="card">
                    <div class="card-content">
                        <div ng-hide="new_users == null" ng-repeat="(key, item) in new_users">
                            <p class="valign center"><img ng-src="{{item.imgPath}}" style="height: 25px;"> {{item.pseudo}} <i class="twa twa-{{item.flag}}-flag"></i> s'est inscrit récemment. </p>                         
                        </div>
                    </div>

                    

                </div>
            </div>

            <div class="preloader-wrapper small active" ng-hide="new_users != null" style="margin-left: 10px;">
                <div class="spinner-layer spinner-green-only">
                    <div class="circle-clipper left">
                        <div class="circle"></div>
                    </div><div class="gap-patch">
                            <div class="circle"></div>
                    </div><div class="circle-clipper right">
                    <div class="circle"></div>
                    </div>
                </div>
            </div>

        </div>

  </div>
  
</div>

</div>

