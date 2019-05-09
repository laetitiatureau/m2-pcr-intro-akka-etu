package m2dl.pcr.akka.cryptage;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Recepteur extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public void onReceive(Object msg) throws Exception {
        if(msg instanceof String){
            log.info("Receive : " + msg);
        }else{
            unhandled(msg);
        }
    }
}