import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graphs2 {
    public static void main(String[] args) {
        Map<String, Integer> startMap = new HashMap<>();
        startMap.put("A", 6);
        startMap.put("B", 2);

        Map<String, Integer> AMap = new HashMap<>();
        AMap.put("fin", 1);

        Map<String, Integer> BMap = new HashMap<>();
        BMap.put("A", 3);
        BMap.put("fin", 5);

        Map<String, Integer> finMap = new HashMap<>();

        Map<String, Map<String, Integer>> fullMap = new HashMap<>();
        fullMap.put("start", startMap);
        fullMap.put("A", AMap);
        fullMap.put("B", BMap);
        fullMap.put("fin", finMap);

        Map<String, Integer> costs = new HashMap<>();
        costs.put("A", 6);
        costs.put("B", 2);
        costs.put("fin", Integer.MAX_VALUE);

        Map<String, String> parents = new HashMap<>();
        parents.put("A", "start");
        parents.put("B", "start");
        parents.put("fin", null);

        List<String> processed = new ArrayList<>();

        String node = findLowestCostNode(costs, processed);
        while (!node.equals("")) {
            System.out.println(node);
            int cost = costs.get(node);
            Map<String, Integer> neighbors = new HashMap<>(fullMap.get(node));
            for(String n : neighbors.keySet()) {
                int newCost = cost + neighbors.get(n);
                if (costs.get(n) > newCost) {
                    costs.put(n, newCost);
                    parents.put(n, node);
                }
            }
            processed.add(node);
            System.out.println(costs.get("fin"));
            node = findLowestCostNode(costs, processed);
        }
    }

    public static String findLowestCostNode(Map<String, Integer> costs, List<String> processed) {
        int lowestCost = Integer.MAX_VALUE;
        String lowestCostNode = "";
        for (Map.Entry<String, Integer> kv : costs.entrySet()) {
            if (kv.getValue() < lowestCost && !processed.contains(kv.getKey())) {
                lowestCost = kv.getValue();
                lowestCostNode = kv.getKey();
            }
        }
        return lowestCostNode;
    }
}
