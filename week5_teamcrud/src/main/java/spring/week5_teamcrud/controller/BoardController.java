package spring.week5_teamcrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import spring.week5_teamcrud.controller.request.BoardRequest;
import spring.week5_teamcrud.controller.response.BoardListResponse;
import spring.week5_teamcrud.controller.response.BoardResponse;
import spring.week5_teamcrud.domain.Board;
import spring.week5_teamcrud.service.BoardService;
import spring.week5_teamcrud.service.UserDetailsImpl;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    //게시물 작성
    @PostMapping("/auth/boards")
    public String createBoard(@RequestBody BoardRequest boardRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.createBoard(boardRequest, userDetails.getMember());
        return "게시글 작성완료";
    }

    //단건 게시물 조회
    @GetMapping(value = "/boards/{boardId}")
    public BoardResponse getBoard(@PathVariable Long boardId) {
        return boardService.getBoard(boardId);
    }

    //게시물 조회
    @GetMapping(value = "/posts")
    public BoardListResponse getAllPosts() {
        return boardService.getAllPost();
    }

    //단건 게시물 수정
    @PutMapping(value = "/auth/boards/{boardId}")
    public String updateBoard(@PathVariable Long boardId, @RequestBody BoardRequest boardRequest,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.updateBoard(boardId, boardRequest, userDetails.getMember());
        return "수정에 성공하였습니다";
    }

    //단건 게시물 삭제
    @DeleteMapping(value = "/auth/boards/{boardId}")
    public String deleteBoard(@PathVariable Long boardId,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.deleteBoard(boardId, userDetails.getMember());
    }

    // 좋아요기능
    @PostMapping("/auth/boards/{boardId}/hearts")
    public String postLike(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.BoardHeart(boardId, userDetails.getMember());
        return "좋아요 완료!";
    }

}

