/**
 * 
 */
package fr.licpro.filebox.dto.error;

import java.io.File;

import fr.licpro.filebox.utils.FileboxConstant;
import android.R.integer;
import android.content.Context;
import android.content.Intent;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;

/**
 * @author jordanbustos
 *
 */
public class CustomErrorHandler implements ErrorHandler {
	
	/**
	 * Context.
	 */
	private Context mContext;
	
	/**
	 * Constructeur.
	 * @param context le context.
	 */
	public CustomErrorHandler(Context context)
	{
		mContext = context;
	}

	/* (non-Javadoc)
	 * @see retrofit.ErrorHandler#handleError(retrofit.RetrofitError)
	 */
	@Override
	public Throwable handleError(RetrofitError error) 
	{
		HttpExceptionDto errorDto = (HttpExceptionDto) error.getBodyAs(HttpExceptionDto.class);

		Intent it = new Intent (FileboxConstant.TOKEN_ERROR);
		it.setPackage(FileboxConstant.PACKAGE);
		if (errorDto != null)
		{
			it.putExtra(it.getAction(), errorDto);
		}
		
		mContext.sendBroadcast(it);

		return error;

	}

}
