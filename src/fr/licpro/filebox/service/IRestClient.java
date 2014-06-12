package fr.licpro.filebox.service;

import retrofit.client.Response;
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
	 * Permit to get the token.
	 * 
	 * @param login  Id of the user.
	 * @param password  Password of the user.
	 * @return The token.
	 */
	@PUT("/customer/token")
	TokenDto getToken(@Query("login") String login,
			@Query("password") String password);

	/**
	 * Permit to list files.
	 * 
	 * @param token  The token.
	 * @param timestamp  Last call date.
	 * @return List of files.
	 */
	@GET("/file/{token}")
	FilesDto getFiles(@Path("token") String token,
			@Query("data") String timestamp);

	/**
	 * Permit to get the content of a file or a folder.
	 * 
	 * @param token  The token.
	 * @param hashid   The hash of the file/folder geted.
	 * @param timestamp   Last call date.
	 * @return The detail of the file or folder.
	 */
	@GET("/file/{token}/{hashid}")
	Response getContentFiles(@Path("token") String token,
			@Path("hashid") String hashid, @Query("date") String timestamp);
}
