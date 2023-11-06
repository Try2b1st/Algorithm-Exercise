package org;

import org.Track.*;
import org.dp.Palindromic;
import org.dp.ShareProblem;
import org.dp.SubQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        Other other = new Other();
        List<List<String>> tickets = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            List<String> ticket = new ArrayList<>();
            ticket.add(sc.nextLine());
            ticket.add(sc.nextLine());
            tickets.add(ticket);
        }
        //MUC
        //LHR
        //JFK
        //MUC
        //SFO
        //SJC
        //LHR
        //SFO
        System.out.println(other.findItinerary(tickets));
    }
}