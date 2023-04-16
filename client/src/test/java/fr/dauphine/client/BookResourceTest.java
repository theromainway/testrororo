package fr.dauphine.client;

import fr.dauphine.client.beans.BookBeans;
import fr.dauphine.client.proxies.MicroserviceBookProxy;
import fr.dauphine.client.rest.BookResource;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;


@WebMvcTest(controllers = BookResource.class)
class BookResourceTest {

    @MockBean
    private MicroserviceBookProxy bookProxy;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_find_All() throws Exception {
        // Given
        BookBeans bookBeans = new BookBeans();
        bookBeans.setAuthor("author");


        // When
        when(bookProxy.getAllBooks()).thenReturn(Collections.singletonList(bookBeans));

        mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                .andExpect(status().isOk());

        verify(bookProxy, times(1)).getAllBooks();
        verifyNoMoreInteractions(bookProxy);
    }
}
