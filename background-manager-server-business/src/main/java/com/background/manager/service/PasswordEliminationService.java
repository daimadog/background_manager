package com.background.manager.service;

import com.background.manager.dto.response.user.PasswordEliminationDTO;
import com.background.manager.model.PasswordElimination;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PasswordEliminationService extends IService<PasswordElimination> {
    List<PasswordEliminationDTO> getPasswordElimination();

    void modify(List<String> passwordList);
}
