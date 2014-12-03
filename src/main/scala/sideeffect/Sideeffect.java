package sideeffect;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class Sideeffect {
    @Inject
    private String foreignHello;

    public void printWithOtherLanguage(List<String> list) {
        List<String> sublist = list.subList(0, 2);

        sublist.remove(0);
        sublist.add(0, foreignHello);
        sublist.add(".");
        System.out.println(sublist);
    }

    public static void main(String[] args) {
        Sideeffect sideeffect = new Sideeffect();

        List<String> list = new ArrayList<>();
        list.add("Hallo");
        list.add("Welt");
        list.add("!");

        sideeffect.printWithOtherLanguage(list);
        System.out.println(list);
    }
}
