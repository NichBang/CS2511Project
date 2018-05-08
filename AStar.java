import java.util.*;

public class AStar{

    private BitMap map;

    public AStar(BitMap map) {
        this.map = map;
    }

    public void setMap(BitMap map) {
        this.map = map;
    }

    public boolean isCompleteable() {
        BitMap currentMap = map;
        PriorityQueue<BitMap> openQueue = new PriorityQueue<>(new Comparator<BitMap>() {
            @Override
            public int compare(BitMap o1, BitMap o2) {
                return o1.getCarsToMove() - o2.getCarsToMove();
            }
        });
        openQueue.add(currentMap);
        Set<int[][]> closedSet = new HashSet<>();

        while (!openQueue.isEmpty()) {
            System.out.println(openQueue.size());
            currentMap = openQueue.poll();

            if (currentMap.getCarsToMove() == 0) {
                return true;
            }

            closedSet.add(currentMap.getMap());

            for (int[][] nextMap : currentMap.getAllPossibleMoves()) {
                BitMap nextBitMap = new BitMap(nextMap);
                //nextBitMap.toString();

                if (closedSet.contains(nextBitMap.getMap())) continue;

                if (nextBitMap.getCarsToMove() > currentMap.getCarsToMove()) continue;

                if (!openQueue.contains(nextBitMap)) {
                    openQueue.add(nextBitMap);
                }

            }
        }
        return false;
    }

    /*private boolean setContains(Set<int[][]> closedSet, int[][] map) {
        for (int[][] closedMap : closedSet) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    if (map[i][j] == closedMap[i][j]) {

                    }
                }
            }
        }
    }*/
}
