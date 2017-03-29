<?php 
	try {
		$bdd = new PDO('<DB_HOST>;<DB_NAME>;charset=utf8', '<USER_NAME>', '<USER_HOST>');

		$query = 'SELECT * FROM youtubers;';
		$reponse = $bdd->query($query);

		$resultArray = [];

		while($r = $reponse->fetch(PDO::FETCH_ASSOC)) {
			$resultArray[$r["id"]] = $r;
		}

		$resultJSON = json_encode($resultArray);

		echo $resultJSON;

	}
	catch(Exception $e) {
        die('Erreur : '.$e->getMessage());
	}

?>