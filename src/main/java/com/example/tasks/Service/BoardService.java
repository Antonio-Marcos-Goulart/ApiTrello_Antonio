package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // Criar um Board
    public Board createBoard(Board board) {
        if (board.getBoardName() == null || board.getBoardName().length() < 3) {
            throw new IllegalArgumentException("O nome do Board deve ter pelo menos 3 caracteres");
        }
        return boardRepository.save(board);
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board não encontrado com id: " + id));
    }

    // Obter todos os Boards do banco de dados
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    // Atualizar o Board
    public Board updateBoard(Long id, Board updatedBoard) {
        Board existingBoard = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board não encontrado com id: " + id));

        if (updatedBoard.getBoardName() != null && !updatedBoard.getBoardName().isEmpty()) {
            existingBoard.setBoardName(updatedBoard.getBoardName());
        }

        if (updatedBoard.getBoardDescription() != null && !updatedBoard.getBoardDescription().isEmpty()){
            existingBoard.setBoardDescription(updatedBoard.getBoardDescription());
        }
        return boardRepository.save(existingBoard);
    }

    // Deletar o Board
    public void deleteBoard (Long boardId) {
        if (!boardRepository.existsById(boardId)){
            throw new IllegalArgumentException("Board não encontrado com id: " + boardId);
        }
        boardRepository.deleteById(boardId);
    }
}
