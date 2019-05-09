package m2dl.pcr.akka.cryptage;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import m2dl.pcr.akka.stringservices.StringUtils;

public class ErreurControleProvider extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public void onReceive(Object msg) throws Exception {
        if(msg instanceof MessageWithSender){
            MessageWithSender messageWithSender = (MessageWithSender) msg;
            log.info("receive message to add ctrl : " + messageWithSender.getMessage());
            String encryptedMessage = StringUtils.ajouteCtrl(messageWithSender.getMessage());
            messageWithSender.getTarget().tell(encryptedMessage, null);
        }else{
            unhandled(msg);
        }
    }
}
