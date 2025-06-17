public class Bitboard {

    public long bitboard;

    public Bitboard(long bb) {
        bitboard = bb;
    }

    public long getBitboard() {
        return bitboard;
    }

    public int getBitInPos(int pos) {
        return ((bitboard >> pos) & 1L) == 1L ? 1 : 0;
    }

    public void setBit(int pos) {
        bitboard |= (1L << pos);
    }

    public void clearBit(int pos) {
        bitboard &= ~(1L << pos);
    }

    public void clearBoard() {
        bitboard = 0L;
    }

    public boolean isBitSet(int pos) {
        return (bitboard & (1L << pos)) != 0;
    }

    public static final long DIAG_AH = -9205322385119247871L; // 0b1000000001000000001000000001000000001000000001000000001000000001 
    public static final long DIAG_HA = 72624976668147840L; // 0b0000000100000010000001000000100000010000001000000100000010000000
    public static final long FILE_A = -9187201950435737472L; // 0x8080808080808080L
    public static final long FILE_H = 72340172838076673L; // 0x101010101010101L
    public static final long RANK_1 = 255L; // 0x00000000000000FFL
    public static final long RANK_2 = 65280L; // 0x000000000000FF00L
    public static final long RANK_7 = 71776119061217280L; // 0xFF00000000000000L
    public static final long RANK_8 = -72057594037927936L; // 0x00FF000000000000L

    // Utility methods
    public static long northOne(long bb) {
        return bb << 8;
    }

    public static long southOne(long bb) {
        return bb >>> 8;
    }

    public static long eastOne(long bb) {
        return (bb << 1) & ~FILE_A;
    }

    public static long westOne(long bb) {
        return (bb >>> 1) & ~FILE_H;
    }

    public static long northEastOne(long bb) {
        return (bb << 9) & ~FILE_A;
    }

    public static long northWestOne(long bb) {
        return (bb << 7) & ~FILE_H;
    }

    public static long southEastOne(long bb) {
        return (bb >>> 7) & ~FILE_A;
    }

    public static long southWestOne(long bb) {
        return (bb >>> 9) & ~FILE_H;
    }   
     public static int getBitPosition(int row, int col) {
        return (row - 1) * 8 + (col - 1);
    }

    public static int getRow(int pos) {
        return (pos / 8) + 1;
    }

    public static int getCol(int pos) {
        return (pos % 8) + 1;
    }

    public void removePiece(int row, int col) {
        int pos = getBitPosition(row, col);
        clearBit(pos);
    }

    public void displayBitboard() {
        for (int row = 8; row >= 1; row--) {
            for (int col = 1; col <= 8; col++) {
                int pos = getBitPosition(row, col);
                System.out.print(((1L << pos) & bitboard) != 0 ? "1 " : "0 ");
            }
            System.out.println();
        }
    }

    public static void printBitboard(long bb) {
        for (int row = 8; row >= 1; row--) {
            for (int col = 1; col <= 8; col++) {
                int pos = getBitPosition(row, col);
                System.out.print(((1L << pos) & bb) != 0 ? "1 " : "0 ");
            }
            System.out.println();
        }
    }

}