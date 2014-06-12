package fr.licpro.filebox.service;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.android.AndroidLog;
import android.app.IntentService;
import android.content.Intent;
import fr.licpro.filebox.dto.error.CustomErrorHandler;
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
	@SuppressWarnings("unused")
	private static final String	TAG					= SyncService.class
															.getSimpleName();
	/**
	 * Client rest
	 */
	protected IRestClient		mRestClient;

	/**
	 * Service constructor
	 */
	public SyncService()
	{
		super(SyncService.class.getSimpleName());
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
				.setErrorHandler(customError)
				.build();
		
		mRestClient = restAdapter.create(IRestClient.class);
	}

	@Override
	protected void onHandleIntent(final Intent pIntent)
	{
		final ISync sync = (ISync) pIntent
				.getSerializableExtra(FileboxConstant.SYNC_CLASS_INTENT);
		sync.execute(getApplicationContext(), mRestClient);
	}
}
