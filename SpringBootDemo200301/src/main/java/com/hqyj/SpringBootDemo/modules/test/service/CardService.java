package com.hqyj.SpringBootDemo.modules.test.service;

import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.test.entity.Card;

/**
 * @Description CardService
 * @Author HymanHu
 * @Date 2020/7/31 9:50
 */
public interface CardService {

    Result<Card> editCard(Card card);

    Result<Object> deleteCard(int cardId);
}
