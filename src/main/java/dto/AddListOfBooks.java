package dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class AddListOfBooks {
    private String userId;
    private List<CollectionOfISBNs> collectionOfIsbns;
}
