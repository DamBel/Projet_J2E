-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Mer 29 Mars 2017 à 19:45
-- Version du serveur :  5.7.14
-- Version de PHP :  7.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `taverne`
--

-- --------------------------------------------------------

--
-- Structure de la table `follow_user`
--

CREATE TABLE `follow_user` (
  `follower_id` int(11) NOT NULL,
  `followed_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `follow_user`
--

INSERT INTO `follow_user` (`follower_id`, `followed_id`) VALUES
(1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `games`
--

CREATE TABLE `games` (
  `game_id` int(20) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `releasedate` date NOT NULL,
  `imgPath` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `games`
--

INSERT INTO `games` (`game_id`, `name`, `releasedate`, `imgPath`) VALUES
(1, 'Overwatch', '2016-05-24', 'http://www.mobygames.com/images/covers/l/339807-overwatch-windows-front-cover.jpg'),
(2, 'Rocket League', '2015-07-07', 'http://res.cloudinary.com/lmn/image/upload/c_limit,h_360,w_640/e_sharpen:100/f_auto,fl_lossy,q_auto/v1/gameskinnyc/r/o/c/rocket-league-logostadium-08180.jpg'),
(3, 'The Legend of Zelda: Breath of the Wild', '2017-03-03', 'https://articles.pvplive.net/article_16_9/Nintendo-20170224-BoTW.jpg'),
(9, 'Pokemon Go', '2016-07-05', 'http://www.mobygames.com/images/covers/l/349556-pokemon-go-ipad-front-cover.png'),
(4, 'Final Fantasy XV', '2016-11-29', 'http://images.newseveryday.com/data/thumbs/full/56455/720/0/0/0/final-fantasy-xv-updates-news-noctis-voice-actor-teases-dlc-classic-antagonist-to-return-in-expansion.jpg'),
(5, 'Counter-Strike: Global Offensive', '2012-08-21', 'http://cdn.supersoluce.com/file/docs/docid_4f719b558f152f5d66011267/elemid_4ee9d6ec0a2fe93f0e00000c/counter-strike-global-offensive-pc.jpg'),
(6, 'Tom Clancy\'s Rainbow Six: Siege', '2015-12-01', 'https://s-media-cache-ak0.pinimg.com/736x/25/ce/55/25ce55dc0c0415ed4d02871b1f27b270.jpg'),
(7, 'For Honor', '2017-02-17', 'http://static9.cdn.ubisoft.com/resource/en-US/game/forhonor/game/For_Honor_Keyart_2016.jpg'),
(8, 'Horizon Zero Dawn', '2017-02-28', 'https://cdnb.artstation.com/p/assets/images/images/002/856/949/large/luc-de-haan-horizon-zero-dawn-box-cover.jpg?1466522569'),
(10, 'League of Legends', '2009-10-27', 'http://dotageeks.com/wp-content/uploads/2015/10/League-Of-Legends-Logo-7.png'),
(11, 'FIFA 17', '2016-09-27', 'http://www.gamingtarget.com/images/content/news/fifa-17-logo.jpg'),
(12, 'Doom', '2016-05-13', 'http://i.kinja-img.com/gawker-media/image/upload/s--Nh1FB-5k--/xdcdsmbpab8gx44tn233.jpg'),
(13, 'Death Road to Canada', '2016-07-22', 'http://igg-games.com/wp-content/uploads/2016/07/Death-Road-to-Canada-Free-Download.jpg'),
(14, 'Minecraft', '2011-11-29', 'http://www4.ac-nancy-metz.fr/clg-p-e-victor-corcieux/new/wp-content/uploads/minecraft-cover.jpg'),
(15, 'Nier: Automata', '2017-02-27', 'http://cooldown.fr/wp-content/uploads/2017/03/08658408-photo-1475076582-5741-jaquette-avant.jpg'),
(16, 'Kingdom Hearts', '2002-03-28', 'http://static.giantbomb.com/uploads/original/8/87790/1897536-box_khearts.png'),
(17, 'The Witcher 3: Wild Hunt', '2015-05-19', 'https://upload.wikimedia.org/wikipedia/en/0/0c/Witcher_3_cover_art.jpg'),
(18, 'Battlefield 1', '2016-10-21', 'https://media.playstation.com/is/image/SCEA/battlefield-1-box-art-two-column-01-ps4-us-28apr16?$TwoColumn_Image$'),
(19, 'Clash Royale', '2016-03-02', 'http://www.astuces2jeux.com/wp-content/uploads/2016/06/image-01-700x393.jpg'),
(20, 'Hearthstone', '2014-03-11', 'https://is4-ssl.mzstatic.com/image/thumb/Purple6/v4/f3/07/33/f30733e6-009f-2340-44ba-8161183ef122/mzl.fctprxwo.png/0x0ss-85.jpg'),
(21, 'Star Wars: Battlefront', '2015-11-17', 'http://image.jeuxvideo.com/medias/144498/1444982838-1766-jaquette-avant.jpg'),
(22, 'Pokemon Soleil et Lune', '2016-11-18', 'http://cdn.neurogadget.net/wp-content/uploads/2017/02/Pokemon-Sun-and-Moon-Pokemon.jpg'),
(23, 'Resident Evil 7: Biohazard', '2017-01-24', 'http://images.techtimes.com/data/images/full/252477/resident-evil-7.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `post`
--

CREATE TABLE `post` (
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `adventure_id` int(11) DEFAULT NULL,
  `publication_time` datetime DEFAULT NULL,
  `type` int(11) NOT NULL,
  `content` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `link` varchar(255) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Contenu de la table `post`
--

INSERT INTO `post` (`post_id`, `user_id`, `adventure_id`, `publication_time`, `type`, `content`, `link`) VALUES
(1, 1, NULL, '2017-03-25 13:42:57', 0, 'test', NULL);


-- --------------------------------------------------------

--
-- Structure de la table `tools`
--

CREATE TABLE `tools` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `imgPath` varchar(40) NOT NULL,
  `url` varchar(40) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `tools`
--

INSERT INTO `tools` (`id`, `name`, `imgPath`, `url`) VALUES
(1, 'JavaScript', 'images/tools/js.jpg', 'https://www.javascript.com/'),
(2, 'AngularJS', 'images/tools/angular.png', 'https://angular.io/'),
(3, 'JQuery', 'images/tools/jquery.png', 'https://jquery.com/'),
(4, 'Materialize CSS', 'images/tools/materialize.png', 'http://materializecss.com/'),
(5, 'PHP', 'images/tools/php.png', 'https://php.net/'),
(6, 'MySQL', 'images/tools/mysql.png', 'https://www.mysql.com/');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `pseudo` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `imgPath` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `flag` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `birthdate` date NOT NULL,
  `banned` tinyint(1) NOT NULL,
  `validation` tinyint(1) NOT NULL,
  `admin` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`user_id`, `pseudo`, `first_name`, `last_name`, `imgPath`, `flag`, `email`, `password`, `gender`, `birthdate`, `banned`, `validation`, `admin`) VALUES
(1, 'tommywalkie', '', '', 'images/youtubers/tommywalkie.png', 'france', 'tommywalkie@gmail.com', 'test', 'M', '1995-06-19', 0, 1, 0),
(2, 'NEWVACHE', '', '', 'images/unknown.png', 'italie', 'newvache@gmail.com', 'test', 'M', '1995-06-19', 0, 1, 0),
(3, 'Pascal', '', '', 'images/unknown.png', 'france', 'pascal.dupont@gmail.com', 'test', 'M', '1995-06-19', 0, 1, 0),
(4, 'John', '', '', 'images/unknown.png', 'america-stars-and-stripes-united-united-states', 'john@gmail.com', 'test', 'M', '1995-06-19', 0, 1, 0),
(5, 'Bernanda', '', '', 'images/unknown.png', 'spain', 'bernanda@gmail.com', 'test', 'F', '1995-06-19', 0, 1, 0),
(6, 'Reinhardt', '', '', 'images/unknown.png', 'germany', 'reinhardt@gmail.com', 'test', 'M', '1995-06-19', 0, 1, 0);


-- --------------------------------------------------------

--
-- Structure de la table `youtubers`
--

CREATE TABLE `youtubers` (
  `id` double NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `imgPath` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `flag` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `OW_PC` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `youtubers`
--

INSERT INTO `youtubers` (`id`, `name`, `imgPath`, `description`, `flag`, `OW_PC`) VALUES
(1, 'Letavernier', 'images/youtubers/letavernier.png', 'La bière c\'est la vie. Bienvenue dans mon antre ! Ici on est là pour s\'amuser et délirer entre potes !', 'france', 'https://owapi.net/api/v3/u/Letavernier-2588/stats'),
(2, 'Shergun', 'images/youtubers/shergun.png', '25 ans, passionné par les jeux vidéo en tout genre et de tout époque. ', 'france', 'https://owapi.net/api/v3/u/Shergun-2127/stats'),
(3, 'Tommywalkie', 'images/youtubers/tommywalkie.png', '21 ans, vidéaste et programmeur en herbe, joue principalement sur PS4 et PC. Aime chasser les bugs et collectionner les gros fails en tout genre !', 'france', 'https://owapi.net/api/v3/u/tommywalkie-2369/stats'),
(4, 'Ducky', 'images/youtubers/ducky.png', 'T\'aimes apprendre des trucs en te marrant sur des blagues de mauvais goût ? Tu veux avoir l\'air cultivé en histoire lors de repas mondains ? Alors sois le bienvenu sur ma chaîne !', 'france', ''),
(5, 'Maurin3', 'images/youtubers/maurin3.png', 'Youtubeuse inconnue au bataillon !', 'belgium', 'https://owapi.net/api/v3/u/Maurin3-2289/stats'),
(6, 'Zapan', 'images/youtubers/zapan.png', 'Youtubeur misanthrope.', 'france', 'https://owapi.net/api/v3/u/Zapan-2616/stats'),
(7, 'Lord Poulpy', 'images/youtubers/lordpoulpy.png', 'Chef incontesté et incontestable de la Sainte Eglise Poulpesque, Lord Poulpy invite ses fidèles quasi-quotidiennement à célébrer l\'Amour, la Joie et la Félicité, afin de trouver la voie.', 'france', ''),
(8, 'Daggoth18', 'images/youtubers/daggoth.png', 'Chaîne gaming détente et découverte, essentiellement sur du contenu Xbox 360, Xbox One, PS4, PC et Rétro-Gaming.', 'france', 'https://owapi.net/api/v3/u/Daggoth-2360/stats'),
(9, 'Herhane', 'images/youtubers/herhane.png', 'Vidéaste végétarien de 24 ans, dormeur et surtout gamer !', 'france', 'https://owapi.net/api/v3/u/Herhane-2723/stats'),
(10, 'Mr-Grydou', 'images/youtubers/mr-grydou.png', 'Bienvenue dans la chaîne de Mr-Grydou vous trouverez des vidéos sur l\'univers du Jeux Vidéo, essentiellement du Let\'s Play sur PS4 et PC, mais aussi quelques autres vidéos toujours dans ce thème !', 'france', ''),
(11, 'Dooms', 'images/youtubers/dooms.png', 'Salut tout le monde, c\'est Dooms ! Je suis youtubeuse gaming et voila ma chaîne ! Suivez moi dans tout un tas d\'aventures !', 'belgium', ''),
(12, 'Lasagames', 'images/youtubers/lasagames.png', 'J\'aime bien parodier des jeux vidéo en doublant les cinématiques à ma sauce quand j\'ai du temps libre ! Éclatez-vous bien à regarder mes vidéos ! Sinon c\'est moi qui vous éclate ! ', 'france', ''),
(13, 'Zoron', 'images/youtubers/zoron.png', 'Moi c\'est Zoron, un passionné de jeu vidéo et tout ce qui y touche de prêt ou de loin. Ma chaîne a pour but premier de vous divertir, mais aussi de vous faire rire !', 'belgium', 'https://owapi.net/api/v3/u/zoron-2854/stats'),
(14, 'Plava Station', 'images/youtubers/plavastation.png', 'En quelques mots, PlavaStation c\'est humour et gaming rétro pour vous faire découvrir ces superbes jeux du passé ! ', 'france', ''),
(15, 'Nakumi Games', 'images/youtubers/nakumi.png', 'Geek, passionnée de Gaming, Japon, dessin. Joueuse de Minecraft, Destiny, Final Fantasy, Pokemon et bien d\'autres encore.', 'france', ''),
(16, 'Sissou', 'images/youtubers/sissou.png', 'Streameur inconnu.', 'belgium', ''),
(17, 'DeathPixHell', 'images/youtubers/deathpixhell.png', '4 Jeunes sans aucun point commun se retrouvent enfermé loin de tout sans aucune explication et avec d\'étranges pouvoirs.', 'france', 'https://owapi.net/api/v3/u/DeathPixHell-2700/stats');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `follow_user`
--
ALTER TABLE `follow_user`
  ADD PRIMARY KEY (`follower_id`,`followed_id`);

--
-- Index pour la table `games`
--
ALTER TABLE `games`
  ADD PRIMARY KEY (`game_id`);

--
-- Index pour la table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`post_id`);

--
-- Index pour la table `tools`
--
ALTER TABLE `tools`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Index pour la table `youtubers`
--
ALTER TABLE `youtubers`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `games`
--
ALTER TABLE `games`
  MODIFY `game_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT pour la table `post`
--
ALTER TABLE `post`
  MODIFY `post_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;
--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
