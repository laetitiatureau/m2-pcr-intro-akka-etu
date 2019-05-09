package m2dl.pcr.akka.cryptage;

import akka.actor.ActorRef;

import java.io.Serializable;

public class MessageWithSender implements Serializable {

    private ActorRef target;

    private String message;

    public MessageWithSender(ActorRef target, String message) {
        this.target = target;
        this.message = message;
    }

    public ActorRef getTarget() {
        return target;
    }

    public void setTarget(ActorRef target) {
        this.target = target;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
