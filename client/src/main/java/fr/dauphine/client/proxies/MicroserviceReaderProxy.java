package fr.dauphine.client.proxies;

import fr.dauphine.client.beans.ReaderBeans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "readers", dismiss404 = true
       // , url = "host.docker.internal:9191"
)
public interface MicroserviceReaderProxy {

    @GetMapping(value="/readers/readers")
    List<ReaderBeans> getAllReaders();

    @GetMapping(value = "/readers/readers/{id}")
    ReaderBeans getReaderById(@PathVariable Long id);

    @PostMapping(value ="/readers/readers")
    ReaderBeans createReader(@RequestBody ReaderBeans readerBeans);

    @PutMapping(value ="/readers/readers")
    ReaderBeans updateReader(@RequestBody ReaderBeans readerBeans);

    @DeleteMapping("/readers/readers/{id}")
    void deleteReaderById(@PathVariable Long id);

}