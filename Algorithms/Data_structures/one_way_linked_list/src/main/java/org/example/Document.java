package org.example;

import java.util.List;
import java.util.Scanner;

public class Document{
    public String name;
    public OneWayLinkedList<Link> links;
    public Document(String name, Scanner scan) {
        // ==================================
        this.name = name;
        // ==================================
        load(scan);
    }
    public void load(Scanner scan) {

        for(int i=0;i<10;++i){
            String input = scan.nextLine();
            if(input.equals("break")) return;
            if(correctLink(input)) {
                Link link = new Link(input);
                links.add(link);
            }
        }

    }
    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    private static boolean correctLink(String link) {

        String regex = "^[a-zA-z0-9]+$";

        return link.matches(regex);

//        if (link.isEmpty() || link.charAt(0) == '_') {
//            return false;}
//
//        for(char ch : link.toCharArray()){
//            if(!Character.isLetterOrDigit(ch) && ch!='_') {
//                return false;
//            }
//        }
//        return true;
    }

    @Override
    public String toString() {
        return null;
    }

}
