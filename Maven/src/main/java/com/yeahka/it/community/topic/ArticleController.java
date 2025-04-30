package com.yeahka.it.community.topic;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.yeahka.it.community.topic.ArticleStatus.valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ArticleController {
    private final ArticleRepository repository;

    public ArticleController(ArticleRepository repository) { // (2)
        this.repository = repository;
    }

    /**
     * Look up all articles, and transform them into a REST collection resource. Then return them through Spring Web's
     * {@link ResponseEntity} fluent API.
     */
    @GetMapping("/articles")
    ResponseEntity<CollectionModel<EntityModel<Article>>> findAll() {

        List<EntityModel<Article>> articles = StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(article -> EntityModel.of(article, //
                        linkTo(methodOn(ArticleController.class).findOne(article.getId())).withSelfRel(), //
                        linkTo(methodOn(ArticleController.class).findAll()).withRel("articles"))) //
                .collect(Collectors.toList());

        return ResponseEntity.ok( //
                CollectionModel.of(articles, //
                        linkTo(methodOn(ArticleController.class).findAll()).withSelfRel()));
    }

    @PostMapping("/articles")
    ResponseEntity<?> newArticle(@RequestBody Article article) {

        try {
            Article savedArticle = repository.save(article);

            EntityModel<Article> articleResource = EntityModel.of(savedArticle, //
                    linkTo(methodOn(ArticleController.class).findOne(savedArticle.getId())).withSelfRel());

            return ResponseEntity //
                    .created(new URI(articleResource.getRequiredLink(IanaLinkRelations.SELF).getHref())) //
                    .body(articleResource);
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Unable to create " + article);
        }
    }

    /**
     * Look up a single {@link Article} and transform it into a REST resource. Then return it through Spring Web's
     * {@link ResponseEntity} fluent API.
     *
     * @param id
     */
    @GetMapping("/articles/{id}")
    ResponseEntity<EntityModel<Article>> findOne(@PathVariable long id) {

        return repository.findById(id) //
                .map(article -> EntityModel.of(article, //
                        linkTo(methodOn(ArticleController.class).findOne(article.getId())).withSelfRel(), //
                        linkTo(methodOn(ArticleController.class).findAll()).withRel("articles"))) //
                .map(ResponseEntity::ok) //
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update existing article then return a Location header.
     *
     * @param article
     * @param id
     * @return
     */
    @PutMapping("/articles/{id}")
    ResponseEntity<?> updateArticle(@RequestBody Article article, @PathVariable long id) {

        Article articleToUpdate = article;
        articleToUpdate.setId(id);
        repository.save(articleToUpdate);

        Link newlyCreatedLink = linkTo(methodOn(ArticleController.class).findOne(id)).withSelfRel();

        try {
            return ResponseEntity.noContent().location(new URI(newlyCreatedLink.getHref())).build();
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Unable to update " + articleToUpdate);
        }
    }
    
    

    @PostMapping("/articles/{id}/pay") // (1)
    ResponseEntity<?> pay(@PathVariable Long id) throws ArticleNotFoundException { // (2)

        Article article = this.repository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id)); // (3)

        if (valid(article.getArticleStatus(), ArticleStatus.PAID_FOR)) { // (4)
            article.setArticleStatus(ArticleStatus.PAID_FOR);
            return ResponseEntity.ok(repository.save(article)); // (5)
        }

        return ResponseEntity.badRequest()
                .body("Transitioning from " + article.getArticleStatus() + " to " + ArticleStatus.PAID_FOR + " is not valid."); // (6)
    }

    @PostMapping("/articles/{id}/cancel")
    ResponseEntity<?> cancel(@PathVariable Long id) throws ArticleNotFoundException {

        Article article = this.repository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));

        if (valid(article.getArticleStatus(), ArticleStatus.CANCELLED)) {

            article.setArticleStatus(ArticleStatus.CANCELLED);
            return ResponseEntity.ok(repository.save(article));
        }

        return ResponseEntity.badRequest()
                .body("Transitioning from " + article.getArticleStatus() + " to " + ArticleStatus.CANCELLED + " is not valid.");
    }

    @PostMapping("/articles/{id}/fulfill")
    ResponseEntity<?> fulfill(@PathVariable Long id) throws ArticleNotFoundException {

        Article article = this.repository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));

        if (valid(article.getArticleStatus(), ArticleStatus.FULFILLED)) {

            article.setArticleStatus(ArticleStatus.FULFILLED);
            return ResponseEntity.ok(repository.save(article));
        }

        return ResponseEntity.badRequest()
                .body("Transitioning from " + article.getArticleStatus() + " to " + ArticleStatus.FULFILLED + " is not valid.");
    }
}
