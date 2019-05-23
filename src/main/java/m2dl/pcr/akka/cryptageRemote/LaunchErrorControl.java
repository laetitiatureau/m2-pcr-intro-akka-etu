package m2dl.pcr.akka.cryptageRemote;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import m2dl.pcr.akka.cryptage.ErreurControleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaunchErrorControl {

    public static final Logger log = LoggerFactory.getLogger(LaunchCryptage.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("ErrorControllerSystem", ConfigFactory.load(("erreurcontrol")));
        final ActorRef erreurController = actorSystem.actorOf(Props.create(ErreurControleProvider.class), "erreur-controller");

        log.info("start Cryptage");
    }

}
