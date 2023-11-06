package org.Track;

import java.util.*;

public class Other {

    /**
     * 332. 重新安排行程
     *
     * @param tickets
     * @return
     */
    LinkedList<String> result = new LinkedList<>();
    Map<String, Map<String, Integer>> target = new HashMap<>();
    Integer ticketNumber = 0;

    public List<String> findItinerary(List<List<String>> tickets) {
        System.out.println(tickets);
        // 初始化target
        // key是储存出发点，
        // value是出发点可达的目的地TreeMap（底层为红黑树，经过排序）,key是目的地，value是出发点到目的地的机票数（防止重复使用进入死循环）
        for (List<String> list : tickets) {
            Map<String, Integer> temp;
            if (target != null && target.containsKey(list.get(0))) {
                temp = target.get(list.get(0));
                temp.put(list.get(1), temp.getOrDefault(list.get(1), 0) + 1);
            } else {
                temp = new TreeMap<>();
                temp.put(list.get(1), 1);
            }
            target.put(list.get(0), temp);
        }
        result.add("JFK");
        ticketNumber = tickets.size() + 1;
        backtrackingToFindItinerary(1);
        return result;
    }

    public boolean backtrackingToFindItinerary(int currentTicketNumber) {
        if (currentTicketNumber == ticketNumber) {
            return true;
        }
        String current = result.getLast();
        if (target.containsKey(current)) {
            for (Map.Entry<String, Integer> entry : target.get(current).entrySet()) {
                if (entry.getValue() > 0) {
                    result.add(entry.getKey());
                    target.get(current).put(entry.getKey(),entry.getValue() - 1);
                    currentTicketNumber++;
                    if (backtrackingToFindItinerary(currentTicketNumber)) {
                        return true;
                    }
                    result.removeLast();
                    currentTicketNumber--;
                }
            }
        }
        return false;
    }
}
