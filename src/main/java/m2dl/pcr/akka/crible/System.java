package m2dl.pcr.akka.crible;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class System {

    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static final int N = 20;

    public static void main(String... args) throws Exception {
        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        Thread.sleep(5000);

        final ActorRef actorRef = actorSystem.actorOf(Props.create(SenderActor.class, N), "sender");

        Thread.sleep(10000);
        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }

}
