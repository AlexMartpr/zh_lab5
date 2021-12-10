import akka.NotUsed;
import akka.actor.ActorRef;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import javafx.util.Pair;


public class FlowCreator {
    private static final String URL = "testUrl";
    private static final String COUNT = "count";
    private static final String 

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
                int count;
                try {
                    if (query.get(COUNT).isPresent()) {
                        count = Integer.parseInt(query.get(COUNT).get());
                        return new Pair<>(url, count);
                    } else {
                        throw new Exception("No count parameter");
                    }
                } catch (NumberFormatException e) {
                    throw new Exception("Can not parse value of count");
                }
          }
        ).mapAsync()
    }
}
