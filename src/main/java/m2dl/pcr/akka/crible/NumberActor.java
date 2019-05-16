package m2dl.pcr.akka.crible;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

public class NumberActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private int nombre;

    private ActorRef next;

    public NumberActor(int nombre) {
        log.info(nombre + " est un entier");
        this.nombre = nombre;
    }

    Procedure<Object> isLast = new Procedure<Object>() {
        public void apply(Object o) throws Exception {
            if (o instanceof Integer) {
                int nb = (Integer)o;
                if (nb % nombre != 0) {
                    next = getContext().actorOf(Props.create(NumberActor.class, nb), "actor-" + nb);
                    getContext().become(isNotLast, false);
                }
            } else {
                unhandled(o);
            }
        }
    };

    Procedure<Object> isNotLast = new Procedure<Object>() {
        public void apply(Object o) throws Exception {
            if (o instanceof Integer) {
                int nb = (Integer)o;
                if (nb % nombre != 0) {
                    next.tell(nb, null);
                }
            } else {
                unhandled(o);
            }
        }
    };

    public void onReceive(Object o) throws Exception {
        isLast.apply(o);
    }
}
