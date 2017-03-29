require_once("db.php");

	switch($_GET['action']){
		case "signup_user" : signup_user(); break;
		case "signup_check" : signup_check(); break;
		case "tools" : tools(); break;
		case "login" : login(); break;
		case "all_games" : all_games(); break;
		case "new_users" : new_users(); break;
		case "posts" : posts(); break;
		case "feed_posts" : feed_posts(); break;
		case "new_feed_post" : new_feed_post(); break;
		case "recherche_user" : recherche_user(); break;
		case "follow_user" : follow_user(); break;
		case "unfollow_user" : unfollow_user(); break;
		case "subscriptions" : subscriptions(); break;
		default : break;
	}
	exit();

	// Insertion d'un nouveau statut
	function new_feed_post() {
		$data = json_decode(file_get_contents("php://input"));
		$user_id = $data->user_id;
		$post_content = $data->content;
		$publication_time = $data->publication_time;

		$query = 'INSERT INTO `post`(`user_id`, `adventure_id`, `publication_time`, `type`, `content`, `link`) VALUES (:user_id,NULL,:publication_time,0,:post_content,null)';

		$stmt = DB::prepare($query);

		$stmt->bindParam(':user_id', $user_id);
		$stmt->bindParam(':post_content', $post_content);
		$stmt->bindParam(':publication_time', $publication_time);

		$stmt->execute();
		echo "Nouveau statut publié.";

	}

	// Abonnement à un utilisateur
	function follow_user() {
		$data = json_decode(file_get_contents("php://input"));
		$follower_id = $data->follower_id;
		$followed_id = $data->followed_id;

		$query = 'INSERT INTO `follow_user`(`follower_id`, `followed_id`) VALUES (:follower_id,:followed_id)';

		$stmt = DB::prepare($query);

		$stmt->bindParam(':follower_id', $follower_id);
		$stmt->bindParam(':followed_id', $followed_id);

		$stmt->execute();
		echo "Nouvel abonnement.";

	}

	// Désabonnement à un utilisateur
	function unfollow_user() {
		$data = json_decode(file_get_contents("php://input"));
		$follower_id = $data->follower_id;
		$followed_id = $data->followed_id;

		$query = 'DELETE FROM `follow_user` WHERE follower_id = :follower_id AND followed_id = :followed_id';

		$stmt = DB::prepare($query);

		$stmt->bindParam(':follower_id', $follower_id);
		$stmt->bindParam(':followed_id', $followed_id);

		$stmt->execute();
		echo "Abonnement supprimé.";

	}

	// Inscription d'un nouvel utilisateur 
	function signup_user() {
		$data = json_decode(file_get_contents("php://input"));

		$email = $data->email;
		$pseudo = $data->pseudo;
		$gender = $data->gender;
		$birthdate = $data->birthdate;
		$flag = $data->flag;

		//$bdd = db();

		$password = $data->password;
		$password = password_hash($password, PASSWORD_DEFAULT);

		//$bdd->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		
		$query = 'INSERT INTO `users`(`pseudo`, `first_name`, `last_name`, `imgPath`, `flag`, `email`, `password`, `gender`, `birthdate`, `banned`, `validation`, `admin`) VALUES (:pseudo,"","","images/unknown.png",:flag,:email,:password,:gender,:birthdate,"0","1","0")';

		$stmt = DB::prepare($query);

		$stmt->bindParam(':email', $email);
		$stmt->bindParam(':pseudo', $pseudo);
		$stmt->bindParam(':password', $password);
		$stmt->bindParam(':gender', $gender);
		$stmt->bindParam(':birthdate', $birthdate);
		$stmt->bindParam(':flag', $flag);

		$stmt->execute();
		echo "Enregistré.";
	}

	// Vérifie que l'email ou le pseudo n'a pas été pris
	function signup_check() {
		try {
			$data = json_decode(file_get_contents("php://input"));

			$email = $data->email;
			$pseudo = $data->pseudo;
			
			$query = 'SELECT * FROM users WHERE email = :email OR pseudo = :pseudo;';

			$stmt = DB::prepare($query);

			$stmt->bindParam(':email', $email);
			$stmt->bindParam(':pseudo', $pseudo);

			$stmt->execute();

			$resultJSON = json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));

			echo $resultJSON;
			

		}
		catch(Exception $e) {
	        die('Erreur : '.$e->getMessage());
		}
	}

	// Retourne les outils utilisés pour le projet
	function tools() {
		try {

			$query = 'SELECT * FROM tools;';

			$reponse = DB::query($query);
			
			$resultJSON = json_encode($reponse->fetchAll(PDO::FETCH_ASSOC));

			echo $resultJSON;
		

		}
		catch(Exception $e) {
	        die('Erreur : '.$e->getMessage());
		}
	}

	// Retourne les utilisateurs correspondant à un pseudo
	function recherche_user() {

		try {

			$data = json_decode(file_get_contents("php://input"));

			$user_id = $data->user_id;
			$pseudo_search = $data->pseudo_search;

			// Retourne les utilisateurs correspondant à un pseudo excepté l'utilisateur courant
			$query = "SELECT user_id, pseudo, imgPath, flag FROM `users` WHERE pseudo LIKE :pseudo_search AND user_id != :user_id LIMIT 20;";

			$stmt = DB::prepare($query);

			$stmt->execute(array(':user_id' => $user_id , ':pseudo_search' => '%'.$pseudo_search.'%'));


			$resultJSON = json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));

			echo $resultJSON;

		}
		catch(Exception $e) {
	        die('Erreur : '.$e->getMessage());
		}



	}

	// Retourne les posts
	function posts() {
		try {

			$query = 'SELECT u.pseudo as pseudo, p.content as content, u.imgPath as imgPath, p.publication_time as publication_time, u.flag as flag FROM post p INNER JOIN users u ON p.user_id = u.user_id ORDER BY p.publication_time DESC LIMIT 10;';

			$reponse = DB::query($query);
			
			$resultJSON = json_encode($reponse->fetchAll(PDO::FETCH_ASSOC));

			echo $resultJSON;
		

		}
		catch(Exception $e) {
	        die('Erreur : '.$e->getMessage());
		}
	}

	// Retourne les posts des abonnements
	function feed_posts() {
		try {

			$data = json_decode(file_get_contents("php://input"));

			$follower_id = $data->follower_id;

			$query = 'SELECT u.pseudo as pseudo, p.content as content, u.imgPath as imgPath, p.publication_time as publication_time, u.flag as flag FROM post p 
						INNER JOIN users u ON (p.user_id = u.user_id) 
						INNER JOIN follow_user f ON (p.user_id = f.followed_id) 
						WHERE f.follower_id = :follower_id 
						UNION 
						SELECT u2.pseudo as pseudo, p2.content as content, u2.imgPath as imgPath, p2.publication_time as publication_time, u2.flag as flag FROM post p2 
						INNER JOIN users u2 ON (p2.user_id = u2.user_id) 
						WHERE u2.user_id = :follower_id 
						ORDER BY publication_time 
						DESC LIMIT 10;';

			$stmt = DB::prepare($query);

			$stmt->execute(array(':follower_id' => $follower_id));


			$resultJSON = json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));

			echo $resultJSON;
		

		}
		catch(Exception $e) {
	        die('Erreur : '.$e->getMessage());
		}
	}

	// Retourne les abonnements
	function subscriptions() {
		try {

			$data = json_decode(file_get_contents("php://input"));
			$follower_id = $data->follower_id;

			$query = 'SELECT u.user_id as user_id, u.pseudo as pseudo, u.imgPath as imgPath, u.flag as flag FROM follow_user f INNER JOIN users u ON f.follower_id = :follower_id AND f.followed_id = u.user_id';

			$stmt = DB::prepare($query);

			$stmt->execute(array(':follower_id' => $follower_id));


			$resultJSON = json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));

			echo $resultJSON;
		

		}
		catch(Exception $e) {
	        die('Erreur : '.$e->getMessage());
		}
	}

	// Connection de l'utilisateur
	function login(){
		try {
			$data = json_decode(file_get_contents("php://input"));

			$email = $data->email;
			$password = $data->password;
			
			$query = 'SELECT * FROM users WHERE email = :email';
			$query2 = 'SELECT user_id, pseudo, imgPath, flag, email, gender, birthdate FROM users WHERE email = :email';

			$stmt = DB::prepare($query);
			$stmt2 = DB::prepare($query2);

			$stmt->bindParam(':email', $email);
			$stmt2->bindParam(':email', $email);
			

			$stmt->execute();
			$stmt2->execute();

			$user = $stmt->fetch();

			$resultJSON = json_encode($stmt2->fetchAll(PDO::FETCH_ASSOC));

			if (password_verify($password, $user['password'])){
			   echo $resultJSON; 
			}
			else {
			   echo null;
			}
			

		}
		catch(Exception $e) {
	        die('Erreur : '.$e->getMessage());
		}
	}

	// Retourne la liste de tous les jeux
	function all_games() {
		try {

			$query = 'SELECT * FROM `games` ORDER BY `game_id` ASC;';
			$reponse = DB::query($query);

			$resultJSON = json_encode($reponse->fetchAll(PDO::FETCH_ASSOC));

			echo $resultJSON;

		}
		catch(Exception $e) {
	        die('Erreur : '.$e->getMessage());
		}
	}

	// Retourne les 5 derniers inscrits
	function new_users() {

		try {

			$query = 'SELECT pseudo, imgPath, flag FROM `users` WHERE 1 ORDER BY `user_id` DESC LIMIT 5;';
			$reponse = DB::query($query);

			$resultJSON = json_encode($reponse->fetchAll());

			echo $resultJSON;

		}
		catch(Exception $e) {
	        die('Erreur : '.$e->getMessage());
		}

	}