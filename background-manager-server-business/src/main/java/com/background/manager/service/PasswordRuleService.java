package com.background.manager.service;

import com.background.manager.dto.response.user.PasswordRuleDTO;
import com.background.manager.model.PasswordRule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PasswordRuleService extends IService<PasswordRule> {

    List<PasswordRuleDTO> getPasswordRule();

    void modify(List<Integer> ruleList);

    List<PasswordRuleDTO> listPasswordRule();
}
