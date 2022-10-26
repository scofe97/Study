package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

//@Data //다만들어줘서 위험하다..(숙지 필요)일반 왔다갔다하는 DTO 정도는 괜찮
@Getter
@Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {

    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
