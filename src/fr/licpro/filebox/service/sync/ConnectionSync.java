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
	 * L'identifiant de connexion.
	 */
	private String login;

	/**
	 * Le mot de passe.
	 */
	private String password;

	/**
	 * Constructeur.
	 * 
	 * @param login
	 *            l'identifiant de connexion.
	 * @param password
	 *            le mot de passe.
	 * @param context
	 */
	public ConnectionSync(String login, String password, Context context) {
		this.login = login;
		this.password = password;
		this.mContext = context;
	}

	/**
	 * Permet d'obtenir l'identifiant de connexion.
	 * 
	 * @return L'identifiant de connexion.
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * Permet d'obtenir le mot de passe.
	 * 
	 * @return Le mot de passe.
	 */
	public String getPassword() {
		return this.password;
	}

	@Override
	protected TokenDto execute(final IRestClient pRestClient)
			throws RetrofitError {
		return pRestClient.getToken(login, password);
	}

	@Override
	protected void onSuccess() {
		// broadcast pour envoyer le token connexion done à l'interface
		Intent intent = new Intent(FileboxConstant.TOKEN_SUCCESS);
		intent.setPackage(mContext.getPackageName());
		mContext.sendBroadcast(intent);
	}

	@Override
	protected void onError(final Exception e) {
		// laisser vide -> utiliser retrofit
		// cas spécifiques
	}
}
