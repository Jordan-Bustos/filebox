package fr.licpro.filebox.utils;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import fr.licpro.filebox.database.DatabaseHandler;


/**
 * Utils class to provide save/get method for all dao
 * 
 */
public final class DaoUtils {

	/**
	 * Class Tag for the Logger
	 */
	private static final String TAG = DaoUtils.class.getSimpleName();

	/**
	 * Method to delete an object
	 * 
	 * @param pContext
	 *            the application context
	 * @param pData
	 *            bean to delete
	 */
	@SuppressWarnings("unchecked")
	public static <T> void deleteData(final Context pContext, final T pData) {
		if (pData != null) {
			Class<?> type = pData.getClass();
			SQLiteOpenHelper db = DatabaseHandler.getInstance(pContext);
			ConnectionSource connectionSource = new AndroidConnectionSource(db);

			Dao<T, Integer> dao = null;
			try {
				dao = (Dao<T, Integer>) DaoManager.createDao(connectionSource, type);
				if (dao != null) {
					dao.delete(pData);
				}
			} catch (SQLException e) {
				Log.w(TAG, "unable to delete the object " + type, e);
			} finally {
				connectionSource.closeQuietly();
			}
		}
	}

	/**
	 * Method to getData all data
	 * 
	 * @param pContext
	 *            the application context
	 * @param pClazz
	 *            IDatabaseData class
	 * @return the List of object
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getAllData(final Context pContext, final Class<T> pClazz) {
		List<T> ret = null;
		SQLiteOpenHelper db = DatabaseHandler.getInstance(pContext);
		ConnectionSource connectionSource = new AndroidConnectionSource(db);
		Dao<T, Integer> dao = null;
		try {
			dao = (Dao<T, Integer>) DaoManager.createDao(connectionSource, pClazz);
			if (dao != null) {
				ret = dao.queryForAll();
			}
		} catch (SQLException e) {
			Log.e(TAG, "unable to get all data", e);
		} finally {
			connectionSource.closeQuietly();
		}
		return ret;
	}

	/**
	 * Method to getData with it's id
	 * 
	 * @param pContext
	 *            the application context
	 * @param pData
	 *            id of the data
	 * @param pClazz
	 *            IDatabaseData class
	 * @return the object or null
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getDataById(final Context pContext, final int pData, final Class<T> pClazz) {
		T ret = null;
		SQLiteOpenHelper db = DatabaseHandler.getInstance(pContext);
		ConnectionSource connectionSource = new AndroidConnectionSource(db);
		Dao<T, Integer> dao = null;
		try {
			dao = (Dao<T, Integer>) DaoManager.createDao(connectionSource, pClazz);
			if (dao != null) {
				ret = dao.queryForId(pData);
			}
		} catch (SQLException e) {
			Log.e(TAG, "unable to get object with it's id", e);
		} finally {
			connectionSource.closeQuietly();
		}
		return ret;
	}

	/**
	 * Method to get object with where clause
	 * 
	 * @param pContext
	 *            the application context
	 * @return the list of object
	 */
	public static <T> T getEqData(final Context pContext, final Class<T> pClazz, final String pField, final Object pValue) {
		List<T> ret = getEqDatas(pContext, pClazz, pField, pValue, null, false, 1);
		T val = null;
		if (ret != null && !ret.isEmpty()) {
			val = ret.get(0);
		}
		return val;
	}

	/**
	 * Method to get first ordered object with where clause
	 * 
	 * @param pContext
	 *            the application context
	 * @return the list of object
	 */
	public static <T> T getEqData(final Context pContext, final Class<T> pClazz, final String pField, final Object pValue,
			final String pOrder, final boolean pAsc) {
		List<T> ret = getEqDatas(pContext, pClazz, pField, pValue, pOrder, pAsc, 1);
		T val = null;
		if (ret != null && !ret.isEmpty()) {
			val = ret.get(0);
		}
		return val;
	}

	/**
	 * Method to get objects with the specified field
	 * 
	 * @param pContext
	 *            application context
	 * @param pClazz
	 *            bean class
	 * @param pField
	 *            filed to filter
	 * @param pValue
	 *            value to match
	 * @return List of object or null
	 */
	public static <T> List<T> getEqDatas(final Context pContext, final Class<T> pClazz, final String pField, final Object pValue) {
		return getEqDatas(pContext, pClazz, pField, pValue, null, false, 0);
	}

	/**
	 * Method to get objects ordered with the specified field
	 * 
	 * @param pContext
	 *            application context
	 * @param pClazz
	 *            bean class
	 * @param pField
	 *            filed to filter
	 * @param pValue
	 *            value to match
	 * @param pOrder
	 *            Field order
	 * @return List of object or null
	 */
	public static <T> List<T> getEqDatas(final Context pContext, final Class<T> pClazz, final String pField, final Object pValue,
			final String pOrder, final boolean pAsc) {
		return getEqDatas(pContext, pClazz, pField, pValue, pOrder, pAsc, 0);
	}

	/**
	 * Method to get objects ordered with the specified field
	 * 
	 * @param pContext
	 *            application context
	 * @param pClazz
	 *            bean class
	 * @param pField
	 *            filed to filter
	 * @param pValue
	 *            value to match
	 * @param pOrder
	 *            Field order
	 * @param pLimit
	 *            limit number
	 * @return List of object or null
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getEqDatas(final Context pContext, final Class<T> pClazz, final String pField, final Object pValue,
			final String pOrder, final boolean pAsc, final long pLimit) {
		List<T> ret = null;
		SQLiteOpenHelper db = DatabaseHandler.getInstance(pContext);
		ConnectionSource connectionSource = new AndroidConnectionSource(db);
		Dao<T, Integer> dao = null;
		try {
			dao = (Dao<T, Integer>) DaoManager.createDao(connectionSource, pClazz);
			if (dao != null) {
				QueryBuilder<T, Integer> queryBuilder = dao.queryBuilder();

				if (pField != null) {
					if (pValue == null) {
						queryBuilder.where().isNull(pField);
					} else {
						queryBuilder.where().eq(pField, pValue);
					}
				}
				if (pLimit != 0) {
					queryBuilder.limit(pLimit);
				}
				if (pOrder != null) {
					queryBuilder.orderBy(pOrder, pAsc);
				}
				ret = queryBuilder.query();
			}
		} catch (SQLException e) {
			Log.e(TAG, "unable to filter data with fieldname", e);
		} finally {
			connectionSource.closeQuietly();
		}
		return ret;
	}

	/**
	 * Method to get the number of objects with the specified field
	 * 
	 * @param pContext
	 *            application context
	 * @param pClazz
	 *            bean class
	 * @param pField
	 *            filed to filter
	 * @param pValue
	 *            value to match
	 * @return the number of object to read
	 */
	@SuppressWarnings("unchecked")
	public static <T> long getNbElement(final Context pContext, final Class<T> pClazz, final String pField, final Object pValue) {
		long ret = 0;
		SQLiteOpenHelper db = DatabaseHandler.getInstance(pContext);
		ConnectionSource connectionSource = new AndroidConnectionSource(db);
		Dao<T, Integer> dao = null;
		try {
			dao = (Dao<T, Integer>) DaoManager.createDao(connectionSource, pClazz);
			if (dao != null) {
				QueryBuilder<T, Integer> queryBuilder = dao.queryBuilder();

				if (pField != null) {
					queryBuilder.where().eq(pField, pValue);
				}
				ret = dao.countOf(queryBuilder.setCountOf(true).prepare());
			}
		} catch (SQLException e) {
			Log.e(TAG, "Get the number of elements", e);
		} finally {
			connectionSource.closeQuietly();
		}
		return ret;
	}

	/**
	 * Method to store a list of data in the data base
	 * 
	 * @param pContext
	 *            application context
	 * @param pList
	 *            list of data to store
	 */
	@SuppressWarnings("unchecked")
	public static <T> void storeDatas(final Context pContext, final List<T> pList) {
		if (!pList.isEmpty()) {
			Class<?> type = pList.get(0).getClass();
			SQLiteOpenHelper db = DatabaseHandler.getInstance(pContext);
			ConnectionSource connectionSource = new AndroidConnectionSource(db);

			Dao<T, Integer> dao = null;
			try {
				dao = (Dao<T, Integer>) DaoManager.createDao(connectionSource, type);
				if (dao != null) {
					// TableUtils.clearTable(connectionSource, type);
					for (T object : pList) {
						dao.createOrUpdate(object);
					}
				}
			} catch (SQLException e) {
				Log.w(TAG, "unable to insert " + type, e);
			} finally {
				connectionSource.closeQuietly();

			}
		}
	}

	/**
	 * Method to store a single data
	 * 
	 * @param pContext
	 *            application context
	 * @param pData
	 *            the data to store
	 */
	@SuppressWarnings("unchecked")
	public static <T> void storeSingleData(final Context pContext, final T pData) {
		Class<?> type = pData.getClass();
		SQLiteOpenHelper db = DatabaseHandler.getInstance(pContext);
		ConnectionSource connectionSource = new AndroidConnectionSource(db);

		Dao<T, Integer> dao = null;
		try {
			dao = (Dao<T, Integer>) DaoManager.createDao(connectionSource, type);
			if (dao != null) {
				dao.createOrUpdate(pData);
			}
		} catch (SQLException e) {
			Log.w(TAG, "unable to insert single data" + type, e);
		} finally {
			connectionSource.closeQuietly();
		}
	}

	/**
	 * Method to update an object
	 * 
	 * @param pContext
	 *            the application context
	 * @param pData
	 *            bean to update
	 */
	@SuppressWarnings("unchecked")
	public static <T> void updateData(final Context pContext, final T pData) {
		if (pData != null) {
			Class<?> type = pData.getClass();
			SQLiteOpenHelper db = DatabaseHandler.getInstance(pContext);
			ConnectionSource connectionSource = new AndroidConnectionSource(db);

			Dao<T, Integer> dao = null;
			try {
				dao = (Dao<T, Integer>) DaoManager.createDao(connectionSource, type);
				if (dao != null) {
					dao.update(pData);
				}
			} catch (SQLException e) {
				Log.w(TAG, "unable to update " + type, e);
			} finally {
				connectionSource.closeQuietly();
			}
		}
	}

	/**
	 * private constructor
	 */
	private DaoUtils() {}

}
