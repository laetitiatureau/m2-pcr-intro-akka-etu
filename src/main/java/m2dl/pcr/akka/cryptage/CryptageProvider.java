package m2dl.pcr.akka.cryptage;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import m2dl.pcr.akka.stringservices.StringUtils;

public class CryptageProvider extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public void onReceive(Object o) throws Exception {
        if (o instanceof MessageWithSender) {
            MessageWithSender messageWithSender = (MessageWithSender)o;
            log.info("receive message to crypt : " + messageWithSender.getMessage());
            String encryptMessage = StringUtils.crypte(messageWithSender.getMessage());
            messageWithSender.getTarget().tell(encryptMessage, null);
        } else {
            unhandled(o);
        }
    }
}
