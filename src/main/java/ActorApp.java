import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

public class ActorApp {

    public static void main(String[] args) {
        System.out.println("start!");
        ActorSystem system = ActorSystem.create("Akka_Lab_5");
        ActorRef cache = system.actorOf(Props.create(Cache.class));

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        FlowCreator flowCreator = new FlowCreator();
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = flowCreator.createFlow();

    }

}