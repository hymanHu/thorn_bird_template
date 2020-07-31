package com.hqyj.SpringBootDemo.modules.test.controller;

import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.test.entity.Card;
import com.hqyj.SpringBootDemo.modules.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description CardController
 * @Author HymanHu
 * @Date 2020/7/31 10:00
 */
@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardService cardService;

    /**
     * 127.0.0.1/api/card ---- post
     * {"cardNo":"e37de9fequfioew"}
     */
    @PostMapping(value = "/card", consumes = "application/json")
    public Result<Card> insertCard(@RequestBody Card card) {
        return cardService.editCard(card);
    }

    /**
     * 127.0.0.1/api/card ---- put
     * {"cardId":"1","cardNo":"111111"}
     */
    @PutMapping(value = "/card", consumes = "application/json")
    public Result<Card> updateCard(@RequestBody Card card) {
        return cardService.editCard(card);
    }

    /**
     * 127.0.0.1/api/card/2 ---- delete
     */
    @DeleteMapping("/card/{cardId}")
    public Result<Object> deleteCare(@PathVariable int cardId) {
        return cardService.deleteCard(cardId);
    }
}
