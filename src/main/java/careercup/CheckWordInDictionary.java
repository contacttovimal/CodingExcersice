package careercup;

import java.util.HashSet;
import java.util.Set;

public class CheckWordInDictionary {
	public static Set<String> dic = new HashSet<String>();

	public static void main(String args[]) {
		// restated -> restate -> estate -> state -> stat -> sat -> at -> a
		dic.add("restated");
		dic.add("restate");
		dic.add("estate");
		dic.add("state");
		dic.add("stat");
		dic.add("sat");
		dic.add("at");
		dic.add("a");
		removeword("restated", "restated".length() - 1);
	}

	public static void removeword(String s, int j) {
		int i;
		if (s == null)
			return;
		if (s.length() == 1 || (0 == j)) {
			// System.out.println(s);
			return;
		}
		if (dic.contains(s))
			System.out.println(s);
		for (i = j; i < s.length(); i++) {
			removeword(s.substring(0, i) + s.substring(i + 1, s.length()), i);
		}
		// }

	}
}