package nathanielgabriel.trabalhoia2;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import nathanielgabriel.trabalhoia2.model.*;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        JacksonXmlModule xmlModule = new JacksonXmlModule();
//        xmlModule.addDeserializer(Link.class, new LinkDeserializer());
        XmlMapper mapper = new XmlMapper(xmlModule);
//        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        File networkFile = preProcessNetworkFile(new File(args[0]));
        Database database = generateDatabase(new File(args[1]));

        ProbModelXML model = mapper.readValue(networkFile, ProbModelXML.class);
        ModelProcessor processor = new ModelProcessor();
        List<Table> tables = processor.process(model, database);

        File exportFile = new File(args[2]);
        if (exportFile.exists()) exportFile.delete();
        exportProcessedModel(model, tables, exportFile, mapper);
    }

    private static void exportProcessedModel(ProbModelXML model, List<Table> tables, File file, XmlMapper mapper) throws IOException {
        Potential[] potentials = new Potential[tables.size()];
        for (int i = 0; i < potentials.length; i++) {
            Table table = tables.get(i);
            potentials[i] = new Potential();
            potentials[i].setValues(table);
        }
        model.getProbNet().setPotentials(potentials);
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, tables);
    }

    public static Database generateDatabase(File dataFile) throws IOException {
        if (!dataFile.getName().endsWith(".csv")) {
            throw new IOException("not a csv");
        }
        BufferedSource src = Okio.buffer(Okio.source(dataFile));

        // read vars from first line
        String firstLine = src.readUtf8Line();
        if (firstLine == null) throw new IOException("empty file");

        String[] vars = firstLine.split(",");
        List<Variable> variables = new ArrayList<>(vars.length);
        for (String var : vars) {
            Variable v = new Variable();
            v.setName(var);
            variables.add(v);
        }

        List<Sample> samples = new ArrayList<>();
        while (!src.exhausted()) {
            String l = src.readUtf8Line();
            if (l == null) throw new IOException("eof");

            HashMap<Variable, Boolean> map = new HashMap<>(variables.size());
            String[] data = l.split(",");
            for (int i = 0; i < variables.size(); i++) {
                Variable var = variables.get(i);
                String value = data[i];
                map.put(var, decodeValue(value));
            }
            samples.add(new Sample(map));
        }

        src.close();
        return new Database(variables, samples);
    }

    public static boolean decodeValue(String value) throws IOException {
        if (value.equalsIgnoreCase("present")) return true;
        if (value.equalsIgnoreCase("positive")) return true;
        if (value.equalsIgnoreCase("yes")) return true;

        if (value.equalsIgnoreCase("absent")) return false;
        if (value.equalsIgnoreCase("negative")) return false;
        if (value.equalsIgnoreCase("no")) return false;

        throw new IOException("could not decode value");
    }

    public static File preProcessNetworkFile(File networkFile) throws IOException {
        File outFile = new File(networkFile.getParentFile(), networkFile.getName() + ".temp");
        if (outFile.exists()) {
            outFile.delete();
        }
//        outFile.deleteOnExit();

        BufferedSource src = Okio.buffer(Okio.source(networkFile));
        BufferedSink out = Okio.buffer(Okio.sink(outFile));

        // copy first line
        out.write(ByteString.encodeUtf8(src.readUtf8Line()));
        while (!src.exhausted()) {
            String line = src.readUtf8Line();
            if (line == null) break;

            // write </Variables> before /Link
            if (line.contains("</Link>")) {
                out.write(ByteString.encodeUtf8("\n"));
                out.write(ByteString.encodeUtf8("</Variables>"));
            }

            // copy line
            out.write(ByteString.encodeUtf8("\n"));
            out.write(ByteString.encodeUtf8(line));

            // write <Variables> after Link
            if (line.contains("<Link direc")) {
                out.write(ByteString.encodeUtf8("\n"));
                out.write(ByteString.encodeUtf8("<Variables>"));
            }
        }

        src.close();
        out.close();

        return outFile;
    }

}
