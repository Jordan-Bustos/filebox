package fr.licpro.filebox.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);

		final Button btn_deconnexion = (Button) findViewById(R.id.btn_deconnexion);
		btn_deconnexion.setOnClickListener(this);

		final Intent intentReceive = getIntent();
		String mUserID = intentReceive.getStringExtra(FileboxConstant.USERIDENTIFIANT);
		TextView tv_accountName = (TextView) findViewById(R.id.tvaccountname);
		tv_accountName.setText(mUserID);
		this.mContext = this;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account, menu);
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
					
					// aller dans charet preferences et enlever le token
					
					final Intent intent = new Intent(mContext, MainActivity.class);
					startActivity(intent);
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
