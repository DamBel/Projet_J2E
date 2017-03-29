	class DB {
	   
	    private static $connected = false;
	    private static $PDO = null;
	   
	    private static function getLogs() {
	        //Récupère les logs depuis un XML etc

	        $json = file_get_contents('<LOGS JSON FILE HERE>');
			$obj = json_decode($json);
			return $obj;
	    }
	   
	    private static function connexion() {
	        if(self::$connected)
	            return;
	       
	        $logs = self::getLogs();

	        $dsn = $logs->dsn;
	        $username = $logs->user_name;
	        $password = $logs->user_password;
	        self::$PDO = new PDO($dsn, $username, $password);
	        self::$PDO->exec("set names utf8mb4");
	        //try catch etc
	       
	        self::$connected = true;
	    }
	   
	    public static function beginTransation() {
	        self::connexion();
	        return self::$PDO->beginTransation();
	    }
	   
	    public static function endTransation() {
	        return self::$PDO->endTransation();
	    }
	   
	    public static function commit() {
	        return self::$PDO->commit();
	    }
	   
	    public static function rollback() {
	        return self::$PDO->rollback();
	    }
	   
	    public static function prepare($query) {
	        self::connexion();
	        return self::$PDO->prepare($query);
	    }

	    public static function query($query) {
	        self::connexion();
	        return self::$PDO->query($query);
	    }
	   
	   
	}

?>