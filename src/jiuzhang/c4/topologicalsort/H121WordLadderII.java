package jiuzhang.c4.topologicalsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class H121WordLadderII {
	
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        int minLayer = Integer.MAX_VALUE;
        dict.remove(start);
        dict.add(end);
        List<List<String>> results = new ArrayList<>();
        Map<String, List<String>> record = new HashMap<>();
        Queue<String> taskList = new LinkedList<>();
        Queue<String> elementBin = new LinkedList<>(dict);
        
        if(start == null || end == null){
            return results;
        }
        taskList.offer(start);
        record.put(start, new ArrayList<String>(taskList));
        if(start.equals(end)){
        	results.add(new ArrayList<String>(taskList));
        	return results;
        }
        
        int layer = 1;
        while(!taskList.isEmpty()){
            layer++;
            if(layer > minLayer){
                break;
            }
            int number = taskList.size();
            Queue<String> recycleBin = new LinkedList<>();
            for(int i = 0; i < number; i++){
                String head = taskList.poll();
                for(String next : elementBin){
                	if(nextWord(head, next)){
                        if(next.equals(end)){
                            minLayer = layer;
                            List<String> list = new ArrayList<>(record.get(head));
                            list.add(next);
                            results.add(list);
//                            recycleBin.add(next);//Ѱ�Ҷ����ʱ����Ҫ�����Ĵ𰸷Ż�ȥ���Թ�������ѡȡ��?
                        }else{
                            taskList.add(next);
                            List<String> list = new ArrayList<>(record.get(head));
                            list.add(next);
                            record.put(next, list);
                        }
                    }else{
                    	recycleBin.add(next);
					}
                }
                record.remove(head);
            }
            elementBin = recycleBin;
        }
        return results;
    }
           
    private boolean nextWord(String start, String next){
        int num = 0;
        for(int i = 0; i < start.length(); i++){
            if(start.charAt(i) != next.charAt(i)){
                num++;
            }
        }
        if(num == 1){
            return true;
        }else{
            return false;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		H121WordLadderII one = new H121WordLadderII();
		Set<String> dict = new HashSet<>();
		String start = "qa";
		String end = "sq";
		String[] words= 
			{"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","phoneinterview","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"};
		for(int i = 0; i < words.length; i++){
			dict.add(words[i]);
		}
		
		List<List<String>> result = one.findLadders(start, end, dict);
		for(int i = 0; i < result.size(); i++){
			List<String> oneResult = result.get(i);
			for(int j = 0; j < oneResult.size(); j++){
				System.out.print(oneResult.get(j));
				if(j < oneResult.size() - 1){
					System.out.print("->");
				}
			}
			System.out.println();
		}
	}
	
}
