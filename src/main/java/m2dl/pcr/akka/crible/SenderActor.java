package m2dl.pcr.akka.crible;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

public class SenderActor extends UntypedActor {

    private int number = 3;

    private ActorRef next;

    private int max;

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public SenderActor(int max) {
        this.max = max;
        next = getContext().actorOf(Props.create(NumberActor.class, 2), "actor-2");
        sendToNext();
    }

    private Procedure<Object> send = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if(msg instanceof Ack){
                sendToNext();
            }else {
                unhandled(msg);
            }
        }
    };

    @Override
    public void onReceive(Object o) throws Exception {
        send.apply(o);
    }

    private void sendToNext(){
        if(number < max){
            next.tell(number, getSelf());
            number++;
        }
    }
}
