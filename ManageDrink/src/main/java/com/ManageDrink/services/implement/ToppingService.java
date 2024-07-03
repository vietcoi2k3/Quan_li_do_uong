//package com.ManageDrink.services.implement;
//
//import com.ManageDrink.dto.ToppingDTO;
//import com.ManageDrink.entity.DrinkEntity;
//import com.ManageDrink.entity.ToppingEntity;
//import com.ManageDrink.repository.DrinkRepository;
//import com.ManageDrink.repository.ToppingRepository;
//import com.ManageDrink.services.IToppingService;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ToppingService implements IToppingService {
//
//    @Autowired
//    private ToppingRepository toppingRepository;
//
//    @Autowired
//    private DrinkRepository drinkRepository;
//
//    @Override
//    public boolean deleteTopping(Long idTopping) {
//        try {
//            Optional<ToppingEntity> toppingEntityOptional = toppingRepository.findById(idTopping);
//            if (toppingEntityOptional.isPresent()) {
//                toppingRepository.deleteById(idTopping);
//                return true;
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace(); // Log lỗi
//            return false; // Xóa không thành công
//        }
//        return false;
//    }
//
//    @Override
//    public List<ToppingEntity> getToppingsByIds(List<Long> ids) {
//        return toppingRepository.findAllById(ids);
//    }
//
////    @Override
////    public List<ToppingDTO> getListTopping(Long id) {
////        if (!drinkRepository.existsById(id)){
////            throw new RuntimeException("DRINK NOT EXIT");
////        }
////
////        List<ToppingEntity> toppingEntities = drinkRepository.findById(id).get().getToppingEntities();
////        List<ToppingDTO> result = new ArrayList<>();
////        for (ToppingEntity x:toppingEntities
////             ) {
////            result.add(convertEntityTODTO(x));
////        }
////        return result;
////    }
//
//    @Override
//    public ToppingDTO saveTopping(ToppingDTO toppingDTO) {
//        if (!drinkRepository.existsById(toppingDTO.getDrinkEntityID())){
//            throw new RuntimeException("DRINK NOT EXIT");
//        }
//        ToppingEntity toppingEntity = toppingRepository.save(convertDTOToEntity(toppingDTO));
//        return convertEntityTODTO(toppingEntity);
//    }
//
//    @Override
//    public ToppingDTO updateTopping(ToppingDTO toppingDTO) {
//        if (!toppingRepository.existsById(toppingDTO.getId())){
//            throw new RuntimeException("TOPPING NOT EXIT");
//        }
//        ToppingEntity toppingEntity=  toppingRepository.save(convertDTOToEntity(toppingDTO));
//        return this.convertEntityTODTO(toppingEntity);
//    }
//
//    private ToppingEntity convertDTOToEntity(ToppingDTO toppingDTO){
//        ToppingEntity toppingEntity = ToppingEntity.builder()
//                .id(toppingDTO.getId())
//                .drinkEntityID(toppingDTO.getDrinkEntityID())
//                .name(toppingDTO.getName())
//                .price(toppingDTO.getPrice())
//                .build();
//        return toppingEntity;
//    }
//
//    private ToppingDTO convertEntityTODTO(ToppingEntity toppingEntity){
//        ToppingDTO toppingDTO = ToppingDTO.builder()
//                .id(toppingEntity.getId())
//                .drinkEntityID(toppingEntity.getDrinkEntityID())
//                .price(toppingEntity.getPrice())
//                .name(toppingEntity.getName())
//                .build();
//        return toppingDTO;
//    }
//}
