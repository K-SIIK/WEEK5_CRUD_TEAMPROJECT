package spring.week5_teamcrud.controller.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardListResponse {

    List<BoardResponse> boards = new ArrayList<>();


    public void addboard(BoardResponse boardResponse) {
        boards.add(boardResponse);
    }
}
