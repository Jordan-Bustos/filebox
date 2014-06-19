/**
 * 
 */
package fr.licpro.filebox.service.sync;

import retrofit.RetrofitError;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import fr.licpro.filebox.dto.response.FilesDto;
import fr.licpro.filebox.service.IRestClient;
import fr.licpro.filebox.utils.FileboxConstant;

/**
 * @author jordanbustos
 *
 */
public class ListFilesSync extends AbstractSync<FilesDto> {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1093780033789915549L;

	/**
	 * The filesDto.
	 */
	private FilesDto mFilesDto;

	@Override
	protected FilesDto execute(IRestClient pRestClient) throws RetrofitError
	{
		SharedPreferences pref = mContext.getSharedPreferences(FileboxConstant.TOKEN_DTO, Context.MODE_PRIVATE);
		String token = pref.getString(FileboxConstant.TOKEN_PREF, null);
		mFilesDto = pRestClient.getFiles(token, "0");
		return mFilesDto;
	}

	@Override
	protected void onSuccess() 
	{
		Intent intent = new Intent(FileboxConstant.FILESDTO);
		intent.setPackage(mContext.getPackageName());
		intent.putExtra(FileboxConstant.FILESDTOVALUE, mFilesDto);
		mContext.sendBroadcast(intent);
	}

	@Override
	protected void onError(Exception e) 
	{		
	}
}
