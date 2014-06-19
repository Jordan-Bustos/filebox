/**
 * 
 */
package fr.licpro.filebox.utils;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;

/**
 * @author jordanbustos
 *
 * Permet to save and get activity.
 */
public class ActivityContainer 
{
	/**
	 * The map who contains all the activities saved.
	 */
	private static Map<String, Activity> mActivities = new HashMap<String, Activity>(); 
	
	/**
	 * Permit to save an activity in the map.
	 * @param activityName The key of the activity in the map. 
	 * @param activity The activity.
	 */
	public static void putActivity(String activityName, Activity activity)
	{
		mActivities.put(activityName, activity);
	}
	
	/**
	 * Permit to get an activity saved.
	 * @param activityName the activity to get.
	 * @return an activity saved.
	 */
	public static Activity getActivity(String activityName)
	{
		return mActivities.get(activityName);
	}

}
