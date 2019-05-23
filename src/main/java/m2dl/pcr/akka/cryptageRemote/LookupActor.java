package m2dl.pcr.akka.cryptageRemote;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;

public class LookupActor extends UntypedActor {

    private final String path;
    private ActorSelection actor = null;

    public LookupActor(String path) {
        this.path = path;
        actor = getContext().actorSelection(path);
    }

    public void onReceive(Object o) throws Exception {
        actor.tell(o, getSender());
    }
}
