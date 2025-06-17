public class FEN {
    private String fenString;
    public FEN(String fenString) {
        this.fenString = fenString;
    }
    public String getFenString() {
        return fenString;
    }
    public void setFenString(String fenString) {
        this.fenString = fenString;
    }
    public static String getFENFromBoard(Chessboard board) {
        String newFENString = "";
        int emptyCount = 0;
        for(int i=8; i>=1; i--) {
            for(int j=1; j<=8; j++) {
                Piece piece = board.getPieceFromSquare(i, j);
                if(piece == null) {
                    emptyCount++;
                } else {
                    if(emptyCount > 0) {
                        newFENString += emptyCount;
                        emptyCount = 0;
                    }
                    newFENString += piece.getSymbol();
                }
            }
            if (emptyCount > 0) {
                newFENString += emptyCount;
                emptyCount = 0;
            }
            emptyCount = 0;
            if (i != 1) {
                newFENString += '/';
            }
        }
            newFENString +=(board.getTurn() ? " w" : " b");
            newFENString += " " + board.castlingRights(); 
            // en passant square placeholder
            newFENString += " -";
            newFENString += board.getTurn() ? " 0" : " 1";
            newFENString += " " + board.fullMoveCount();
        return newFENString;
    }
}
