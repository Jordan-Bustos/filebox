package fr.licpro.filebox.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import fr.licpro.filebox.R;
import fr.licpro.filebox.service.SyncService;
import fr.licpro.filebox.service.sync.ConnectionSync;
import fr.licpro.filebox.utils.FileboxConstant;

public class MainActivity extends Activity implements OnClickListener
{
	
	/**
	 * Broadcast receiver.
	 */
	private SyncDoneReceiver mSyncDoneReceiver;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null)
		{
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		// TODO Add intent to launch service
		final Button btn_getToken = (Button) findViewById(R.id.btn_getToken);
		btn_getToken.setOnClickListener(this);
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		mSyncDoneReceiver = new SyncDoneReceiver();
		registerReceiver(mSyncDoneReceiver, new IntentFilter(FileboxConstant.TOKEN_SUCCESS));
	}
	
	@Override
	protected void onStop()
	{
		if (mSyncDoneReceiver != null)
		{
			unregisterReceiver(mSyncDoneReceiver);
		}
		super.onStop();		
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		final int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment
	{
		public PlaceholderFragment()
		{
		}

		@Override
		public View onCreateView(final LayoutInflater inflater,
				final ViewGroup container, final Bundle savedInstanceState)
		{
			final View rootView = inflater.inflate(R.layout.fragment_main,
					container, false);
			return rootView;
		}
	}

	/* _________________________________________________________ */
	/**
	 * @param view
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(final View view)
	{
		if (view.getId() == R.id.btn_getToken)
		{
			Toast.makeText(getApplicationContext(), FileboxConstant.TENTATIVE_CO, Toast.LENGTH_SHORT)
			.show();		
			
			final Intent intent = new Intent(this, SyncService.class);
			
			String identifiant = ((EditText)findViewById(R.id.editTextIdentifiant)).getText().toString(); // jejebubu
			String mdp = ((EditText)findViewById(R.id.editTextMdp)).getText().toString(); // projet
			
			if (identifiant != null && !identifiant.trim().equals("")
					||
				mdp != null && !mdp.trim().equals(""))
			{
				ConnectionSync connectionSync = new ConnectionSync(identifiant, mdp, getApplicationContext()); 
				intent.putExtra(FileboxConstant.SYNC_CLASS_INTENT, connectionSync);
				startService(intent);
			}
			else
				Toast.makeText(getApplicationContext(), FileboxConstant.RENSEIGNEZ_ID_MDP, Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * Broadcast receiver.	
	 */
	private class SyncDoneReceiver extends BroadcastReceiver
	{			
		@Override
		public void onReceive(final Context pParamContext, final Intent pParamIntent)
		{
			if (pParamIntent.getAction().equals(FileboxConstant.TOKEN_SUCCESS))
			{
				Toast.makeText(pParamContext, FileboxConstant.CONNEXION_REUSSIE, Toast.LENGTH_SHORT).show();			
			}
			else if (pParamIntent.getAction().equals(FileboxConstant.TOKEN_ERROR))
			{
				Toast.makeText(pParamContext, FileboxConstant.CONNEXION_ECHOUEE, Toast.LENGTH_SHORT).show();	
			}
		}
	}
}
