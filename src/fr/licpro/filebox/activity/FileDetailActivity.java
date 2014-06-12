package fr.licpro.filebox.activity;

import fr.licpro.filebox.R;
import fr.licpro.filebox.utils.FileboxConstant;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class FileDetailActivity extends Activity 
{
	/**
	 * L'identifiant de l'utilisateur.
	 */
	private String mUserID = FileboxConstant.EMPTY;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
		setContentView(R.layout.activity_file_detail);
		
		final Intent intentReceive = getIntent();
		mUserID = intentReceive.getStringExtra(FileboxConstant.USERIDENTIFIANT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file_detail, menu);
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

}
