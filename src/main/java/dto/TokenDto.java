package dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class TokenDto {
    private String token;
    private String expires;
    private String status;
    private String result;
}
