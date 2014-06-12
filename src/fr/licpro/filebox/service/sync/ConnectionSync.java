package fr.licpro.filebox.service.sync;

import retrofit.RetrofitError;
import android.content.Context;
import android.content.Intent;
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
			throws RetrofitError {
		return pRestClient.getToken(mLogin, mPassword);
	}

	@Override
	protected void onSuccess() {
		// broadcast for send the  connexion token done to the UI
		Intent intent = new Intent(FileboxConstant.TOKEN_SUCCESS);
		intent.setPackage(mContext.getPackageName());
		mContext.sendBroadcast(intent);
	}

	@Override
	protected void onError(final Exception e) {

	}
}
