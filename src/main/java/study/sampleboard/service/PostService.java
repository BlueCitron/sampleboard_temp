package study.sampleboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.sampleboard.dto.PageResponse;
import study.sampleboard.dto.PostDto;
import study.sampleboard.entity.Category;
import study.sampleboard.entity.Post;
import study.sampleboard.repository.CategoryRepository;
import study.sampleboard.repository.PostRepository;
import study.sampleboard.service.exception.CategoryEntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public PageResponse<PostDto> getPosts(PostDto postDto, Pageable pageable) {
//        Category category = categoryRepository.findByName(postDto.getCategory())
//                .orElseThrow(() -> new CategoryEntityNotFoundException(postDto.getCategory()));
//        Page<PostDto> allAsDto = postRepository.findAllAsDto(category, pageable);
//        PageResponse<PostDto> postDtoPageResponse = new PageResponse<PostDto>(allAsDto);

        Page<Post> all = postRepository.findAll(pageable);
        PageResponse<Post> postDtoPageResponse = new PageResponse<Post>(all);

        log.info(postDtoPageResponse.toString());

        return null;
    }

}
