import java.util.*;

public class Chessboard {

    public List < String > moves;

    //private Piece[][] boardMatrix;

    private Map < Character, Integer > fileCodes;
    private Map < Integer, Character > letterCodes;
    private Map < Piece, Bitboard > whiteBitboards;
    private Map < Piece, Bitboard > blackBitboards;

    //private Map<String, Bitboard> bbdict;

    private Bitboard whitePawns;
    private Bitboard whiteKnights;
    private Bitboard whiteBishops;
    private Bitboard whiteRooks;
    private Bitboard whiteQueens;
    private Bitboard whiteKing;
    private Bitboard whitePieces;

    private Bitboard blackPawns;
    private Bitboard blackKnights;
    private Bitboard blackBishops;
    private Bitboard blackRooks;
    private Bitboard blackQueens;
    private Bitboard blackKing;
    private Bitboard blackPieces;

    private Bitboard allPieces;


    //private Bitboard[][] bitboardMatrix;

    private boolean whiteKingHasMoves = false;
    private boolean blackKingHasMoves = false;

    private boolean A1RookHasMoved = false;
    private boolean H1RookHasMoved = false;
    private boolean A8RookHasMoved = false;
    private boolean H8RookHasMoved = false;

    private boolean turn = true; // true for white's turn false for black

    public Chessboard() {
        moves = new ArrayList < > ();
        //bbdict = new HashMap<>();
        whiteBitboards = new HashMap < > ();
        blackBitboards = new HashMap < > ();
        fileCodes = new HashMap < > ();
        letterCodes = new HashMap < > ();
        //bbdict.put("whitePawns", new Bitboard(65280L));
        whitePawns = new Bitboard(65280L); // 0b0000000000000000000000000000000000000000000000001111111100000000L
        whiteKnights = new Bitboard(66L); // 0b0000000000000000000000000000000000000000000000000000000001000010L
        whiteBishops = new Bitboard(36L); // 0b0000000000000000000000000000000000000000000000000000000000100100L
        whiteRooks = new Bitboard(129L); // 0b0000000000000000000000000000000000000000000000000000000010000001L
        whiteQueens = new Bitboard(8L); // 0b0000000000000000000000000000000000000000000000000000000000001000L
        whiteKing = new Bitboard(16L); // 0b00000000000000000000000000000000000000000000000000000000000010000L
        whitePieces = new Bitboard(whitePawns.getBitboard() | whiteKnights.getBitboard() | whiteBishops.getBitboard() | whiteRooks.getBitboard() | whiteQueens.getBitboard() | whiteKing.getBitboard());

        whiteBitboards.put(new Pawn(true, 0, 0), whitePawns);
        whiteBitboards.put(new Knight(true, 0, 0), whiteKnights);
        whiteBitboards.put(new Bishop(true, 0, 0), whiteBishops);
        whiteBitboards.put(new Rook(true, 0, 0), whiteRooks);
        whiteBitboards.put(new Queen(true, 0, 0), whiteQueens);
        whiteBitboards.put(new King(true, 0, 0), whiteKing);


        blackPawns = new Bitboard(71776119061217280L); // 0b0000000011111111000000000000000000000000000000000000000000000000L
        blackKnights = new Bitboard(4755801206503243776L); // 0b0100001000000000000000000000000000000000000000000000000000000000L
        blackBishops = new Bitboard(2594073385365405696L); // 0b0010010000000000000000000000000000000000000000000000000000000000L
        blackRooks = new Bitboard(-9151314442816847872L); // 0b1000000100000000000000000000000000000000000000000000000000000000L
        blackQueens = new Bitboard(576460752303423488L); // 0b0000100000000000000000000000000000000000000000000000000000000000L
        blackKing = new Bitboard(1152921504606846976L); // 0b0001000000000000000000000000000000000000000000000000000000000000L
        blackPieces = new Bitboard(blackPawns.getBitboard() | blackKnights.getBitboard() | blackBishops.getBitboard() | blackRooks.getBitboard() | blackQueens.getBitboard() | blackKing.getBitboard());

        blackBitboards.put(new Pawn(false, 0, 0), blackPawns);
        blackBitboards.put(new Knight(false, 0, 0), blackKnights);
        blackBitboards.put(new Bishop(false, 0, 0), blackBishops);
        blackBitboards.put(new Rook(false, 0, 0), blackRooks);
        blackBitboards.put(new Queen(false, 0, 0), blackQueens);
        blackBitboards.put(new King(false, 0, 0), blackKing);

        allPieces = new Bitboard(whitePieces.getBitboard() | blackPieces.getBitboard());

        fileCodes = new HashMap < Character, Integer > ();
        letterCodes = new HashMap < Integer, Character > ();

        String files = "abcdefgh";
        char[] filelist = files.toCharArray();
        int j = 0;
        for (char character: filelist) {
            fileCodes.put(character, j);
            letterCodes.put(j, character);
            j++;
        }


    }

    public Piece getPieceFromSquare(int row, int col) {
        if (((1L << Bitboard.getBitPosition(row, col)) & whitePieces.getBitboard()) != 0) {
            if (((1L << Bitboard.getBitPosition(row, col)) & whitePawns.getBitboard()) != 0) {
                return new Pawn(true, row, col);
            } else if (((1L << Bitboard.getBitPosition(row, col)) & whiteKnights.getBitboard()) != 0) {
                return new Knight(true, row, col);
            } else if (((1L << Bitboard.getBitPosition(row, col)) & whiteBishops.getBitboard()) != 0) {
                return new Bishop(true, row, col);
            } else if (((1L << Bitboard.getBitPosition(row, col)) & whiteRooks.getBitboard()) != 0) {
                return new Rook(true, row, col);
            } else if (((1L << Bitboard.getBitPosition(row, col)) & whiteQueens.getBitboard()) != 0) {
                return new Queen(true, row, col);
            } else if (((1L << Bitboard.getBitPosition(row, col)) & whiteKing.getBitboard()) != 0) {
                return new King(true, row, col);
            }
            return null;
        } else if (((1L << Bitboard.getBitPosition(row, col)) & blackPieces.getBitboard()) != 0) {
            if (((1L << Bitboard.getBitPosition(row, col)) & blackPawns.getBitboard()) != 0) {
                return new Pawn(false, row, col);
            } else if (((1L << Bitboard.getBitPosition(row, col)) & blackKnights.getBitboard()) != 0) {
                return new Knight(false, row, col);
            } else if (((1L << Bitboard.getBitPosition(row, col)) & blackBishops.getBitboard()) != 0) {
                return new Bishop(false, row, col);
            } else if (((1L << Bitboard.getBitPosition(row, col)) & blackRooks.getBitboard()) != 0) {
                return new Rook(false, row, col);
            } else if (((1L << Bitboard.getBitPosition(row, col)) & blackQueens.getBitboard()) != 0) {
                return new Queen(false, row, col);
            } else if (((1L << Bitboard.getBitPosition(row, col)) & blackKing.getBitboard()) != 0) {
                return new King(false, row, col);
            }
            return null;
        }
        return null;
    }    
      public void movePiece(int startrow, int startcol, int endrow, int endcol) {
        Piece piece = getPieceFromSquare(startrow, startcol);

        if (piece == null) {
            throw new IllegalArgumentException("No piece at starting square: " + letterCodes.get(startcol) + startrow);
        }

        if (!piece.isValidMove(this, piece.getColor(), startrow, startcol, endrow, endcol)) {
            throw new IllegalArgumentException("Invalid move for piece at: " + letterCodes.get(startcol) + startrow);
        }

        Piece targetPiece = getPieceFromSquare(endrow, endcol);
        if (targetPiece != null) {
            if (targetPiece.getColor() == piece.getColor()) {
                throw new IllegalArgumentException("Cannot capture own piece at: " + letterCodes.get(endcol) + endrow);
            }
            if (targetPiece.getColor()) {
                if (targetPiece instanceof Pawn) {
                    whitePawns.removePiece(endrow, endcol);
                } else if (targetPiece instanceof Knight) {
                    whiteKnights.removePiece(endrow, endcol);
                } else if (targetPiece instanceof Bishop) {
                    whiteBishops.removePiece(endrow, endcol);
                } else if (targetPiece instanceof Rook) {
                    whiteRooks.removePiece(endrow, endcol);
                } else if (targetPiece instanceof Queen) {
                    whiteQueens.removePiece(endrow, endcol);
                } else if (targetPiece instanceof King) {
                    whiteKing.removePiece(endrow, endcol);
                    whiteKingHasMoves = false;
                }
                whitePieces.removePiece(endrow, endcol);
            } else {
                if (targetPiece instanceof Pawn) {
                    blackPawns.removePiece(endrow, endcol);
                } else if (targetPiece instanceof Knight) {
                    blackKnights.removePiece(endrow, endcol);
                } else if (targetPiece instanceof Bishop) {
                    blackBishops.removePiece(endrow, endcol);
                } else if (targetPiece instanceof Rook) {
                    blackRooks.removePiece(endrow, endcol);
                } else if (targetPiece instanceof Queen) {
                    blackQueens.removePiece(endrow, endcol);
                } else if (targetPiece instanceof King) {
                    blackKing.removePiece(endrow, endcol);
                    blackKingHasMoves = false;
                }
                blackPieces.removePiece(endrow, endcol);
            }
            allPieces.removePiece(endrow, endcol);
        }
        if (piece.getColor()) {
            if (piece instanceof Pawn) {
                whitePawns.removePiece(startrow, startcol);
                whitePawns.setBit(Bitboard.getBitPosition(endrow, endcol));
            } else if (piece instanceof Knight) {
                whiteKnights.removePiece(startrow, startcol);
                whiteKnights.setBit(Bitboard.getBitPosition(endrow, endcol));
            } else if (piece instanceof Bishop) {
                whiteBishops.removePiece(startrow, startcol);
                whiteBishops.setBit(Bitboard.getBitPosition(endrow, endcol));
            } else if (piece instanceof Rook) {
                whiteRooks.removePiece(startrow, startcol);
                whiteRooks.setBit(Bitboard.getBitPosition(endrow, endcol));
            } else if (piece instanceof Queen) {
                whiteQueens.removePiece(startrow, startcol);
                whiteQueens.setBit(Bitboard.getBitPosition(endrow, endcol));
            } else if (piece instanceof King) {
                whiteKing.removePiece(startrow, startcol);
                whiteKing.setBit(Bitboard.getBitPosition(endrow, endcol));
            }
            whitePieces.removePiece(startrow, startcol);
            whitePieces.setBit(Bitboard.getBitPosition(endrow, endcol));
        } else {
            if (piece instanceof Pawn) {
                blackPawns.removePiece(startrow, startcol);
                blackPawns.setBit(Bitboard.getBitPosition(endrow, endcol));
            } else if (piece instanceof Knight) {
                blackKnights.removePiece(startrow, startcol);
                blackKnights.setBit(Bitboard.getBitPosition(endrow, endcol));
            } else if (piece instanceof Bishop) {
                blackBishops.removePiece(startrow, startcol);
                blackBishops.setBit(Bitboard.getBitPosition(endrow, endcol));
            } else if (piece instanceof Rook) {
                blackRooks.removePiece(startrow, startcol);
                blackRooks.setBit(Bitboard.getBitPosition(endrow, endcol));
            } else if (piece instanceof Queen) {
                blackQueens.removePiece(startrow, startcol);
                blackQueens.setBit(Bitboard.getBitPosition(endrow, endcol));
            } else if (piece instanceof King) {
                blackKing.removePiece(startrow, startcol);
                blackKing.setBit(Bitboard.getBitPosition(endrow, endcol));
            }
            blackPieces.removePiece(startrow, startcol);
            blackPieces.setBit(Bitboard.getBitPosition(endrow, endcol));
        }

        allPieces.removePiece(startrow, startcol);
        allPieces.setBit(Bitboard.getBitPosition(endrow, endcol));
    }    
    public void makeMove(String move) {
        int startFile = fileCodes.get(move.charAt(0)) + 1;
        int startRank = Character.getNumericValue(move.charAt(1));
        int endFile = fileCodes.get(move.charAt(2)) + 1;
        int endRank = Character.getNumericValue(move.charAt(3));
        System.out.println("Move made = " + startFile + Integer.toString(startRank) + endFile + Integer.toString(endRank));
        movePiece(startRank, startFile, endRank, endFile);
        this.moves.add(move);
        this.turn = !this.turn;
    }

    public Bitboard getWhitePieces() {
        return whitePieces;
    }

    public Bitboard getBlackPieces() {
        return blackPieces;
    }

    public Bitboard getAllPieces() {
        return allPieces;
    }

    public List < String > getMoves() {
        return moves;
    }

    public Map < Integer, Character > getLetterCodes() {
        return letterCodes;
    }

    public Map < Character, Integer > getFileCodes() {
        return fileCodes;
    } 

    public boolean getTurn() {
        return turn;
    }

    public Bitboard getPieceMoves(int row, int col) {
        Piece piece = this.getPieceFromSquare(row, col);
        if (piece == null) {
            throw new IllegalArgumentException("No piece at the given square!!!!!!!!!");
        }
        return piece.getLegalMoves(this, row, col, piece.getColor());
    }

    public void displayBoard() {

        for (int row = 8; row >= 1; row--) {
            System.out.print(row + " "); // Print row number
            for (int col = 1; col <= 8; col++) {
                Piece piece = this.getPieceFromSquare(row, col);
                System.out.print((piece == null ? "." : piece.getSymbol()) + " ");
            }
            System.out.println();
            }

        System.out.print("  ");

        for (int col = 1; col <= 8; col++) {
            System.out.print(letterCodes.get(col - 1) + " ");
        }

        System.out.println();
    }

    public String getFEN() {
        return FEN.getFENFromBoard(this);
    }

    public String castlingRights() {
        StringBuilder rights = new StringBuilder();
        if (!H8RookHasMoved) {
            rights.append("K");
        }
        if (!H1RookHasMoved) {
            rights.append("Q");
        }
        if (!blackKingHasMoves) {
            rights.append("k");
        }
        if (!H8RookHasMoved) {
            rights.append("q");
        }
        return rights.toString();
    }

    public int fullMoveCount() {
        return (moves.size())/2;
    }
    
}