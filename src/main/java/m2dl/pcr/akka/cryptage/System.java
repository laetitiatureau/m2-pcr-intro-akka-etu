package m2dl.pcr.akka.cryptage;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class System {

    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        Thread.sleep(5000);

        final ActorRef recepteur = actorSystem.actorOf(Props.create(Recepteur.class), "recepteur-actor");
        final ActorRef cryptageProvider = actorSystem.actorOf(Props.create(CryptageProvider.class), "cryptageProvider-actor");
        final ActorRef erreurControleProvider = actorSystem.actorOf(Props.create(ErreurControleProvider.class), "erreurControle-actor");
        final ActorRef intermediaire = actorSystem.actorOf(Props.create(Intermediaire.class, recepteur, erreurControleProvider), "intermediaire-actor");

        //Scenario 1
        MessageWithSender messageWithSender1 = new MessageWithSender(recepteur, "Message 1" );
        cryptageProvider.tell(messageWithSender1, null);

        //Scenario 2
        MessageWithSender messageWithSender2 = new MessageWithSender(recepteur, "Message 2" );
        erreurControleProvider.tell(messageWithSender2, null);

        //Scenario 3
        MessageWithSender messageWithSender3 = new MessageWithSender(intermediaire, "Message 3" );
        cryptageProvider.tell(messageWithSender3, null);

        Thread.sleep(1000);

        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }

}
