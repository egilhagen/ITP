package calendar.json.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import java.time.LocalDate;

import calendar.core.Event;


public class EventDeserializer extends JsonDeserializer<Event> {
    @Override
    public Event deserialize(JsonParser parser, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
      TreeNode treeNode = parser.getCodec().readTree(parser);
      return deserialize((JsonNode) treeNode);
    }
  
    Event deserialize(JsonNode jsonNode) {
        Event item = new Event("jsonHeader", "descriptionHeader", LocalDate.now());
        JsonNode headerNode = jsonNode.get("header");
        if (headerNode instanceof TextNode) {
          item.setHeader(headerNode.asText());
        }
        JsonNode descriptionNode = jsonNode.get("description");
        if (descriptionNode instanceof TextNode) {
          item.setDescription(descriptionNode.asText());
        }
        JsonNode dateNode = jsonNode.get("date");
        if (dateNode instanceof TextNode) {
          item.setDate(LocalDate.parse(dateNode.asText()));
        }
        return item;

    } 
}
