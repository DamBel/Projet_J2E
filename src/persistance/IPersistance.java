package persistance;

public interface IPersistance {
	
	/**
	 * Renvoie en json tous les outils utilisés pour le site
	 * @return le résultat de la requête en JSON
	 */
	public String getTools();
	
	
	/**
	 * Vérifie l'email et le mot de passe en fonction de ceux présents dans
	 * la base de données lors de l'identification d'un utilisateur
	 * @param email de l'utilisateur
	 * @param password de l'utilisateur
	 * @return le résultat de la requête en JSON
	 */
	public String login(String email, String password);
	
	
	/**
	 * Se charge d'inscrire un utilisateur dans la base de données
	 * 
	 * @param pseudo de l'utilisateur
	 * @param flag (=pays) de l'utilisateur
	 * @param email de l'utilisateur
	 * @param password de l'utilisateur
	 * @param gender (= M ou F) de l'utilisateur
	 * @param birthdate (=année de naissance) de l'utilisateur
	 * @return le résultat de la requête en JSON
	 */
	public String signUpUser(String pseudo, String flag, String email, String password, String gender, String birthdate);
	
	/**
	 * Vérifie lors de l'inscription que l'email choisi par l'utilisateur
	 * n'est pas déjà présent dans la base de données
	 * 
	 * @param email de l'utilisateur
	 * @return le résultat de la requête en JSON
	 */
	public String signUpCheck(String email);
	
	/**
	 * Récupère les cinq derniers utilisateurs inscrits au site
	 * 
	 * @return le résultat de la requête en JSON
	 */
	public String getNewUsers();

	/**
	 * Récupère tous les youtubers à partir de la base de données
	 * 
	 * @return le résultat de la requête en JSON
	 */
	public String getYoutubers();
	
	/**
	 * Récupère tous les jeux enregistrés dans la base de données
	 * 
	 * @see modèle/Game.java
	 * @return le résultat de la requête en JSON
	 */
	public String getAllGames();
	
	/**
	 * Récupère tous les posts de la base de données utilisés par le feed du site
	 * 
	 * @deprecated : il n'est plus possible de récupérer tous les posts la méthode "getFeedPosts" est désormais utilisée
	 * @return le résultat de la requête en JSON
	 */
	public String getPosts();
	
	/**
	 * Récupère tous les posts de l'utilisateur spécifié en paramètre à partir de la base de données
	 * 
	 * @param follower_id l'id de l'utilisateur
	 * @return le résultat de la requête en JSON
	 */
	public String getFeedPosts(String follower_id);
	
	/**
	 * Insère un nouveau post en base de données
	 * 
	 * @param user_id l'id de l'utilisateur
	 * @param publication_time la date et heure de publication du post
	 * @param post_content le contenu du post
	 * @return le résultat de la requête en JSON
	 */
	public String newFeedPost(String user_id, String publication_time, String post_content);
	
	/**
	 *Renvoie tous les abonnements de l'utilisateur spécifié en paramètre
	 * 
	 * @param follower_id l'id de l'utilisateur
	 * @return le résultat de la requête en JSON
	 */
	public String getSubscriptions(String follower_id);
	
	/**
	 * Recherche en base de données tous les utilisateurs contenant le string passée en paramètre
	 * 
	 * @param pseudo le string recherché
	 * @return le résultat de la requête en JSON
	 */
	public String lookForUsers(String pseudo);
	
	/**
	 * Permet de s'abonner à un utilisateur
	 * "follower_id" s'abonne à "followed_id"
	 * 
	 * @param follower_id l'utilisateur qui souhaite s'abonner
	 * @param followed_id l'utilisateur concerné par l'abonnement
	 * @return le résultat de la requête en JSON
	 */
	public String followUser(String follower_id, String followed_id);

	/**
	 * Permet de se désabonner d'un utilisateur
	 * 
	 * @param follower_id l'utilisateur qui souhaite se désabonner
	 * @param followed_id l'utilisateur concerné par le désabonnement
	 * @return le résultat de la requête en JSON
	 */
	public String unfollowUser(String follower_id, String followed_id);
	
	
}
