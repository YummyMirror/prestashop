package helper.service;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import java.util.List;

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
        if (wiser != null)
            wiser.stop();
    }

    public String getSender() {
        return wiser.getMessages().stream().findFirst().get().getEnvelopeSender();
    }

    public String getReceiver() {
        return wiser.getMessages().stream().findFirst().get().getEnvelopeReceiver();
    }

    public List<WiserMessage> getMessages() {
        return wiser.getMessages();
    }
}
