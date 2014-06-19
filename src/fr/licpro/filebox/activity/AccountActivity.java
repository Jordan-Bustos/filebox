package fr.licpro.filebox.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import fr.licpro.filebox.R;
import fr.licpro.filebox.utils.FileboxConstant;

public class AccountActivity extends Activity implements OnClickListener
{
	/**
	 * The Context.
	 */
	private AccountActivity mContext;
	
	/**
	 * The user's ID.
	 */
	private String mUserID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);

		final Button btn_deconnexion = (Button) findViewById(R.id.btn_deconnexion);
		btn_deconnexion.setOnClickListener(this);

		final Intent intentReceive = getIntent();
		mUserID = intentReceive.getStringExtra(FileboxConstant.USERIDENTIFIANT);
		TextView tv_accountName = (TextView) findViewById(R.id.tvaccountname);
		tv_accountName.setText(mUserID);
		this.mContext = this;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account, menu);
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
	
	/* _________________________________________________________ */
	/**
	 * @param view
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(final View view)
	{
		if (view.getId() == R.id.btn_deconnexion)
		{
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
			alertDialogBuilder.setTitle(FileboxConstant.DECONNEXION);
			alertDialogBuilder
			.setMessage(R.string.confirmdisconnection)
			.setCancelable(false)
			.setPositiveButton(FileboxConstant.OUI,new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog,int id) 
				{
					AccountActivity.this.finish();
					
					//Delete the TOKEN
					SharedPreferences pref = getSharedPreferences(FileboxConstant.TOKEN_DTO, Context.MODE_PRIVATE);
					Editor edit = pref.edit();
					edit.remove(FileboxConstant.TOKEN_PREF);
					edit.commit();
					
					final Intent intent = new Intent(mContext, MainActivity.class);
					startActivity(intent);
					finish();
				}
			})
			.setNegativeButton(FileboxConstant.NON,new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog,int id) 
				{
					dialog.cancel();
				}
			});
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
		}
	}
}
