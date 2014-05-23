/*
 * Software property of MILLAU Julien. Copyright Copyright (c) 2014.
 */
package fr.licpro.filebox.dto.response;

import fr.licpro.filebox.dto.error.HttpExceptionDto;

/**
 * Dto for user token
 *
 * @author julien
 */
public class TokenDto extends HttpExceptionDto {

    /**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -1693165302370775705L;
	/**
     * User token
     */
    private String token;

    public TokenDto() {
    }

    public TokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
