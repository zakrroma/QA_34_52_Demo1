package dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class CreateUserResult {
    private String userId;
    private String username;
    private List<BookDto> books;
}
