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
    private String mToken;

    /**
     * Constructor.
     */
    public TokenDto() {
    }

    /**
     * Constructor complete.
     * @param token the token.
     */
    public TokenDto(String token) {
        setToken(token);
    }

    /**
     * Get the token.
     * @return the token.
     */
    public String getToken() {
        return mToken;
    }

    /**
     * Set the token.
     * @param token the token.
     */
    public void setToken(String token) {
        mToken = token;
    }

}
