package fr.licpro.filebox.service;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.android.AndroidLog;
import android.app.IntentService;
import android.content.Intent;
import fr.licpro.filebox.dto.error.CustomErrorHandler;
import fr.licpro.filebox.dto.response.TokenDto;
import fr.licpro.filebox.service.json.JacksonConverter;
import fr.licpro.filebox.utils.FileboxConstant;

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
		mToken = new TokenDto();			
	}
	
	
	@Override
	public void onCreate() 
	{
		super.onCreate();
				
		CustomErrorHandler customError = new CustomErrorHandler(getApplicationContext());
				
		final RestAdapter restAdapter = new RestAdapter
				.Builder().setEndpoint("http://91.121.95.210:443/rest")
				.setLog(new AndroidLog(SyncService.class.getName()))
				.setLogLevel(LogLevel.FULL)
				.setConverter(new JacksonConverter())
				.setErrorHandler(customError)  //-> fait planter l'app
				.build();
		
		mRestClient = restAdapter.create(IRestClient.class);
	}

	/**
	 * List files of an user.
	 */
	public void listFiles()
	{
		mRestClient.getFiles(mToken.toString(), "0");
	}

	/**
	 * List the content of a file or a folder.
	 */
	public void listContentFile()
	{
		// mRestClient.getContentFiles(mToken.toString(), hashid, "0");
	}

	@Override
	protected void onHandleIntent(final Intent pIntent)
	{
		final ISync sync = (ISync) pIntent
				.getSerializableExtra(FileboxConstant.SYNC_CLASS_INTENT);
		sync.execute(getApplicationContext(), mRestClient);
	}
}
