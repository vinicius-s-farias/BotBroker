package com.example.bot.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserWallet {

    private Long idUser;
    private Long idStock;
    private String stockSymbol;
    private String stockName;
    private Long volume;


}
