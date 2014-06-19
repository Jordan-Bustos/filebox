/**
 * 
 */
package fr.licpro.filebox.dto.error;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import android.content.Context;
import android.content.Intent;
import fr.licpro.filebox.utils.FileboxConstant;

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
	 * Constructor.
	 * @param context the context.
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
		if (!error.isNetworkError()) // for the getBodyAs !
		{
			HttpExceptionDto errorDto = (HttpExceptionDto) error.getBodyAs(HttpExceptionDto.class);
	
			Intent it = new Intent (FileboxConstant.TOKEN_ERROR);
			it.setPackage(FileboxConstant.PACKAGE);
			if (errorDto != null)
			{
				it.putExtra(it.getAction(), errorDto);
			}			
			mContext.sendBroadcast(it);
		}
		return error;
	}

}
