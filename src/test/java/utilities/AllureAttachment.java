package utilities;

import io.qameta.allure.Attachment;

public class AllureAttachment {
	@Attachment(value = "{0}", type = "text/plain", fileExtension = ".txt")
	public static String attachText(String message) {
		return message;
	}
}
