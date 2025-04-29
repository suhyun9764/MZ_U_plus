package com.springboot.mzuplusspringjpa.repository.sale;

import com.springboot.mzuplusspringjpa.dto.sale.SaleDto;
import com.springboot.mzuplusspringjpa.entity.PhoneSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<PhoneSale,Integer> {
    @Query("""
        select new com.springboot.mzuplusspringjpa.dto.sale.SaleDto(
            s.id,
            new com.springboot.mzuplusspringjpa.dto.customer.CustomerDto(
                s.customer.id, s.customer.address, s.customer.email, s.customer.name, s.customer.phoneNumber, s.customer.rrnFirst
            ),
            new com.springboot.mzuplusspringjpa.dto.phone_model.PhoneModelDto(
                s.phoneModel.id, s.phoneModel.manufacturer, s.phoneModel.name, s.phoneModel.color, s.phoneModel.stockQuantity, s.phoneModel.storageCapacity, s.phoneModel.price
            )
        )
        from PhoneSale s
        join s.customer
        join s.phoneModel
    """)
    List<SaleDto> list();
}
