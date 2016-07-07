package org.nazymko.email.gmail.labels;

/**
 * Created by nazymko.patronus@gmail.com
 */
public class SmsMessage {
    private String body;
    private String from;
    private String subject;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SmsMessage{");
        sb.append("body='").append(body).append('\'');
        sb.append(", from='").append(from).append('\'');
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
