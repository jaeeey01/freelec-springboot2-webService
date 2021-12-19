package com.jojoIdu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    //After : Junit에서 단위테스트가 끝날때마다 수행되는 메소드 지정
    // 보통은 배포전 전체테스트를 수행할 때 테스터간 데이터 침범을 막기위해 사용됨
    //여러테스트가 동시에 수행되면 테스트용 DB인 H2에 데이터가 그대로 남아 있어 다음 테스트 실행시 실패 할 수 있음
    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트본문";

        //save : id값이 db에  있으면 update, 없으면 insert 쿼리가 실행됨
        postsRepository.save(Posts.builder()
                            .title(title)
                            .content(content)
                            .author("yjh@gmail.com")
                            .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                            .title("title")
                            .content("content")
                            .author("author")
                            .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>> createDate= " + posts.getCreatedDate() +", modifidedDate = " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}