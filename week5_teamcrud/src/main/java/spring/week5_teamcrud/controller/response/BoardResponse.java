package spring.week5_teamcrud.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.week5_teamcrud.domain.Board;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponse{

    private String title;
    private String content;

    public BoardResponse(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
