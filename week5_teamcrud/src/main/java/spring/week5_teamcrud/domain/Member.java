package spring.week5_teamcrud.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.week5_teamcrud.controller.request.MemberRequest;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends TimeStamped {

    @Column(name = "member_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    @Column(nullable = false)
    private String userName;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$)")
    @NotBlank
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "member")
    List<Board> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<Reply> replies = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<Heart> hearts = new ArrayList<>();

    public Member(MemberRequest memberRequest) {
        this.userName = memberRequest.getUserName();
        this.password = memberRequest.getPassword();
    }

}
