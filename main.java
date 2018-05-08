public class main {

    public static void main(String args[]) {

        BitMap map = new BitMap(6);
        map.createRandomMap(9);
        map.toString();
        AStar AStar = new AStar(map);
        while (!AStar.isCompleteable()) {
            map.createRandomMap(9);
            map.toString();
            AStar.setMap(map);
        }
        map.toString();
    }
}
