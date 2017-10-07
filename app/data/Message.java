package data;

public class Message {
   public String text;
    public String time;
    public FeedResponse feedResponse;
    public enum Sender{
        BOT,USER
    };
    public Sender sender;


}
