import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ActorApp {

    public static void main(String[] args) {
        System.out.println("start!");
        ActorSystem system = ActorSystem.create("Akka_Lab_5");
        ActorRef cache = system.actorOf(Props.create(Cache.class));

    }

}