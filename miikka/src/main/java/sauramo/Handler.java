package sauramo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

// Handler value: example.Handler
public class Handler implements RequestHandler<Map<String,String>, String>{
  Gson gson = new GsonBuilder().setPrettyPrinting().create();
  @Override
  public String handleRequest(Map<String,String> event, Context context)
  {
    LambdaLogger logger = context.getLogger();
    String response = new String("200 OK");
    // log execution details
    //logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
    //logger.log("CONTEXT: " + gson.toJson(context));
    // process event
    //logger.log("EVENT: " + gson.toJson(event));
    //Gson gson = new Gson();
    String json = gson.toJson(event,LinkedHashMap.class);
    for (Entry<String, String> entry : event.entrySet()) {
      logger.log("ENTRY: " + entry);
    }
    logger.log("EVENT TYPE: " + event.getClass().toString());
    //logger.log("JSON: " + json);

    return "ASDASD";
  }
}