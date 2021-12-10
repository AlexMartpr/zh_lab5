import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ActorApp {

    public static void main(String[] args) {
        System.out.println("start!");
        ActorSystem system = ActorSystem.create("routes");
        ActorRef router = system.actorOf(Props.create(Router.class));

    }

}