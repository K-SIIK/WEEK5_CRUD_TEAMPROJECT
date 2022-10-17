package spring.week5_teamcrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import spring.week5_teamcrud.controller.request.ReplyRequest;
import spring.week5_teamcrud.service.ReplyService;
import spring.week5_teamcrud.service.UserDetailsImpl;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    //댓글 작성
    @PostMapping("auth/auth/boards/{boardId}/replys")
    public String creatComment(@PathVariable Long boardId, @RequestBody ReplyRequest replyRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        replyService.createReply(boardId, replyRequest, userDetails.getMember());
        return "댓글 작성완료";
    }

    //댓글 단건 수정
    @PutMapping("/auth/boards/{boardId}/replys/{replyId}")
    public String updateComment(@PathVariable Long boardId, @PathVariable Long replyId, @RequestBody ReplyRequest replyRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        replyService.updateReply(boardId, replyId, replyRequest, userDetails.getMember());
        return "댓글 수정완료";
    }


    //댓글 단건 삭제
    @DeleteMapping("api/auth/boards/{boardId}/replys/{replyId}")
    public String deleteComment(@PathVariable Long boardId, @PathVariable Long replyId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        replyService.deleteReply(boardId, replyId, userDetails.getMember());
        return "댓글 삭제완료";
    }
}
