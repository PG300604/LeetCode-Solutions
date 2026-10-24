import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice,
                                 String arriveBob, String leaveBob) {
        LocalDate arriveA = LocalDate.parse("2022-" + arriveAlice);
        LocalDate leaveA  = LocalDate.parse("2022-" + leaveAlice);
        LocalDate arriveB = LocalDate.parse("2022-" + arriveBob);
        LocalDate leaveB  = LocalDate.parse("2022-" + leaveBob);

        LocalDate start = arriveA.isAfter(arriveB) ? arriveA : arriveB;
        LocalDate end = leaveA.isBefore(leaveB) ? leaveA : leaveB;

        if (start.isAfter(end)) {
            return 0; // no overlap
        }

        return (int) ChronoUnit.DAYS.between(start, end) + 1;
    }
}
