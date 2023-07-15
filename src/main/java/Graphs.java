import java.util.*;


public class Graphs {
    public static void main(String[] args) {
        Deque<String> searchingList = new ArrayDeque<>();
        List<String> checked = new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();
        makeNode(map, "Tamara");
        fillQueue(searchingList, map, "Tamara");

        if (!searchLongName(searchingList, checked, map)) {
            System.out.println("К сожалению, совпадений не найдено");
        }
    }

    public static List<String> makeList() {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        System.out.println("Сколько друзей вы хотите добавить?");
        String answer = scanner.nextLine();
        int num = 0;
        try {
            num = Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            System.out.println("Вы вводите не цифры, а что-то другое");
        }
        while (num > 0) {
            System.out.println("Введите имя: ");
            String name = scanner.nextLine();
            list.add(name);
            num--;
        }
       return list;
    }

    public static void makeNode(Map<String, List<String>> map, String nodeName) {
        System.out.println("Добавим друзей " + nodeName);
        map.put(nodeName, makeList());
    }

    public static void fillQueue(Deque<String> searchingList, Map<String, List<String>> map, String nodeName) {
        for (String elem : map.get(nodeName)) {
            searchingList.addLast(elem);
        }
    }

    public static boolean longName(String name) {
        return name.length() > 8;
    }

    private static boolean searchLongName(Deque<String> searchingList, List<String> checked, Map<String, List<String>> map) {
        while (searchingList.peek() != null) {
            String name = searchingList.pollFirst();
            if (!checked.contains(name)) {
                if (longName(name)) {
                    System.out.println("Ура, мы нашли что искали! Это " + name);
                    return true;
                } else {
                    makeNode(map, name);
                    fillQueue(searchingList, map, name);
                    checked.add(name);
                }
            }
        }
        return false;
    }
}
