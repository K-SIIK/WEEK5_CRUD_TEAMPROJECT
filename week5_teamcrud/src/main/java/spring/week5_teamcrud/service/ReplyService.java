package spring.week5_teamcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.week5_teamcrud.controller.request.ReplyRequest;
import spring.week5_teamcrud.domain.Board;
import spring.week5_teamcrud.domain.Member;
import spring.week5_teamcrud.domain.Reply;
import spring.week5_teamcrud.repository.BoardRepository;
import spring.week5_teamcrud.repository.ReplyRepository;

@Service
@RequiredArgsConstructor
public class ReplyService {


    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    public String createReply(Long BoardId, ReplyRequest replyRequest, Member member) {

        Board board = boardRepository.findById(BoardId).orElseThrow(
                () -> new IllegalArgumentException("게시물 아이디가 없습니다")
        );

        Reply reply = Reply.builder()
                .member(member)
                .board(board)
                .content(replyRequest.getContent())
                .build();
        replyRepository.save(reply);
        return "댓글 작성완료";
    }

    @Transactional
    public String updateReply(Long BoardId, Long ReplyId, ReplyRequest replyRequest, Member member) {
        Board board = boardRepository.findById(BoardId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다")
        );

        Reply reply = replyRepository.findById(ReplyId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 없습니다")
        );

        if (!reply.getMember().getUserName().equals(member.getUserName())) {
            return "작성자만 수정할 수 있습니다";
        }

        reply.newUpdate(replyRequest);
        return "댓글 수정완료!";
    }

    @Transactional
    public String deleteReply(Long BoardId, Long ReplyId, Member member) {
        Board board = boardRepository.findById(BoardId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다")
        );

        Reply reply = replyRepository.findById(ReplyId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 없습니다")
        );

        if (!reply.getMember().getUserName().equals(member.getUserName())) {
            return "작성자만 삭제할 수 있습니다.";
        }

        replyRepository.delete(reply);
        return "댓글 삭제완료!";
    }
}
