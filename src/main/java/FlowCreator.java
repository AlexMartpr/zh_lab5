import akka.NotUsed;
import akka.actor.ActorRef;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;


public class FlowCreator {
    private static final String URL = "testUrl";
    private static final String COUNT = "count";

    public FlowCreator() {};
    public Flow<HttpRequest, HttpResponse, NotUsed> createFlow(ActorMaterializer materializer, ActorRef cache) {
        return Flow.of(HttpRequest.class).map(
          req -> {
                Query query = req.getUri().query();
                String url;
                if (query.get(URL).isPresent()) {
                    url = query.get(URL).get();
                } else {
                    throw new Exception("No URL parameter");
                }
                
              return null;
          }
        );
    }
}
