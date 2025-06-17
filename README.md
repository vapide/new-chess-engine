# Java Chess Engine with Bitboards

A chess engine implementation in Java using bitboards for efficient board representation and move generation.

## 🚀 Features Implemented

### Board Representation
- ✅ Bitboard-based board representation
- ✅ Piece position tracking using bitboards for each piece type
- ✅ Separate bitboards for white and black pieces
- ✅ Basic board visualization in console

### Move Generation
- ✅ Basic pawn move generation
  - ✅ Single step moves
  - ✅ Double step moves from starting position
  - ✅ Diagonal capture moves
- ✅ Basic move validation system
- ✅ Turn-based move execution
- ✅ Move history tracking

### FEN Support
- ✅ Basic FEN string generation
- ✅ Board position representation in FEN
- ✅ Active color tracking in FEN
- ✅ Basic castling rights tracking
- ✅ Move counter implementation

## 🎯 To-Do List

### Move Generation
- [ ] Implement en passant captures for pawns
- [ ] Complete knight move generation
- [ ] Complete bishop move generation
- [ ] Complete rook move generation
- [ ] Complete queen move generation
- [ ] Complete king move generation
- [ ] Add castling move generation
- [ ] Add check detection
- [ ] Add checkmate detection
- [ ] Add stalemate detection

### FEN Support
- [ ] Implement complete FEN string parsing
- [ ] Add en passant target square tracking
- [ ] Improve halfmove clock for fifty-move rule
- [ ] Add validation for FEN string input

### PGN Support
- [ ] Create PGN parser class
- [ ] Implement move notation parsing
- [ ] Add game metadata handling
- [ ] Add PGN export functionality
- [ ] Support for comments and annotations

### Game Logic
- [ ] Implement complete castling rules
- [ ] Add pawn promotion
- [ ] Add draw detection
  - [ ] Threefold repetition
  - [ ] Fifty-move rule
  - [ ] Insufficient material
- [ ] Add move validation for pieces in check

### Testing & Documentation
- [ ] Add unit tests for all piece movements
- [ ] Add unit tests for FEN parsing/generation
- [ ] Add unit tests for PGN parsing/generation
- [ ] Add JavaDoc documentation
- [ ] Add code examples to documentation

### UI/UX Improvements
- [ ] Add better board visualization
- [ ] Add move input validation
- [ ] Add helpful error messages
- [ ] Add command-line interface improvements

## 🛠️ Project Structure

```
new-chess-engine/
├── Bitboard.java      - Bitboard operations and utility functions
├── Chessboard.java    - Main board representation and game logic
├── FEN.java           - FEN string generation and parsing
├── Main.java          - Entry point and testing
├── Piece.java         - Abstract base class for chess pieces
├── Pawn.java          - Pawn movement logic
├── Rook.java          - Rook piece implementation
├── Knight.java        - Knight piece implementation
├── Bishop.java        - Bishop piece implementation
├── Queen.java         - Queen piece implementation
└── King.java          - King piece implementation
```

## 🔍 Implementation Details

### Bitboard Implementation
The engine uses bitboards (64-bit integers) to represent the chess board, where each bit represents a square on the board. This provides efficient move generation and board state management.

### Move Generation
Currently implements basic pawn movement including:
- Forward moves (single and double from starting position)
- Capture moves
- Basic move validation

### FEN Support
Currently supports:
- Board position representation
- Active color
- Basic castling rights
- Move counters

## 🤝 Contributing

Feel free to contribute to this project by:
1. Implementing items from the to-do list
2. Improving existing implementations
3. Adding tests
4. Improving documentation

## 📝 License

This project is licensed under the terms of the LICENSE file included in the repository.

---
