package Util;

import javax.mail.*;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import java.io.IOException;
import java.util.Properties;

public class EmailConfirmation {

    public static boolean isConfirmationEmailReceived(String email, String password) throws MessagingException, IOException {
        System.out.println(email + "  "+ password);
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", "imap.gmail.com");
        properties.put("mail.imaps.port", "993");
        properties.put("mail.imaps.ssl.enable", "true");
        properties.put("mail.imaps.ssl.trust", "*");  // This bypasses the SSL trust issue
        properties.put("mail.imaps.ssl.protocols", "TLSv1.2");  // Force use of TLSv1.2

        Session session = Session.getDefaultInstance(properties, null);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", email, password);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        // Search for emails with the subject "Congratulations"
        SearchTerm subjectTerm = new SubjectTerm("Congratulations");
        Message[] messages = inbox.search(subjectTerm);

        boolean emailReceived = false;

        for (Message message : messages) {
            Address[] fromAddresses = message.getFrom();
            String senderEmail = fromAddresses[0].toString();

            if (senderEmail.contains("roadtocareer.net")) {
                // If necessary, fetch the email content and check specific keywords in the body
                String emailContent = message.getContent().toString();
                if (emailContent.contains("Welcome to our platform!")) {  // Adjust based on expected content
                    emailReceived = true;
                    break;
                }
            }
        }

        // Close the inbox and store
        inbox.close(false);
        store.close();

        return emailReceived;
    }
}
