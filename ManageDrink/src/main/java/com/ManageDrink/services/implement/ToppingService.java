package com.ManageDrink.services.implement;

import com.ManageDrink.entity.DrinkEntity;
import com.ManageDrink.entity.ToppingEntity;
import com.ManageDrink.repository.DrinkRepository;
import com.ManageDrink.repository.ToppingRepository;
import com.ManageDrink.services.IToppingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToppingService implements IToppingService {

    @Autowired
    private ToppingRepository toppingRepository;


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
    public List<ToppingEntity> getToppingsByIds(List<Long> ids) {
        return toppingRepository.findAllById(ids);
    }

    @Override
    public void saveTopping(ToppingEntity toppingEntity) {
        toppingRepository.save(toppingEntity);
    }


}
