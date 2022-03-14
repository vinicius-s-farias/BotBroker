package com.example.bot.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter

public class UserOrder  {

    private long idOrder;
    private User idUser;
    private Long idStock;
    private String stockSymbol;
    private String stockName;
    private double price;
    private int type;
    private int status;
    private Long volume;
    private Long remainingValue;
    private Timestamp createdOn;
    private Timestamp updatedOn;


}
