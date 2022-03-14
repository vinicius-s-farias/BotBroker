package com.example.Bot.Service;

import com.example.Bot.Model.Stock;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Data
@Service
public class StockService {

    @Autowired
    private WebClient webClientStock;


    public Stock getStock(@RequestHeader("Authorization") String token){
             Mono<Stock> monoStock = this.webClientStock
                .method(HttpMethod.GET)
                .uri("/all")
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(Stock.class);

        monoStock.subscribe(s -> {
            System.out.println("acabou");
        });
        Stock stock = monoStock.block();
        return stock;
        }

//        acabou
    //        acabou
}
