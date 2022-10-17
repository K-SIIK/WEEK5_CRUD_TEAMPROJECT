package spring.week5_teamcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.week5_teamcrud.controller.request.BoardRequest;
import spring.week5_teamcrud.controller.response.BoardListResponse;
import spring.week5_teamcrud.controller.response.BoardResponse;
import spring.week5_teamcrud.domain.Board;
import spring.week5_teamcrud.domain.Heart;
import spring.week5_teamcrud.domain.Member;
import spring.week5_teamcrud.repository.BoardRepository;
import spring.week5_teamcrud.repository.HeartRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final HeartRepository heartRepository;

    //게시물 작성
    public String createBoard(BoardRequest boardRequest, Member member) {
        Board board = Board.builder()
                .title(boardRequest.getTitle())
                .content(boardRequest.getContent())
                .member(member)
                .build();
        boardRepository.save(board);
        return "게시글 작성완료";
    }

    //단건 게시물 조회
    public BoardResponse getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("존재하는 게시물이 없습니다")
        );

        return BoardResponse.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

    //게시물 조회
    public BoardListResponse getAllPost(){
        List<Board> boards = boardRepository.findAllByOrderByModifiedAtDesc();
        BoardListResponse boardListResponse = new BoardListResponse();

        for(Board board : boards){
            boardListResponse.addboard(new BoardResponse(board));
        }

        return boardListResponse;


    }

    //단건 게시물 수정
    @Transactional
    public String updateBoard(Long boardId, BoardRequest boardRequest, Member member) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("존재하는 게시물이 없습니다")
        );

        if(!board.getMember().getUserName().equals(member.getUserName())){
            return "작성자만 수정할 수 있습니다.";
        }

        board.update(boardRequest);
        return "수정에 성공하였습니다";

    }

    //단건 게시물 삭제
    @Transactional
    public String deleteBoard(Long boardId, Member member) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("존재하는 게시물이 없습니다")
        );

        if(!board.getMember().getUserName().equals(member.getUserName())){
            return "작성자만 수정할 수 있습니다.";
        }

        boardRepository.delete(board);
        return "게시물을 삭제하였습니다";
    }

    //좋아요 기능
    public String BoardHeart(Long boardId, Member member) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("게시물이 없습니다")
        );

        Heart heart = Heart.builder()
                .member(member)
                .board(board)
                .build();
        heartRepository.save(heart);
        return "좋아요 완료요!";
    }
}
