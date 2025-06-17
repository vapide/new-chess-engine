public class Pawn extends Piece {
   public Pawn(boolean color, int row, int col) {
    super(color, row, col);
    //bitboard = new Bitboard( 1L << (row * 8 + col));
   }
  @Override
  public char getSymbol() {
      return getColor() ? 'P' : 'p';
  }   
      @Override
    public Bitboard getLegalMoves(Chessboard board, int row, int col, boolean color) {
      System.out.println(color);
        Piece piece = board.getPieceFromSquare(row, col);
        int pos = Bitboard.getBitPosition(row, col);
        if (!(piece instanceof Pawn)) {
            throw new IllegalArgumentException("This piece is not a Pawn!");
        }

        long moves = 0L;
        long pieces = board.getAllPieces().getBitboard();
        
        if (color) {
            if ((pieces & (1L << (pos + 8))) == 0) {
                moves |= (1L << (pos + 8));
                if (row == 2 && (pieces & (1L << (pos + 16))) == 0) {
                    moves |= (1L << (pos + 16));
                }
            }
        } else {
            if ((pieces & (1L << (pos - 8))) == 0) {
                moves |= (1L << (pos - 8));
                if (row == 7 && (pieces & (1L << (pos - 16))) == 0) {
                    moves |= (1L << (pos - 16));
                }
            }
        }
        if (color) {
            long enemyPieces = board.getBlackPieces().getBitboard();
            if ((pos % 8) != 0 && (enemyPieces & (1L << (pos + 7))) != 0) {
                moves |= (1L << (pos + 7));
            }
            if ((pos % 8) != 7 && (enemyPieces & (1L << (pos + 9))) != 0) {
                moves |= (1L << (pos + 9));
            }
        } else {
            long enemyPieces = board.getWhitePieces().getBitboard();
            if ((pos % 8) != 0 && (enemyPieces & (1L << (pos - 9))) != 0) {
                moves |= (1L << (pos - 9));
            }
            if ((pos % 8) != 7 && (enemyPieces & (1L << (pos - 7))) != 0) {
                moves |= (1L << (pos - 7));
            }
        }

        return new Bitboard(moves);
    }

   @Override
   public boolean isValidMove(Chessboard board, boolean color, int startrow, int startcol, int endrow, int endcol) {
       return ((this.getLegalMoves(board, startrow, startcol, color).getBitboard() & (1L << Bitboard.getBitPosition(endrow, endcol))) != 0);
    }
  }