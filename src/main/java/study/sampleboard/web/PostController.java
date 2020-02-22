package study.sampleboard.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.sampleboard.dto.PostDto;
import study.sampleboard.repository.PostRepository;
import study.sampleboard.service.PostService;

@Slf4j
@RequestMapping("/post")
@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping
    public String initListView(Model model, PostDto postDto, Pageable pageable) {

        log.info("pageNumber : {}", pageable.getPageNumber());
        log.info("pageSize : {}", pageable.getPageSize());

        model.addAttribute("posts", postService.getPosts(postDto, pageable));
        return "post-list";
    }

    @GetMapping("/{id}")
    public String initSingleView(Model model, @PathVariable Integer id) {
        return "";
    }

    @GetMapping("/write")
    public String initWriteView() {
        return "";
    }

    @PostMapping("/")
    public String insert() {
        return "post-list";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id) {
        return "";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return "";
    }
}
