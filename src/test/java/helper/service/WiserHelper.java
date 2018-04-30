package helper.service;

import model.public_side.MailMessage;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import java.io.IOException;
import java.util.*;

public class WiserHelper {
    private Wiser wiser;

    //Constructor
    public WiserHelper() {
        wiser = new Wiser();
    }

    public void start() {
        wiser.start();
    }

    public void stop() {
        if (Objects.nonNull(wiser))
            wiser.stop();
    }

    public List<MailMessage> getMessages() throws MessagingException, IOException {
        List<MailMessage> messages = new ArrayList<>();
        for (WiserMessage mail : wiser.getMessages()) {
            String title = mail.getMimeMessage().getSubject();
            String from = mail.getEnvelopeSender();
            String to = mail.getEnvelopeReceiver();
            Date date = mail.getMimeMessage().getSentDate();
            String content = "";
            Object body = mail.getMimeMessage().getContent();
            if (body instanceof String)
                content = (String) body;
            else if (body instanceof Multipart) {
                Multipart multipart = (Multipart) body;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < multipart.getCount(); i++) {
                    if (multipart.getBodyPart(i).isMimeType("text/plain"))
                        content = sb.append((String) multipart.getBodyPart(i).getContent()).toString();
                }
            }
            messages.add(new MailMessage().setTitle(title)
                                          .setFrom(from)
                                          .setTo(to)
                                          .setReceivedDate(date)
                                          .setContent(content));
        }
        return messages;
    }
}
