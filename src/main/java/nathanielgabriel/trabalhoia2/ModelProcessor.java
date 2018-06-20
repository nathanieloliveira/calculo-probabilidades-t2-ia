package nathanielgabriel.trabalhoia2;

import javafx.scene.control.Tab;
import nathanielgabriel.trabalhoia2.model.*;

import java.util.*;

public class ModelProcessor {

    public ModelProcessor() {

    }

    public List<Table> process(ProbModelXML model, Database database) {
        Set<Variable> varsFromModel = new HashSet<>(Arrays.asList(model.getProbNet().getVariables()));
        Set<Variable> varsFromDb = new HashSet<>(database.getVariables());

        if (!varsFromDb.containsAll(varsFromModel)) {
            throw new IllegalStateException("db does not contain all variables from model.");
        }

        List<Table> tables = generateTables(model);
        calculateProbabilities(tables, database);
        return tables;
    }

    private void calculateProbabilities(List<Table> tables, Database database) {
        for (Table table : tables) {
            initializeProbabilities(table, database);
        }
    }

    private void initializeProbabilities(Table table, Database database) {
        Variable main = table.getMain();
        List<Sample> samples = database.getSamples();
        List<HashMap<Variable, Boolean>> combinations = table.getCombinations();
        List<Variable> parents = table.getParents();

        for (int i = 0; i < combinations.size(); i++) {
            HashMap<Variable, Boolean> combination = combinations.get(i);

            int total = 0;
            int matches = 0;

            for (int j = 0; j < samples.size(); j++) {
                Sample sample = samples.get(j);
                Map<Variable, Boolean> values = sample.getValues();

                if (parents.size() == 0) {
                    // just count the main from samples
                    boolean value = combination.get(main);
                    boolean data = values.get(main);
                    if (value == data) {
                        matches += 1;
                    }
                } else {
                    // first search for parents matching
                    boolean allParentsMatch = true;
                    for (Variable parent : parents) {
                        boolean value = combination.get(parent);
                        boolean data = values.get(parent);
                        if (value != data) {
                            allParentsMatch = false;
                            break;
                        }
                    }

                    if (!allParentsMatch) {
                        // this sample is useless
                        continue;
                    }

                    // all parents matched
                    total += 1;

                    boolean value = combination.get(main);
                    boolean data = values.get(main);
                    if (value == data) {
                        matches += 1;
                    }
                }
            }

            double probability;
            if (parents.size() == 0) {
                probability = matches / ((double) samples.size());
            } else {
                probability = matches / (double) total;
            }
            if (Double.isNaN(probability)) {
                System.out.println("Warning: no combination " + combination + " was found on DB");
                probability = 0.5;
            }
            table.getProbabilities().add(probability);
        }
    }

    private List<Table> generateTables(ProbModelXML model) {
        Variable[] variables = model.getProbNet().getVariables();
        Link[] links = model.getProbNet().getLinks();
        List<Table> tables = new ArrayList<>();
        for (Variable variable : variables) {
            Set<Variable> parents = findParents(variable, links);
            tables.add(new Table(variable, parents));
        }
        return tables;
    }

    private Set<Variable> findParents(Variable var, Link[] links) {
        Set<Variable> parents = new HashSet<>();
        for (Link link : links) {
            if (link.getVariable()[1].equals(var)) {
                parents.add(link.getVariable()[0]);
            }
        }
        return parents;
    }

}
