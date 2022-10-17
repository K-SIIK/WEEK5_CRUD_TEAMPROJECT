package spring.week5_teamcrud.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.week5_teamcrud.controller.request.ReplyRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reply extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "board_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @NotBlank
    @Column(nullable = false)
    private String content;


    public void newUpdate(ReplyRequest replyRequest) {

        this.content = replyRequest.getContent();
    }
}

