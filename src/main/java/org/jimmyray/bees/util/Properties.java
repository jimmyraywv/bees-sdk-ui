package org.jimmyray.bees.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Properties exposes message bundle access to the rest of the application.
 * 
 * @author jimmyray
 * @version 1.0
 */
public final class Properties {

	private Properties() {
	}

	public static String getString(String bundleName, String key) {
		try {
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle(bundleName);
			return resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
