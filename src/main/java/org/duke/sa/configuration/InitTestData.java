package org.duke.sa.configuration;

import lombok.extern.log4j.Log4j2;
import org.duke.sa.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.duke.sa.entity.AppUser;
import org.duke.sa.repository.AppUserRepository;
import org.duke.sa.repository.PostRepository;

@Component
@Log4j2
public class InitTestData implements ApplicationListener<ApplicationContextEvent> {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AppUserRepository appUserRepository;


    @Override
    public void onApplicationEvent(ApplicationContextEvent applicationContextEvent) {
        postRepository.save(new Post(0, "Post title nr1", "Post content nr1"));
        postRepository.save(new Post(0, "Post title nr2", "Post content nr2"));
        postRepository.save(new Post(0, "Post title nr3", "Post content nr3"));
        log.info("3 posts saved.");

        String password = "passw0rd";
        String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        appUserRepository.save(
                new AppUser("laiduc@gmail.com", hash, "USER")
        );
        log.info("User saved.");
    }
}
