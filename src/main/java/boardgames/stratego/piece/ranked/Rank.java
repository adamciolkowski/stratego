package boardgames.stratego.piece.ranked;

public enum Rank {
    SPY(1),
    SCOUT(2),
    MINER(3),
    SERGEANT(4),
    LIEUTENANT(5),
    CAPTAIN(6),
    MAJOR(7),
    COLONEL(8),
    GENERAL(9),
    MARSHALL(10);

    private int rank;

    Rank(int rank) {
        this.rank = rank;
    }

}
