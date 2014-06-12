package fr.licpro.filebox.service.sync;

import retrofit.RetrofitError;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import fr.licpro.filebox.dto.response.TokenDto;
import fr.licpro.filebox.service.IRestClient;
import fr.licpro.filebox.utils.FileboxConstant;

/**
 * Method to Sync the startUp data
 */
public class ConnectionSync extends AbstractSync<TokenDto> {
	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -7374379559890502602L;

	/**
	 * Id of connexion.
	 */
	private String mLogin;

	/**
	 * Password.
	 */
	private String mPassword;

	/**
	 * Token.
	 */
	private TokenDto mToken;

	/**
	 * Constructor.
	 * 
	 * @param login id of connexion.
	 * @param password the password.
	 * @param context
	 */
	public ConnectionSync(String login, String password, Context context) {
		this.mLogin = login;
		this.mPassword = password;
		this.mContext = context;
	}

	/**
	 * Permit to get the id of connexion.
	 * 
	 * @return The id of connexion.
	 */
	public String getLogin() {
		return this.mLogin;
	}

	/**
	 * Permit to get the password.
	 * 
	 * @return The password.
	 */
	public String getPassword() {
		return this.mPassword;
	}

	@Override
	protected TokenDto execute(final IRestClient pRestClient)
			throws RetrofitError 
			{
		mToken = pRestClient.getToken(mLogin, mPassword);
		return mToken;
			}

	@Override
	protected void onSuccess() {
		// broadcast for send the  connexion token done to the UI
		Intent intent = new Intent(FileboxConstant.TOKEN_SUCCESS);
		intent.setPackage(mContext.getPackageName());

		SharedPreferences pref = mContext.getSharedPreferences(FileboxConstant.TOKEN_DTO, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		
		editor.putString(FileboxConstant.TOKEN_PREF, mToken.getToken());
		editor.commit();
		
		mContext.sendBroadcast(intent);
	}

	@Override
	protected void onError(final Exception e) {

	}
}
