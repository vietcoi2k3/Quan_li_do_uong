package com.ManageDrink.services.implement;

import com.ManageDrink.dto.ToppingDTO;
import com.ManageDrink.entity.ToppingEntity;
import com.ManageDrink.exception.NotFoundException;
import com.ManageDrink.repository.DrinkRepository;
import com.ManageDrink.repository.ToppingRepository;
import com.ManageDrink.services.IToppingService;
import com.ManageDrink.until.constant.MessageConstant;
import com.ManageDrink.until.mapper.ToppingMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToppingService implements IToppingService {

    @Autowired
    private ToppingRepository toppingRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    @Override
    public boolean deleteTopping(Long idTopping) {
        try {
            Optional<ToppingEntity> toppingEntityOptional = toppingRepository.findById(idTopping);
            if (toppingEntityOptional.isPresent()) {
                toppingRepository.deleteById(idTopping);
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Log lỗi
            return false; // Xóa không thành công
        }
        return false;
    }

    @Override
    @Transactional
    public List<ToppingDTO> getListToppingByIdDrink(Long id) {
        if (!drinkRepository.existsById(id)){
            throw new NotFoundException(MessageConstant.DRINK_NOT_FOUND);
        }

        List<ToppingEntity> result = toppingRepository.getToppingEntitiesByDrinkId(id);
        return result.stream()
                .map(ToppingMapper::convertEntityTODTO)
                .collect(Collectors.toList());
    }

    @Override
    public ToppingDTO saveTopping(ToppingDTO toppingDTO) {
        ToppingEntity toppingEntity = toppingRepository.save(ToppingMapper.convertDTOToEntity(toppingDTO));
        return ToppingMapper.convertEntityTODTO(toppingEntity);
    }

    @Override
    public ToppingDTO updateTopping(ToppingDTO toppingDTO) {
        if (!toppingRepository.existsById(toppingDTO.getId())){
            throw new NotFoundException(MessageConstant.TOPPING_NOT_FOUND);
        }
        toppingRepository.save(ToppingMapper.convertDTOToEntity(toppingDTO));
        return toppingDTO;
    }

    @Override
    public List<ToppingDTO> getAllTopping() {
        List<ToppingEntity> toppingEntities = toppingRepository.findAll();
        return toppingEntities.stream()
                .map(ToppingMapper::convertEntityTODTO)
                .collect(Collectors.toList());
    }


}
