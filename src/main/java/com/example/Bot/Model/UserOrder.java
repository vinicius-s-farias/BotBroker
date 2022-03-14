package com.example.Bot.Model;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter

public class UserOrder  {

    private long id_order;
    private User id_user;
    private Long id_stock;
    private String stock_symbol;
    private String stock_name;
    private double price;
    private int type;
    private int status;
    private Long volume;
    private Long remaining_value;
    private Timestamp created_on;
    private Timestamp updated_on;


}
