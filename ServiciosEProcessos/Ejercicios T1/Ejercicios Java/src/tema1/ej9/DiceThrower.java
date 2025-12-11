package tema1.ej9;

import staticMethods.GlobalStaticMethods;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DiceThrower implements Runnable {

    List<Dice> completedDice;
    List<Dice> remainingDice;
    private final String id;
    private static final AtomicInteger counter = new AtomicInteger(1);

    public DiceThrower(Dice[] dices) {
        completedDice = new ArrayList<>();
        remainingDice = new ArrayList<>(List.of(dices));
        this.id = "DiceThrower-" + counter.getAndIncrement();
    }


    @Override
    public void run() {
        do {
            var newCompletedDice = remainingDice.stream()
                    .filter(Dice::throwDice)
                            .toList();

            completedDice.addAll(newCompletedDice);
            remainingDice.removeAll(newCompletedDice);

            try {
                if(GlobalStaticMethods.DEBUG_MESSAGES)System.out.printf("%s: Dados tirados, recuperando. Dados finalizados de momento %d%n",id,completedDice.size());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Interrupted");
                Thread.currentThread().interrupt();

            }
        } while (!remainingDice.isEmpty());

        var message = new StringBuilder(id + "; Finalizo con lanzar datos, resultados finales\n");
        for (Dice d : completedDice) {
                message.append(d);
        }
        System.out.println(message);
    }
}
