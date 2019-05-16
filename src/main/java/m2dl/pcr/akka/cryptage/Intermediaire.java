package m2dl.pcr.akka.cryptage;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Intermediaire extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef to;

    private ActorRef next;

    public Intermediaire(ActorRef toActor, ActorRef nextActor) {
        this.next = nextActor;
        this.to = toActor;
    }

    public void onReceive(Object o) throws Exception {
        if (o instanceof String) {
            MessageWithSender messageWithSender = new MessageWithSender(to, (String)o);
            next.tell(messageWithSender, null);
        } else {
            unhandled(o);
        }
    }
}
