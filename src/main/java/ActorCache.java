import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import java.util.HashMap;
import java.util.Map;

public class ActorCache extends AbstractActor {
    private final Map<String, Integer> store = new HashMap<>();
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(
                Message.class,
                message -> sender().tell(
                        store.getOrDefault(message.getUrl(), -1),
                        ActorRef.noSender()
                )
        ).match(
                MessageCache.class,
                messageCache -> store.put(messageCache.getUrl(), messageCache.getTime())
        ).build();
    }
}
