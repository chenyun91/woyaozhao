package org.china.xzb.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.Set;

public class PreferenceUtil {

	public static void save(Context context, String key, boolean value) {
		SharedPreferences preferences = getSharePre(context);
		Editor editor = preferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public static void save(Context context, String key, Float value) {
		SharedPreferences preferences = getSharePre(context);
		Editor editor = preferences.edit();
		editor.putFloat(key, value);
		editor.commit();
	}

	public static void save(Context context, String key, long value) {
		SharedPreferences preferences = getSharePre(context);
		Editor editor = preferences.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public static void save(Context context, String key, int value) {
		SharedPreferences preferences = getSharePre(context);
		Editor editor = preferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public static void save(Context context, String key, String value) {
		SharedPreferences preferences = getSharePre(context);
		Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static boolean save(Context context, String key, Set<String> value) {
		SharedPreferences preferences = getSharePre(context);
		Editor editor = preferences.edit();
		editor.putStringSet(key, value);
		return editor.commit();
	}

	public static float getFloat(Context context, String key, float defValue) {
		SharedPreferences preferences = getSharePre(context);
		return preferences.getFloat(key, defValue);
	}

	public static Set<String> getStringSet(Context context, String key) {
		SharedPreferences preferences = getSharePre(context);
		return preferences.getStringSet(key, null);
	}

	/**
	 * "id" "username" "nickName" "sex" "token" "brith" "mobile"
	 * "province" "city" "area" "remark" "userPhoto"
	 */
	public static String getString(Context context, String param) {
		SharedPreferences preferences = getSharePre(context);
		return preferences.getString(param, "");
	}

	/**
	 *"isPush"是否开启推送消息
	 * 
	 * @return 默认false
	 */
	public static Boolean getBoolean(Context context, String param) {
		SharedPreferences preferences = getSharePre(context);
		if (param.equals("isPush") || param.equals("isgpsopen")) {
			return preferences.getBoolean(param, true);
		} else {
			return preferences.getBoolean(param, false);
		}
	}

	public static void clearUserInfo(Context context) {
		SharedPreferences preferences = getSharePre(context);
		boolean isGuideShow = preferences.getBoolean("isGuideShow",false);
		boolean isgpsopen = preferences.getBoolean("isgpsopen",false);
		Editor editor = preferences.edit();
		/*editor.putString("id", "");
		editor.putString("username", "");
		editor.putString("nickName", "");
		editor.putString("sex", "");
		editor.putString("token", "");
		editor.putString("brith", "");
		editor.putString("mobile", "");
		editor.putString("provinceId", "");
		editor.putString("cityId", "");
		editor.putString("areaId", "");
		editor.putString("remark", "");
		editor.putString("userPhoto", "");
		editor.putString("province", "");
		editor.putString("city", "");
		editor.putString("area", "");*/
		editor.clear();
		editor.putBoolean("isGuideShow",isGuideShow);
		editor.putBoolean("isgpsopen",isgpsopen);
		editor.commit();
	}

	public static boolean isLogin(Context context) {
		SharedPreferences preferences = getSharePre(context);
		String userId = preferences.getString("id", "");
		String username = preferences.getString("username", "");
		String token = preferences.getString("token", "");
//		AppUtils.log("userId----"+userId+"-------username-------"+username+"-------token-------"+token);
		if (TextUtils.isEmpty(token) || TextUtils.isEmpty(userId)
				|| TextUtils.isEmpty(username)) {
			return false;
		} else {
			return true;
		}
	}

	public static SharedPreferences getSharePre(Context context) {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		return preferences;
	}

}
