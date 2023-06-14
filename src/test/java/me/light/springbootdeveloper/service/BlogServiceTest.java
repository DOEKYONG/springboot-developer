package me.light.springbootdeveloper.service;

import me.light.springbootdeveloper.domain.Article;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

import me.light.springbootdeveloper.dto.AddArticleRequest;
import me.light.springbootdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.assertj.core.api.InstanceOfAssertFactories.list;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


class BlogServiceTest {


    BlogService blogService;
    @Mock
    BlogRepository blogRepository;

    @Captor
    private ArgumentCaptor<Article> articleCaptor;


    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        blogService = new BlogService(blogRepository);
    }

    
    @DisplayName("AddArticleRequest 를 파라미터로 서비스의 save 실행 ")
    @Test
    void save() {
        // given
        String title = "제목";
        String content = "내용";
        AddArticleRequest request = new AddArticleRequest(title, content);
        Article savedArticle = Article.builder().title(title).content(content).build();
        given(blogRepository.save(any())).willReturn(savedArticle);
        // when
       // blogService.save(request);
        // then
        verify(blogRepository).save(articleCaptor.capture());
        Article capturedArticle = articleCaptor.getValue();
        assertEquals(title, capturedArticle.getTitle());
        assertEquals(content, capturedArticle.getContent());
    }

    @DisplayName("전체 조회 ")
    @Test
    void findAll() {
        // given
        String title = "title";
        String content = "content";
        AddArticleRequest request = new AddArticleRequest(title, content);
        Article savedArticle = Article.builder().title(title).content(content).build();
        given(blogRepository.save(any())).willReturn(savedArticle);
        // Article result = blogService.save(request);
        List<Article> articles = new ArrayList<>();
       // articles.add(result);
        given(blogRepository.findAll()).willReturn(articles);
        // when

        // then
        assertThat(blogService.findAll().size()).isEqualTo(1);
        assertThat(blogService.findAll().get(0).getTitle()).isEqualTo(title);
        assertThat(blogService.findAll().get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("id로 Article 찾기 성공")
    @Test
    void findById() {
        // given
        String title = "title";
        String content = "content";
        Article article = Article.builder()
                .title(title)
                .content(content)
                .build();

        given(blogRepository.findById(1L)).willReturn(Optional.of(article));

        // when
        Article result = blogService.findById(1L);

        // then
        assertEquals(title, result.getTitle());
        assertEquals(content, result.getContent());

    }

}