package com.background.manager.service.impl;

import com.background.manager.convert.PasswordEliminationConvertor;
import com.background.manager.dto.response.user.PasswordEliminationDTO;
import com.background.manager.mapper.security.PasswordEliminationMapper;
import com.background.manager.model.PasswordElimination;
import com.background.manager.service.PasswordEliminationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PasswordEliminationServiceImpl extends ServiceImpl<PasswordEliminationMapper, PasswordElimination> implements PasswordEliminationService {

    private final PasswordEliminationConvertor passwordEliminationConvertor;

    @Override
    public List<PasswordEliminationDTO> getPasswordElimination() {
        List<PasswordElimination> passwordEliminations = this.list(new LambdaQueryWrapper<PasswordElimination>());
        return passwordEliminationConvertor.toPasswordEliminationDTOS(passwordEliminations);
    }

    @Override
    public void modify(List<String> passwordList) {
        List<PasswordElimination> passwordEliminations = this.list(new LambdaQueryWrapper<PasswordElimination>());
        this.removeBatchByIds(passwordEliminations);
        List<PasswordElimination> addPasswordElimination=new ArrayList<>();
        passwordList.forEach(passwordEliminationId->{
            PasswordElimination passwordElimination=new PasswordElimination();
            passwordElimination.setPassword(passwordEliminationId);
            addPasswordElimination.add(passwordElimination);
        });
        this.saveBatch(addPasswordElimination);
    }
}
