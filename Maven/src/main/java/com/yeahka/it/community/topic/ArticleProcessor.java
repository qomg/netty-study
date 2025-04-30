package com.yeahka.it.community.topic;

import lombok.SneakyThrows;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

import static com.yeahka.it.community.topic.ArticleStatus.valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ArticleProcessor implements RepresentationModelProcessor<EntityModel<Article>> {

    private final RepositoryRestConfiguration configuration;

    public ArticleProcessor(RepositoryRestConfiguration configuration) { // (2)
        this.configuration = configuration;
    }

    @SneakyThrows
    @Override
    public EntityModel<Article> process(EntityModel<Article> model) {
        Article article = model.getContent();
        if (article == null) return model;

        ArticleController controller = methodOn(ArticleController.class); // (1)
        String basePath = configuration.getBasePath().toString(); // (2)

        // If PAID_FOR is valid, add a link to the `pay()` method
        if (valid(article.getArticleStatus(), ArticleStatus.PAID_FOR)) {
            model.add(applyBasePath( //
                    linkTo(controller.pay(model.getContent().getId())) //
                            .withRel(IanaLinkRelations.PAYMENT), //
                    basePath));
        }

        // If CANCELLED is valid, add a link to the `cancel()` method
        if (valid(article.getArticleStatus(), ArticleStatus.CANCELLED)) {
            model.add(applyBasePath( //
                    linkTo(controller.cancel(model.getContent().getId())) //
                            .withRel(LinkRelation.of("cancel")), //
                    basePath));
        }

        // If FULFILLED is valid, add a link to the `fulfill()` method
        if (valid(article.getArticleStatus(), ArticleStatus.FULFILLED)) {
            model.add(applyBasePath( //
                    linkTo(controller.fulfill(model.getContent().getId())) //
                            .withRel(LinkRelation.of("fulfill")), //
                    basePath));
        }

        return model;
    }

    /**
     * Adjust the {@link Link} such that it starts at {@literal basePath}.
     *
     * @param link - link presumably supplied via Spring HATEOAS
     * @param basePath - base path provided by Spring Data REST
     * @return new {@link Link} with these two values melded together
     */
    private static Link applyBasePath(Link link, String basePath) throws URISyntaxException {

        URI uri = link.toUri();

        URI newUri = null;
        try {
            newUri = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), //
                    uri.getPort(), basePath + uri.getPath(), uri.getQuery(), uri.getFragment());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw e;
        }

        return Link.of(newUri.toString(), link.getRel());
    }
}
