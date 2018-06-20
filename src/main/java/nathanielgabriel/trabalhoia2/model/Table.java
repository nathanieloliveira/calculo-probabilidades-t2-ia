package nathanielgabriel.trabalhoia2.model;

import java.util.*;

public class Table {

    private final Variable main;
    private final List<Variable> parents;
    private final List<HashMap<Variable, Boolean>> combinations;
    private final List<Double> probabilities;

    public Table(Variable main, Set<Variable> parents) {
        this.main = main;
        this.parents = new ArrayList<>(parents);
        this.combinations = generateCombinations();
        this.probabilities = new ArrayList<>(combinations.size());
    }

    private List<HashMap<Variable, Boolean>> generateCombinations() {
        int entries = 1 + parents.size();
        int size = (int) Math.pow(2, entries);
        List<HashMap<Variable, Boolean>> combinations = new ArrayList<>(size);
        List<Variable> parnts = new ArrayList<>(parents.size());
        parnts.addAll(parents);

        for (int i = 0; i < size; i++) {
            HashMap<Variable, Boolean> combination = new HashMap<>(entries);
            combination.put(main, getBit(i, 0));
            for (int j = 1; j < entries; j++) {
                combination.put(parnts.get(j - 1), getBit(i, j));
            }
            combinations.add(combination);
        }
        return combinations;
    }

    private boolean getBit(int val, int position)
    {
        return ((val >> position) & 1) == 1;
    }

    public Variable getMain() {
        return main;
    }

    public List<Variable> getParents() {
        return parents;
    }

    public List<HashMap<Variable, Boolean>> getCombinations() {
        return combinations;
    }

    public List<Double> getProbabilities() {
        return probabilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.equals(main, table.main) &&
                Objects.equals(parents, table.parents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(main, parents);
    }

    @Override
    public String toString() {
        return "Table{" +
                "main=" + main +
                ", parents=" + parents +
                '}';
    }
}
