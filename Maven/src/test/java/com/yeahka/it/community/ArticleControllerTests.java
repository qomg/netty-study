package com.yeahka.it.community;


        import static org.hamcrest.CoreMatchers.*;
        import static org.mockito.BDDMockito.*;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

        import java.util.Arrays;

        import com.yeahka.it.community.topic.Article;
        import com.yeahka.it.community.topic.ArticleController;
        import com.yeahka.it.community.topic.ArticleRepository;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
        import org.springframework.boot.test.mock.mockito.MockBean;
        import org.springframework.hateoas.MediaTypes;
        import org.springframework.http.HttpHeaders;
        import org.springframework.test.context.junit4.SpringRunner;
        import org.springframework.test.web.servlet.MockMvc;

/**
 * How to test the hypermedia-based {@link ArticleController} with everything else mocked out.
 *
 * @author Greg Turnquist
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ArticleController.class)
public class ArticleControllerTests {

    @Autowired private MockMvc mvc;

    @MockBean private ArticleRepository repository;

    @Test
    public void getShouldFetchAHalDocument() throws Exception {

        given(repository.findAll()).willReturn( //
                Arrays.asList( //
                        new Article(1L, "doring bearer"), //
                        new Article(2L, "bilbo burglar")));

        mvc.perform(get("/articles").accept(MediaTypes.HAL_JSON_VALUE)) //
                .andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("$._embedded.articles[0].id", is(1)))
                .andExpect(jsonPath("$._embedded.articles[0].description", is("doring bearer")))
                .andExpect(jsonPath("$._embedded.articles[0]._links.self.href", is("http://localhost/articles/1")))
                .andExpect(jsonPath("$._embedded.articles[0]._links.articles.href", is("http://localhost/articles")))
                .andExpect(jsonPath("$._embedded.articles[1].id", is(2)))
                .andExpect(jsonPath("$._embedded.articles[1].description", is("bilbo burglar")))
                .andExpect(jsonPath("$._embedded.articles[1]._links.self.href", is("http://localhost/articles/2")))
                .andExpect(jsonPath("$._embedded.articles[1]._links.articles.href", is("http://localhost/articles")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/articles"))) //
                .andReturn();
    }
}