import akka.NotUsed;
import akka.actor.ActorRef;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import akka.japi.Pair;


public class FlowCreator {
    private static final String URL = "testUrl";
    private static final String COUNT = "count";
    private static final int DURATION = 5;
    private static final int NUMBER = 10;

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
        ).mapAsync(NUMBER, req -> {
            Patterns.ask(cache, new Message(req.first()), Duration.ofSeconds(DURATION)).thenCompose(
                    res -> {
                        if ((int)res >= 0) {
                            return CompletableFuture.completedFuture(new Pair<>(req.first(), (int)res));
                        } else {

                        }
                    }
            ) {

                    }
            )
        })
    }
}
