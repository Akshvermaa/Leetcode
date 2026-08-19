class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int s = seat[1];
            if (s >= 2 && s <= 9) {
                map.put(row, map.getOrDefault(row, 0) | (1 << (s - 1)));
            }
        }
        int result = (n - map.size()) * 2;
        for (int mask : map.values()) {
            boolean left = (mask & 0b0000011110) == 0;
            boolean middle = (mask & 0b0001111000) == 0;
            boolean right = (mask & 0b0111100000) == 0;
            if (left && right) {
                result += 2;
            } else if (left || middle || right) {
                result++;
            }
        }
        return result;
    }
}