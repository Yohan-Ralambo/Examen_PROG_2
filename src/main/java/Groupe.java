import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public abstract class Groupe {
    private int id;
    private String nom;
}
