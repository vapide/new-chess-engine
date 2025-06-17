public class Rook extends Piece {

  public Rook(boolean color, int row, int col) {
   super(color, row, col);
    //bitboard = new Bitboard( 1L << (row * 8 + col));
  }


  @Override
  public char getSymbol() {
    return getColor() ? 'R' : 'r';
  }

  @Override
  public boolean isValidMove(Chessboard board, boolean color, int startrow, int startcol, int endrow, int endcol) {
      return true;
   }

  public Bitboard getMoves() { // psuedo legal moves
    return new Bitboard(0L);
   //return new Bitboard((Bitboard.FILE_A >>> getColor()) ^ (Bitboard.RANK_8 >>> getColor())); 
  }

    @Override
    public Bitboard getLegalMoves(Chessboard board, int row, int col, boolean color) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}