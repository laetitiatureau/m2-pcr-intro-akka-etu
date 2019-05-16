package m2dl.pcr.akka.cryptage;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Recepteur extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public void onReceive(Object o) throws Exception {
        if(o instanceof String){
            log.info("Receive : " + o);
        }else{
            unhandled(o);
        }
    }
}
