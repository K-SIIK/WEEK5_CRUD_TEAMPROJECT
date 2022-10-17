package spring.week5_teamcrud.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.week5_teamcrud.domain.TimeStamped;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class MemberRequest extends TimeStamped {

    @NotBlank
    private String userName;
    @NotBlank
    private String password;

    public MemberRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setEncodePwd(String encodePwd) {
        this.password = encodePwd;
    }

}
