package com.example.bot.service;

import com.example.bot.model.Stock;
import com.example.bot.model.UserOrder;

import com.nimbusds.jose.shaded.json.JSONObject;

import com.example.bot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class UserOrdemService {

    @Autowired
    private WebClient webClientStock;
    private Random gerador = new Random();

    private final WebClient webClientUser;
    public UserOrdemService (WebClient.Builder builder){
        webClientUser = builder.baseUrl("http://localhost:8081").build();
    }


    public UserOrder postORder(@RequestHeader("Authorization") String token) {

        int a = gerador.nextInt(getUSer(token).size());
        int b = gerador.nextInt(getStock(token).size());
        Long idUser = getUSer(token).get(a).getId();
        Long idStock = getStock(token).get(b).getId();
        int volume = gerador.nextInt(1, 200);
        int type = gerador.nextInt(0, 2 );
        if(type == 1){
            criarWallet(token, idUser, idStock, getStock(token).get(b).getStockName(),getStock(token).get(b).getStockSymbol(), volume);

        }
        Double price = Math.round(gerador.nextDouble(10, 500)  * 100.00 ) / 100.00;
        JSONObject json = new JSONObject();
        json.put("id_user", idUser);
        json.put("id_stock",idStock );
        json.put("stock_name", getStock(token).get(b).getStockName());
        json.put("stock_symbol", getStock(token).get(b).getStockSymbol());
        json.put("volume", volume);
        json.put("price", price );
        json.put("type", type);
        json.put("status",1 );
        json.put("remaining_value", volume);
        Mono<UserOrder> monoStock =
                this.webClientUser
                        .post()
                        .uri("/order")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .body(BodyInserters.fromValue(json))
                        .retrieve()
                        .bodyToMono(UserOrder.class);

        return monoStock.block();
    }


    public List<User> getUSer(@RequestHeader("Authorization") String token ) {
        Mono<User[]> monoStock =
                this.webClientUser
                        .get()
                        .uri("/users")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .retrieve()
                        .bodyToMono(User[].class);
        return Arrays.stream(monoStock.block()).toList();
    }

    public void criarWallet (String token, Long idUser, Long idStock, String stockName, String stockSymbol, int volume){

        JSONObject json = new JSONObject();
        json.put("id_user", idUser);
        json.put("id_stock", idStock);
        json.put("stock_name", stockName);
        json.put("stock_symbol",  stockSymbol);
        json.put("volume", volume);
                this.webClientUser
                        .post()
                        .uri("/")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .body(BodyInserters.fromValue(json))
                        .retrieve().bodyToMono(String.class)
                        .block();
    }

    public List <Stock> getStock(@RequestHeader("Authorization") String token){
        Mono<Stock[]> monoStock = this.webClientStock
                .get()
                .uri("/stocks/all")
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(Stock[].class);

        return Arrays.stream(monoStock.block()).toList();
    }

    @PostConstruct
    public void post(){
        for (int i= 0; i < 2; i ++){
            String token = "Bearer eyJraWQiOiJuQktld1lTTHB6YU5RaW9pRFJoQ2xRcUJtQ0V0OVRROXUzbU1zMm1LZkpRIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULl96NjdJcXNMX0FyaU1ENkFYS1VqQWpsbHM1SmNNa0pZQVJnQVhGckpBYmsiLCJpc3MiOiJodHRwczovL2Rldi0zNDE2MjE0Lm9rdGEuY29tL29hdXRoMi9kZWZhdWx0IiwiYXVkIjoiYXBpOi8vZGVmYXVsdCIsImlhdCI6MTY0NjkzODE4MywiZXhwIjoxNjQ2OTQxNzgzLCJjaWQiOiIwb2Ezang1cXpiZk1ub1lMYzVkNyIsInVpZCI6IjAwdTN4OXJzMTd3N3hBYXdINWQ3Iiwic2NwIjpbIm9wZW5pZCIsInByb2ZpbGUiLCJlbWFpbCJdLCJhdXRoX3RpbWUiOjE2NDY5MzQwNzIsInN1YiI6InZpZmFyaWFzNDdAZ21haWwuY29tIn0.UntXrZ3RSQoP4aYQ1bcicpKoB9EjhF47YOZGd5DfmOsV3DMXlj7W_wqzUS7mI_giwePmvpjln1jU8B5t797gt6s-aKsE_ow5WXJif93o8eV-_lQHcesABEIJJR_GkqhMw-0lZKrt3Fp15p8BAEzZzBzDTYaIFnyeKnRhuOpAHTOTFagUBveD3Hf654sI4-35QjtVV9ZOm78Gto_tR5JdYqRzu3i9Ncs_-p-voTU57i-qN9rfk3kyUFKVeUcupg4imeAdw05sBlyCWqmwyKOeNqzt0bLgTB73Ti0LjpuAINzyk0KhqJCgV5rCA898Xbb11ND239vZ7UW1PhX1timYbw";
        postORder(token);
        }
    }


}
