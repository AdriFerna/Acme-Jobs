
package acme.forms;

import org.apache.commons.lang3.StringUtils;

import acme.entities.customParams.Configuration;

public class SpamCheck {

	public static Boolean checkSpam(final String text, final Configuration c) {
		Boolean res = false;

		String lowerCaseText = text.toLowerCase();
		String wordsOfTheText = lowerCaseText.replaceAll("\\p{P}", "").replaceAll("\r\n", " ").trim();
		Double numberOfWordsOfTheText = (double) wordsOfTheText.split(" ").length;

		String[] spamWords = c.getSpamWords().toLowerCase().trim().split(",");
		Double threshold = c.getSpamThreshold();
		Double spamWordCounter = 0.;

		for (String e : spamWords) {
			spamWordCounter = spamWordCounter + StringUtils.countMatches(wordsOfTheText, e);
		}

		Double spamRatio = spamWordCounter / numberOfWordsOfTheText * 100;
		res = spamRatio >= threshold ? true : false;
		return res;
	}
}
