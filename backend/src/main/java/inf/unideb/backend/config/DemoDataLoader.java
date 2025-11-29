package inf.unideb.backend.config;

import inf.unideb.backend.model.Board;
import inf.unideb.backend.model.Item;
import inf.unideb.backend.model.User;
import inf.unideb.backend.repository.BoardRepository;
import inf.unideb.backend.repository.ItemRepository;
import inf.unideb.backend.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("prod")
public class DemoDataLoader {

    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;
    private static final int FOURTH = 3;
    private static final int FIFTH = 4;
    private static final int SIXTH = 5;
    private static final int SEVENTH = 6;

    private final PasswordEncoder passwordEncoder;

    public DemoDataLoader(PasswordEncoder pE) {
        this.passwordEncoder = pE;
    }

    private static final List<String> URLS = Arrays.asList(
            "https://images.unsplash.com/"
                    + "photo-1761323320291-61869b66f9e9?"
                    + "w=600&auto=format&"
                    + "fit=crop&q=60&ixlib=rb-4.1.0&ixid"
                    + "=M3wxMjA3fDB8MHx0b3BpYy1mZWVkfD"
                    + "Z8aVVJc25WdGpCMFl8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1753729967894"
                    + "-cd1ba12f11c1?w=600&auto="
                    + "format&fit=crop&q="
                    + "60&ixlib=rb-4.1.0"
                    + "&ixid=M3wxMjA3fDB8MHx0b3BpYy1m"
                    + "ZWVkfDh8aVVJc25WdGpCMFl8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1760978631985"
                    + "-590e3b5f4057?w"
                    + "=600&auto=format&"
                    + "fit=crop&q=60&ixlib=rb-4.1.0&ixid"
                    + "=M3wxMjA3fDB8MHx0b3BpYy1mZWVkfDI5fGlVSXNu"
                    + "VnRqQjBZfHxlbnwwfHx8fHw%3D",
            "https://images.unsplash.com/"
                    + "photo-1751315574558-d185d266b16d"
                    + "?w=600&auto=format&fit"
                    + "=crop&q=60&ixlib="
                    + "rb-4.1.0&ixid=M3wxMjA3fDB8MHx0b3BpYy1mZWVk"
                    + "fDI3fGlVSXNuVnRqQjBZfHxlbnwwfHx8fHw%3D",
            "https://images.unsplash.com/"
                    + "photo-1516041774595-cc1b7969480c"
                    + "?q=80&w=1170&auto=format&"
                    + "fit=crop&ixlib="
                    + "rb-4.1.0&ixid=M3wxMjA"
                    + "3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA"
                    + "%3D%3D",
            "https://images.unsplash.com/"
                    + "photo-1747767296029-"
                    + "c5116a707614?q="
                    + "80&w=1169"
                    + "&auto=format&fit"
                    + "=crop&ixlib=rb-"
                    + "4.1.0&"
                    + "ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8f"
                    + "GVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/"
                    + "photo-1610917423396-689c35842cd1?q"
                    + "=80&w=1170&auto=format&fit=crop&ixlib=rb-"
                    + "4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by"
                    + "1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    );


    @Bean
    ApplicationRunner initData(
            UserRepository userRepo,
            ItemRepository itemRepo,
            BoardRepository boardRepo
    ) {
        return args -> {
            if (userRepo.count() > 0) {
                return;
            }
            var admin = userRepo.save(
                    User.builder()
                            .username("admin").email("admin@example.com")
                            .password(passwordEncoder.encode("admin123"))
                            .role("ADMIN").build()
            );
            var user1 = userRepo.save(
                    User.builder()
                            .username("demo_user")
                            .email("demo@example.com")
                            .password(passwordEncoder.encode("password"))
                            .build()
            );
            var user2 = userRepo.save(
                    User.builder()
                            .username("demo_user2")
                            .email("demo2@example.com")
                            .password(passwordEncoder.encode("password"))
                            .build()
            );
            var user3 = userRepo.save(
                    User.builder()
                            .username("traveler_dude")
                            .email("traveler@example.com")
                            .password(passwordEncoder.encode("password"))
                            .build()
            );
            var user4 = userRepo.save(
                    User.builder()
                            .username("nature_lover")
                            .email("lover@example.com")
                            .password(passwordEncoder.encode("password"))
                            .build()
            );
            var item1 = itemRepo.save(
                    Item.builder()
                            .title("Valley greenery")
                            .description(
                                    "Photo I took while on vacation in Austria."
                            )
                            .imageUrl(URLS.get(FIRST))
                            .tags("mountains,snow,alps")
                            .user(user1)
                            .build()
            );
            var item2 = itemRepo.save(
                    Item.builder()
                            .title("Lunch in Paris")
                            .description(
                                    "I was in a cute little"
                                    + "café in Paris, this is what I ate."
                            )
                            .imageUrl(URLS.get(SECOND))
                            .tags("coffee,croissant")
                            .user(user2)
                            .build()
            );
            var item3 = itemRepo.save(
                    Item.builder()
                            .title("Houses in the woods")
                            .description(
                                    "Came across this pretty"
                                    + "village in the woods while on a hike"
                            )
                            .imageUrl(URLS.get(THIRD))
                            .tags("forest,cabins,trail")
                            .user(user1)
                            .build()
            );
            var item4 = itemRepo.save(
                    Item.builder()
                            .title("Mount Fuji")
                            .description(
                                    "Captured this peaceful morning in Japan."
                            )
                            .imageUrl(URLS.get(FOURTH))
                            .tags("mountain,Fuji,Japan")
                            .user(user3)
                            .build()
            );
            var item5 = itemRepo.save(
                    Item.builder()
                            .title("Street market")
                            .description(
                                    "Street market in Tokyo. Amazing vibes!"
                            )
                            .imageUrl(URLS.get(FIFTH))
                            .tags("street,Japan")
                            .user(user3)
                            .build()
            );
            var item6 = itemRepo.save(
                    Item.builder()
                            .title("Allay")
                            .description(
                                    "They had a sense of design back then."
                            )
                            .imageUrl(URLS.get(SIXTH))
                            .tags("old,street,pretty")
                            .user(user4)
                            .build()
            );
            var item7 = itemRepo.save(
                    Item.builder()
                            .title("Middle of nowhere")
                            .description(
                                    "A trail leading to a little church"
                            )
                            .imageUrl(URLS.get(SEVENTH))
                            .tags("airy,trail")
                            .user(user4)
                            .build()
            );
            var board1 = Board.builder()
                    .name("Austrian vacation")
                    .user(user1)
                    .items(new ArrayList<>(List.of(item1, item3)))
                    .build();
            var board2 = Board.builder()
                    .name("Paris Vibes")
                    .user(user2)
                    .items(new ArrayList<>(List.of(item2)))
                    .build();
            var board3 = Board.builder()
                    .name("Japan")
                    .user(user3)
                    .items(new ArrayList<>(List.of(item4)))
                    .build();
            var board4 = Board.builder()
                    .name("City life")
                    .user(user3)
                    .items(new ArrayList<>(List.of(item5)))
                    .build();
            var board5 = Board.builder()
                    .name("Nature photography")
                    .user(user4)
                    .items(new ArrayList<>(List.of(item6, item7)))
                    .build();
            boardRepo.save(board1);
            boardRepo.save(board2);
            boardRepo.save(board3);
            boardRepo.save(board4);
            boardRepo.save(board5);
        };
    }
}
