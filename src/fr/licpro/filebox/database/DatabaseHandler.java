/**
 * 
 */
package fr.licpro.filebox.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import fr.licpro.filebox.dto.commons.FileDto;
import fr.licpro.filebox.utils.ActivityContainer;
import fr.licpro.filebox.utils.FileboxConstant;

/**
 * @author jordanbustos
 * The database handler.
 */
public class DatabaseHandler extends OrmLiteSqliteOpenHelper
{
	/**
	 * L'instance unique de la DatabaseHelper
	 */
	private static DatabaseHandler	instance;
	
	/**
	 * The context
	 */
	@SuppressWarnings("unused")
	private Context mContext;

	/* _________________________________________________________ */
	/**
	 * @param context
	 *           The context.
	 * @return unic Instance.
	 */
	public static DatabaseHandler getInstance(final Context context)
	{
		if (instance == null)
		{
			instance = new DatabaseHandler(context);
		}
		return instance;
	}

	/* _________________________________________________________ */
	/**
	 * @param pContext
	 *            The context.
	 */
	private DatabaseHandler(final Context pContext)
	{
		super(pContext, FileboxConstant.DATABASE_NAME, null, 3);
		setContext(pContext);
		if (instance == null)
		{
			instance = this;
		}
	}

	/**
	 * Setter of context.
	 * @param pContext the context.
	 */
	private void setContext(Context pContext) 
	{
		mContext = pContext;
	}

	/* _________________________________________________________ */
	/**
	 * @param pDb
	 *           Database.
	 * @param pConnectionSource
	 *            Connexion.
	 * @see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase,
	 *      com.j256.ormlite.support.ConnectionSource)
	 */
	@Override
	public void onCreate(final SQLiteDatabase pDb,
			final ConnectionSource pConnectionSource)
	{
		try
		{
			TableUtils.createTableIfNotExists(pConnectionSource, FileDto.class);
		}
		catch (final SQLException e)
		{
			Crouton.makeText(ActivityContainer.getActivity(FileboxConstant.ACTIVITY_MAIN), 
					FileboxConstant.EROR_CREATION_BDD, Style.ALERT).show();
		}
	}

	/* _________________________________________________________ */
	/**
	 * @param pDb
	 *            Database.
	 * @param pConnectionSource
	 *            Connexion.
	 * @param pOldVersion
	 *            Old version.
	 * @param pNewVersion
	 *            New version.
	 * @see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase,
	 *      com.j256.ormlite.support.ConnectionSource, int, int)
	 */
	@Override
	public void onUpgrade(final SQLiteDatabase pDb,
			final ConnectionSource pConnectionSource, final int pOldVersion,
			final int pNewVersion)
	{
		try
		{
			TableUtils.dropTable(pConnectionSource, FileDto.class, true);
			onCreate(pDb, pConnectionSource);
		}
		catch (final SQLException e)
		{
			Crouton.makeText(ActivityContainer.getActivity(FileboxConstant.ACTIVITY_MAIN), 
					FileboxConstant.EROR_SUPPRESSION_BDD, Style.ALERT).show();		}
	}

}
