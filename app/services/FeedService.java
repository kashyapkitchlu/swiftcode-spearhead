package services;

import data.FeedResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import play.libs.ws.WS;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class FeedService {
    public FeedResponse getfeedresponse(String keyword) {

        FeedResponse feedresponseObject = new FeedResponse();
        try {

            WSRequest feedRequest = WS.url("http://nws.google.com/news");
            CompletionStage<WSResponse> responsePromise = feedRequest
                    .setQueryParameter("q",keyword)
                    .setQueryParameter("output","rss")
                    .get();
            Document feedResponse = responsePromise.thenApply(WSResponse::asXml).toCompletableFuture().get();
            Node item = feedResponse.getFirstChild().getFirstChild().getChildNodes().item(9);
            feedresponseObject.title = item.getChildNodes().item(0).getFirstChild().getNodeValue();
            feedresponseObject.pubdate= item.getChildNodes().item(3).getFirstChild().getNodeValue();
            feedresponseObject.desciption=item.getChildNodes().item(4).getFirstChild().getNodeValue();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return feedresponseObject;

}
}