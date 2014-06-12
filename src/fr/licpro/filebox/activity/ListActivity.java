package fr.licpro.filebox.activity;

import fr.licpro.filebox.R;
import fr.licpro.filebox.dto.commons.FileDto;
import fr.licpro.filebox.utils.FileboxConstant;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListActivity extends Activity implements OnItemClickListener
{
	/** 
	 * The listView of FileDto. 
	 */
	private ListView	lvFile;
	/**
	 * L'identifiant de l'utilisateur.
	 */
	private String mUserID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
		setContentView(R.layout.activity_list);
		
		lvFile = (ListView) findViewById(R.id.FileDtoList);
		lvFile.setOnItemClickListener(this);
		//TODO
		//Envoyer la liste à l'adapteur recupérée depuis le serveur ICI
		//lvFile.setAdapter(new FileItemArrayAdapter(this, listeFiles));
		
		final Intent intentReceive = getIntent();
		mUserID = intentReceive.getStringExtra(FileboxConstant.USERIDENTIFIANT);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(final MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.action_compte:
				Intent intent = new Intent(getApplicationContext(),AccountActivity.class);
				intent.putExtra(FileboxConstant.USERIDENTIFIANT, mUserID);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	/* _________________________________________________________ */
	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView,
	 *      android.view.View, int, long)
	 */
	@Override
	public void onItemClick(final AdapterView<?> arg0, final View v,
			final int position, final long arg3)
	{
		final FileDto selectedFile = (FileDto) lvFile.getItemAtPosition(position);
		final Intent intent = new Intent(getBaseContext(),
				FileDetailActivity.class);
		//final Bundle bundleToSend = new Bundle();
		//Ajouter au bundle les infos necessaires.
		//TODO
		//intent.putExtras(bundleToSend);
		intent.putExtra(FileboxConstant.USERIDENTIFIANT, mUserID);
		startActivity(intent);
	}
}
