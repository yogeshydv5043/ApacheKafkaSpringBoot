package com.learn.ms.kafka.log;

import com.learn.ms.kafka.model.Product;
import org.springframework.stereotype.Component;

@Component
public class Output {

    public void showResult(Product product){

        System.out.println("KAFKA OUTPUT  : "+product);
    }
}
