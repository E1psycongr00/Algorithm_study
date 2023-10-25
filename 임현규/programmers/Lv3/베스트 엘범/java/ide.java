import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
	public int[] solution(String[] genres, int[] plays) {
		HashMap<String, Integer> playsMap = new HashMap<>();
		HashMap<String, List<List<Integer>>> genresIndexes = new HashMap<>();
		for (int i = 0; i < plays.length; i++) {
			playsMap.put(genres[i], playsMap.getOrDefault(genres[i], 0) + plays[i]);
			genresIndexes.computeIfAbsent(genres[i], key -> new ArrayList<>())
				.add(List.of(i, plays[i]));
		}
		genresIndexes.keySet().forEach(key -> {
			genresIndexes.get(key).sort(Comparator.comparing((List<Integer> list) -> list.get(1)).reversed()
				.thenComparing(list -> list.get(0)));
		});
		List<Integer> result = new ArrayList<>();
		List<String> collect = playsMap.entrySet().stream()
			.sorted(Comparator.comparingInt((Map.Entry<String, Integer> entry) -> entry.getValue()).reversed())
			.map(Map.Entry::getKey)
			.collect(Collectors.toList());
		for (String genre : collect) {
			List<List<Integer>> list = genresIndexes.get(genre);
			result.add(list.get(0).get(0));
			if (list.size() > 1) {
				result.add(list.get(1).get(0));
			}
		}
		return result.stream().mapToInt(Integer::intValue).toArray();
	}
}