package pg.pt;

import java.io.Serializable;

public class Message implements Serializable {
    private int number;
    private String content;
    public Message(int _number, String _content) {
        number  = _number;
        content = _content;
    }
    public int getNumber() {
        return number;
    }
    public String getContent() {
        return content;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public String toString() {
        return String.format("Message nr %d: %s ", number, content);
    }
}