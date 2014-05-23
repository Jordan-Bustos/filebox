package fr.licpro.filebox.service;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.android.AndroidLog;
import android.app.IntentService;
import android.content.Intent;
import fr.licpro.filebox.dto.error.CustomErrorHandler;
import fr.licpro.filebox.dto.response.TokenDto;
import fr.licpro.filebox.service.json.JacksonConverter;

/**
 * Service for sync data
 */
public class SyncService extends IntentService
{
	/**
	 * SyncService Tag for Log
	 */
	private static final String	TAG					= SyncService.class
															.getSimpleName();
	/**
	 * Data in the intent
	 */
	public static final String	SYNC_CLASS_INTENT	= "fr.licpro.filebox.syncData";
	/**
	 * Client rest
	 */
	protected IRestClient		mRestClient;
	/**
	 * Token
	 */
	private final TokenDto		mToken;

	/**
	 * Service constructor
	 */
	public SyncService()
	{
		super(SyncService.class.getSimpleName());
		
		final RestAdapter restAdapter = new RestAdapter
				.Builder().setEndpoint("http://91.121.95.210:443/rest")
				.setLog(new AndroidLog(SyncService.class.getName()))
				.setLogLevel(LogLevel.FULL)
				.setConverter(new JacksonConverter())
				.setErrorHandler(new CustomErrorHandler(getApplicationContext()))
				.build();
		
		mRestClient = restAdapter.create(IRestClient.class);
		mToken = new TokenDto();
	}

	/**
	 * Methode permettant de lister les fichiers d'un utilisateur.
	 */
	public void listFiles()
	{
		mRestClient.getFiles(mToken.toString(), "0");
	}

	/**
	 * Methode permettant de lister le contenu d'un fichier ou d'un dossier
	 * utilisateur.
	 */
	public void listContentFile()
	{
		// mRestClient.getContentFiles(mToken.toString(), hashid, "0");
	}

	@Override
	protected void onHandleIntent(final Intent pIntent)
	{
		final ISync sync = (ISync) pIntent
				.getSerializableExtra(SYNC_CLASS_INTENT);
		sync.execute(getApplicationContext(), mRestClient);
	}
}
