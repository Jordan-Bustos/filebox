package fr.licpro.filebox.activity;

import fr.licpro.filebox.R;
import fr.licpro.filebox.dto.commons.FileDto;
import fr.licpro.filebox.utils.FileboxConstant;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
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
		FileDto fileDto = (FileDto) intentReceive.getSerializableExtra(FileboxConstant.FILESDTOVALUE); 
		//TODO Traiter le fichier en question
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file_detail, menu);
		return true;
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	 {
		 if ((keyCode == KeyEvent.KEYCODE_BACK)) 
		 {
			 Intent intent = new Intent(this,ListActivity.class);
			 intent.putExtra(FileboxConstant.USERIDENTIFIANT, mUserID);
			 startActivity(intent);
		     finish();   
		 }
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
				finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

}
