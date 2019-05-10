package m2dl.pcr.akka.crible;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

import java.util.ArrayDeque;
import java.util.Queue;

public class NumberActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private Queue<Integer> queue = new ArrayDeque<Integer>();

    private int number;

    private ActorRef next;

    public NumberActor(int number) {
        log.info(number + " est un nombre premier");
        this.number = number;
    }

    private Procedure<Object> isLast = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Integer) {
                getSender().tell(new Ack(), getSelf());
                int nb = (Integer)msg;
                if(nb % number != 0){
                    next = getContext().actorOf(Props.create(NumberActor.class, nb), "actor-" + nb);
                    getContext().become(isNotLast,false);
                }
            } else {
                unhandled(msg);
            }
        }
    };

    private Procedure<Object> isNotLast = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Integer) {
                getSender().tell(new Ack(), getSelf());
                int nb = (Integer)msg;
                if(nb % number != 0){
                    next.tell(nb, getSelf());
                    getContext().become(waitForAck);
                }
            } else {
                unhandled(msg);
            }
        }
    };

    private Procedure<Object> waitForAck = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Integer) {
                getSender().tell(new Ack(), getSelf());
                int nb = (Integer)msg;
                if(nb % number != 0){
                    queue.add(nb);
                }
            } else if (msg instanceof Ack) {
                if(queue.isEmpty()){
                    getContext().become(isNotLast);
                }else {
                    int nb = queue.poll();
                    next.tell(nb, getSelf());
                }
            } else {
                unhandled(msg);
            }
        }
    };

    public void onReceive(Object o) throws Exception {
        isLast.apply(o);
    }
}
