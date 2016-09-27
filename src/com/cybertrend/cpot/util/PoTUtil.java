package com.cybertrend.cpot.util;

public class PoTUtil {

	public static String convertUrlIntoAction(String input) {
		String output = null;

		String[] paths = input.split("/");

		for (String item : paths) {
			// System.out.println(""+item);

			if (item.contains(".cbi")) {

				output = item;
			} else {
				output = "";
			}

		}

		return output;
	}

}
