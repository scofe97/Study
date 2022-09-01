package 알고리즘.문제코드.SSAFY.수업_220824;

class Node implements Comparable<Node>{
    int start;      // 시작 정점
    int end;        // 종료 정점
    int cost;       // 비용

    Node(int start, int end, int cost){
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    // 비용으로 오름차순 정렬하기 위한 Comparable method
    @Override
    public int compareTo(Node node) {
        return node.cost >= this.cost ? -1 : 1;
    }
}
