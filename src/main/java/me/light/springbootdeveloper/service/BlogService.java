package me.light.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.light.springbootdeveloper.domain.Article;
import me.light.springbootdeveloper.dto.AddArticleRequest;
import me.light.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // final 이 붙거나 @NotNull 이 붙은 필드의 생성자 추가
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }
}
