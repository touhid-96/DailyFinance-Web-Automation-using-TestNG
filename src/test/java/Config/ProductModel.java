package Config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    private String name;
    private String amount;
    private String remarks;
}
