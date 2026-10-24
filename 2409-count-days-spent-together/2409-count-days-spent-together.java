class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int start=Math.max(toDay(arriveAlice), toDay(arriveBob));
        int end = Math.min(toDay(leaveAlice), toDay(leaveBob));
        if (start > end) {
            return 0;
        }
        return end - start + 1;
    }
    private int toDay(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int[] daysInMonth = {
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };
        int total = 0;
        for (int i = 0; i < month - 1; i++) {
            total += daysInMonth[i];
        }
        total += day;
        return total;
    }
}