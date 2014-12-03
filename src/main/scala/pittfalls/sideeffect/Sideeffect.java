package pittfalls.sideeffect;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class Sideeffect {
    @Inject
    private String foreignHello;

    public void printInOtherLanguage(List<String> list) {
        List<String> subList = list.subList(0, 2);

        subList.remove(0);
        subList.add(0, foreignHello);
        subList.add(".");
        System.out.println(subList);
    }

    public static void main(String[] args) {
        Sideeffect sideeffect = new Sideeffect();

        List<String> list = new ArrayList<>();
        list.add("Hallo");
        list.add("Welt");
        list.add("!");

        sideeffect.printInOtherLanguage(list);
        System.out.println(list);
    }
}
