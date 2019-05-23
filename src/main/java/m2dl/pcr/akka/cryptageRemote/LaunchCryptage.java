package m2dl.pcr.akka.cryptageRemote;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import m2dl.pcr.akka.cryptage.CryptageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaunchCryptage {

    public static final Logger log = LoggerFactory.getLogger(LaunchCryptage.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("CryptageSystem", ConfigFactory.load(("cryptage")));
        final ActorRef cryptageProvider = actorSystem.actorOf(Props.create(CryptageProvider.class), "cryptage");

        log.info("start Cryptage");
    }

}
