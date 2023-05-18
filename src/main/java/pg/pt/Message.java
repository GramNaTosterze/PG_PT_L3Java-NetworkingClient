package pg.pt;

import java.io.Serializable;

public class Message implements Serializable {
    final private int number;
    final private String content;
    public Message(int _number, String _content) {
        number  = _number;
        content = _content;
    }
    @Override
    public String toString() {
        return String.format("Message nr %d: %s ", number, content);
    }
}