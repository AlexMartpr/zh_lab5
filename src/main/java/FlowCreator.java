import akka.NotUsed;
import akka.actor.ActorRef;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;


public class FlowCreator {
    public FlowCreator() {};
    public Flow<HttpRequest, HttpResponse, NotUsed> createFlow(ActorMaterializer materializer, ActorRef cache) {
        return Flow.of(HttpRequest.class).map(
                
        );
        return null;
    };
}
