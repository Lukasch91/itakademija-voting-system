package _utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class EmailReader {
	static Date currDate = new Date();

	/**
	 * Use this to get password sent from system's email. Thread.sleep is not
	 * needed here.
	 * 
	 * @param user
	 * @param pass
	 * @return
	 */
	public static String returnPassFromEmail(String user, String pass) {
		String password = "";
		int i = 0;
		while (password.equals("") && i != 41) {
			i++;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			password = getPass(user, pass);
		}

		return password;
	}

	/**
	 * Use this to get password sent from system's email. Thread.sleep is needed
	 * here, 60000 mili seconds.
	 * 
	 * @param user
	 * @param pass
	 * @return
	 */
	public static String getPass(String user, String pass) {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		String emailMessage = null;
		try {
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", user, pass);

			// IMAP host for yahoo.
			// store.connect("imap.mail.yahoo.com", "<username>", "<password>");
			// System.out.println(store);

			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_WRITE);

			BufferedReader optionReader = new BufferedReader(new InputStreamReader(System.in));

			try {
				emailMessage = showAllMails(inbox);
				optionReader.close();
			} catch (IOException e) {
				System.out.println(e);
			}
			inbox.close(true);
		} catch (NoSuchProviderException e) {
			System.out.println(e.toString());
			System.exit(1);
		} catch (MessagingException e) {
			System.out.println(e.toString());
			System.exit(2);
		}

		String password = parsePassFromEmail(emailMessage);
		return password;

	}

	private static String parsePassFromEmail(String emailMessage) {
		if (!emailMessage.equals("")) {
			String[] lines = emailMessage.split("\\n+");
			String[] words = lines[5].split("\\s+");
			return words[6];
		} else {
			return "";
		}
	}

	static public String showAllMails(Folder inbox) {
		String asdEmail = "";
		try {
			Message msg[] = inbox.getMessages();
			for (Message message : msg) {
				try {
					if (message.getFrom()[0].toString().equals("dikis_mikis@tavo_peidzas.com")) {

						asdEmail = message.getContent().toString();
					}
					message.setFlag(Flag.DELETED, true);
				} catch (Exception e) {
					System.out.println("No Information");
				}
			}
		} catch (MessagingException e) {
			System.out.println(e.toString());
		}
		return asdEmail;
	}

}
