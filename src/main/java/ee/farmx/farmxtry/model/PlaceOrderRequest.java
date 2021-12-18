package ee.farmx.farmxtry.model;

import javax.validation.constraints.NotBlank;

public class PlaceOrderRequest {

    @NotBlank
    private Long lotId;

    @NotBlank
    private String username;

    @NotBlank
    private Integer amount;

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
