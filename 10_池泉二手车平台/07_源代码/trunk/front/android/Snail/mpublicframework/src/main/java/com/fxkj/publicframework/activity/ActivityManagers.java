package com.fxkj.publicframework.activity;

import android.app.Activity;

public class ActivityManagers extends BaseActivityManager {

	private static ActivityManagers instance;

	public static ActivityManagers getInstance() {
		if (instance == null) {
			instance = new ActivityManagers();
		}
		return instance;
	}

	public ActivityManagers() {
		super();
	}

	/**
	 * 判断App是否活跃的
	 * 
	 * @return
	 */
	public boolean isAppActive() {
		for (Activity activity : activityStack) {
			if (((BaseTitleActivity) activity).isActive) {
				return true;
			}
		}
		return false;
	}
}