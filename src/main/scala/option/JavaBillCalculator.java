package option;

import option.model.Contract;
import option.model.Tariff;
import option.model.User;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class JavaBillCalculator {

    private Map<String, Integer> currentBill = new HashMap<>();

    public JavaBillCalculator(List<Integer> someNumbers) {
        for (int n: someNumbers) {
            currentBill.put("Eintrag " + n, n);
        }
    }

    public void bill(String entry, int number) {
        Integer currentValue = currentBill.get(entry);
        if (currentValue == null) {
            currentValue = 0;
        }
        currentBill.put(entry, currentValue + number);
    }

    private File billingListFile = new File("/tmp/myBillingList");;

    // Did this work??
    public void bill(Integer number) throws IOException {
        List<String> currentLines = FileUtils.readLines(billingListFile);
        currentLines.add(number.toString());
        FileUtils.writeLines(billingListFile, currentLines);
    }

    // Oh no! What if any object is null!?
    public int calculateBillForUser(User user) {
        return user.getContract().getTariff().basicFee();
    }

    // Let's check all the objects in out getter chain
    // Hmmm... Looks familiar in our java world.
    // But isn't it weird and boiler plated?
    public int calculateBillForUser_2(User user) {
        if(user != null) {
            Contract contract = user.getContract();
            if(contract != null) {
                Tariff tariff = contract.getTariff();
                if (tariff != null) {
                    return tariff.basicFee();
                }
            }
        }

        return 0;
    }

    // Let's do it more in a return early style.
    // Pfuh... Many ifs. Many braces. 12 lines of
    // code only because this stuff can be null!?
    public int calculateBillForUser_3(User user) {
        if (user == null) {
            return 0;
        }

        Contract contract = user.getContract();
        if (contract == null) {
            return 0;
        }

        Tariff tariff = contract.getTariff();
        if(tariff == null) {
            return 0;
        }

        return tariff.basicFee();
    }


    // Idea: leakage of stateful object reference
}
