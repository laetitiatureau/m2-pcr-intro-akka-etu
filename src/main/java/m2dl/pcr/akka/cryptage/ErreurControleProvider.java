package m2dl.pcr.akka.cryptage;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import m2dl.pcr.akka.stringservices.StringUtils;

public class ErreurControleProvider extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public void onReceive(Object o) throws Exception {
        if(o instanceof MessageWithSender){
            MessageWithSender messageWithSender = (MessageWithSender) o;
            log.info("receive message to add ctrl : " + messageWithSender.getMessage());
            String encryptedMessage = StringUtils.ajouteCtrl(messageWithSender.getMessage());
            messageWithSender.getTarget().tell(encryptedMessage, null);
        }else{
            unhandled(o);
        }
    }
}
