package com.hqyj.SpringBootDemo.modules.test.service.impl;

import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.test.entity.Card;
import com.hqyj.SpringBootDemo.modules.test.repository.CardRepository;
import com.hqyj.SpringBootDemo.modules.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Description CardServiceImpl
 * @Author HymanHu
 * @Date 2020/7/31 9:53
 */
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    @Transactional
    public Result<Card> editCard(Card card) {
        cardRepository.saveAndFlush(card);
        return new Result<Card>(Result.ResultStatus.SUCCESS.status, "Edit success.", card);
    }

    @Override
    public Result<Object> deleteCard(int cardId) {
        cardRepository.deleteById(cardId);
        return new Result<Object>(Result.ResultStatus.SUCCESS.status, "Delete success.");
    }
}
