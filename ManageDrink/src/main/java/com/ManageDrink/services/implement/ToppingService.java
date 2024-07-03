package com.ManageDrink.services.implement;

import com.ManageDrink.dto.ToppingDTO;
import com.ManageDrink.entity.DrinkEntity;
import com.ManageDrink.entity.ToppingEntity;
import com.ManageDrink.repository.DrinkRepository;
import com.ManageDrink.repository.ToppingRepository;
import com.ManageDrink.services.IToppingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<ToppingDTO> getListToppingByIdDrink(Long id) {
        if (!drinkRepository.existsById(id)){
            throw new RuntimeException("DRINK NOT EXIT");
        }
        DrinkEntity drinkEntity = drinkRepository.findById(id).get();
        List<ToppingDTO> result = new ArrayList<>();
        for (ToppingEntity x:drinkEntity.getToppings()) {
            result.add(this.convertEntityTODTO(x));
        }
        return result;
    }

    @Override
    public ToppingDTO saveTopping(ToppingDTO toppingDTO) {
        ToppingEntity toppingEntity = toppingRepository.save(convertDTOToEntity(toppingDTO));
        return this.convertEntityTODTO(toppingEntity);
    }

    @Override
    public ToppingDTO updateTopping(ToppingDTO toppingDTO) {
        if (!toppingRepository.existsById(toppingDTO.getId())){
            throw new RuntimeException("TOPPING NOT EXIT");
        }
        ToppingEntity toppingEntity=  toppingRepository.save(convertDTOToEntity(toppingDTO));
        return toppingDTO;
    }

    private ToppingEntity convertDTOToEntity(ToppingDTO toppingDTO){
        ToppingEntity toppingEntity = ToppingEntity.builder()
                .id(toppingDTO.getId())
                .name(toppingDTO.getName())
                .price(toppingDTO.getPrice())
                .build();
        return toppingEntity;
    }

    private ToppingDTO convertEntityTODTO(ToppingEntity toppingEntity){
        ToppingDTO toppingDTO = ToppingDTO.builder()
                .id(toppingEntity.getId())
                .price(toppingEntity.getPrice())
                .name(toppingEntity.getName())
                .build();
        return toppingDTO;
    }
}
