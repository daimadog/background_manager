package com.background.manager.service.impl;

import com.background.manager.constants.PasswordRuleEnum;
import com.background.manager.convert.PasswordRuleConvertor;
import com.background.manager.dto.response.user.PasswordRuleDTO;
import com.background.manager.mapper.security.PasswordRuleMapper;
import com.background.manager.model.PasswordElimination;
import com.background.manager.model.PasswordRule;
import com.background.manager.service.PasswordRuleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PasswordRuleServiceImpl extends ServiceImpl<PasswordRuleMapper, PasswordRule> implements PasswordRuleService {

    private final PasswordRuleConvertor passwordRuleConvertor;

    @Override
    public List<PasswordRuleDTO> getPasswordRule() {
        List<PasswordRule> passwordRuleList = this.list(new LambdaQueryWrapper<PasswordRule>());
        return passwordRuleConvertor.toPasswordRuleDTOS(passwordRuleList);
    }

    @Override
    public void modify(List<Integer> ruleList) {
        List<PasswordRule> passwordRuleList = this.list(new LambdaQueryWrapper<PasswordRule>());
        this.removeBatchByIds(passwordRuleList);
        List<PasswordRule> addPassWordRule=new ArrayList<>();
        ruleList.forEach(rule->{
            PasswordRule passwordRule=new PasswordRule();
            String description = PasswordRuleEnum.findRuleByType(rule);
            passwordRule.setType(rule);
            passwordRule.setDescription(description);
            addPassWordRule.add(passwordRule);
        });
        this.saveBatch(addPassWordRule);
    }

    @Override
    public List<PasswordRuleDTO> listPasswordRule() {
        List<PasswordRuleDTO> passwordRuleDTOList=new ArrayList<>();
        for (PasswordRuleEnum passwordRuleEnum:PasswordRuleEnum.values()){
            PasswordRule passWordRule = this.getOne(new LambdaQueryWrapper<PasswordRule>()
                    .eq(PasswordRule::getType, passwordRuleEnum.getType())
                    .eq(PasswordRule::getDescription, passwordRuleEnum.getRule())
            );
            PasswordRuleDTO passwordRuleDTO = passwordRuleConvertor.toPasswordRuleDTO(passWordRule);
            passwordRuleDTOList.add(passwordRuleDTO);
        }
        return passwordRuleDTOList;
    }

}
