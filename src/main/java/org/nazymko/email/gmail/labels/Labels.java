package org.nazymko.email.gmail.labels;

import org.nazymko.GmailService;
import org.nazymko.UserImpl;

import javax.mail.*;
import java.io.IOException;
import java.util.Enumeration;

public class Labels {

    public static void main(String[] args) throws MessagingException, IOException {

        GmailService gmail = GmailService.getInstance();

        String user = "";
        String passw0rd = "";
        Session session = gmail.session(new UserImpl(user, passw0rd));

        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", -1, user, passw0rd);

        Folder folder = store.getDefaultFolder();

        folder = folder.getFolder("SMS");
        folder.open(Folder.READ_WRITE);

        int totalMessages = folder.getMessageCount();
        int newMessages = folder.getNewMessageCount();
        System.out.println("Total messages = " + totalMessages);
        System.out.println("New messages = " + newMessages);

        int messageCount = folder.getMessageCount();
        System.out.println("messageCount = " + messageCount);


//        Message[] messages = folder.getMessages(totalMessages - 1, totalMessages - 1);
//        for (Message message : messages) {
//            debug(message);
//        }

//        Message[] messages = folder.getMessages();
//        List<SmsMessage> msgList = new ArrayList<SmsMessage>();
//        for (Message message : messages) {
//            msgList.add(convert(message));
//        }

        System.out.println(convert(folder.getMessage(messageCount)));

    }

    private static SmsMessage convert(Message message) throws IOException, MessagingException {
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setBody(message.getContent().toString());
        smsMessage.setFrom(message.getHeader("X-smssync-address")[0]);
        smsMessage.setDate(message.getHeader("Date")[0]);
        smsMessage.setSubject(message.getSubject());
        return smsMessage;
    }

    private static void debug(Message message) throws MessagingException, IOException {
        System.out.println("DATA:");
        System.out.println("\t" + message.getContentType());
        System.out.println("\t" + message.getContent());
        System.out.println("\t" + message.getReceivedDate());
        System.out.println("\t" + message.getSubject());
        System.out.println("\t" + message.getMessageNumber());
        System.out.println(print(message.getAllHeaders()));

        System.out.println("ADDRESS:");
        for (Address address : message.getFrom()) {
            System.out.println("\tfrom: \t" + address);
        }
        System.out.println("--------------------");
        System.out.println("END OF SMS");
        System.out.println("--------------------");
    }


    private static String print(Enumeration<Header> allHeaders) {
        StringBuilder builder = new StringBuilder();

        while (allHeaders.hasMoreElements()) {
            Header header = allHeaders.nextElement();
            builder.append("\t\t").append(header.getName()).append("=").append(header.getValue()).append("\n");
        }

        return builder.toString();
    }
}
