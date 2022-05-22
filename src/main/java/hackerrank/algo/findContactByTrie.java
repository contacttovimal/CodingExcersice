package hackerrank.algo;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class findContactByTrie {
    static Map<String, String> contactMap = new HashMap<>();

    /*
     * Complete the contacts function below.
     */
    static int[] contacts(String[][] queries) {
        /*
         * Write your code here.
         */
        List<Integer> resultList = new ArrayList<>();
        List<String[]> addQuery = Arrays.stream(queries).filter(query->query[0].startsWith("add")).collect(Collectors.toList());
        List<String[]> findQuery = Arrays.stream(queries).filter(query->query[0].startsWith("find")).collect(Collectors.toList());

        addQuery.forEach(addQry->contactMap.put(addQry[1],addQry[1]));
        findQuery.forEach(findQ->resultList.add(contactMap.keySet().stream().filter(key -> key.contains(findQ[1])).collect(Collectors.toSet()).size()));
        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            String operation = scan.next();
            String contact   = scan.next();
            if (operation.equals("add")) {
                trie.add(contact);
            } else if (operation.equals("find")) {
                System.out.println(trie.find(contact));
            }
        }
        scan.close();
    }

    static class TrieNode {
        private HashMap<Character, TrieNode> children = new HashMap<>();
        public int size = 0; // this was the main trick to decrease runtime to pass tests.

        public void putChildIfAbsent(char ch) {
            children.putIfAbsent(ch, new TrieNode());
        }

        public TrieNode getChild(char ch) {
            return children.get(ch);
        }
    }

    static class Trie {
        TrieNode root = new TrieNode();

        public void add(String str) {
            TrieNode curr = root;
            for (char ch : str.toCharArray()) {
                curr.putChildIfAbsent(ch);
                curr = curr.getChild(ch);
                curr.size++;
            }
        }

        public int find(String prefix) {
            TrieNode curr = root;

            for (char ch : prefix.toCharArray()) {
                curr = curr.getChild(ch);
                if (curr == null) {
                    return 0;
                }
            }
            return curr.size;
        }
    }
}
