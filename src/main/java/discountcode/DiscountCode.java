/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discountcode;

/**
 *
 * @author pedago
 */
public class DiscountCode {
    private final String code;
    private final double rate;
    
    public DiscountCode(String code, double rate) {
        this.code = code;
        this.rate = rate;
    }
    
    public String getCode() {
        return code;
    }
    
    public double getRate() {
        return rate;
    }
}
