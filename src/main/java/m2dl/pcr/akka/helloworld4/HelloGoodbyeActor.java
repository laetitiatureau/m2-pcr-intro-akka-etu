package m2dl.pcr.akka.helloworld4;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

public class HelloGoodbyeActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef helloActor;

    private ActorRef goodByeActor;

    public HelloGoodbyeActor() {
        log.info("HelloGoodbyeActor constructor");
        helloActor = getContext().actorOf(Props.create(HelloGoodbyeChildActor.class, "Hello"), "hello-actor");
        goodByeActor = getContext().actorOf(Props.create(HelloGoodbyeChildActor.class, "Goodbye"), "goodbye-actor");
    }

    private Procedure<Object> hello = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                helloActor.tell(msg, null);
                getContext().become(goodbye,false);
            } else {
                unhandled(msg);
            }
        }
    };

    private Procedure<Object> goodbye = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                goodByeActor.tell(msg, null);
                getContext().unbecome();
            } else {
                unhandled(msg);
            }
        }
    };

    public void onReceive(Object o) throws Exception {
        hello.apply(o);
    }

}
