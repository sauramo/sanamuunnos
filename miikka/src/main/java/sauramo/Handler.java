package sauramo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.lang.IllegalStateException;

public class Handler implements RequestStreamHandler {
  Gson gson = new GsonBuilder().setPrettyPrinting().create();
  App app = new App();

  @Override
  public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
    LambdaLogger logger = context.getLogger();
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
    PrintWriter writer = new PrintWriter(
        new BufferedWriter(new OutputStreamWriter(outputStream, Charset.forName("UTF-8"))));
    try {
      HashMap<?, ?> event = gson.fromJson(reader, HashMap.class);
      String input = event.get("body-json").toString();
      String result = app.transform(input);
      writer.write("\"" + result + "\"");
      if (writer.checkError()) {
        logger.log("WARNING: Writer encountered an error.");
      }
    } catch (IllegalStateException | JsonSyntaxException exception) {
      logger.log(exception.toString());
    } finally {
      reader.close();
      writer.close();
    }
  }
}