package fr.licpro.filebox.service;

import java.util.List;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import fr.licpro.filebox.dto.response.FilesDto;
import fr.licpro.filebox.dto.response.TokenDto;

/**
 * Interface for REST API
 */
public interface IRestClient
{
	/**
	 * Permet de retourner le token.
	 * 
	 * @param login
	 *            Le login utilisateur.
	 * @param password
	 *            Le mot de passe utilisateur.
	 * @return Le token.
	 */
	@PUT("/customer/token")
	TokenDto getToken(@Query("login") String login,
			@Query("password") String password);

	/**
	 * Permet de lister les fichiers
	 * 
	 * @param token
	 *            Le token.
	 * @param timestamp
	 *            Date depuis le dernier appel.
	 * @return La liste des fichiers associée au token.
	 */
	@GET("/file/{token}")
	List<FilesDto> getFiles(@Path("token") String token,
			@Query("data") String timestamp);

	/**
	 * Contenu d'un fichier ou dossier
	 * 
	 * @param token
	 *            Le token.
	 * @param hashid
	 *            Le hash du fichier ou dossier à récupérer
	 * @param timestamp
	 *            Date depuis le dernier appel.
	 * @return Le détail du fichier ou du dossier.
	 */
	@GET("/file/{token}/{hashid}")
	List<FilesDto> getContentFiles(@Path("token") String token,
			@Path("hashid") String hashid, @Query("date") String timestamp);
}
