package blps.lab1.service;

import blps.lab1.model.domain.Topic;
import blps.lab1.dao.TopicRepository;
import blps.lab1.model.domain.TopicCategory;
import blps.lab1.model.requests.CreateTopicRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    private TopicRepository topicRepository;
    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    public Optional<Long> delete(long id) {
        if (topicRepository.existsById(id)) {
            topicRepository.deleteById(id);
            return Optional.of(id);
        }
        return Optional.empty();
    }

    public Topic update(long id, CreateTopicRequest req) {
        Topic topic = topicRepository.findById(id).orElseThrow();
        topic.setTitle(req.getTitle());
        topic.setDescription(req.getDescription());
        topic.setContent(req.getContent());
        topic.setCategory(TopicCategory.valueOf(req.getCategory().toUpperCase()));
        topic.setUpdatedAt(new Date());
        return topicRepository.save(topic);
    }

    public Optional<Topic> findById(long id) {
        return topicRepository.findById(id);
    }

    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    public List<Topic> findByCategory(TopicCategory category) {
        return topicRepository.findAllByCategory(category);
    }
    public List<Topic> findAllByQuery(String query) {
        return topicRepository.findByQuery(query);
    }

    public List<Topic> findByCategoryAndQuery(TopicCategory category, String query) {
        return topicRepository.findByCategoryAndQuery(category, query);
    }
}
