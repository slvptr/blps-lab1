package blps.lab1.controller;


import blps.lab1.model.domain.TopicCategory;
import blps.lab1.model.responses.TopicView;
import blps.lab1.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/search")
public class SearchController {
    private TopicService topicService;

    @Autowired
    public SearchController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/")
    public List<TopicView> searchAllByQuery(@RequestParam Optional<String> query) {
        try {
            return topicService.findAllByQuery(query.orElseThrow()).stream().map(TopicView::fromTopic).toList();
        } catch (NoSuchElementException e) {
            return topicService.findAll().stream().map(TopicView::fromTopic).toList();
        }
    }

    @GetMapping("/news")
    public List<TopicView> searchNewsByQuery(@RequestParam Optional<String> query) {
        try {
            return topicService.findByCategoryAndQuery(TopicCategory.NEWS,
                    query.orElseThrow()).stream().map(TopicView::fromTopic).toList();
        } catch (NoSuchElementException e) {
            return topicService.findByCategory(TopicCategory.NEWS).stream().map(TopicView::fromTopic).toList();
        }
    }

    @GetMapping("/articles")
    public List<TopicView> searchArticlesByQuery(@RequestParam Optional<String> query) {
        try {
            return topicService.findByCategoryAndQuery(TopicCategory.ARTICLES,
                    query.orElseThrow()).stream().map(TopicView::fromTopic).toList();
        } catch (NoSuchElementException e) {
            return topicService.findByCategory(TopicCategory.ARTICLES).stream().map(TopicView::fromTopic).toList();
        }
    }

    @GetMapping("/test-drives")
    public List<TopicView> searchTestDrivesByQuery(@RequestParam Optional<String> query) {
        try {
            return topicService.findByCategoryAndQuery(TopicCategory.TEST_DRIVES,
                    query.orElseThrow()).stream().map(TopicView::fromTopic).toList();
        } catch (NoSuchElementException e) {
            return topicService.findByCategory(TopicCategory.TEST_DRIVES).stream().map(TopicView::fromTopic).toList();
        }
    }
}
