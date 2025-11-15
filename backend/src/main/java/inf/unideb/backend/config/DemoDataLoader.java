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

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DemoDataLoader {

    @Bean
    ApplicationRunner initData(
            UserRepository userRepo,
            ItemRepository itemRepo,
            BoardRepository boardRepo
    ) {
        return args -> {

            if (userRepo.count() > 0) return;

            var user1 = userRepo.save(
                    User.builder()
                            .username("demo_user")
                            .email("demo@example.com")
                            .build()
            );

            var user2 = userRepo.save(
                    User.builder()
                            .username("demo_user2")
                            .email("demo2@example.com")
                            .build()
            );

            var user3 = userRepo.save(
                    User.builder()
                            .username("traveler_dude")
                            .email("traveler@example.com")
                            .build()
            );

            var user4 = userRepo.save(
                    User.builder()
                            .username("nature_lover")
                            .email("lover@example.com")
                            .build()
            );

            var item1 = itemRepo.save(
                    Item.builder()
                            .title("Valley greenery")
                            .description("Photo I took while on vacation in Austria.")
                            .imageUrl("https://images.pexels.com/photos/34144977/pexels-photo-34144977.jpeg")
                            .tags("mountains,snow,alps")
                            .user(user1)
                            .build()
            );

            var item2 = itemRepo.save(
                    Item.builder()
                            .title("Lunch in Paris")
                            .description("I was in a cute little café in Paris, this is what I ate.")
                            .imageUrl("https://images.pexels.com/photos/32614530/pexels-photo-32614530.jpeg")
                            .tags("coffee,croissant")
                            .user(user2)
                            .build()
            );

            var item3 = itemRepo.save(
                    Item.builder()
                            .title("Houses in the woods")
                            .description("Came across this pretty village in the woods while on a hike")
                            .imageUrl("https://images.pexels.com/photos/34335194/pexels-photo-34335194.jpeg")
                            .tags("forest,cabins,trail")
                            .user(user1)
                            .build()
            );


            var item4 = itemRepo.save(
                    Item.builder()
                            .title("Mount Fuji")
                            .description("Captured this peaceful morning in Japan.")
                            .imageUrl("https://images.pexels.com/photos/3408353/pexels-photo-3408353.jpeg")
                            .tags("mountain,Fuji,Japan")
                            .user(user3)
                            .build()
            );

            var item5 = itemRepo.save(
                    Item.builder()
                            .title("Street market")
                            .description("Street market in Tokyo. Amazing vibes!")
                            .imageUrl("https://images.pexels.com/photos/5761962/pexels-photo-5761962.jpeg")
                            .tags("street,Japan")
                            .user(user3)
                            .build()
            );

            var item6 = itemRepo.save(
                    Item.builder()
                            .title("Allay")
                            .description("They had a sense of design back then.")
                            .imageUrl("https://images.pexels.com/photos/34563044/pexels-photo-34563044.jpeg")
                            .tags("old,street,pretty")
                            .user(user4)
                            .build()
            );

            var item7 = itemRepo.save(
                    Item.builder()
                            .title("Middle of nowhere")
                            .description("A trail leading to a little church")
                            .imageUrl("https://images.pexels.com/photos/34678668/pexels-photo-34678668.jpeg")
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
