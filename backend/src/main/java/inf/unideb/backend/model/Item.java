package inf.unideb.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "ITEMS")
public class Item {

    public static final int MAX_DESCRIPTION_LENGTH = 2000;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String imageUrl;
    @Column(length = MAX_DESCRIPTION_LENGTH)
    private String description;
    private String tags;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @OneToMany(
            mappedBy = "item",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<BoardItem> boardItems = new ArrayList<>();

    public Item(UUID idC,
                String titleC,
                String imageUrlC,
                String descriptionC,
                String tagsC,
                User userC) {
        this.id = idC;
        this.title = titleC;
        this.imageUrl = imageUrlC;
        this.description = descriptionC;
        this.tags = tagsC;
        this.user = userC;
        this.boardItems = new ArrayList<>();
    }
}
