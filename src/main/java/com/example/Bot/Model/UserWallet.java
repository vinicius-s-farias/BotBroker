package com.example.Bot.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserWallet {

    private Long id_user;
    private Long id_stock;
    private String stock_symbol;
    private String stock_name;
    private Long volume;


}
