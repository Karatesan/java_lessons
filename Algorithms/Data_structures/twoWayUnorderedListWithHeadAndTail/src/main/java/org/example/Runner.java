package org.example;

public class Runner {

    public static void main(String[] args) {
        TwoWayUnorderedListWithHeadAndTail<Link> list = new TwoWayUnorderedListWithHeadAndTail<>();
        Link l = new Link("jeden");
        Link l2 = new Link("dwa");
        Link l3 = new Link("trzy");

        list.add(l);
        list.add(l2);
        list.add(l3);

        list.toStringReverse();

    }
    }

