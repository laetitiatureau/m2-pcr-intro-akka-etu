package m2dl.pcr.akka.cryptage;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Hatch extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef to;

    private ActorRef next;

    public Hatch(ActorRef to, ActorRef next) {
        this.to = to;
        this.next = next;
    }

    public void onReceive(Object msg) throws Exception {
        if(msg instanceof String){
            MessageWithSender messageWithSender = new MessageWithSender(to, (String)msg);
            next.tell(messageWithSender, null);
        }else{
            unhandled(msg);
        }
    }
}
