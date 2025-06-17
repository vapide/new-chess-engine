public class Main {
    public static void main(String[] args) {
        //Bitboard bb = new Bitboard(Bitboard.RANK_1);
        //bb.setBit(0);
        //System.out.println(Bitboard.getBitPosition(1,1));
        //System.out.println("Bitboard after setting bit 0: " + bb.bitboard);
        //bb.displayBitboard();
        Chessboard board = new Chessboard();
        board.displayBoard();
        //Bitboard a = (board.getPieceFromSquare(2, 5).getLegalMoves(board, 2, 5, board.getPieceFromSquare(2,5).getColor()));        // Get legal moves for e2 pawn (e is the 5th file, 1-based)
        Bitboard a = board.getPieceMoves(2, 5);
        System.out.println("Legal moves for e2 pawn:");
        a.displayBitboard();
        
        board.makeMove("e2e4");
        board.displayBoard();
        System.out.println("White's turn? " + board.getTurn());
        
        a = board.getPieceMoves(7, 5);
        System.out.println("Legal moves for e7 pawn:");
        a.displayBitboard();
        
        board.makeMove("e7e5");
        board.displayBoard();
        System.out.println("White's turn? " + board.getTurn());
        
        a = board.getPieceMoves(4, 5);
        a.displayBitboard();

        board.makeMove("f2f4");
        board.displayBoard();

        board.getPieceMoves(4,6).displayBitboard();

        board.makeMove("e5f4");
        board.displayBoard();
        System.out.println(board.getMoves());
        
    }
}