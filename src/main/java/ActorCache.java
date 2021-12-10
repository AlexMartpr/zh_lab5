import akka.actor.AbstractActor;

import java.util.HashMap;
import java.util.Map;

public class ActorCache extends AbstractActor {
    private Map<String, Long> store = new HashMap<>();
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(
                Message.class,
                message -> store.put()
        )
    }
}
