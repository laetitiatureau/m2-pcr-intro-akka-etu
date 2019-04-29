package m2dl.pcr.akka.helloworld4;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class HelloGoodbyeChildActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private String message;

    public HelloGoodbyeChildActor(String message) {
        this.message = message;
    }

    public void onReceive(Object msg) throws Exception {
        if (msg instanceof String) {
            log.info(message + " " + msg + "!");
        } else {
            unhandled(msg);
        }
    }

}
