package nathanielgabriel.trabalhoia2.model.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import nathanielgabriel.trabalhoia2.model.Link;
import nathanielgabriel.trabalhoia2.model.Variable;

import java.io.IOException;

public class LinkDeserializer extends StdDeserializer<Link> {
    public LinkDeserializer() {
        super(Link.class);
    }

    protected LinkDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected LinkDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public Link deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Link link = new Link();
        TreeNode node = p.getCodec().readTree(p);
        JsonToken firstToken = p.getCurrentToken();

        Variable var1 = new Variable();
        Variable var2 = new Variable();

        boolean processingDirected = false;
        boolean processingVar1 = false;
        boolean processingVar2 = false;

        boolean directed = false;

        JsonToken token = p.nextToken();
        while (token != null) {
            token = p.nextToken();
            String aadw = p.getText();
            System.out.println(aadw);
            switch (token) {
                case FIELD_NAME:
                    String s = p.getText();
                    if (s.compareTo("directed") == 0) {
                        processingDirected = true;
                    }
                    break;

                case VALUE_STRING:
                    if (processingDirected) {
                        directed = Boolean.valueOf(p.getText());
                    } else if (processingVar1) {
                        var1.setName(p.getText());
                    } else if (processingVar2) {
                        var2.setName(p.getText());
                    }

                    break;

                case START_OBJECT:
                    if (!processingVar1) {
                        processingDirected = false;
                        processingVar1 = true;
                    } else if (!processingVar2) {
                        processingVar1 = false;
                        processingVar2 = true;
                    }
                    break;

                case END_OBJECT:
                    if (processingVar2) {
                        token = null;
                        break;
                    }
            }
        }
        JsonToken lastToken = p.nextToken();
        p.clearCurrentToken();
//        TreeNode node = p.getCodec().readTree(p);
        link.setVariable(new Variable[]{var1, var2});
        return link;
    }
}
