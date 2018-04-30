package model.public_side;

import java.util.Date;

public class MailMessage {
    private String title;
    private String from;
    private String to;
    private Date receivedDate;
    private String content;

    //Getters
    public String getTitle() {
        return title;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public String getContent() {
        return content;
    }

    //Setters
    public MailMessage setTitle(String title) {
        this.title = title;
        return this;
    }

    public MailMessage setFrom(String from) {
        this.from = from;
        return this;
    }

    public MailMessage setTo(String to) {
        this.to = to;
        return this;
    }

    public MailMessage setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
        return this;
    }

    public MailMessage setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        return "MailMessage{" +
                "title='" + title + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
