
package acme.forms;

import acme.entities.customParams.Configuration;

public class SpamCheck {

	public static Boolean checkSpam(final String text, final Configuration c) {
		Boolean res = false;

		String lowerCaseText = text.toLowerCase();
		String[] wordsOfTheText = lowerCaseText.replaceAll("\\p{P}", "").split(" ");
		Double numberOfWordsOfTheText = (double) lowerCaseText.replaceAll("\\p{P}", "").split(" ").length;

		String spamWords = c.getSpamWords().replace(",", "|");
		spamWords = ".*(" + spamWords + ").*";
		Double threshold = c.getSpamThreshold();
		Double spamWordCounter = 0.;

		for (String wr : wordsOfTheText) {
			if (wr.matches(spamWords)) {
				spamWordCounter++;
			}
		}

		Double spamRatio = spamWordCounter / numberOfWordsOfTheText * 100;
		res = spamRatio > threshold ? true : false;
		return res;
	}
}
