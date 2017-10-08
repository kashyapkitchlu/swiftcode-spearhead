package controllers;

import actors.MessageActor;
import play.mvc.Controller;
import play.mvc.LegacyWebSocket;
import play.mvc.Result;
import play.mvc.WebSocket;

public class HomeController extends Controller {
    public Result index() {

        return ok(views.html.index.render());
    }

    public LegacyWebSocket<String> chatSocket() {
        return WebSocket.withActor(MessageActor::props);
    }
}